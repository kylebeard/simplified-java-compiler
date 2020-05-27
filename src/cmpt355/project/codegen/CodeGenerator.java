package cmpt355.project.codegen;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.node.*;
import cmpt355.project.ast.node.expression.*;
import cmpt355.project.ast.node.statement.Block;
import cmpt355.project.ast.node.statement.ExpressionStatement;
import cmpt355.project.ast.node.statement.LocalScope;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.language.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static cmpt355.project.language.PrimitiveType.DOUBLE;
import static cmpt355.project.language.PrimitiveType.LONG;

/**
 * A class that generates JVM assembly code from an AST. Responsible for putting together the overall structure of the
 * assembly files, but delegates code generation for statements and expressions to the respective AST nodes through the
 * {@link Statement#generateCode(CompileContext)} and {@link Expression#generateCode(CompileContext)} methods.
 */
public class CodeGenerator {

    private static final int
            CLASS_MAJOR_VERSION = 49,
            CLASS_MINOR_VERSION = 0; // Java 5 (requires less validation than subsequent versions)

    private JavaFile ast;
    private ClassDefinition classDef;
    private PrintWriter out;
    private boolean ownsOut = false;
    private StringBuilder sb = new StringBuilder(100);

    public CodeGenerator(JavaFile ast, PrintWriter out) {
        this.ast = ast;
        this.out = out;
    }

    public CodeGenerator(JavaFile ast, Path outPath) throws IOException {
        this(ast, new PrintWriter(Files.newBufferedWriter(outPath)));
        this.ownsOut = true;
    }

    public void generateCode() {
        if (ast.getClassDefinitions().size() == 0)
            throw new IllegalStateException("No class definitions to compile!");
        if (ast.getClassDefinitions().size() > 1)
            System.out.println("Warning: only compiling one out of " + ast.getClassDefinitions().size() + " class definitions");
        out.printf("""
                .version %d %d
                """, CLASS_MAJOR_VERSION, CLASS_MINOR_VERSION);
        classDef = ast.getClassDefinitions().get(0);
        try {
            generateCode(classDef);
        } catch (DataTypeException ex) {
            // Presumably this should never happen (we should catch all data type errors in the semantic analysis phase)
            throw new RuntimeException(ex);
        }

        if (ownsOut)
            out.close();
    }

    private void generateCode(ClassDefinition classDef) throws DataTypeException  {
        out.printf(".class");
        out.printf(" %s", modifiersToString(classDef.getModifiers()));

        boolean isInterface = classDef.getDefinitionType() == ClassDefinition.Type.INTERFACE;
        if (isInterface)
            out.printf(" abstract interface");
        else
            out.printf(" super"); // TODO: figure out what this means
        out.printf(" %s\n", classDef.getQualifiedName().toJvmString());

        if (isInterface) {
            out.printf(".super %s\n", ExternalClassType.OBJECT.toJvm());
            classDef.getExtending().forEach(imp -> out.printf(".implements %s\n", ((ClassType)imp.getType()).getClassName().toJvmString()));
        } else {
            ClassType superclass = ExternalClassType.OBJECT;
            if (classDef.getExtending().size() > 0)
                superclass = (ClassType)classDef.getExtending().get(0).getType();
            out.printf(".super %s\n", superclass.getClassName().toJvmString());
            classDef.getImplementing().forEach(imp -> out.printf(".implements %s\n", ((ClassType)imp.getType()).getClassName().toJvmString()));
        }

        for (var field : classDef.getFields()) {
            out.printf(".field %s %s %s\n",
                    modifiersToString(field.getModifiers()),
                    field.getName(),
                    field.getTypeNode().getType().toJvm());
        }

        List<FieldDeclaration> staticFieldInits = classDef.getFields().stream()
                .filter(fieldDecl -> fieldDecl.getModifiers().contains(Modifier.STATIC))
                .filter(fieldDecl -> fieldDecl.getInitializer().isPresent())
                .collect(Collectors.toList());
        if (staticFieldInits.size() > 0) {
            var clinit = MethodDefinition.builder()
                    .name(Method.STATIC_INITIALIZER_NAME)
                    .modifier(Modifier.PUBLIC)
                    .modifier(Modifier.STATIC)
                    .returnType(new DataTypeNode(DataType.VOID))
                    .body(fieldInitializationBlock(staticFieldInits))
                    .build();
            classDef.addMethod(clinit);
        }

        for (var method : classDef.getMethods())
            generateCode(method);


        out.printf(".end class\n");
    }

