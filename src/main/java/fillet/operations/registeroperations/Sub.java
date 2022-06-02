package fillet.operations.registeroperations;

import fillet.exceptions.InvalidRegisterNumberException;

public class Sub extends RegisterOperation {

    public Sub(int opcode, int destinationRegister, int firstRegister, int secondRegister, int shiftAmount) {
        super(opcode, destinationRegister, firstRegister, secondRegister, shiftAmount);
    }

    @Override
    public void execute() throws InvalidRegisterNumberException {
        setResult(getFirstOperand() - getSecondOperand());
    }
}
    

