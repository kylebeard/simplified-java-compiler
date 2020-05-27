package cmpt355.project.codegen;

import cmpt355.project.SyntaxException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class ForStatementTest extends CodegenTest {

    private static final String CLASS_CODE = """
            package cmpt355.test;
            import cmpt355.project.codegen.ForStatementTest;
            public class ForStatementTestClass {
                private ForStatementTest test;
                
                public ForStatementTestClass(ForStatementTest test) {
                    this.test = test;
                }
                
                public void neverLoop() {
                    for ( ; false; ) {
                        test.tick();
                    }
                }
                
                public void countingLoop(int n) {
                    for (int i = 0; i < n; ++i)
                        test.tick();
                }
                
                public void noConditionLoop() {
                    for ( ;; )
                        test.maybeThrow();
                }
                
                public void basicLoop(int n) {
                    for (test.startLoop(), int i = 0; i < n; ++i, test.outerBodyEnd()) {
                        test.outerBodyStart();
                        test.inner();
                    }
                }

                public void nestedLoop(int m, int n) {
                    int N = n;
                    for (test.startLoop(), int i = 0; i < m; ++i, test.outerBodyEnd()) {
                        test.outerBodyStart();
                        n = N--;
                        for (int j = 0; j < n; ++j) {
                            test.inner();
                        }
                    }
                }
            }
            """;

    private static Class<?> clazz;

    @BeforeAll
    static void compileClass() throws SyntaxException, IOException, ReflectiveOperationException {
        clazz = compile(CLASS_CODE);

    }

    @org.junit.jupiter.api.Test
    void neverLoopTest() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(ForStatementTest.class).newInstance(this);
        // obj.neverLoop();
        clazz.getMethod("neverLoop").invoke(obj);
        assertEquals(0, ticks, "tick() should have been called 0 times");
    }

    @org.junit.jupiter.api.Test
    void countingLoopTest() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(ForStatementTest.class).newInstance(this);
        // obj.countingLoop(87);
        clazz.getMethod("countingLoop", int.class).invoke(obj, 87);
        assertEquals(87, ticks, "tick() called the wrong number of times");
    }

    @org.junit.jupiter.api.Test
    void noConditionLoopTest() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(ForStatementTest.class).newInstance(this);
        // obj.noConditionLoop();
        assertThrows(BreakLoopException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                try {
                    clazz.getMethod("noConditionLoop").invoke(obj);
                } catch (InvocationTargetException ex) {
                    throw ex.getTargetException();
                }
            }
        });
    }

    @org.junit.jupiter.api.Test
    void basicLoopTest() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(ForStatementTest.class).newInstance(this);
        // obj.basicLoop(4);
        clazz.getMethod("basicLoop", int.class).invoke(obj, 4);
        assertEquals(4, outerCounter, "Loop ran the wrong number of times");
        assertEquals("S[X][X][X][X]", loopProgress.toString(), "Loop flow was incorrect");
    }

    @org.junit.jupiter.api.Test
    void nestedLoopTest() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(ForStatementTest.class).newInstance(this);
        // obj.nestedLoop(5, 3);
        clazz.getMethod("nestedLoop", int.class, int.class).invoke(obj, 5, 3);
        assertEquals(5, outerCounter, "Outer loop ran the wrong number of times");
        assertEquals("S[XXX][XX][X][][]", loopProgress.toString(), "Loop flow was incorrect");
    }

    private int ticks;
    public void tick() {
        ++ticks;
    }

    private int ticks2;
    public void tick2() { ++ticks2; }

    private class BreakLoopException extends RuntimeException {}
    private int maybeThrowTicks = 100000000; // maybeThrow() will throw a BreakLoopException after this many calls
    public void maybeThrow() {
        --maybeThrowTicks;
        if (maybeThrowTicks == 0)
            throw new BreakLoopException();
    }

    private int outerCounter, innerCounter;
    private StringBuilder loopProgress = new StringBuilder();

    public void startLoop() {
        loopProgress.append('S');
    }

    public void outerBodyStart() {
        ++outerCounter;
        loopProgress.append('[');

    }

    public void outerBodyEnd() {
        loopProgress.append(']');
    }

    public void inner() {
        ++innerCounter;
        loopProgress.append('X');
    }
}