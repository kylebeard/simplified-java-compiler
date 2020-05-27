package cmpt355.project.codegen;

import cmpt355.project.SyntaxException;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReturnTest extends CodegenTest {

    private static final String CLASS_CODE = """
            package cmpt355.test;
            public class ReturnTestClass {
                
                public int x = 0;
                
                public int f() {
                    return 997;
                }
                
                public String g() {
                    return "OK";
                }
                
                public ReturnTestClass h() {
                    return this;
                }
                
                public void i() {
                    return;
                    x = 5;
                }
            }
            """;

    private static Class<?> clazz;
    private static Object obj;

    @BeforeAll
    static void compileClass() throws SyntaxException, IOException, ReflectiveOperationException {
        clazz = compile(CLASS_CODE);
        obj = clazz.getConstructor().newInstance();
    }

    @org.junit.jupiter.api.Test
    void fTest() throws ReflectiveOperationException {
        assertEquals(997, clazz.getMethod("f").invoke(obj), "Wrong return value from f()");
    }

    @org.junit.jupiter.api.Test
    void gTest() throws ReflectiveOperationException {
        assertEquals("OK", clazz.getMethod("g").invoke(obj), "Wrong return value from g()");
    }

    @org.junit.jupiter.api.Test
    void hTest() throws ReflectiveOperationException {
        assertEquals(obj, clazz.getMethod("h").invoke(obj), "Wrong return value from h()");
    }

    @org.junit.jupiter.api.Test
    void iTest() throws ReflectiveOperationException {
        clazz.getMethod("i").invoke(obj);
        assertEquals(0, clazz.getField("x").get(obj), "Generated code for i() failed to return before setting value of x");
    }
}