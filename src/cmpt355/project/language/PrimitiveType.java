package cmpt355.project.language;

import cmpt355.project.ast.node.AstNode;

import java.util.List;
import java.util.Optional;

/**
 * A class representing one of the eight primitive types.
 */
public final class PrimitiveType extends DataType {

    public static final PrimitiveType BYTE = new PrimitiveType("byte", "B", 0),
                                  SHORT = new PrimitiveType("short", "S", 1),
                                  INT = new PrimitiveType("int", "I", 2),
                                  LONG = new PrimitiveType("long", "J", 3),
                                  FLOAT = new PrimitiveType("float", "F", 4),
                                  DOUBLE = new PrimitiveType("double", "D", 5),
                                  BOOLEAN = new PrimitiveType("boolean", "Z", -1),
                                  CHAR = new PrimitiveType("char", "C", 2);

    private final int ordinal;
    private final String str;
    private final String jvm;

    private PrimitiveType(String str, String jvm, int ordinal) {
        this.str = str;
        this.jvm = jvm;
        this.ordinal = ordinal;
    }

    @Override
    public String toString() {
        return str;
    }

    @Override
    public String toJvm() {
        return jvm;
    }

    /**
     * Returns the primitive type corresponding to their names as they appear in the Java language. For example,
     * <code>fromString("char")</code> returns {@link #CHAR}.
     */
    public static PrimitiveType fromString(String text) {
        return switch (text) {
            case "byte" -> BYTE;
            case "short" -> SHORT;
            case "int" -> INT;
            case "long" -> LONG;
            case "float" -> FLOAT;
            case "double" -> DOUBLE;
            case "boolean" -> BOOLEAN;
            case "char" -> CHAR;
            default -> throw new IllegalArgumentException("Invalid primitive type: " + text);
        };
    }

    /**
     * Performs unary numeric promotion on this primitive type as specified in JLS §5.6.1.
     */
    public PrimitiveType unaryNumericPromotion() {
        if (this == BYTE || this == SHORT || this == CHAR)
            return INT;
        else
            return this;
    }

    /**
     * Performs binary numeric promotion on this primitive type and <code>other</code> as specified in JLS §5.6.2.
     */
    public PrimitiveType binaryNumericPromotion(PrimitiveType other) {
        if (this == DOUBLE || other == DOUBLE)
            return DOUBLE;
        else if (this == FLOAT || other == FLOAT)
            return FLOAT;
        else if (this == LONG || other == LONG)
            return LONG;
        else
            return INT;
    }

    /**
     * Returns <code>true</code> if this primitive type is integral, i.e. if it is one of {@link #BYTE}, {@link #SHORT},
     * {@link #CHAR}, {@link #INT}, or {@link #LONG}.
     * @return
     */
    public boolean isIntegral() {
        return (this == BYTE || this == SHORT || this == INT || this == LONG || this == CHAR);
    }

    public boolean isEffectivelyInt() {
        return (this == BYTE || this == SHORT || this == CHAR || this == INT || this == BOOLEAN);
    }

    /**
     * Returns <code>false</code> (no primitive type is a reference type).
     */
    @Override
    public boolean isReferenceType() {
        return false;
    }

    @Override
    public boolean isSupertypeOf(DataType other) {
        if (other == this)
            return true;
        else if (this == BOOLEAN || other == BOOLEAN)
            return false;
        else if (!(other instanceof PrimitiveType))
            return false;

        var pType = (PrimitiveType)other;
        if (this == CHAR)
            return false;
        else if (pType == CHAR)
            return this.ordinal >= INT.ordinal;

        return ordinal >= pType.ordinal;
    }

    /**
     * Returns <code>this</code> (primitive types are already resolved).
     */
    @Override
    public DataType resolve(AstNode context) {
        return this;
    }

    /**
     * Returns an empty {@link java.util.Optional} (primitives do not have fields).
     */
    @Override
    public Optional<Field> findField(String name) {
        return Optional.empty();
    }

    /**
     * Returns an empty {@link java.util.List} (primitives do not have methods).
     * @param name
     * @return
     */
    @Override
    public List<Method> findMethods(String name) {
        return List.of();
    }

    @Override
    public int hashCode() {
        return ordinal;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }
}
