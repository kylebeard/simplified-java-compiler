package cmpt355.project.jvm;

import java.util.Objects;

public class InstructionAddressOperand extends Operand {

    private final String label;

    public InstructionAddressOperand(String label) {
        this.label = Objects.requireNonNull(label);
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstructionAddressOperand that = (InstructionAddressOperand) o;
        return label.equals(that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
