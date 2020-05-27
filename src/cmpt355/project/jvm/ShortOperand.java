package cmpt355.project.jvm;

import java.util.Objects;

public class ShortOperand extends Operand {

    private final short value;

    public ShortOperand(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortOperand that = (ShortOperand) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return Short.toString(value);
    }
}
