package cmpt355.project.jvm;

import cmpt355.project.language.ClassType;
import cmpt355.project.language.DataType;

import java.util.Objects;

public class TypeOperand extends Operand {

    private final DataType type;

    public TypeOperand(DataType type) {
        this.type = Objects.requireNonNull(type);
    }

    public DataType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeOperand that = (TypeOperand) o;
        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        if (type instanceof ClassType)
            return ((ClassType)type).getClassName().toJvmString();
        else
            return type.toJvm();
    }
}
