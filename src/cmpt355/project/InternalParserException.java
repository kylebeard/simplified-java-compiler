package cmpt355.project;

/**
 * An exception representing an error within the parser (or AST generation) code itself. If this exception is thrown it
 * signifies a bug or unimplemented feature within the parser, not an error in the input.
 */
public class InternalParserException extends RuntimeException {

   public InternalParserException() {
   }

   public InternalParserException(String message) {
      super(message);
   }

   public InternalParserException(String message, Throwable cause) {
      super(message, cause);
   }

   public InternalParserException(Throwable cause) {
      super(cause);
   }

   public InternalParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }

}
