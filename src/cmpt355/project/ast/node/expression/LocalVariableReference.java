package cmpt355.project.ast.node.expression;

import static cmpt355.project.language.PrimitiveType.*;

import cmpt355.project.InternalCompilerException;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.codegen.Helper;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.language.DataType;
import cmpt355.project.language.LocalVariable;
import cmpt355.project.language.PrimitiveType;

import java.util.List;

public class LocalVariableReference extends LValue {

    private LocalVariable variable;

    public LocalVariableReference(LocalVariable variable) {
        setVariable(variable);
    }

    public LocalVariable getVariable() {
        return variable;
    }

    public void setVariable(LocalVariable variable) {
        this.variable = reparentNonNull(variable);
    }

    @Override
    public DataType getType() {
        return variable.getType();
    }

    @Override
    public void validateType() {
        // Nothing to do
    }

    @Override
    public String toString() {
        return "variable reference";
    }

    @Override
    public List<LocalVariable> children() {
        return List.of(variable);
    }

    @Override
    public void generateCode(CompileContext context) {
        int index = context.findMethodVariable(variable.getName());
        var type = getType();

        if (type.isReferenceType())
            Helper.aload(context, index);
        else if (((PrimitiveType)type).isEffectivelyInt())
            Helper.iload(context, index);
        else if (type == PrimitiveType.LONG)
            Helper.lload(context, index);
        else if (type == PrimitiveType.FLOAT)
            Helper.fload(context, index);
        else if (type == PrimitiveType.DOUBLE)
            Helper.dload(context, index);
        else
            throw new InternalCompilerException("Don't know how to handle loading value of local of type " + type);

    }

    @Override
    public void generateSetCode(CompileContext context, Expression rhs) {
        int index = context.findMethodVariable(variable.getName());
        var varType = getType();
        var rhsType = rhs.getType();

        rhs.generateCode(context);
        // value →

        // Do we need to do some conversion?
        if (varType instanceof PrimitiveType)
            Helper.convertTypes(context, (PrimitiveType)rhsType, (PrimitiveType)varType);
        else
            context.addCode(Instruction.new_checkcast(varType));

        // value →
        Helper.dup(context, varType);
        // value value →


        if (varType.isReferenceType())
            Helper.astore(context, index);
        else if (((PrimitiveType)varType).isEffectivelyInt())
            Helper.istore(context, index);
        else if (varType == PrimitiveType.LONG)
            Helper.lstore(context, index);
        else if (varType == PrimitiveType.FLOAT)
            Helper.fstore(context, index);
        else if (varType == PrimitiveType.DOUBLE)
            Helper.dstore(context, index);
        else
            throw new InternalCompilerException("Don't know how to handle setting value of local of type " + varType);

        // value →
    }
}
