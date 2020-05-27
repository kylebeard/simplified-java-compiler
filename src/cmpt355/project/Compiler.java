package cmpt355.project;

import cmpt355.project.ast.Typed;
import cmpt355.project.ast.node.AstNode;
import cmpt355.project.ast.node.ClassDefinition;
import cmpt355.project.ast.node.JavaFile;
import cmpt355.project.ast.node.statement.LocalScope;
import cmpt355.project.ast.node.statement.LocalVariableDeclaration;
import cmpt355.project.codegen.CodeGenerator;
import cmpt355.project.language.DataType;
import cmpt355.project.language.ExternalClassType;
import cmpt355.project.language.Method;
import cmpt355.project.language.Modifier;
import cmpt355.project.util.ConsumerEx;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.platform.engine.support.hierarchical.Node;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * A class that compiles Java code into JVM assembly. As the project is incomplete, currently it can only build an AST
 * (Abstract Syntax Tree) and then typecheck it.
 */
public class Compiler {

    private enum OS {
        Linux {
            @Override
            Path executablePath() {
                return findPythonInPath();
            }
        },
        Mac {
            @Override
            Path executablePath() {
                return Path.of("python27/mac/bin/python");
            }
        },
        Windows {
            @Override
            Path executablePath() {
                return Path.of("python27/windows/python.exe");
            }
        };

        abstract Path executablePath();
    }

    private static final Path PYTHONPATH_FILE = Path.of("pythonpath");
    private static final String LAST_FILE_PREFS_KEY = "lastFile";
    private static Path PYTHON_PATH = null;

    private static List<String> typeErrorMessages;
    private static DataTypeException typeException;

    /**
     * Parses the Java file referred to by the given {@link Path}, returning an AST whose root is a {@link JavaFile}.
     * @param sourceFile the path of the file to parse
     * @throws IOException if an I/O error occurs
     * @throws SyntaxException if the file is syntactically incorrect
     */
    public static JavaFile parse(Path sourceFile) throws IOException, SyntaxException {
        return parse(Files.newBufferedReader(sourceFile, StandardCharsets.UTF_8));
    }

    /**
     * Parses the Java code read from the supplied {@link Reader}, returning an AST whose root is a {@link JavaFile}.
     * @param reader the reader to read Java code from
     * @throws IOException if an I/O error occurs
     * @throws SyntaxException if the file is syntactically incorrect
     */
    public static JavaFile parse(Reader reader) throws IOException, SyntaxException {
        var lexer = new JavaLexer(CharStreams.fromReader(reader));
        var parser = new JavaParser(new CommonTokenStream(lexer));

        var tree = parser.javaFile();
        var visitor = new Visitor();
        var ast = visitor.generateAst(tree);

        return ast;
    }

    private static void checkParents(AstNode ast) {
        ast.preOrderPathTraversal(p -> {
            if (p.length() > 1) {
                var last = p.last();
                var lastParent = p.pop().last();
                if (last.getParentNode() != lastParent)
                    System.err.printf("Wrong parent node: %s\n", p);
            }
        });
    }

    private static void defineLocals(AstNode ast) {
        ast.preOrderTraversal()
                .filter(n -> n instanceof LocalVariableDeclaration)
                .map(n -> (LocalVariableDeclaration)n)
                .forEach(localDecl -> {
                    Optional<LocalScope> maybeScope = localDecl.findAncestor(n -> n instanceof LocalScope);
                    if (maybeScope.isPresent())
                        maybeScope.get().defineLocalVariable(localDecl.getVariable());
                    else
                        throw new InternalParserException("Local variable declaration not inside a local variable holder");
                });

    }

    private static void typecheck(JavaFile ast) throws DataTypeException {
        typeErrorMessages = new LinkedList<>();

        ConsumerEx<AstNode, RuntimeException> checkNode = node -> {
            try {
                node.validateType();
            } catch (DataTypeException ex) {
                typeErrorMessages.add(ex.getMessage());
            } catch (NullPointerException ex) {
                // Assume this is caused by an earlier DataTypeException — do nothing
            }
        };

        for (var classDef : ast.getClassDefinitions()) {
            // First, typecheck method parameters and return types so that methods can be present in any order with no issue
            for (var methodDef : classDef.getMethods()) {
                checkNode.accept(methodDef.getReturnType());
                for (var param : methodDef.getParameters())
                    checkNode.accept(param.getTypeNode());
            }

            // Then typecheck field declaration types
            for (var fieldDecl : classDef.getFields())
                checkNode.accept(fieldDecl.getTypeNode());
        }

        // Now typecheck the tree in general
        ast.postOrderTraversal(checkNode);

        if (!typeErrorMessages.isEmpty())
            throw new DataTypeException(String.join("\n", typeErrorMessages));
    }

