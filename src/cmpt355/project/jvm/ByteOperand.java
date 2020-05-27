package cmpt355.project.jvm;

import java.util.Objects;

public class ByteOperand extends Operand {

    private final byte value;

    public ByteOperand(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ByteOperand that = (ByteOperand) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return Byte.toString(value);
    }
}
