package cmpt355.project.codegen;

import cmpt355.project.SyntaxException;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ParameterTest extends CodegenTest {

    private static final String CLASS_CODE = """
            package cmpt355.test;
            public class ParameterTestClass {
            
                public int f(int x) {
                    return x << 3;
                }
                
                public String g(String s) {
                    return s + " for the glory of the Empire";
                }
                
                public Object h(Object o) {
                    return o;
                }
                
                public int i(int x) {
                    x = x & 5;
                    return x << 1;
                }
                
                public String j(String s) {
                    s = s + " on the couch";
                    return s;
                }
                
                public Object k(Object o) {
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
        assertEquals(72, (int)clazz.getMethod("f", int.class).invoke(obj, 9));
    }

    @org.junit.jupiter.api.Test
    void gTest() throws ReflectiveOperationException {
        assertEquals("I'm writing unit tests for the glory of the Empire",
                clazz.getMethod("g", String.class).invoke(obj, "I'm writing unit tests"));
    }

    @org.junit.jupiter.api.Test
    void hTest() throws ReflectiveOperationException {
        var temp = new Object();
        assertEquals(temp, clazz.getMethod("h", Object.class).invoke(obj, temp));
    }

    @org.junit.jupiter.api.Test
    void iTest() throws ReflectiveOperationException {
        assertEquals(2, (int)clazz.getMethod("i", int.class).invoke(obj, 9));
    }

    @org.junit.jupiter.api.Test
    void jTest() throws ReflectiveOperationException {
        assertEquals("I'm writing unit tests on the couch",
                clazz.getMethod("j", String.class).invoke(obj, "I'm writing unit tests"));
    }

    @org.junit.jupiter.api.Test
    void kTest() throws ReflectiveOperationException {
        assertNull(clazz.getMethod("k", Object.class).invoke(obj, new Object()));
    }
}