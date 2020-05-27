package cmpt355.project.ast.node.statement;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.node.AstNode;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.ast.node.Statement;
import cmpt355.project.codegen.Label;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.jvm.Opcode;
import cmpt355.project.language.PrimitiveType;

import java.util.List;

public class WhileStatement extends Statement implements Loop {

    private Expression condition;
    private Statement body;
    private Label breakLabel, continueLabel;

    public WhileStatement(Expression condition, Statement body) {
        this.setCondition(condition);
        this.setBody(body);
    }

    @Override
    public String toString() {
        return "while";
    }

    @Override
    public List<? extends AstNode> children() {
        return List.of(getCondition(), getBody());
    }

    @Override
    public void validateType() throws DataTypeException {
        var conditionType = condition.getType();
        if (conditionType != PrimitiveType.BOOLEAN)
            throw new DataTypeException("Condition of while loop of type " + conditionType);
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = reparentNonNull(condition);
    }

    public Statement getBody() {
        return body;
    }

    public void setBody(Statement body) {
        this.body = reparentNonNull(body);
    }

    @Override
    public void generateCode(CompileContext context) {
        // Get new labels from the context
        // These are needed as branch targets (the places we will want to jump to in the code)
        // The String parameter to context.newLabel() is optional - if given, it is incorporated in the name of the
        // label in the generated assembly (for easier debugging)
        var topLabel = context.newLabel("while");
        var loopEndLabel = context.newLabel("endwhile");
        this.continueLabel = topLabel;  // a "continue;" inside the loop branches to the top
        this.breakLabel = loopEndLabel; // a "break;" inside the loop branches to the end

        // Mark the top of the loop
        topLabel.mark();

        // Generate code for condition
        condition.generateCode(context);
        // Top of stack should now be 0 (false) or 1 (true)

        // If condition fails, branch out of loop
        context.addCode(new Instruction(Opcode.ifeq, loopEndLabel));

        // Generate code of body
        body.generateCode(context);

        // Branch back to top of loop
        context.addCode(new Instruction(Opcode.goto_, topLabel));

        // Mark the end point of the loop
        loopEndLabel.mark();
    }

    @Override
    public Label getBreakLabel() {
        return breakLabel;
    }

    @Override
    public Label getContinueLabel() {
        return continueLabel;
    }
}
