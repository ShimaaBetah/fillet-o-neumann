package operations.registeroperations;

import exceptions.InvalidRegisterNumberException;
import memory.Registers;

public class ShiftRight extends RegisterOperation {

    public ShiftRight(int opcode, int destinationRegister, int firstRegister, int secondRegister, int shiftAmount) {
        super(opcode, destinationRegister, firstRegister, secondRegister, shiftAmount);
    }

    @Override
    public void execute() throws InvalidRegisterNumberException {
        Registers registers = Registers.getInstance();
        int firstOperand = registers.getRegister(getFirstRegister());
        int shiftAmount = getShiftAmount();
        setResult(firstOperand >> shiftAmount);
    }
}
    

