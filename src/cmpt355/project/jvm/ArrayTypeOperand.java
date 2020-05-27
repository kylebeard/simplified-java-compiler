package cmpt355.project.jvm;

import cmpt355.project.language.PrimitiveType;

import java.util.Objects;

public class ArrayTypeOperand extends Operand {

    private final PrimitiveType type;

    public ArrayTypeOperand(PrimitiveType type) {
        this.type = Objects.requireNonNull(type);
    }

    @Override
    public String toString() {
        return type.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayTypeOperand that = (ArrayTypeOperand) o;
        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
