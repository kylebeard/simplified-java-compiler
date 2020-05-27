package cmpt355.project.ast.node.expression;

import cmpt355.project.DataTypeException;
import cmpt355.project.InternalParserException;
import cmpt355.project.ast.node.AstNode;
import cmpt355.project.ast.node.DataTypeNode;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.language.ArrayType;
import cmpt355.project.language.DataType;
import cmpt355.project.language.PrimitiveType;
import cmpt355.project.util.Lists;

import java.util.List;

public class ArrayInstantiationExpression extends Expression {

    private DataTypeNode typeNode;
    private List<Expression> dims;

    public ArrayInstantiationExpression(DataTypeNode typeNode, List<Expression> dims) {
        setTypeNode(typeNode);
        setDims(dims);
    }

    @Override
    public DataType getType() {
        return typeNode.getType();
    }

    public DataTypeNode getTypeNode() {
        return typeNode;
    }

    @Override
    public void validateType() throws DataTypeException {
        if (!(typeNode.getType() instanceof ArrayType))
            throw new DataTypeException("Array instantiation of non-array type");
        for (var dim : dims) {
            var dimType = dim.getType();
            if (dimType != PrimitiveType.INT
                    && dimType != PrimitiveType.SHORT
                    && dimType != PrimitiveType.CHAR
                    && dimType != PrimitiveType.BYTE)
                throw new DataTypeException("Array dimension of type " + dimType);
        }
    }

    @Override
    public List<AstNode> children() {
        return Lists.<AstNode>builder()
                .add(typeNode)
                .addAll(dims)
                .build();
    }

    @Override
    public String toString() {
        return "new array";
    }

    public void setTypeNode(DataTypeNode type) {
        if (!(type.getType() instanceof ArrayType))
            throw new IllegalArgumentException("Type must be an ArrayType");
        this.typeNode = reparentNonNull(type);
    }

    public List<Expression> getDims() {
        return dims;
    }

    public void setDims(List<Expression> dims) {
        this.dims = reparentNonNull(dims);
    }

    @Override
    public void generateCode(CompileContext context) {
        var arrayType = (ArrayType)typeNode.getType();
        var baseType = arrayType.getBaseType();

        if (arrayType.getDimension() == 1) {
            if (dims.size() != 1)
                throw new InternalParserException("Instantiation of one-dimensional array with " + dims.size() + " sizes specified");
            dims.get(0).generateCode(context);
            // count →
            if (baseType.isReferenceType())
                context.addCode(Instruction.new_anewarray(baseType));
            else
                context.addCode(Instruction.new_newarray((PrimitiveType)baseType));
            // newarray →
        } else {
            if (dims.size() == 0 || dims.size() > arrayType.getDimension())
                throw new InternalParserException("Instantiation of " + dims.size() + "-dimensional array with " + dims.size() + " sizes specified");
            DataType allocArrayType = arrayType;
            while (allocArrayType instanceof ArrayType && ((ArrayType)allocArrayType).getDimension() > dims.size())
                allocArrayType = ((ArrayType)allocArrayType).getComponentType();

            dims.forEach(dim -> dim.generateCode(context));

            if (allocArrayType instanceof ArrayType)
                context.addCode(Instruction.new_multianewarray((ArrayType)allocArrayType));
            else if (allocArrayType.isReferenceType())
                context.addCode(Instruction.new_anewarray(allocArrayType));
            else
                context.addCode(Instruction.new_newarray((PrimitiveType)allocArrayType));
        }
    }
}
