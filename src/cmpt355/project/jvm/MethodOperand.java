package cmpt355.project.jvm;

import cmpt355.project.language.Method;

import java.util.Objects;

public class MethodOperand extends Operand {

    private final Method method;

    public MethodOperand(Method method) {
        this.method = Objects.requireNonNull(method);
    }

    public Method getMethod() {
        return method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodOperand that = (MethodOperand) o;
        return method.equals(that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method);
    }

    @Override
    public String toString() {
        return String.format("Method %s %s %s",
                method.getContainingClass().getClassName().toJvmString(),
                method.getName(),
                method.getJvmSignature());
    }
}
