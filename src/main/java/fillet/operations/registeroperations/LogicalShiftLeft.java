package fillet.operations.registeroperations;

import fillet.exceptions.InvalidRegisterNumberException;

public class LogicalShiftLeft extends RegisterOperation {

    public LogicalShiftLeft(int opcode, int destinationRegister, int firstRegister, int secondRegister, int shiftAmount) {
        super(opcode, destinationRegister, firstRegister, secondRegister, shiftAmount);
    }

    @Override
    public void execute() throws InvalidRegisterNumberException {
        int shiftAmount = getShiftAmount();
        setResult(getFirstOperand() << shiftAmount);
    }
}
    

