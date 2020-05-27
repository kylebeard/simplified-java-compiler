package cmpt355.project.ast.node.expression;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.QualifiedIdentifier;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.jvm.Instruction;
import cmpt355.project.language.ClassType;
import cmpt355.project.language.DataType;
import cmpt355.project.language.ExternalClassType;

public class ClassLiteral extends Expression {

    public final QualifiedIdentifier className;
    private ClassType resolved;

    public ClassLiteral(QualifiedIdentifier className) {
        this.className = className;
    }

    @Override
    public void validateType() throws DataTypeException {
        var maybeClassType = findClass(className.toString());
        resolved = maybeClassType.orElseThrow(() -> new DataTypeException("Unknown class name: " + className));
    }

    @Override
    public DataType getType() {
        return ExternalClassType.CLASS;
    }

    @Override
    public String toString() {
        return String.format("%s.class", className);
    }

    @Override
    public void generateCode(CompileContext context) {
        context.addCode(Instruction.new_ldc(resolved));
    }
}
