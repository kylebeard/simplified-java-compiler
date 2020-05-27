package cmpt355.project.codegen;

import cmpt355.project.SyntaxException;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ArithmeticOperatorTest extends CodegenTest {

    private static final String CLASS_CODE = """
            package cmpt355.test;
            public class ArithmeticOperatorTestClass {
                public int i;
                public long l;
                public float f;
                public double d;
                
                public void plus() {
                    i = 3483 + 99922; // 103405
                    l = 3483L + 99922L;
                    f = 3483F + 99922F;
                    d = 3483.0 + 99922.0;
                }
                
                public void minus() {
                    i = 20280 - 833421; // -813141
                    l = 20280L - 833421L;
                    f = 20280F - 833421L;
                    d = 20280.0 - 833421.0;
                }
                
                public void times() {
                    i = 253251 * 525221;    // -131242705
                    l = 253251L * 525221L;  // 133012743471
                    f = 253251F * 525221F;
                    d = 253251.0 * 525221.0;
                }
                
                public void divide() {
                    i = 159221 / 362;       // 439
                    l = 159221L / 362;
                    f = 159221F / 362F;     // 439.8370165745856
                    d = 159221.0 / 362.0;
                }
                
                public void mod() {
                    i = 934175 % 8488;      // 495
                    l = 934175L % 8488L;
                    f = 934175F % 8488F;
                    d = 934175.0 % 8488.0;
                }
                
                public void mixed() {
                    i = '☺' * 42;                       // 411012
                    l = Integer.MAX_VALUE / (byte)11;   // 195225786
                    f = 383.38745F / 'ẞ';               // 0.048913937
                    d = 99933883F % Math.PI;            // 3.0432752427778986
                }
                
                public void compound() {
                    i = 29823 - 'p' + -38385 / 99 - 38387487;               // -38358163
                    l = Long.MAX_VALUE - 39783723 % 9999667 % 987877 % 653; // 9223372036854775282
                    f = 92835398347L / 3747753F - 1919191919191919F - 99887765522.9887654433456F * 100; // -1.929180685690731E15
                    d = 38738372325.3993 % 87283475 + 9299299 + 15f - 39939;    // 8.105227539929962E7
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
    void plusTest() throws ReflectiveOperationException {
        clazz.getMethod("plus").invoke(obj);
        assertEquals(103405, (int)clazz.getField("i").get(obj), "Wrong answer for int");
        assertEquals(103405L, (long)clazz.getField("l").get(obj), "Wrong answer for long");
        assertEquals(103405F, (float)clazz.getField("f").get(obj), "Wrong answer for float");
        assertEquals(103405.0, (double)clazz.getField("d").get(obj), "Wrong answer for double");
    }

    @org.junit.jupiter.api.Test
    void minusTest() throws ReflectiveOperationException {
        clazz.getMethod("minus").invoke(obj);
        assertEquals(-813141, (int)clazz.getField("i").get(obj), "Wrong answer for int");
        assertEquals(-813141L, (long)clazz.getField("l").get(obj), "Wrong answer for long");
        assertEquals(-813141F, (float)clazz.getField("f").get(obj), "Wrong answer for float");
        assertEquals(-813141.0, (double)clazz.getField("d").get(obj), "Wrong answer for double");
    }

    @org.junit.jupiter.api.Test
    void timesTest() throws ReflectiveOperationException {
        clazz.getMethod("times").invoke(obj);
        assertEquals(-131242705, (int)clazz.getField("i").get(obj), "Wrong answer for int");
        assertEquals(133012743471L, (long)clazz.getField("l").get(obj), "Wrong answer for long");
        assertEquals(133012743471F, (float)clazz.getField("f").get(obj), "Wrong answer for float");
        assertEquals(133012743471.0, (double)clazz.getField("d").get(obj), "Wrong answer for double");
    }

    @org.junit.jupiter.api.Test
    void divideTest() throws ReflectiveOperationException {
        clazz.getMethod("divide").invoke(obj);
        assertEquals(439, (int)clazz.getField("i").get(obj), "Wrong answer for int");
        assertEquals(439L, (long)clazz.getField("l").get(obj), "Wrong answer for long");
        assertEquals(439.8370165745856F, (float)clazz.getField("f").get(obj), "Wrong answer for float");
        assertEquals(439.8370165745856, (double)clazz.getField("d").get(obj), "Wrong answer for double");
    }

    @org.junit.jupiter.api.Test
    void modTest() throws ReflectiveOperationException {
        clazz.getMethod("mod").invoke(obj);
        assertEquals(495, (int)clazz.getField("i").get(obj), "Wrong answer for int");
        assertEquals(495L, (long)clazz.getField("l").get(obj), "Wrong answer for long");
        assertEquals(495L, (float)clazz.getField("f").get(obj), "Wrong answer for float");
        assertEquals(495.0, (double)clazz.getField("d").get(obj), "Wrong answer for double");
    }

    @org.junit.jupiter.api.Test
    void mixedTest() throws ReflectiveOperationException {
        clazz.getMethod("mixed").invoke(obj);
        assertEquals(411012, (int)clazz.getField("i").get(obj), "Wrong answer for int");
        assertEquals(195225786L, (long)clazz.getField("l").get(obj), "Wrong answer for long");
        assertEquals(0.048913937F, (float)clazz.getField("f").get(obj), "Wrong answer for float");
        assertEquals(3.0432752427778986, (double)clazz.getField("d").get(obj), "Wrong answer for double");
    }

    @org.junit.jupiter.api.Test
    void compoundTest() throws ReflectiveOperationException {
        clazz.getMethod("compound").invoke(obj);
        assertEquals(-38358163, (int)clazz.getField("i").get(obj), "Wrong answer for int");
        assertEquals(9223372036854775282L, (long)clazz.getField("l").get(obj), "Wrong answer for long");
        assertEquals(-1.929180685690731E15F, (float)clazz.getField("f").get(obj), "Wrong answer for float");
        assertEquals(8.105227539929962E7, (double)clazz.getField("d").get(obj), "Wrong answer for double");
    }
}