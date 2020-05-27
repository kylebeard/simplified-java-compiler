package cmpt355.project.jvm;

import java.util.Objects;

public class UnsignedByteOperand extends Operand {

    private final int value;

    public UnsignedByteOperand(int value) {
        if (value < 0 || value > 0xff)
            throw new IllegalArgumentException("Value must be between 0 and 255");
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnsignedByteOperand that = (UnsignedByteOperand) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
