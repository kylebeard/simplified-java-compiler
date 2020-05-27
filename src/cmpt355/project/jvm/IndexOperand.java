package cmpt355.project.jvm;

import java.util.Objects;

public class IndexOperand extends Operand {

    private final int index;

    public IndexOperand(int index) {
        if (index < 0)
            throw new IllegalArgumentException("Index cannot be negative");
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexOperand that = (IndexOperand) o;
        return index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }

    @Override
    public String toString() {
        return Integer.toString(index);
    }
}
