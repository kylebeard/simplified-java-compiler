package cmpt355.project.language;

/**
 * A field type representing the <code>length</code> field of an array.
 */
public class ArrayLengthField extends Field {

    public ArrayLengthField(ArrayType enclosingType) {
        super(Modifiers.of(Modifier.PUBLIC, Modifier.FINAL), PrimitiveType.INT, "length", enclosingType);
    }

    /**
     * Returns {@link PrimitiveType#INT}.
     */
    @Override
    public PrimitiveType getType() {
        return PrimitiveType.INT;
    }
}
