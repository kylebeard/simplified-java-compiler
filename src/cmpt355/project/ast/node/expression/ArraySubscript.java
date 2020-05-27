package cmpt355.project.ast.node.expression;

import static cmpt355.project.language.PrimitiveType.*;

import cmpt355.project.DataTypeException;
import cmpt355.project.InternalCompilerException;
import cmpt355.project.ast.node.AstNode;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.codegen.Helper;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.language.ArrayType;
import cmpt355.project.language.DataType;
import cmpt355.project.language.PrimitiveType;

import java.util.List;

public class ArraySubscript extends LValue {

    private Expression array;
    private Expression index;

    public ArraySubscript(Expression array, Expression index) {
        setArray(array);
        setIndex(index);
    }

    @Override
    public DataType getType() {
        return ((ArrayType)array.getType()).getComponentType();
    }

    @Override
    public void validateType() throws DataTypeException {
        if (!(array.getType() instanceof ArrayType))
            throw new DataTypeException("Subscripting value of type " + array.getType());
        var indexType = index.getType();
        if (indexType != PrimitiveType.INT
                && indexType != PrimitiveType.SHORT
                && indexType != PrimitiveType.CHAR
                && indexType != PrimitiveType.BYTE)
            throw new DataTypeException("Index of type " + indexType);
    }

    @Override
    public List<? extends AstNode> children() {
        return List.of(array, index);
    }

    @Override
    public String toString() {
        return "subscript";
    }

    public Expression getArray() {
        return array;
    }

    public void setArray(Expression array) {
        this.array = reparentNonNull(array);
    }

    public Expression getIndex() {
        return index;
    }

    public void setIndex(Expression index) {
        this.index = reparentNonNull(index);
    }

    @Override
    public void generateCode(CompileContext context) {
       var componentType = getType();

       array.generateCode(context);
       index.generateCode(context);

       if (componentType.isReferenceType())
           context.addCode(Instruction.new_aaload());
       else if (componentType == BOOLEAN || componentType == BYTE)
           context.addCode(Instruction.new_baload());
       else if (componentType == CHAR)
           context.addCode(Instruction.new_caload());
       else if (componentType == SHORT)
           context.addCode(Instruction.new_saload());
       else if (componentType == INT)
           context.addCode(Instruction.new_iaload());
       else if (componentType == LONG)
           context.addCode(Instruction.new_laload());
       else if (componentType == FLOAT)
           context.addCode(Instruction.new_faload());
       else if (componentType == DOUBLE)
           context.addCode(Instruction.new_daload());
       else
           throw new InternalCompilerException("Don't know how to handle reference to array of " +
                                                     "component type " + componentType);


    }

    @Override
    public void generateSetCode(CompileContext context, Expression rhs) {
        var cType = getType();
        var rhsType = rhs.getType();

        array.generateCode(context);
        index.generateCode(context);
        rhs.generateCode(context);

        // Do we need to do some conversion?
        if (cType instanceof PrimitiveType)
            Helper.convertTypes(context, (PrimitiveType)rhsType, (PrimitiveType)cType);
        else
            context.addCode(Instruction.new_checkcast(cType));

        // array index value →
        Helper.dup_x2(context, cType);
        // value array index value →

        if (cType.isReferenceType())
            context.addCode(Instruction.new_aastore());
        else if (cType == BYTE || cType == BOOLEAN)
            context.addCode(Instruction.new_bastore());
        else if (cType == SHORT)
            context.addCode(Instruction.new_sastore());
        else if (cType == INT)
            context.addCode(Instruction.new_iastore());
        else if (cType == LONG)
            context.addCode(Instruction.new_lastore());
        else if (cType == FLOAT)
            context.addCode(Instruction.new_fastore());
        else if (cType == PrimitiveType.DOUBLE)
            context.addCode(Instruction.new_dastore());
        else if (cType == CHAR)
            context.addCode(Instruction.new_castore());
        else
            throw new InternalCompilerException("Don't know how to handle assignment to array of component type " + cType);

        // value →
    }
}
