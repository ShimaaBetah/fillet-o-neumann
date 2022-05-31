package exceptions;

public class InvalidRegisterException extends Exception {
    public InvalidRegisterException() {
        super();
    }

    public InvalidRegisterException(String message) {
        super(message);
    }
}
