package cmpt355.project.codegen;

import cmpt355.project.SyntaxException;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class IfStatementTest extends CodegenTest {

    private static final String CLASS_CODE = """
            package cmpt355.test;
            import cmpt355.project.codegen.IfStatementTest;
            public class IfStatementTestClass {
                private IfStatementTest test;
                
                public IfStatementTestClass(IfStatementTest test) {
                    this.test = test;
                }
                
                public void simpleIf() {
                    if (true)
                        test.f();   // should be called
                        
                    if (false)
                        test.g();   // should not be called
                }
                
                public void ifElse(int x) {
                    if (x > 0)
                        test.f();
                    else
                        test.g();
                }
                
                public void ifIfElse(int x, int y) {
                    if (x > 0)
                        test.f();
                    if (y > 0)
                        test.g();
                    else
                        test.h();
                }
                
                public void ifElseIfElse(int x, int y) {
                    if (x > 0)
                        test.f();
                    else if (y > 0)
                        test.g();
                    else
                        test.h();
                }
                
                public void nested(int x, int y) {
                    if (x > y) {
                        if (y > 0)
                            test.f();
                        else
                            test.g();
                    } else
                        test.h();
                }
            }
            """;

    private static Class<?> clazz;

    @BeforeAll
    static void compileClass() throws SyntaxException, IOException, ReflectiveOperationException {
        clazz = compile(CLASS_CODE);

    }

    @org.junit.jupiter.api.Test
    void simpleIfTest() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(IfStatementTest.class).newInstance(this);
        clazz.getMethod("simpleIf").invoke(obj);
        assertTrue(fCalled, "f was not called");
        assertFalse(gCalled, "g was called");
    }

    @org.junit.jupiter.api.Test
    void ifElseTest() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(IfStatementTest.class).newInstance(this);
        clazz.getMethod("ifElse", int.class).invoke(obj, 20);
        assertTrue(fCalled, "f was not called");
        assertFalse(gCalled, "g was called");
    }

    @org.junit.jupiter.api.Test
    void ifElseTest2() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(IfStatementTest.class).newInstance(this);
        clazz.getMethod("ifElse", int.class).invoke(obj, 0);
        assertFalse(fCalled, "f was called");
        assertTrue(gCalled, "g was not called");
    }

    @org.junit.jupiter.api.Test
    void ifIfElseTest() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(IfStatementTest.class).newInstance(this);
        clazz.getMethod("ifIfElse", int.class, int.class).invoke(obj, 5, 8);
        assertTrue(fCalled, "f was not called");
        assertTrue(gCalled, "g was not called");
        assertFalse(hCalled, "h was called");
    }

    @org.junit.jupiter.api.Test
    void ifIfElseTest2() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(IfStatementTest.class).newInstance(this);
        clazz.getMethod("ifIfElse", int.class, int.class).invoke(obj, 5, -4);
        assertTrue(fCalled, "f was not called");
        assertFalse(gCalled, "g was called");
        assertTrue(hCalled, "h was not called");
    }

    @org.junit.jupiter.api.Test
    void ifIfElseTest3() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(IfStatementTest.class).newInstance(this);
        clazz.getMethod("ifIfElse", int.class, int.class).invoke(obj, Integer.MIN_VALUE, Integer.MAX_VALUE);
        assertFalse(fCalled, "f wascalled");
        assertTrue(gCalled, "g was not called");
        assertFalse(hCalled, "h was called");
    }

    @org.junit.jupiter.api.Test
    void ifIfElseTest4() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(IfStatementTest.class).newInstance(this);
        clazz.getMethod("ifIfElse", int.class, int.class).invoke(obj, -1, -1);
        assertFalse(fCalled, "f was called");
        assertFalse(gCalled, "g was called");
        assertTrue(hCalled, "h was not called");
    }

    @org.junit.jupiter.api.Test
    void ifElseIfElseTest() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(IfStatementTest.class).newInstance(this);
        clazz.getMethod("ifElseIfElse", int.class, int.class).invoke(obj, 10, 4);
        assertTrue(fCalled, "f was not called");
        assertFalse(gCalled, "g was called");
        assertFalse(hCalled, "h was called");
    }

    @org.junit.jupiter.api.Test
    void ifElseIfElseTest2() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(IfStatementTest.class).newInstance(this);
        clazz.getMethod("ifElseIfElse", int.class, int.class).invoke(obj, 0, 1);
        assertFalse(fCalled, "f was called");
        assertTrue(gCalled, "g was not called");
        assertFalse(hCalled, "h was called");
    }

    @org.junit.jupiter.api.Test
    void ifElseIfElseTest3() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(IfStatementTest.class).newInstance(this);
        clazz.getMethod("ifElseIfElse", int.class, int.class).invoke(obj, -4, -8);
        assertFalse(fCalled, "f was called");
        assertFalse(gCalled, "g was called");
        assertTrue(hCalled, "h was not called");
    }

    @org.junit.jupiter.api.Test
    void nestedTest() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(IfStatementTest.class).newInstance(this);
        clazz.getMethod("nested", int.class, int.class).invoke(obj, 10, 4);
        assertTrue(fCalled, "f was not called");
        assertFalse(gCalled, "g was called");
        assertFalse(hCalled, "h was called");
    }

    @org.junit.jupiter.api.Test
    void nestedTest2() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(IfStatementTest.class).newInstance(this);
        clazz.getMethod("nested", int.class, int.class).invoke(obj, 25, -4);
        assertFalse(fCalled, "f was called");
        assertTrue(gCalled, "g was not called");
        assertFalse(hCalled, "h was called");
    }

    @org.junit.jupiter.api.Test
    void nestedTest3() throws ReflectiveOperationException {
        var obj = clazz.getConstructor(IfStatementTest.class).newInstance(this);
        clazz.getMethod("nested", int.class, int.class).invoke(obj, 11, 11);
        assertFalse(fCalled, "f was called");
        assertFalse(gCalled, "g was called");
        assertTrue(hCalled, "h was not called");
    }

    private boolean fCalled, gCalled, hCalled;
    public void f() {
        fCalled = true;
    }

    public void g() {
        gCalled = true;
    }

    public void h() {
        hCalled = true;
    }
}