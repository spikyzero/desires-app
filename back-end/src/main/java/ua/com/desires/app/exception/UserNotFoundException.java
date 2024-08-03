package ua.com.desires.app.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("The user could not be found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

}