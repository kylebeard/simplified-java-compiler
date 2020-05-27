package cmpt355.project.language;

import cmpt355.project.ast.Typed;
import cmpt355.project.ast.node.AstNode;
import cmpt355.project.ast.node.DataTypeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a general variable (consisting of name, data type, and modifiers).
 */
public abstract class Variable extends AstNode implements Typed {

    private Modifiers modifiers;
    private DataTypeNode typeNode;
    private String name;

    Variable(Modifiers modifiers, DataType type, String name) {
        this.setModifiers(modifiers);
        this.setType(type);
        this.setName(name);
    }

    @Override
    public String toString() {
        List<String> words = new ArrayList<>();
        getModifiers().forEach(mod -> words.add(mod.toString()));
        words.add(getType().toString());
        words.add(getName());
        return String.join(" ", words);
    }

    public Modifiers getModifiers() {
        return modifiers;
    }

    public void setModifiers(Modifiers modifiers) {
        this.modifiers = Objects.requireNonNull(modifiers);
    }

    public DataType getType() {
        return typeNode.getType();
    }

    public DataTypeNode getTypeNode() {
        return typeNode;
    }

    public void setType(DataType type) {
        this.typeNode = reparent(new DataTypeNode(type));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public List<DataTypeNode> children() {
        return List.of(typeNode);
    }
}
