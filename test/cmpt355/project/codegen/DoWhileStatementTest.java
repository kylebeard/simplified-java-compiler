package cmpt355.project.codegen;

import cmpt355.project.SyntaxException;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class DoWhileStatementTest extends CodegenTest {

    private static final String CLASS_CODE = """
            package cmpt355.test;
            import cmpt355.project.codegen.DoWhileStatementTest;
            public class DoWhileStatementTestClass {
                private DoWhileStatementTest test;
                
                public DoWhileStatementTestClass(DoWhileStatementTest test) {
                    this.test = test;
                }
                
                public void loopOnce() {
                    do test.tick();
                    while (false);
                }
                
                public void loop(int n) {
                    do test.tick();
                    while (--n > 0);
                }
                
                public void loopLoop(int m, int n) {
                    do test.tick();
                    while (--m > 0);
                    
                    do test.tick2();
                    while (--n > 0);
                }
                
                public void nestedLoop(int m, int n) {
                    int N = n;
                    
                    do {
                        test.outerBodyStart();
                        n = N--;
                        
                        do test.inner();
                        while (--n > 0);
                        
                        test.outerBodyEnd();
                    } while (--m > 0);
                }
            }
            """;

    private static Class<?> clazz;

    @BeforeAll
    static void compileClass() throws SyntaxException, IOException, ReflectiveOperationException {
        clazz = compile(CLASS_CODE);

    }

    @org.junit.jupiter.api.Test
    void loopOnceTest() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(DoWhileStatementTest.class).newInstance(this);
        // obj.loopOnce();
        clazz.getMethod("loopOnce").invoke(obj);
        assertEquals(1, ticks, "tick() was called the wrong number of times");
    }

    @org.junit.jupiter.api.Test
    void loopTest() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(DoWhileStatementTest.class).newInstance(this);
        // obj.loop(139);
        clazz.getMethod("loop", int.class).invoke(obj, 139);
        assertEquals(139, ticks, "tick() was called the wrong number of times");
    }

    @org.junit.jupiter.api.Test
    void loopLoopTest() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(DoWhileStatementTest.class).newInstance(this);
        // obj.loopLoop(27, 185);
        clazz.getMethod("loopLoop", int.class, int.class).invoke(obj, 27, 185);
        assertEquals(27, ticks, "tick() was called the wrong number of times");
        assertEquals(185, ticks2, "tick2() was called the wrong number of times");
    }

    @org.junit.jupiter.api.Test
    void nestedLoopTest() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(DoWhileStatementTest.class).newInstance(this);
        // obj.loopLoop(5, 3);
        clazz.getMethod("nestedLoop", int.class, int.class).invoke(obj, 5, 3);
        assertEquals(5, outerCounter, "Outer loop executed the wrong number of times");
        assertEquals(8, innerCounter, "Inner loop executed the wrong number of times");
        assertEquals("[XXX][XX][X][X][X]", loopProgress.toString(), "Nested loop iterated in wrong order");
    }

    private int ticks;
    public void tick() {
        ++ticks;
    }

    private int ticks2;
    public void tick2() { ++ticks2; }

    private int outerCounter, innerCounter;
    private StringBuilder loopProgress = new StringBuilder();

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