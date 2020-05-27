package cmpt355.project.codegen;

import cmpt355.project.SyntaxException;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThisTest extends CodegenTest {

    private static final String CLASS_CODE = """
            package cmpt355.test;
            public class ThisTestClass {
                
                public ThisTestClass x;
                
                public void f() {
                    x = this;
                }
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
        assertEquals(obj, clazz.getField("x").get(obj));
    }
}