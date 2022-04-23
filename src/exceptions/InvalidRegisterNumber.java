package exceptions;

public class InvalidRegisterNumber extends Exception {
    public InvalidRegisterNumber(int registerNumber) {
        super(registerNumber + " is an invalid register number.");
    }

    public InvalidRegisterNumber(String message) {
        super(message);
    }
}
