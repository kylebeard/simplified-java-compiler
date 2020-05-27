package cmpt355.project.language;

import java.util.Objects;

/**
 * Class representing a field (static or non-static) of a class. On top of the base {@link Variable}, this adds an
 * <em>enclosing type,</em> the data type that holds the field.
 */
public class Field extends Variable {

    private DataType enclosingType;

    public Field(Modifiers modifiers, DataType type, String name, DataType enclosingType) {
        super(modifiers, type, name);
        setEnclosingType(enclosingType);
    }

    public DataType getEnclosingType() {
        return enclosingType;
    }

    public void setEnclosingType(DataType enclosingType) {
        this.enclosingType = Objects.requireNonNull(enclosingType);
    }

    /**
     * Creates a <code>Field</code> object from an external Java field defined by the reflection API's
     * {@link java.lang.reflect.Field} class.
     */
    public static Field fromExternal(java.lang.reflect.Field field) {
        return new Field(
                Modifiers.fromExternal(field.getModifiers()),
                DataType.fromExternal(field.getType()),
                field.getName(),
                new ExternalClassType(field.getDeclaringClass())
        );
    }
}
