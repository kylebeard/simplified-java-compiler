package cmpt355.project.jvm;

import java.util.Objects;

public class FloatOperand extends Operand {

    private float value;

    public FloatOperand(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloatOperand that = (FloatOperand) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return Float.toString(value) + "F";
    }
}
