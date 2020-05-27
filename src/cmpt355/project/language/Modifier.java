package cmpt355.project.language;

/**
 * An enum representing a modifier (that could be applied to a class, field, method, or variable).
 */
public enum Modifier {

    PUBLIC, PROTECTED, PRIVATE,
    ABSTRACT, DEFAULT, FINAL, NATIVE, STATIC, STRICTFP, SYNCHRONIZED, TRANSIENT, VOLATILE;

    public static Modifier fromString(String text) {
        return switch (text) {
            case "public" -> PUBLIC;
            case "protected" -> PROTECTED;
            case "private" -> PRIVATE;
            case "abstract" -> ABSTRACT;
            case "default" -> DEFAULT;
            case "final" -> FINAL;
            case "native" -> NATIVE;
            case "static" -> STATIC;
            case "strictfp" -> STRICTFP;
            case "synchronized" -> SYNCHRONIZED;
            case "transient" -> TRANSIENT;
            case "volatile" -> VOLATILE;
            default -> throw new IllegalArgumentException("Invalid modifier: " + text);
        };
    }

    /**
     * Returns the modifier as it appears in the Java language (e.g. "abstract" or "public").
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
