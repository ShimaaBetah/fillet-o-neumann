package exceptions;

public class InvalidInstructionException extends Exception {
    public InvalidInstructionException() {
        super();
    }

    public InvalidInstructionException(String message) {
        super(message);
    }
}
