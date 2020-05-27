package cmpt355.project.ast.node.statement;

import cmpt355.project.ast.node.AstNode;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.ast.node.Statement;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.codegen.Helper;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.language.DataType;
import cmpt355.project.language.PrimitiveType;

import java.util.List;

public class ExpressionStatement extends Statement {

    private Expression expression;

    public ExpressionStatement(Expression expression) {
        this.setExpression(expression);
    }

    @Override
    public List<? extends AstNode> children() {
        return List.of(getExpression());
    }

    @Override
    public String toString() {
        return "expression statement";
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = reparentNonNull(expression);
    }

    @Override
    public void generateCode(CompileContext context) {
        var exprType = expression.getType();
        expression.generateCode(context);

        Helper.pop(context, exprType);
    }
}
