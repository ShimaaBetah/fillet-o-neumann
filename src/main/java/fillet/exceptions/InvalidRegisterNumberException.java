package fillet.exceptions;

public class InvalidRegisterNumberException extends Exception {
    public InvalidRegisterNumberException(int registerNumber) {
        super(registerNumber + " is an invalid register number.");
    }

    public InvalidRegisterNumberException(String message) {
        super(message);
    }
}
