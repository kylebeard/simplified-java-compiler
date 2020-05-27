package cmpt355.project;

/**
 * Subtype of {@link SyntaxException} for syntax errors having to do with misuse of data type (for example, assigning a
 * String value to an int variable or casting to void type).
 */
public class DataTypeException extends ParseException {

    public DataTypeException() {
        super();
    }

    public DataTypeException(String message) {
        super(message);
    }

    public DataTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataTypeException(Throwable cause) {
        super(cause);
    }

    public DataTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
