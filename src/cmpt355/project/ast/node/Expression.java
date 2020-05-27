package cmpt355.project.ast.node;

import cmpt355.project.ast.Typed;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;

/**
 * Abstract base class for expression-like AST nodes.
 */
public abstract class Expression extends AstNode implements Typed {

    /**
     * Generates JVM assembly code for this expression into the context. <strong>Requirement: the code generated here
     * must push the value of the expression onto the stack, and its type must agree with {@link #getType()}.</strong>
     * @param context the context to add code to
     * @see cmpt355.project.jvm.Instruction
     * @see CompileContext#addCode(Instruction)
     */
    public abstract void generateCode(CompileContext context);
}
