package cmpt355.project.codegen;

import cmpt355.project.jvm.InstructionAddressOperand;

/**
 * A class representing a label in assembly code: something that can be branched by to another instruction such as
 * {@link cmpt355.project.jvm.Instruction#new_goto(InstructionAddressOperand)}.
 *
 * A label must have its {@link #mark()} method called at the point that the label is to branch to. For example, in
 * code like
 * <pre><code>
 *     context.addCode(/&ast; instruction A &ast;/);
 *     label.mark();
 *     context.addCode(/&ast; instruction B &ast;/);</code></pre>
 * a branch to {@code label} would execute instruction B next.
 */
public class Label extends InstructionAddressOperand {

    Label(String text) {
        super(text);
    }

    /**
     * Marks the label at this particular point in the code. If this method is not called on a label before the assembly
     * file is to be generated, an exception will be thrown at that time.
     */
    public void mark() {
        throw new UnsupportedOperationException("Cannot mark() a vanilla label");
    }
}
