package operations.registeroperations;

import exceptions.InvalidRegisterNumberException;
import memory.Registers;

public class LogicalShiftRight extends RegisterOperation {

    public LogicalShiftRight(int opcode, int destinationRegister, int firstRegister, int secondRegister, int shiftAmount) {
        super(opcode, destinationRegister, firstRegister, secondRegister, shiftAmount);
    }

    @Override
    public void execute() throws InvalidRegisterNumberException {
        int shiftAmount = getShiftAmount();
        setResult(getFirstOperand() >>> shiftAmount);
    }
}
    

