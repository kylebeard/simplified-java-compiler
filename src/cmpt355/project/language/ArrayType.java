package cmpt355.project.language;

import cmpt355.project.DataTypeException;
import cmpt355.project.ast.node.AstNode;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * A class representing an array type, e.g. an int[] or String[][]. An ArrayType has a base type (e.g int or String) and
 * a (positive) dimension (the number of []s). Normally ArrayType objects are created by
 * {@link DataType#arrayOf(DataType)} or {@link DataType#arrayOf(DataType, int)}.
 */
public final class ArrayType extends DataType {

    private final DataType baseType;
    private final int dimension;

    /**
     * Create a new ArrayType with the given base type and dimension. If baseType is itself an ArrayType, then the newly
     * constructed ArrayType has the same base type.
     * @param baseType
     * @param dimension
     */
    ArrayType(DataType baseType, int dimension) {
        if (baseType instanceof ArrayType) {
            var a = (ArrayType)baseType;
            this.baseType = a.baseType;
            this.dimension = a.dimension + dimension;
        } else {
            this.baseType = baseType;
            this.dimension = dimension;
        }
    }

    /** Returns the data type of a single element of this array. If this type represents a multidimensional array,
     * the returned type is an array type of one dimension less. */
    public DataType getComponentType() {
        return (dimension == 1) ? baseType : new ArrayType(baseType, dimension - 1);
    }

    /** Returns the underlying type of value stored in this array. The return value is not an array type. */
    public DataType getBaseType() {
        return baseType;
    }

    public int getDimension() {
        return dimension;
    }

    /**
     * Returns <code>true</code> (all array types are reference types).
     * @return
     */
    @Override
    public boolean isReferenceType() {
        return true;
    }

    @Override
    public boolean isSupertypeOf(DataType other) {
        if (other.equals(this))
            return true;
        else if (other.equals(ExternalClassType.OBJECT))
            return true;
        else if (other instanceof ArrayType && getComponentType().isSupertypeOf(((ArrayType) other).getComponentType()))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        String baseTypeStr = baseType.toString();
        StringBuilder sb = new StringBuilder(baseTypeStr.length() + 2 * dimension);
        sb.append(baseTypeStr);
        for (int i = 0; i < dimension; ++i)
            sb.append("[]");
        return sb.toString();
    }

    @Override
    public String toJvm() {
        String baseTypeJvm = baseType.toJvm();
        StringBuilder sb = new StringBuilder(baseTypeJvm.length() + dimension);
        for (int i = 0; i < dimension; ++i)
            sb.append('[');
        return sb.append(baseTypeJvm).toString();
    }

    @Override
    public DataType resolve(AstNode context) throws DataTypeException {
        DataType resolvedBase = baseType.resolve(context);
        return (resolvedBase == baseType) ? this : new ArrayType(resolvedBase, dimension);
    }

    /**
     * If <code>name</code> is <code>length</code>, returns an {@link ArrayLengthField}; otherwise returns an empty
     * {@link java.util.Optional}.
     */
    @Override
    public Optional<Field> findField(String name) {
        return ("length".equals(name)) ? Optional.of(new ArrayLengthField(this)) : Optional.empty();
    }

    /**
     * Returns an empty list (arrays have no methods).
     */
    @Override
    public List<Method> findMethods(String name) {
        return List.of();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayType arrayType = (ArrayType) o;
        return dimension == arrayType.dimension &&
                baseType.equals(arrayType.baseType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseType, dimension);
    }
}
