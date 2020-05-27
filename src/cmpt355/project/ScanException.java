package cmpt355.project;

/**
 * A {@link SyntaxException} for the case when the input cannot be scanned (lexed/tokenized), for exmaple if an invalid
 * character is present in the input file.
 */
public class ScanException extends SyntaxException {

    public ScanException() {
    }

    public ScanException(String message) {
        super(message);
    }

    public ScanException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScanException(Throwable cause) {
        super(cause);
    }

    public ScanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
