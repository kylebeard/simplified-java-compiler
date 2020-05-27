package cmpt355.project.ast.node.expression;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.node.AstNode;
import cmpt355.project.ast.node.DataTypeNode;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.language.DataType;
import cmpt355.project.language.PrimitiveType;

import java.util.List;

public class InstanceofExpression extends Expression {

    private Expression expr;
    private DataTypeNode typeNode;

    public InstanceofExpression(Expression expr, DataTypeNode typeNode) {
        this.setExpr(expr);
        this.setTypeNode(typeNode);
    }

    @Override
    public DataType getType() {
        return PrimitiveType.BOOLEAN;
    }

    @Override
    public void validateType() throws DataTypeException {
        var type = expr.getType();

        if (!type.isReferenceType())
            throw new DataTypeException("Cannot apply instanceof to type " + type);

        var predType = typeNode.getType();
        if (!predType.isReferenceType())
            throw new DataTypeException("Cannot check whether instanceof type " + type);
    }

    @Override
    public List<? extends AstNode> children() {
        return List.of(expr, typeNode);
    }

    @Override
    public String toString() {
        return "instanceof";
    }

    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = reparentNonNull(expr);
    }

    public void setTypeNode(DataTypeNode typeNode) {
        this.typeNode = reparentNonNull(typeNode);
    }

    public DataTypeNode getTypeNode() {
        return typeNode;
    }

    @Override
    public void generateCode(CompileContext context) {
        expr.generateCode(context);
        context.addCode(Instruction.new_instanceof(typeNode.getType()));
    }
}
