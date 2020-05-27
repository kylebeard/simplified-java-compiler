package cmpt355.project.codegen;

import cmpt355.project.SyntaxException;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LocalVariableTest extends CodegenTest {

    private static final String CLASS_CODE = """
            package cmpt355.test;
            public class LocalVariableTestClass {
            
                public int f() {
                    int x = 9;
                    return x << 3;
                }
                
                public String g() {
                    String s = "I'm writing unit tests";
                    return s + " for the glory of the Empire";
                }
                
                public Object h() {
                    Object o = this;
                    return o;
                }
                
                public int i() {
                    int x = 9;
                    x = x & 5;
                    return x << 1;
                }
                
                public String j() {
                    String s = "I'm writing unit tests";
                    s = s + " on the couch";
                    return s;
                }
                
                public Object k() {
                    Object o = this;
                    o = null;
                    return o;
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
        assertEquals(72, (int)clazz.getMethod("f").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void gTest() throws ReflectiveOperationException {
        assertEquals("I'm writing unit tests for the glory of the Empire",
                clazz.getMethod("g").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void hTest() throws ReflectiveOperationException {
        assertEquals(obj, clazz.getMethod("h").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void iTest() throws ReflectiveOperationException {
        assertEquals(2, (int)clazz.getMethod("i").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void jTest() throws ReflectiveOperationException {
        assertEquals("I'm writing unit tests on the couch",
                clazz.getMethod("j").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void kTest() throws ReflectiveOperationException {
        assertNull(clazz.getMethod("k").invoke(obj));
    }
}