    public static void print(AstNode ast, PrintStream out) {
        final String indent = "  ";
        ast.preOrderPathTraversal(p -> {
            for (int i = 1; i < p.length(); ++i)
                out.print(indent);
            out.print(p.last());

            if (p.last() instanceof Typed) {
                DataType type = ((Typed) p.last()).getType();
                out.printf("\t{%s}", type);
            }

            out.println();
        });
    }

    private static void generateCode(JavaFile ast, Path outFilename) throws IOException {
        CodeGenerator gen = new CodeGenerator(ast, outFilename);
        gen.generateCode();
    }

    public static void compile(Reader reader, Path output) throws IOException, SyntaxException {
        JavaFile ast = parse(reader);
        compile(ast, output);
    }

    public static void compile(JavaFile ast, Path output) throws IOException, SyntaxException {
        checkParents(ast);
        defineLocals(ast);
        typecheck(ast);
        generateCode(ast, output);
    }

    public static void assemble(Path asmFile, Path outDir) throws IOException {
        Path pythonPath = locatePythonExecutable();
        var pb = new ProcessBuilder(pythonPath.toString(), "Krakatau/assemble.py", "-out", outDir.toString(), asmFile.toString());
        pb.redirectOutput(ProcessBuilder.Redirect.DISCARD);
        pb.redirectError(ProcessBuilder.Redirect.PIPE);
        var process = pb.start();
        try {
            int code = process.waitFor();
            if (code != 0) {
                System.err.println("Krakatau reports error in assembling. Error message follows:\n");
                var errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                errReader.lines().forEach(System.err::println);
            }
        } catch (InterruptedException ex) { }
    }

    private static Path locatePythonExecutable() throws IOException {
        if (PYTHON_PATH != null)
            return PYTHON_PATH;

        Path pythonPath = null;

        // First, see if the pythonpath file has a valid path listed (must be a file that exists and is executable)
        if (Files.exists(PYTHONPATH_FILE)) {
            Optional<String> maybePythonPath = Files.lines(PYTHONPATH_FILE)
                    .map(String::trim)
                    .filter(line -> !line.startsWith("#"))
                    .filter(line -> !line.isBlank())
                    .findFirst();
            if (maybePythonPath.isPresent()) {
                Path p = Path.of(maybePythonPath.get());
                if (Files.exists(p) && Files.isExecutable(p))
                    pythonPath = p;
                else
                    System.err.printf("Warning: pythonpath entry '%s' does not exist or is not executable!\n", maybePythonPath.get());
            }
        }

        // If not, try to figure out the OS based on the os.name system property, then use the appropriate version of
        // Python bundled with this code
        if (pythonPath == null) {
            OS os = null;
            String osName = System.getProperty("os.name", null);
            if (osName != null) {
                osName = osName.trim().toLowerCase();
                if (osName.contains("linux"))
                    os = OS.Linux;
                else if (osName.contains("mac"))
                    os = OS.Mac;
                else if (osName.contains("windows"))
                    os = OS.Windows;
            }

            if (os != null) {
                Path p = os.executablePath();
                if (Files.exists(p) && Files.isExecutable(p))
                    pythonPath = p;
            }
        }

        // Otherwise, try to find a Python in the system path....
        if (pythonPath == null)
            pythonPath = findPythonInPath();

        if (pythonPath == null) {
            throw new RuntimeException("""
                    Could not locate a Python 2 interpreter. Please make sure one is installed, then edit the pythonpath
                    file in the project directory to include the path to the python (or python2) executable.
                    """);
        }

        System.out.printf("Note: using Python executable %s\n", pythonPath);
        PYTHON_PATH = pythonPath;
        return pythonPath;
    }

    private static Path findPythonInPath() {
        String pathVar = System.getenv("path");
        if (pathVar == null)
            pathVar = System.getenv("PATH");

        if (pathVar == null)
            return null;

        // Look for an executable named "python2" in the path
        for (String pathEntry : pathVar.split(System.getProperty("path.separator"))) {
            Path p = Path.of(pathEntry).resolve("python2");
            if (Files.exists(p) && Files.isExecutable(p))
                return p;
        }

        // Failing that, look for one called "python" (maybe it's actually Python 2!)
        for (String pathEntry : pathVar.split(System.getProperty("path.separator"))) {
            Path p = Path.of(pathEntry).resolve("python");
            if (Files.exists(p) && Files.isExecutable(p))
                return p;
        }

        // Give up ☹
        return null;
    }

