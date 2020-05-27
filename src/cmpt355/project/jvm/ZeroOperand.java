package cmpt355.project.jvm;

public class ZeroOperand extends Operand {

    public boolean equals(Object o) {
        return o != null && getClass().equals(o.getClass());
    }

    public int hashCode() {
        return 0;
    }
}
