package cmpt355.project.ast.node.expression;

import cmpt355.project.ast.node.Expression;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.language.DataType;

import java.util.Objects;
import java.util.function.Consumer;

public class PseudoExpression extends Expression {

    private final DataType dataType;
    private final Consumer<CompileContext> codeGenerator;

    public PseudoExpression(DataType dataType, Consumer<CompileContext> codeGenerator) {
        this.dataType = Objects.requireNonNull(dataType);
        this.codeGenerator = codeGenerator;
    }

    @Override
    public void generateCode(CompileContext context) {
        codeGenerator.accept(context);
    }

    @Override
    public DataType getType() {
        return dataType;
    }

    @Override
    public String toString() {
        return String.format("Pseudo expression (type=%s)", dataType);
    }
}
