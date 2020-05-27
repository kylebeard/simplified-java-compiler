package cmpt355.project.ast.node.expression;

import static cmpt355.project.language.PrimitiveType.*;

import cmpt355.project.ast.node.Expression;
import cmpt355.project.codegen.Helper;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.jvm.Opcode;
import cmpt355.project.language.PrimitiveType;

import cmpt355.project.DataTypeException;
import cmpt355.project.InternalParserException;
import cmpt355.project.language.DataType;

import java.util.List;

public abstract class UnaryOp extends Expression {

    // +, -
    public static final class SignOp extends UnaryOp {
        SignOp(String op, Expression expr) {
            super(op, expr);
        }

        @Override
        DataType computeType(DataType arg) throws DataTypeException {
            if (!(arg instanceof PrimitiveType)
                    || arg == BOOLEAN)
                throwIncompatiblePoints(arg);
            return ((PrimitiveType)arg).unaryNumericPromotion();
        }

        @Override
        public void generateCode(CompileContext context) {
            PrimitiveType exprType = (PrimitiveType)getExpr().getType(),
                    type = (PrimitiveType)getType();
            getExpr().generateCode(context);
            Helper.convertTypes(context, exprType, type);
            if ("-".equals(getOp())) {
                var negOpcode = (type.isEffectivelyInt()) ? Opcode.ineg
                        : (type == LONG) ? Opcode.lneg
                        : (type == FLOAT) ? Opcode.fneg
                        : (type == DOUBLE) ? Opcode.dneg
                        : null;
                context.addCode(new Instruction(negOpcode));
            }
        }
    }

    // ~
    public static final class BitwiseComplementOp extends UnaryOp {
        BitwiseComplementOp(String op, Expression expr) {
            super(op, expr);
        }

        @Override
        DataType computeType(DataType arg) throws DataTypeException {
            if (!(arg instanceof PrimitiveType)
                    || !((PrimitiveType) arg).isIntegral())
                throwIncompatiblePoints(arg);
            return ((PrimitiveType)arg).unaryNumericPromotion();
        }

        @Override
        public void generateCode(CompileContext context) {
            PrimitiveType type = (PrimitiveType)getType();
            getExpr().generateCode(context);

            // JVM doesn't have a complement instruction, so we XOR the number with -1 (which is 1111 ... 1111 in binary)
            if (type == PrimitiveType.INT) {
                context.addCode(Instruction.new_iconst_m1());
                context.addCode(Instruction.new_ixor());
            } else if (type == PrimitiveType.LONG) {
                context.addCode(Instruction.new_iconst_m1());
                context.addCode(Instruction.new_i2l());
                context.addCode(Instruction.new_lxor());
            }
        }
    }

    // !
    public static final class LogicalComplementOp extends UnaryOp {
        LogicalComplementOp(String op, Expression expr) {
            super(op, expr);
        }

        @Override
        DataType computeType(DataType arg) throws DataTypeException {
            if (arg != BOOLEAN)
                throwIncompatiblePoints(arg);
            return BOOLEAN;
        }

        @Override
        public void generateCode(CompileContext context) {
            getExpr().generateCode(context);
            context.addCode(Instruction.new_iconst_1());
            context.addCode(Instruction.new_ixor());
        }
    }

    private final String op;
    private Expression expr;
    private DataType computedType;

    UnaryOp(String op, Expression expr) {
        this.op = op;
        this.setExpr(expr);
    }

    public static UnaryOp of(String op, Expression expr) {
        return switch(op) {
            case "+", "-" ->    new SignOp(op, expr);
            case "~" ->         new BitwiseComplementOp(op, expr);
            case "!" ->         new LogicalComplementOp(op, expr);
            default ->          throw new InternalParserException("Unknown unary op: " + op);
        };
    }

    abstract DataType computeType(DataType arg) throws DataTypeException;

    @Override
    public DataType getType() {
        return computedType;
    }

    @Override
    public void validateType() throws DataTypeException {
        if (computedType == null) {
            var argType = getExpr().getType();
            if (argType == DataType.VOID)
                throwIncompatiblePoints(argType);
            this.computedType = computeType(argType);
        }
    }

    void throwIncompatiblePoints(DataType arg) throws DataTypeException {
        throw new DataTypeException(String.format("Incompatible types: %s %s", getOp(), arg));
    }

    public String getOp() {
        return op;
    }

    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = reparentNonNull(expr);
    }

    @Override
    public final List<Expression> children() {
        return List.of(getExpr());
    }

    @Override
    public String toString() {
        return getOp();
    }
}
