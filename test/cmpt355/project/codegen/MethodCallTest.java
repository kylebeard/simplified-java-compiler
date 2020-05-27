package cmpt355.project.codegen;

import cmpt355.project.SyntaxException;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MethodCallTest extends CodegenTest {

    private static final String CLASS_CODE = """
            package cmpt355.test;
            import cmpt355.project.codegen.MethodCallTest;
            public class MethodCallTestClass {
                private MethodCallTest test = new MethodCallTest();
                
                public void f() {
                    test.f();
                }
                
                public void g(int i) {
                    test.g(i);
                }
                
                public void h(char c) {
                    test.h(c);
                }
                
                public void i(byte b, long l) {
                    test.i(b, l);
                }
                
                public void j(double d, short s) {
                    test.j(d, s);
                }
                
                public void k(MethodCallTest o, boolean z) {
                    test.k(o, z);
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
        clazz.getMethod("f").invoke(obj);
        assertTrue(fCalled);
    }

    @org.junit.jupiter.api.Test
    void gTest() throws ReflectiveOperationException {
        clazz.getMethod("g", int.class).invoke(obj, 8725);
        assertEquals(8725, MethodCallTest.i);
    }

    @org.junit.jupiter.api.Test
    void hTest() throws ReflectiveOperationException {
        clazz.getMethod("h", char.class).invoke(obj, 'k');
        assertEquals('k', MethodCallTest.c);
    }

    @org.junit.jupiter.api.Test
    void iTest() throws ReflectiveOperationException {
        clazz.getMethod("i", byte.class, long.class).invoke(obj, (byte)87, 8745L);
        assertEquals((byte)87, MethodCallTest.b, "Byte argument wrong");
        assertEquals(8745L, MethodCallTest.l, "Long argument wrong");
    }

    @org.junit.jupiter.api.Test
    void jTest() throws ReflectiveOperationException {
        clazz.getMethod("j", double.class, short.class).invoke(obj, -12284.48, (short)28);
        assertEquals(-12284.48, MethodCallTest.d, "Double argument wrong");
        assertEquals((short)28, MethodCallTest.s, "Short argument wrong");
    }

    @org.junit.jupiter.api.Test
    void kTest() throws ReflectiveOperationException {
        clazz.getMethod("k", MethodCallTest.class, boolean.class).invoke(obj, this, true);
        assertEquals(this, MethodCallTest.o, "MethodCallTest argument wrong");
        assertEquals(true, MethodCallTest.z, "Boolean argument wrong");
    }

    @org.junit.jupiter.api.Test
    void kTestAgain() throws ReflectiveOperationException {
        clazz.getMethod("k", MethodCallTest.class, boolean.class).invoke(obj, null, false);
        assertEquals(null, MethodCallTest.o, "MethodCallTest argument wrong");
        assertEquals(false, MethodCallTest.z, "Boolean argument wrong");
    }

    private static boolean fCalled = false;
    public void f() {
        fCalled = true;
    }

    private static int i;
    public void g(int i) {
        MethodCallTest.i = i;
    }

    private static char c;
    public void h(char c) {
        MethodCallTest.c = c;
    }

    private static byte b;
    private static long l;
    public void i(byte b, long l) {
        MethodCallTest.b = b;
        MethodCallTest.l = l;
    }

    private static double d;
    private static short s;
    public void j(double d, short s) {
        MethodCallTest.d = d;
        MethodCallTest.s = s;
    }

    private static MethodCallTest o;
    private static boolean z;
    public static void k(MethodCallTest o, boolean z) {
        MethodCallTest.o = o;
        MethodCallTest.z = z;
    }
}