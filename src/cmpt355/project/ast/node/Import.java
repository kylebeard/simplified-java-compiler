package cmpt355.project.ast.node;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.QualifiedIdentifier;
import cmpt355.project.language.ExternalClassType;

import java.util.Objects;

/**
 * AST node representing an import, e.g.
 * <pre><code>
 *     import java.util.*;
 *     import java.io.IOException;
 * </code></pre>
 */
public class Import extends AstNode {

    private QualifiedIdentifier id;
    private boolean wildcard;

    public Import(QualifiedIdentifier id, boolean wildcard) {
        this.setId(id);
        this.setWildcard(wildcard);
    }

    @Override
    public String toString() {
        return String.format("import %s",
                isWildcard() ? getId().toString() + ".*" : getId().toString());
    }

    @Override
    public void validateType() throws DataTypeException {
        if (!wildcard) {
            // Check that this actually names a class type!
            new ExternalClassType(id);
        }
    }

    public QualifiedIdentifier getId() {
        return id;
    }

    public void setId(QualifiedIdentifier id) {
        this.id = Objects.requireNonNull(id);
    }

    public boolean isWildcard() {
        return wildcard;
    }

    public void setWildcard(boolean wildcard) {
        this.wildcard = wildcard;
    }
}
