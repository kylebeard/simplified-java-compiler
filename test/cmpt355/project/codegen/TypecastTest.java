package cmpt355.project.codegen;

import cmpt355.project.SyntaxException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TypecastTest extends CodegenTest {

    private static final String CLASS_CODE = """
            package cmpt355.test;
            public class TypecastTestClass {
            
                public int i = 20;
                public long l = 101010;
                public float f = 0.123456f;
                public double d = -9.87654321;
                public char c = 'A';
                
                public String s = "cheese";
                public Object o = new Object();
                public String s2;
            
                public int i2i() {
                    return (int)i;
                }
                
                public long i2l() {
                    return (long)i;
                }
                
                public float i2f() {
                    return (float)i;
                }
                
                public double i2d() {
                    return (double)i;
                }
                
                public byte i2b() {
                    return (byte)i;
                }
                
                public short i2s() {
                    return (short)i;
                }
                
                public char i2c() {
                    return (char)i;
                }
                
                public int l2i() {
                    return (int)l;
                }
                
                public int f2i() {
                    return (int)f;
                }
                
                public int d2i() {
                    return (int)d;
                }

                public int c2i(){
                    return (int)c;
                }
                
                public float l2f() {
                    return (float)l;
                }
                
                public long d2l() {
                    return (long)d;
                }
                
                public Object stringToObject() {
                    return (Object)s;
                }
                
                // This test should throw ClassCastException (from checkcast) since we are casting a non-String to String
                public void objectToString() {
                    s2 = "" + (String)o;
                }




                public static void main(String[] args){


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
    void i2iTest() throws ReflectiveOperationException {
        assertEquals(20, (int)clazz.getMethod("i2i").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void i2lTest() throws ReflectiveOperationException {
        assertEquals(20L, (long)clazz.getMethod("i2l").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void i2fTest() throws ReflectiveOperationException {
        assertEquals(20F, (float)clazz.getMethod("i2f").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void i2dTest() throws ReflectiveOperationException {
        assertEquals(20.0, (double)clazz.getMethod("i2d").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void i2bTest() throws ReflectiveOperationException {
        assertEquals((byte)20, (byte)clazz.getMethod("i2b").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void i2sTest() throws ReflectiveOperationException {
        assertEquals((short)20, (short)clazz.getMethod("i2s").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void i2cTest() throws ReflectiveOperationException {
        assertEquals((char)20, (char)clazz.getMethod("i2c").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void l2iTest() throws ReflectiveOperationException {
        assertEquals(101010, (int)clazz.getMethod("l2i").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void f2iTest() throws ReflectiveOperationException {
        assertEquals(0, (int)clazz.getMethod("f2i").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void d2iTest() throws ReflectiveOperationException {
        assertEquals(-9, (int)clazz.getMethod("d2i").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void c2iTest() throws ReflectiveOperationException {
        assertEquals(65, (int)clazz.getMethod("c2i").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void l2fTest() throws ReflectiveOperationException {
        assertEquals(101010F, (float)clazz.getMethod("l2f").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void d2lTest() throws ReflectiveOperationException {
        assertEquals(-9L, (long)clazz.getMethod("d2l").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void stringToObjectTest() throws ReflectiveOperationException {
        assertEquals("cheese", clazz.getMethod("stringToObject").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void objectToStringTest() {
        // Since we're accessing the objectToString() method through reflection, we won't get a pure ClassCastException
        // from calling it, but rather a ClassCastException wrapped inside an InvocationTargetException. We work around
        // this by catching the InvocationTargetException and rethrowing the juicy exception inside!
        assertThrows(ClassCastException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                try {
                    clazz.getMethod("objectToString").invoke(obj);
                } catch (InvocationTargetException ex) {
                    throw ex.getTargetException();
                }
            }
        });
    }
}