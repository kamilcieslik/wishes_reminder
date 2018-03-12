package pl.escience.zdpp.lab03gr1.database.exception;

public class UniqueViolationException extends Exception {
    public UniqueViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
