package cmpt355.project.codegen;

import cmpt355.project.Compiler;
import cmpt355.project.SyntaxException;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.file.Path;

public abstract class CodegenTest {

    private static final Path COMPILE_DESTINATION_PATH = Path.of("compiled");
    private static final Path ASSEMBLE_DESTINATION_PATH = Path.of("assembled");

    static Class<?> compile(String code) throws SyntaxException, IOException, ClassNotFoundException {
        var reader = new StringReader(code);
        var ast = Compiler.parse(reader);
        String className = ast.getClassDefinitions().get(0).getQualifiedName().toString();
        var asmFile = COMPILE_DESTINATION_PATH.resolve(className + ".j");
        Compiler.compile(ast, asmFile);
        Compiler.assemble(asmFile, ASSEMBLE_DESTINATION_PATH);
        return Class.forName(className);
    }
}