    /*
     * NB: for a non-static method, local variable 0 is "this" pointer; parameters are in locals starting at 1 (in order)
     * For a static method, parameters are in locals starting at 0.
     * Boolean arrays are treated as byte arrays
     */
    private void generateCode(MethodDefinition method) throws DataTypeException  {
        var mods = new Modifiers(method.getModifiers());
        if (classDef.getDefinitionType() == ClassDefinition.Type.INTERFACE) {
            // For an interface:
            // • Methods are abstract unless declared default or static
            // • Methods are public
            if (!mods.contains(Modifier.DEFAULT) && !mods.contains(Modifier.STATIC))
                mods.add(Modifier.ABSTRACT);
            if (!mods.contains(Modifier.PRIVATE))
                mods.add(Modifier.PUBLIC);
        } else {
            // For a class:
            // • Methods are private unless otherwise specified
            if (!mods.intersects(Modifiers.of(Modifier.PUBLIC, Modifier.PROTECTED, Modifier.PRIVATE)))
                mods.add(Modifier.PRIVATE);
        }

        out.printf(".method %s %s : %s\n", modifiersToString(method.getModifiers()), method.getName(), method.getMethod().getJvmSignature());

        var maybeBody = method.getBody();
        if (maybeBody.isPresent()) {
            CompileContext context = initMethodContext(method);

            if (method.getMethod().isConstructor()) {
                // TODO: do the right thing here
                var superclass = new InternalClassType(classDef).getSupertype().get();
                context.addCode(Instruction.new_aload_0());
                context.addCode(Instruction.new_invokespecial(new Method(superclass, Modifiers.of(), DataType.VOID, List.of(), Method.CONSTRUCTOR_NAME)));

                var fieldInits = classDef.getFields().stream()
                        .filter(fieldDecl -> fieldDecl.getInitializer().isPresent())
                        .filter(fieldDecl -> !fieldDecl.getModifiers().contains(Modifier.STATIC))
                        .collect(Collectors.toList());
                fieldInitializationBlock(fieldInits).generateCode(context);
            }

            var body = maybeBody.get();

            body.generateCode(context);
            if (method.getReturnType().getType() == DataType.VOID)
                context.addCode(Instruction.new_return()); // Make sure we return!

            context.applyLabels();

            int numLocals = context.getNumLocals(),
                maxStack = context.getMaxStackSize();

            out.printf("\t.code stack %d locals %d\n", maxStack, numLocals);

            context.getCode().forEach(instr -> out.printf("\t\t%s\n", instr));

            out.println("\t.end code");
        }

        out.println(".end method");
        out.println();
    }

    private String modifiersToString(Modifiers mods) {
        sb.setLength(0);
        var iter = mods.iterator();
        while (iter.hasNext()) {
            sb.append(iter.next().toString().toLowerCase());
            if (iter.hasNext())
                sb.append(' ');
        }
        return sb.toString();
    }

    private CompileContext initMethodContext(MethodDefinition method) {
        var context = new CompileContext();

        Function<Integer, Integer> numberSupplier = new Function<>() {
            private int number = 0;

            @Override
            public Integer apply(Integer integer) {
                int n = number;
                this.number += integer;
                return n;
            }
        };

        if (!method.getModifiers().contains(Modifier.STATIC))
            numberSupplier.apply(1); // reserve space for "this"
        method.getParameters().forEach(param -> {
            var paramType = param.getType();
            int paramSize = (paramType == LONG || paramType == DOUBLE) ? 2 : 1;
            context.defineMethodVariable(param.getName(), numberSupplier.apply(paramSize));
        });

        var maybeBody = method.getBody();
        if (maybeBody.isEmpty())
            return context;

        maybeBody.get().preOrderTraversal()
                .filter(node -> node instanceof LocalScope)
                .map(node -> (LocalScope)node)
                .forEach(scope -> {
                    for (var localVar : scope.getDefinedLocalVariables()) {
                        var varType = localVar.getType();
                        int varSize = (varType == LONG || varType == DOUBLE) ? 2 : 1;
                        int varIndex = numberSupplier.apply(varSize);
                        context.defineMethodVariable(localVar.getName(), varIndex);
                    }
                });

        context.setNumLocals(numberSupplier.apply(1));

        return context;
    }

    private Block fieldInitializationBlock(List<FieldDeclaration> fields) {
        List<Statement> statements = new ArrayList<>(fields.size());
        for (var field : fields) {
            var maybeInit = field.getInitializer();
            if (maybeInit.isEmpty())
                continue;
            Expression init = (Expression)maybeInit.get();

            LValue fieldRef;
            if (field.getModifiers().contains(Modifier.STATIC))
                fieldRef = new StaticFieldReference(field.getField());
            else
                fieldRef = new FieldReference(new ThisExpression(), field.getField());

            var assignment = new AssignmentExpression(fieldRef, init);
            statements.add(new ExpressionStatement(assignment));
        }

        return new Block(statements);
    }
}
