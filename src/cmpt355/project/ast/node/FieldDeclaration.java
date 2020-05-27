package cmpt355.project.ast.node;

import cmpt355.project.DataTypeException;
import cmpt355.project.InternalParserException;
import cmpt355.project.ast.Typed;
import cmpt355.project.language.*;
import cmpt355.project.util.Lists;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Class representing the declaration of a field (instance variable or static/class variable) in a
 * {@link ClassDefinition}.
 */
public class FieldDeclaration extends AstNode implements Typed {

    private Modifiers modifiers;
    private DataTypeNode typeNode;
    private String name;
    private Field field;
    private Expression initializer;

    private FieldDeclaration(Modifiers modifiers, DataTypeNode typeNode, String name, Expression initializer) {
        this.setModifiers(modifiers);
        this.setTypeNode(typeNode);
        this.setName(name);
        this.setInitializer((Expression)initializer);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return String.format("field: %s %s",
                getModifiers(), getName());
    }

    @Override
    public List<? extends AstNode> children() {
        return Lists.<AstNode>builder()
                .add(typeNode)
                .addIfPresent(getInitializer())
                .build();
    }

    public Modifiers getModifiers() {
        return modifiers;
    }

    public void setModifiers(Modifiers modifiers) {
        this.modifiers = Objects.requireNonNull(modifiers);
    }

    public DataTypeNode getTypeNode() {
        return typeNode;
    }

    public DataType getType() {
        return typeNode.getType();
    }

    @Override
    public void validateType() throws DataTypeException {
        if (initializer != null) {
            var fieldType = typeNode.getType();
            var initType = initializer.getType();
            if (!fieldType.isSupertypeOf(initType))
                throw new DataTypeException("Cannot initialize field " + name + " of type " + fieldType + " with value of type " + initType);
        }
    }

    public void setTypeNode(DataTypeNode type) {
        this.typeNode = reparentNonNull(type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public Field getField() throws InternalParserException {
        if (field == null) {
            Optional<ClassDefinition> maybeClassDef = findAncestor(n -> n instanceof ClassDefinition);
            if (maybeClassDef.isPresent())
                field = new Field(modifiers, typeNode.getType(), name, new InternalClassType(maybeClassDef.get()));
            else
                throw new InternalParserException("FieldDeclaration has no ClassDefinition parent");
        }
        return field;
    }

    public Optional<AstNode> getInitializer() {
        return Optional.ofNullable(initializer);
    }

    public void setInitializer(Expression initializer) {
        this.initializer = reparent(initializer);
    }

    public static final class Builder {

        private final Modifiers modifiers = new Modifiers();
        private DataType type;
        private String name;
        private Expression initializer;

        private Builder() {}

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

        public Builder initializer(Expression expression) {
            this.initializer = expression;
            return this;
        }

        public FieldDeclaration build() {
            if (type == null || name == null)
                throw new IllegalStateException("Type and name must be specified");
            return new FieldDeclaration(modifiers, new DataTypeNode(type), name, initializer);
        }

        public Builder duplicate() {
            var b = new Builder();
            b.modifiers.addAll(modifiers);
            b.type = type;
            b.name = name;
            b.initializer = initializer;

            return b;
        }
    }
}
