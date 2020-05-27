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

public class DoWhileStatement extends Statement implements Loop {

    private Expression condition;
    private Statement body;
    private Label breakLabel, continueLabel;

    public DoWhileStatement(Expression condition, Statement body) {
        this.setCondition(condition);
        this.setBody(body);
    }

    @Override
    public String toString() {
        return "do-while";
    }

    @Override
    public List<? extends AstNode> children() {
        return List.of(getCondition(), getBody());
    }

    @Override
    public void validateType() throws DataTypeException {
        var conditionType = condition.getType();
        if (conditionType != PrimitiveType.BOOLEAN)
            throw new DataTypeException("do-while condition of type " + conditionType);
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
        var topLabel = context.newLabel("dowhile");
        var loopEndLabel = context.newLabel("end_dowhile");
        var condLabel = context.newLabel("dowhile_condition");
        this.continueLabel = condLabel;
        this.breakLabel = loopEndLabel;

        topLabel.mark();
        body.generateCode(context);
        condLabel.mark();
        condition.generateCode(context);
        context.addCode(new Instruction(Opcode.ifne, topLabel));
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
