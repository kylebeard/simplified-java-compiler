package cmpt355.project.language;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.node.AstNode;

import java.util.List;
import java.util.Optional;

/**
 * Base class for all classes representing a Java data type.
 */

public abstract class DataType {

    /**
     * The data type <code>void</code>. Only seen as the return type of a method.
     */
    public static final DataType VOID = new DataType() {
        @Override
        public String toString() {
            return "void";
        }

        @Override
        public String toJvm() {
            return "V";
        }

        @Override
        public boolean isReferenceType() {
            return false;
        }

        @Override
        public boolean isSupertypeOf(DataType other) {
            return other == this;
        }

        @Override
        public DataType resolve(AstNode context) {
            return this;
        }

        @Override
        public Optional<Field> findField(String name) {
            return Optional.empty();
        }

        @Override
        public List<Method> findMethods(String name) {
            return List.of();
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this;
        }
    };

    /**
     * The data type <code>null</code>. Only seen as the data type of the <code>null</code> literal.
     */
    public static final DataType NULL = new DataType() {
        @Override
        public String toString() {
            return "null";
        }

        @Override
        public String toJvm() {
            throw new IllegalStateException("null is not a type");
        }

        @Override
        public boolean isReferenceType() {
            return true;
        }

        @Override
        public boolean isSupertypeOf(DataType other) {
            return other == this;
        }

        @Override
        public DataType resolve(AstNode context) {
            return this;
        }

        @Override
        public Optional<Field> findField(String name) {
            return Optional.empty();
        }

        @Override
        public List<Method> findMethods(String name) {
            return List.of();
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this;
        }
    };

    public abstract String toString();
    public abstract String toJvm();

    /**
     * Returns <code>true</code> if this data type represents a reference type (i.e. not a primitive type nor void).
     */
    public abstract boolean isReferenceType();

    /**
     * Returns <code>true</code> if this type is a supertype of <code>other</code>.
     */
    public abstract boolean isSupertypeOf(DataType other);

    /**
     * Resolves this type in the context of the given {@link AstNode}.
     * @throws DataTypeException if this object does not represent a legitimate data type.
     */
    public abstract DataType resolve(AstNode context) throws DataTypeException;

    /**
     * Returns the {@link Field} of this type with the given name, or an empty {@link Optional} if no such field exists.
     */
    public abstract Optional<Field> findField(String name);

    /**
     * Returns the {@link Method}s of this type of the given name.
     */
    public abstract List<Method> findMethods(String name);

    /**
     * Returns the data type of an array of componentType. If componentType is itself an array type, this method returns
     * an array type with one higher dimension (e.g., if componentType represents int[], this method returns a type
     * representing int[][]).
     * @param componentType the data type of components of an array
     * @return the data type of the array
     * @throws java.lang.IllegalArgumentException if componentType is VOID or NULL or {@code null}
     */
    public static ArrayType arrayOf(DataType componentType) {
        if (componentType instanceof ArrayType) {
            var arrayType = (ArrayType)componentType;
            return new ArrayType(arrayType.getBaseType(), arrayType.getDimension() + 1);
        } else
            return new ArrayType(componentType, 1);
    }

    /**
     * Returns the data type of an array of componentType with the given number of dimensions. If componentType is
     * itself an array type, this method returns an array type whose dimension is increased by the argument (e.g., if
     * componentType represents int[] and dimension is 3, this method returns a type representing int[][][][]).
     * @param componentType the data type of components of the array
     * @param dimension     the number of dimensions to add
     * @throws IllegalArgumentException if componentType is VOID or NULL or {@code null}, or dimension &lt; 1
     */
    public static ArrayType arrayOf(DataType componentType, int dimension) {
        if (dimension < 1)
            throw new IllegalArgumentException("dimension must be at least 1");
        return new ArrayType(componentType, dimension);
    }

    /**
     * Returns a <code>DataType</code> object representing the same data type as that represented by the
     * {@link java.lang.Class} parameter. For instance, if <code>clazz</code> is {@link Long#TYPE}, this returns
     * {@link PrimitiveType#LONG}; if <code>clazz</code> is <code>Object.class</code>, this returns an
     * {@link ExternalClassType} for {@link Object}.
     */
    public static DataType fromExternal(Class<?> clazz) {
        if (clazz.isPrimitive()) {
            if (clazz.equals(Byte.TYPE))
                return PrimitiveType.BYTE;
            else if (clazz.equals(Short.TYPE))
                return PrimitiveType.SHORT;
            else if (clazz.equals(Integer.TYPE))
                return PrimitiveType.INT;
            else if (clazz.equals(Long.TYPE))
                return PrimitiveType.LONG;
            else if (clazz.equals(Float.TYPE))
                return PrimitiveType.FLOAT;
            else if (clazz.equals(Double.TYPE))
                return PrimitiveType.DOUBLE;
            else if (clazz.equals(Character.TYPE))
                return PrimitiveType.CHAR;
            else if (clazz.equals(Boolean.TYPE))
                return PrimitiveType.BOOLEAN;
            else if (clazz.equals(Void.TYPE))
                return DataType.VOID;
            else
                throw new IllegalStateException("Primitive class that is not one of the eight!");
        } else if (clazz.isArray())
            return DataType.arrayOf(fromExternal(clazz.getComponentType()));
        else if (clazz.equals(Void.TYPE))
            return DataType.VOID;
        else
            return new ExternalClassType(clazz);
    }
}
