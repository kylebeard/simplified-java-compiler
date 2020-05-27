package cmpt355.project.codegen;

import cmpt355.project.SyntaxException;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ArraySubscriptTest extends CodegenTest {

    private static final String CLASS_CODE = """
            package cmpt355.test;
            public class ArraySubscriptTestClass {
                public byte byteArray() {
                    byte[] bytes = new byte[5];
                    bytes[0] = (byte)27;
                    bytes[1] = (byte)7;
                    bytes[2] = (byte)(bytes[0] << bytes[1]);
                    bytes[3] = (byte)(bytes[2] | bytes[0]);
                    return bytes[3]; // -101
                }
                
                public short shortArray() {
                    short[] shorts = new short[5];
                    shorts[0] = (short)27;
                    shorts[1] = (short)7;
                    shorts[2] = (short)(shorts[0] << shorts[1]);
                    shorts[3] = (short)(shorts[2] | shorts[0]);
                    return shorts[3]; // 3483
                }
                
                public char charArray() {
                    char[] chars = new char[5];
                    chars[0] = (char)27;
                    chars[1] = (char)7;
                    chars[2] = (char)(chars[0] << chars[1]);
                    chars[3] = (char)(chars[2] | chars[0]);
                    return chars[3]; // 'ඛ' (3483)
                }
                
                public int intArray() {
                    int[] ints = new int[5];
                    ints[0] = (int)27;
                    ints[1] = (int)7;
                    ints[2] = (int)(ints[0] << ints[1]);
                    ints[3] = (int)(ints[2] | ints[0]);
                    return ints[3]; // 3483
                }
                
                public long longArray() {
                    long[] longs = new long[5];
                    longs[0] = (long)27;
                    longs[1] = (long)7;
                    longs[2] = (long)(longs[0] << longs[1]);
                    longs[3] = (long)(longs[2] | longs[0]);
                    return longs[3]; // 3483
                }
                
                public float floatArray() {
                    float[] floats = new float[5];
                    floats[0] = -8287.384f;
                    floats[1] = 2273.38445f;
                    floats[2] = ((int)floats[0]) ^ ((int)floats[1]);
                    floats[3] = ((int)floats[2]) >>> 11;
                    return floats[3]; // 2097146
                }
                
                public double doubleArray() {
                    double[] doubles = new double[5];
                    doubles[0] = -8287.384;
                    doubles[1] = 2273.38445;
                    doubles[2] = ((int)doubles[0]) ^ ((int)doubles[1]);
                    doubles[3] = ((int)doubles[2]) >>> 11;
                    return doubles[3]; // 2097146
                }
                
                public boolean booleanArray() {
                    boolean[] b = new boolean[3];
                    b[0] = true;
                    b[1] = b[0] | false;
                    b[2] = !b[1];
                    return b[2]; // false
                }
                
                public String stringArray() {
                    String[] strings = new String[5];
                    strings[0] = "placebos";
                    strings[1] = "weatherproof " + strings[0];
                    strings[2] = strings[1] + " " + strings[0] + " standee";
                    strings[3] = "gentility's " + strings[2] + strings[0];
                    return strings[3];
                }
                
                public String mixedArray() {
                    double[] doubles = new double[7];
                    doubles[0] = (byte)5;
                    doubles[1] = (short)11;
                    doubles[2] = 45;
                    doubles[3] = -67L;
                    doubles[4] = 15.8F;
                    doubles[5] = Math.PI;
                    doubles[6] = 'Z';
                    return "" + doubles[0] + doubles[1] + doubles[2] + doubles[3] + doubles[4] + doubles[5] + doubles[6];
                    // "5.011.045.0-67.015.8000001907348633.14159265358979390.0"
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
    void byteArrayTest() throws ReflectiveOperationException {
        assertEquals((byte)-101, (byte)clazz.getMethod("byteArray").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void shortArrayTest() throws ReflectiveOperationException {
        assertEquals((short)3483, (short)clazz.getMethod("shortArray").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void charArrayTest() throws ReflectiveOperationException {
        assertEquals('ඛ', (char)clazz.getMethod("charArray").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void intArrayTest() throws ReflectiveOperationException {
        assertEquals(3483, (int)clazz.getMethod("intArray").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void longArrayTest() throws ReflectiveOperationException {
        assertEquals(3483L, (long)clazz.getMethod("longArray").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void floatArrayTest() throws ReflectiveOperationException {
        assertEquals(2097146F, (float)clazz.getMethod("floatArray").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void doubleArrayTest() throws ReflectiveOperationException {
        assertEquals(2097146.0, (double)clazz.getMethod("doubleArray").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void booleanArrayTest() throws ReflectiveOperationException {
        assertEquals(false, (boolean)clazz.getMethod("booleanArray").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void stringArrayTest() throws ReflectiveOperationException {
        assertEquals("gentility's weatherproof placebos placebos standeeplacebos",
                (String)clazz.getMethod("stringArray").invoke(obj));
    }

    @org.junit.jupiter.api.Test
    void mixedArrayTest() throws ReflectiveOperationException {
        assertEquals("5.011.045.0-67.015.8000001907348633.14159265358979390.0",
                (String)clazz.getMethod("mixedArray").invoke(obj));
    }

}