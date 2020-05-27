package cmpt355.project.ast.node.expression;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.language.DataType;

import java.util.List;

public class AssignmentExpression extends Expression {

    private LValue lvalue;
    private Expression rvalue;

    public AssignmentExpression(LValue lvalue, Expression rvalue) {
        setLValue(lvalue);
        setRValue(rvalue);
    }

    @Override
    public DataType getType() {
        return lvalue.getType();
    }

    @Override
    public void validateType() throws DataTypeException {
        var lvalueType = lvalue.getType();
        var rvalueType = rvalue.getType();
        if (!lvalueType.isSupertypeOf(rvalueType))
            throw new DataTypeException("Cannot assign value of type " + rvalueType + " to lvalue of type " + lvalueType);
    }

    @Override
    public List<Expression> children() {
        return List.of(lvalue, rvalue);
    }

    @Override
    public String toString() {
        return "assignment";
    }

    public LValue getLValue() {
        return lvalue;
    }

    public void setLValue(LValue lvalue) {
        this.lvalue = reparentNonNull(lvalue);
    }

    public Expression getRValue() {
        return rvalue;
    }

    public void setRValue(Expression rvalue) {
        this.rvalue = reparentNonNull(rvalue);
    }

    @Override
    public void generateCode(CompileContext context) {
        lvalue.generateSetCode(context, rvalue);
    }
}
