package cmpt355.project.jvm;

import cmpt355.project.language.ClassType;
import cmpt355.project.language.Field;

import java.util.Objects;

public class FieldOperand extends Operand {

    private final Field field;

    public FieldOperand(Field field) {
        this.field = Objects.requireNonNull(field);
    }

    public Field getField() {
        return field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldOperand that = (FieldOperand) o;
        return field.equals(that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }

    @Override
    public String toString() {
        return String.format("Field %s %s %s",
                ((ClassType)field.getEnclosingType()).getClassName().toJvmString(),
                field.getName(),
                field.getType().toJvm());
    }
}
