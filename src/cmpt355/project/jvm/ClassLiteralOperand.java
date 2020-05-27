package cmpt355.project.jvm;

import cmpt355.project.ast.node.expression.ClassLiteral;
import cmpt355.project.language.ClassType;

import java.util.Objects;

public class ClassLiteralOperand extends Operand {

    private final ClassType classType;

    public ClassLiteralOperand(ClassType classType) {
        this.classType = Objects.requireNonNull(classType);
    }

    public ClassType getClassType() {
        return classType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassLiteralOperand that = (ClassLiteralOperand) o;
        return classType.equals(that.classType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classType);
    }

    @Override
    public String toString() {
        return String.format("Class %s", classType.getClassName().toJvmString());
    }
}
