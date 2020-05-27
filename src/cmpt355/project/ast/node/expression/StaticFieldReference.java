package cmpt355.project.ast.node.expression;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.codegen.Helper;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.language.*;

import java.util.List;
import java.util.Objects;

import static cmpt355.project.language.PrimitiveType.*;

public class StaticFieldReference extends LValue {

    private DataType classType;
    private String fieldName;
    private Field resolvedVariable;

    public StaticFieldReference(ClassType classType, String fieldName) {
        setClassType(classType);
        setFieldName(fieldName);
    }

    public StaticFieldReference(Field field) {
        this((ClassType)field.getEnclosingType(), field.getName());
        if (!field.getModifiers().contains(Modifier.STATIC))
            throw new IllegalArgumentException("Field " + field + " is non-static");
        this.resolvedVariable = field;
    }

    @Override
    public DataType getType() {
        return resolvedVariable.getType();
    }

    @Override
    public void validateType() throws DataTypeException {
        resolve();
    }

    @Override
    public String toString() {
        return String.format("Static field reference: %s.%s", classType, fieldName);
    }

    @Override
    public List<Variable> children() {
        return (resolvedVariable == null) ? List.of() : List.of(resolvedVariable);
    }

    public DataType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = Objects.requireNonNull(classType);
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = Objects.requireNonNull(fieldName);
    }

    public Field getVariable() {
        return resolvedVariable;
    }

    private void resolve() throws DataTypeException {
        if (resolvedVariable == null) {
            classType = classType.resolve(this);
            var maybeVar = classType.findField(fieldName);
            if (maybeVar.isPresent()) {
                this.resolvedVariable = maybeVar.get();
                if (!resolvedVariable.getModifiers().contains(Modifier.STATIC))
                    throw new DataTypeException(String.format("Cannot reference non-static field %s.%s from a static context",
                            classType, fieldName));
            } else
                throw new DataTypeException(String.format("Class %s has no field named %s", classType, fieldName));
        }
    }

    @Override
    public void generateCode(CompileContext context) {
        context.addCode(Instruction.new_getstatic(resolvedVariable));
    }

    @Override
    public void generateSetCode(CompileContext context, Expression rhs) {
        var varType = resolvedVariable.getType();
        var rhsType = rhs.getType();
        rhs.generateCode(context);

        // Do we need to do some conversion?
        if (varType instanceof PrimitiveType)
            Helper.convertTypes(context, (PrimitiveType)rhsType, (PrimitiveType)varType);
        else
            context.addCode(Instruction.new_checkcast(varType));

        if (varType == LONG || varType == DOUBLE)
            // value(2) →
            context.addCode(Instruction.new_dup2());
            // value(2) value(2) →
        else
            // value(1) →
            context.addCode(Instruction.new_dup());
            // value(1) value(1) →

        context.addCode(Instruction.new_putstatic(resolvedVariable));
        // value →
    }
}
