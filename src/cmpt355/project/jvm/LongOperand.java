package cmpt355.project.jvm;

import java.util.Objects;

public class LongOperand extends Operand {

    private long value;

    public LongOperand(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongOperand that = (LongOperand) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return Long.toString(value) + "L";
    }
}
