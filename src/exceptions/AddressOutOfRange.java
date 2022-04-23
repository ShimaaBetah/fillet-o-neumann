package exceptions;

public class AddressOutOfRange extends Exception {

    public AddressOutOfRange() {
        super("Address out of range");
    }

    public AddressOutOfRange(String message) {
        super(message);
    }
}
