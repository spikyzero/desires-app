package ua.com.desires.app.exception;

public class NotUniqueEmailException extends RuntimeException {

    public NotUniqueEmailException() {
        super("The email address is already in use.");
    }

    public NotUniqueEmailException(String message) {
        super(message);
    }

    public NotUniqueEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotUniqueEmailException(Throwable cause) {
        super(cause);
    }

}