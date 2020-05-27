package cmpt355.project.language;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.QualifiedIdentifier;
import cmpt355.project.ast.node.AstNode;

import java.util.List;
import java.util.Optional;

/**
 * Base class for classes representing class data types.
 */
public abstract class ClassType extends DataType {

    private final QualifiedIdentifier className;

    public ClassType(QualifiedIdentifier className) {
        this.className = className;
    }

    public QualifiedIdentifier getClassName() {
        return className;
    }

    /**
     * Returns <code>true</code> (class types are reference types).
     * @return
     */
    @Override
    public boolean isReferenceType() {
        return true;
    }

    /**
     * Returns <code>true</code> if this class type represents a class (as opposed to an interface).
     */
    public abstract boolean isClass();

    /**
     * Returns <code>false</code> if this class type represents an interface (as opposed to a class).
     * @return
     */
    public abstract boolean isInterface();

    /**
     * Returns the modifiers applied to this class type's definition, for example {@link Modifier#PUBLIC} or
     * {@link Modifier#FINAL}.
     */
    public abstract Modifiers getModifiers();

    @Override
    public String toString() {
        return className.toString();
    }

    @Override
    public String toJvm() {
        return String.format("L%s;", className.toJvmString());
    }

    /**
     * Returns the immediate supertype (in this case the superclass) of this class type.
     */
    public abstract Optional<ClassType> getSupertype();

    /**
     * Returns <code>true</code> if this class type is a supertype of <code>other</code>. Every type is a supertype of
     * itself.
     */
    @Override
    public boolean isSupertypeOf(DataType other) {
        if (this.equals(ExternalClassType.OBJECT))
            return other.isReferenceType();
        else if (other == DataType.NULL)
            return true;
        else if (other instanceof ClassType) {
            var cType = (ClassType)other;
            while (true) {
                if (cType.equals(this))
                    return true;
                var maybeSupertype = cType.getSupertype();
                if (maybeSupertype.isEmpty())
                    break;
                cType = maybeSupertype.get();
            }
        }

        return false;
    }

    /**
     * Returns the {@link Field} of this class of the given name, or an empty {@link Optional} if no such field exists.
     */
    public abstract Optional<Field> findField(String name);

    /**
     * Returns all {@link Method}s of this class of the given name, including those from superclasses.
     */
    public abstract List<Method> findMethods(String name);

    @Override
    public abstract ClassType resolve(AstNode context) throws DataTypeException;

    public String getPackage() {
        if (className.names.size() == 1)
            return "";
        return className.head(className.names.size() - 1).toString();
    }
}
