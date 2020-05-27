package cmpt355.project.ast.node.statement;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.Typed;
import cmpt355.project.ast.node.AstNode;
import cmpt355.project.ast.node.Expression;
import cmpt355.project.ast.node.Statement;
import cmpt355.project.ast.node.expression.LocalVariableReference;
import cmpt355.project.codegen.CompileContext;
import cmpt355.project.codegen.Helper;
import cmpt355.project.language.DataType;
import cmpt355.project.language.LocalVariable;
import cmpt355.project.language.Modifier;
import cmpt355.project.language.Modifiers;
import cmpt355.project.util.Lists;

import java.util.List;
import java.util.Optional;

public class LocalVariableDeclaration extends Statement implements Typed {

    private LocalVariable variable;
    private Expression init;

    private LocalVariableDeclaration(LocalVariable variable, Expression init) {
        this.setVariable(variable);
        this.setInit(init);
    }

    public static Builder builder() {
        return new Builder();
    }

    public LocalVariable getVariable() {
        return variable;
    }

    public void setVariable(LocalVariable variable) {
        this.variable = reparentNonNull(variable);
    }

    public Optional<AstNode> getInit() {
        return Optional.ofNullable(init);
    }

    public void setInit(Expression init) {
        this.init = reparent(init);
    }

    @Override
    public List<AstNode> children() {
        return Lists.<AstNode>builder()
                .add(getVariable())
                .addIfPresent(getInit())
                .build();
    }

    @Override
    public DataType getType() {
        return variable.getType();
    }

    @Override
    public void validateType() throws DataTypeException {
        if (init != null) {
            var varType = variable.getType();
            var initType = init.getType();
            if (!varType.isSupertypeOf(initType))
                throw new DataTypeException("Cannot initialize variable of type " + varType + " with expression of type " + initType);
        }
    }

    @Override
    public String toString() {
        return "local variable";
    }

    @Override
    public void setParentNode(AstNode parentNode) {
        super.setParentNode(parentNode);
    }

    @Override
    public void generateCode(CompileContext context) {
        if (init != null) {
            new LocalVariableReference(variable).generateSetCode(context, init);
            Helper.pop(context, variable.getType());
        }
    }

    public static final class Builder {

        private final Modifiers modifiers = new Modifiers();
        private DataType type;
        private String name;
        private Expression init;

        private Builder() {}

        private Builder(Builder other) {
            this.modifiers.addAll(other.modifiers);
            this.type = other.type;
            this.name = other.name;
            this.init = other.init;
        }

        public Builder modifier(Modifier modifier) {
            modifiers.add(modifier);
            return this;
        }

        public Builder type(DataType type) {
            this.type = type;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder init(Expression init) {
            this.init = init;
            return this;
        }

        public Builder duplicate() {
            return new Builder(this);
        }

        public LocalVariableDeclaration build() {
            if (type == null || name == null)
                throw new IllegalStateException("Type and name must be specified");
            return new LocalVariableDeclaration(new LocalVariable(type, modifiers, name), init);
        }
    }
}
