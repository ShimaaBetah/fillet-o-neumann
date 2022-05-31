package operations.registeroperations;

import exceptions.InvalidRegisterNumberException;
import memory.Registers;

public class Add extends RegisterOperation {

    public Add(int opcode, int destinationRegister, int firstRegister, int secondRegister, int shiftAmount) {
        super(opcode, destinationRegister, firstRegister, secondRegister, shiftAmount);
    }

    @Override
    public void execute() throws InvalidRegisterNumberException {
        setResult(getFirstOperand() + getSecondOperand());
    }

}
    

