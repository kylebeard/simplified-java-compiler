package cmpt355.project;

public class InternalCompilerException extends RuntimeException {

    public InternalCompilerException() {
        super();
    }

    public InternalCompilerException(String message) {
        super(message);
    }

    public InternalCompilerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalCompilerException(Throwable cause) {
        super(cause);
    }

    protected InternalCompilerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
