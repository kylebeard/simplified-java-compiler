package cmpt355.project.ast.node.expression;

import cmpt355.project.DataTypeException;
import cmpt355.project.InternalCompilerException;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.codegen.Helper;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.language.*;

import java.util.List;

import static cmpt355.project.language.PrimitiveType.*;

public class PostIncrementExpression extends Expression {

    private boolean decrement;
    private LValue lvalue;

    public PostIncrementExpression(boolean decrement, LValue lvalue) {
        this.setDecrement(decrement);
        this.setLvalue(lvalue);
    }

    @Override
    public DataType getType() {
        return lvalue.getType();
    }

    @Override
    public void validateType() throws DataTypeException {
        var lvalueType = getLvalue().getType();

        if (!(lvalueType instanceof PrimitiveType)
                || lvalueType == PrimitiveType.BOOLEAN)
            throw new DataTypeException(String.format("Incompatible types: %s %s", lvalueType, isDecrement() ? "--" : "++"));
    }

    @Override
    public List<LValue> children() {
        return List.of(getLvalue());
    }

    @Override
    public String toString() {
        return isDecrement() ? "( )--" : "( )++";
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
    public void generateCode(CompileContext context) {
        generateCode(context, this.lvalue);
    }

    private void generateCode(CompileContext context, LValue lvalue) {
        // Code generation for a postincrement is complicated by the fact that the result of x++ needs to be the value
        // of x *before* being incremented. We can't just use a similar approach to a preincrement because there is no
        // way to hook in to the internal LValue generateSetCode() logic without evaluating the lvalue twice (which
        // could cause side effects, e.g. f().y++ would call f() twice).
        // So we get to basically duplicate the logic of every kind of lvalue's generateSetCode. Ugh ugh ugh!
        if (lvalue instanceof LocalVariableReference) {
            var localVar = ((LocalVariableReference)lvalue).getVariable();
            generateCode(context, (PrimitiveType)localVar.getType(), localVar.getName());
        } else if (lvalue instanceof ParameterReference) {
            var param = ((ParameterReference)lvalue).getVariable();
            generateCode(context, (PrimitiveType)param.getType(), param.getName());
        } else if (lvalue instanceof FieldReference)
            generateCode(context, (FieldReference)lvalue);
        else if (lvalue instanceof StaticFieldReference)
            generateCode(context, (StaticFieldReference)lvalue);
        else if (lvalue instanceof ArraySubscript)
            generateCode(context, (ArraySubscript)lvalue);
        else if (lvalue instanceof NameExpression)
            generateCode(context, ((NameExpression)lvalue).getResolved());
        else
            throw new InternalCompilerException("Unsupported lvalue for postincrement: " + lvalue);
    }

    private void generateCode(CompileContext context, PrimitiveType localVarType, String localVarName) {
        int localVarIndex = context.findMethodVariable(localVarName);
        lvalue.generateCode(context);
        // oldvalue →
        Helper.dup(context, localVarType);
        // oldvalue oldvalue →
        increment(context, localVarType);
        // oldvalue newvalue →

        if (localVarType.isEffectivelyInt())
            context.addCode(switch (localVarIndex) {
                case 0  -> Instruction.new_istore_0();
                case 1  -> Instruction.new_istore_1();
                case 2  -> Instruction.new_istore_2();
                case 3  -> Instruction.new_istore_3();
                default -> Instruction.new_istore(localVarIndex);
            });
        else if (localVarType == LONG)
            context.addCode(switch (localVarIndex) {
                case 0  -> Instruction.new_lstore_0();
                case 1  -> Instruction.new_lstore_1();
                case 2  -> Instruction.new_lstore_2();
                case 3  -> Instruction.new_lstore_3();
                default -> Instruction.new_lstore(localVarIndex);
            });
        else if (localVarType == FLOAT)
            context.addCode(switch (localVarIndex) {
                case 0  -> Instruction.new_fstore_0();
                case 1  -> Instruction.new_fstore_1();
                case 2  -> Instruction.new_fstore_2();
                case 3  -> Instruction.new_fstore_3();
                default -> Instruction.new_fstore(localVarIndex);
            });
        else if (localVarType == DOUBLE)
            context.addCode(switch (localVarIndex) {
                case 0  -> Instruction.new_dstore_0();
                case 1  -> Instruction.new_dstore_1();
                case 2  -> Instruction.new_dstore_2();
                case 3  -> Instruction.new_dstore_3();
                default -> Instruction.new_dstore(localVarIndex);
            });
        else
            throw new InternalCompilerException("Unsupported increment of type " + localVarType);

        // oldvalue →
    }

    private void generateCode(CompileContext context, FieldReference ref) {
        var field = ref.getVariable();
        var fieldType = (PrimitiveType)field.getType();

        ref.getExpr().generateCode(context);
        // obj →
        if (field.getModifiers().contains(Modifier.STATIC)) {
            // Despite being accessed through an object reference, this field is static
            context.addCode(Instruction.new_pop());
            // →
            context.addCode(Instruction.new_getstatic(field));
            // oldvalue →
            Helper.dup(context, fieldType);
            // oldvalue oldvalue →
            increment(context, fieldType);
            // oldvalue newvalue →
            context.addCode(Instruction.new_putstatic(field));
            // oldvalue →
        } else {
            context.addCode(Instruction.new_dup());
            // obj obj →
            context.addCode(Instruction.new_getfield(field));
            // obj oldvalue →
            Helper.dup_x1(context, fieldType);
            // oldvalue obj oldvalue →
            increment(context, fieldType);
            // oldvalue obj newvalue →
            context.addCode(Instruction.new_putfield(field));
            // oldvalue →
        }
    }

    private void generateCode(CompileContext context, StaticFieldReference ref) {
        var field = ref.getVariable();
        var fieldType = (PrimitiveType)field.getType();

        context.addCode(Instruction.new_getstatic(field));
        // oldvalue →
        Helper.dup(context, fieldType);
        // oldvalue oldvalue →
        increment(context, fieldType);
        // oldvalue newvalue →
        context.addCode(Instruction.new_putstatic(field));
        // oldvalue →
    }

    private void generateCode(CompileContext context, ArraySubscript subscript) {
        var arrayType = (ArrayType)subscript.getArray().getType();
        var compType = (PrimitiveType)arrayType.getComponentType();

        subscript.getArray().generateCode(context);
        // array →
        context.addCode(Instruction.new_dup());
        // array array →
        subscript.getIndex().generateCode(context);
        // array array index →
        context.addCode(Instruction.new_dup_x1());
        // array index array index →

        if (compType == BYTE || compType == BOOLEAN)
            context.addCode(Instruction.new_baload());
        else if (compType == SHORT)
            context.addCode(Instruction.new_saload());
        else if (compType == CHAR)
            context.addCode(Instruction.new_caload());
        else if (compType == INT)
            context.addCode(Instruction.new_iaload());
        else if (compType == LONG)
            context.addCode(Instruction.new_laload());
        else if (compType == FLOAT)
            context.addCode(Instruction.new_faload());
        else if (compType == DOUBLE)
            context.addCode(Instruction.new_daload());
        else
            throw new InternalCompilerException("Unsupported type for array subscript postincrement: " + compType);
        // array index oldvalue →
        Helper.dup_x2(context, compType);
        // oldvalue array index oldvalue →

        increment(context, compType);
        // oldvalue array index newvalue →

        if (compType == BYTE || compType == BOOLEAN)
            context.addCode(Instruction.new_bastore());
        else if (compType == SHORT)
            context.addCode(Instruction.new_sastore());
        else if (compType == CHAR)
            context.addCode(Instruction.new_castore());
        else if (compType == INT)
            context.addCode(Instruction.new_iastore());
        else if (compType == LONG)
            context.addCode(Instruction.new_lastore());
        else if (compType == FLOAT)
            context.addCode(Instruction.new_fastore());
        else if (compType == DOUBLE)
            context.addCode(Instruction.new_dastore());
        else
            throw new InternalCompilerException("Unsupported type for array subscript postincrement: " + compType);

        // oldvalue →
    }

    private void increment(CompileContext context, PrimitiveType type) {
        if (type.isEffectivelyInt()) {
            context.addCode(Instruction.new_iconst_1());
            context.addCode(decrement ? Instruction.new_isub() : Instruction.new_iadd());
        } else if (type == LONG) {
            context.addCode(Instruction.new_lconst_1());
            context.addCode(decrement ? Instruction.new_lsub() : Instruction.new_ladd());
        } else if (type == FLOAT) {
            context.addCode(Instruction.new_fconst_1());
            context.addCode(decrement ? Instruction.new_fsub() : Instruction.new_fadd());
        } else if (type == DOUBLE) {
            context.addCode(Instruction.new_dconst_1());
            context.addCode(decrement ? Instruction.new_dsub() : Instruction.new_dadd());
        } else
            throw new InternalCompilerException("Unsupported type for increment: " + type);
    }
}
