package cmpt355.project.ast.node.expression;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.language.DataType;
import cmpt355.project.language.PrimitiveType;

import java.util.List;
import java.util.function.Consumer;

public class PreIncrementExpression extends Expression {

    private boolean decrement;
    private LValue lvalue;

    public PreIncrementExpression(boolean decrement, LValue lvalue) {
        this.setDecrement(decrement);
        this.setLvalue(lvalue);
    }

    @Override
    public DataType getType() {
        return lvalue.getType();
    }

    @Override
    public List<LValue> children() {
        return List.of(getLvalue());
    }

    @Override
    public void validateType() throws DataTypeException {
        var lvalueType = getLvalue().getType();

        if (!(lvalueType instanceof PrimitiveType)
                || lvalueType == PrimitiveType.BOOLEAN)
            throw new DataTypeException(String.format("Incompatible types: %s %s", isDecrement() ? "--" : "++", lvalueType));
    }

    @Override
    public String toString() {
        return isDecrement() ? "--( )" : "++( )";
    }

    public boolean isDecrement() {
        return decrement;
    }

    public void setDecrement(boolean decrement) {
        this.decrement = decrement;
    }

    public LValue getLvalue() {
        return lvalue;
    }

    public void setLvalue(LValue lvalue) {
        this.lvalue = reparentNonNull(lvalue);
    }

    @Override
    public void generateCode(CompileContext _context) {
        // Result is value *after* increment
        var type = (PrimitiveType)getType();

        Consumer<CompileContext> rhs = context -> {
            lvalue.generateCode(context);
            if (type.isEffectivelyInt()) {
                context.addCode(Instruction.new_iconst_1());
                context.addCode(decrement ? Instruction.new_isub() : Instruction.new_iadd());
            } else if (type == PrimitiveType.LONG) {
                context.addCode(Instruction.new_lconst_1());
                context.addCode(decrement ? Instruction.new_lsub() : Instruction.new_ladd());
            } else if (type == PrimitiveType.FLOAT) {
                context.addCode(Instruction.new_fconst_1());
                context.addCode(decrement ? Instruction.new_fsub() : Instruction.new_fadd());
            } else if (type == PrimitiveType.DOUBLE) {
                context.addCode(Instruction.new_dconst_1());
                context.addCode(decrement ? Instruction.new_dsub() : Instruction.new_dadd());
            }
        };

        lvalue.generateSetCode(_context, new PseudoExpression(type, rhs));
    }
}
