package exceptions;

public class AddressOutOfRangeException extends Exception {

    public AddressOutOfRangeException() {
        super("Address out of range");
    }

    public AddressOutOfRangeException(String message) {
        super(message);
    }
}
