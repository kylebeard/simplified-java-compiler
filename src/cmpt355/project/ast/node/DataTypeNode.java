package cmpt355.project.ast.node;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.Typed;
import cmpt355.project.language.DataType;

import java.util.Objects;

/**
 * AST node type for a data type. Embedding data types in the AST allows them to be resolved through tree traversal.
 */
public class DataTypeNode extends AstNode implements Typed {

    private DataType type;

    public DataTypeNode(DataType type) {
        setType(type);
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = Objects.requireNonNull(type);
    }

    @Override
    public void validateType() throws DataTypeException {
        this.type = type.resolve(this);
    }

    @Override
    public String toString() {
        return "type: " + type;
    }
}
