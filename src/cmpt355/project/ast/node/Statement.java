package cmpt355.project.ast.node;

import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;

/**
 * Abstract base class for a statement-like AST node.
 */
public abstract class Statement extends AstNode {

    /**
     * Generates JVM assembly code for this statement into the context. <strong>Requirement: the code generated here
     * must leave the stack as it found it; anything pushed onto the stack needs to also be popped.</strong>
     * @param context the context to add code to
     * @see cmpt355.project.jvm.Instruction
     * @see CompileContext#addCode(Instruction)
     */
    public abstract void generateCode(CompileContext context);
}