    private static boolean isExecutableClass(ClassDefinition classDef) {
        // Look for methods named "main" that are public and static and have a single String[] parameter
        List<Method> mainMethods = classDef.getType().findMethods("main");
        for (var method : mainMethods) {
            var mods = method.getModifiers();
            if (mods.contains(Modifier.PUBLIC) && mods.contains(Modifier.STATIC)
                    && method.getParameterTypes().size() == 1
                    && method.getParameterTypes().get(0).equals(DataType.arrayOf(ExternalClassType.STRING)))
                return true;
        }

        return false;
    }

    /**
     * Entry point for the Java compiler. Presently, since the compiler is not yet complete, the program simply parses
     * each Java file given on the command line, typechecks it, and prints the AST with type information.
     */
    public static void main(String... args) {
        if (args.length > 0) {
            for (String arg : args)
                process(arg, null);
        } else {
            // Interactive mode!

            // To make this a little more convenient, we'll save the last compiled filename - here, check whether there
            // is one saved
            var prefs = Preferences.userNodeForPackage(Compiler.class);
            String defaultFilename = prefs.get(LAST_FILE_PREFS_KEY, null);
            Scanner in = new Scanner(System.in);

            System.out.println();
            if (defaultFilename == null || defaultFilename.isBlank())
                System.out.print("Filename to compile (or Q to quit): ");
            else
                System.out.printf("Filename to compile [default: %s] (or Q to quit): ", defaultFilename);
            String input = in.nextLine();

            if ("Q".equals(input) || "q".equals(input))
                return;

            String filename = defaultFilename;
            if (!input.trim().isEmpty())
                filename = input;

            if (filename == null || filename.isBlank())
                return;

            if (!Files.exists(Path.of(filename))) {
                System.out.printf("File %s does not exist.\n", filename);
                return;
            }

            try {
                prefs.put(LAST_FILE_PREFS_KEY, filename);
                prefs.sync();
            } catch (BackingStoreException ex) {
                // So we can't save the filename for next time? Boo-hoo.
            }

            defaultFilename = filename;
            process(filename, in);

        }
    }

    private static void process(String sourceFilename, Scanner userInput) {
        System.out.printf("\nProcessing %s...\n", sourceFilename);
        Path sourceFile = Path.of(sourceFilename);
        Path asmDir = Path.of("compiled");
        Path classDir = Path.of("assembled");

        String stem;
        String astFilename, asmFilename;
        if (sourceFilename.toLowerCase().endsWith(".java")) {
            stem = Path.of(sourceFilename).getFileName().toString();
            stem = stem.substring(0, stem.length() - 5);
        } else
            stem = Path.of(sourceFilename).getFileName().toString();

        astFilename = asmDir.resolve(stem + ".ast").toString();
        asmFilename = asmDir.resolve(stem + ".j").toString();

        if (!Files.exists(sourceFile)) {
            System.err.printf("No such file: \"%s\"\n", sourceFilename);
            return;
        }

        try (var astOut = new PrintStream(astFilename)) {
            JavaFile ast = parse(sourceFile);
            checkParents(ast);
            defineLocals(ast);
            typecheck(ast);
            print(ast, astOut);
            System.out.printf("\tSaved AST to %s.\n", astFilename);
            generateCode(ast, Path.of(asmFilename));
            System.out.printf("\tGenerated JVM assembly code to %s.\n", asmFilename);

            var qClassName = ast.getClassDefinitions().get(0).getQualifiedName();
            var classFile = classDir.resolve(qClassName.toJvmString() + ".class");
            assemble(Path.of(asmFilename), classDir);
            System.out.printf("\tAssembled code to %s.\n", classFile);

            if (userInput != null && isExecutableClass(ast.getClassDefinitions().get(0))) {
                System.out.print("\nThis class has a main method. Execute it? (y/[n]) ");
                String line = userInput.nextLine().toLowerCase();
                if ("y".equals(line)) {
                    System.out.printf("=== Executing %s ===\n", qClassName);
                    try {
                        var clazz = Class.forName(qClassName.toString());
                        var mainMethod = clazz.getMethod("main", String[].class);
                        mainMethod.invoke(null, new Object[] { new String[0] });
                        System.out.printf("=== %s exited ===\n", qClassName);
                    } catch (InvocationTargetException ex) {
                        System.err.printf("Exception occurred while executing %s.main():\n", qClassName);
                        ex.getCause().printStackTrace();
                    } catch (ReflectiveOperationException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (SyntaxException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
