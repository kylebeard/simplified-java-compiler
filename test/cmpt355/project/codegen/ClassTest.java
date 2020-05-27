package cmpt355.project.codegen;

import cmpt355.project.Compiler;
import cmpt355.project.SyntaxException;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ClassTest extends CodegenTest {

    private static final String CLASS_CODE = """
            package cmpt355.test;
            public class Empty {
                public Empty() {}
                public void f() {}
                public static void g() {}
            }
            """;

    private static Class<?> clazz;

    @BeforeAll
    static void compileClass() throws SyntaxException, IOException, ReflectiveOperationException {
        clazz = compile(CLASS_CODE);
    }

    @org.junit.jupiter.api.Test
    void generateCode() throws ReflectiveOperationException {
        var obj = clazz.getConstructor().newInstance();
        clazz.getMethod("f").invoke(obj);
        clazz.getMethod("g").invoke(null);
    }
}