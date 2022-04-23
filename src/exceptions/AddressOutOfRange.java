package exceptions;

public class AddressOutOfRange extends Exception {

    public AddressOutOfRange() {
        super();
    }

    public AddressOutOfRange(String message) {
        super(message);
    }
}
