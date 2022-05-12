package operations.registeroperations;

import exceptions.InvalidRegisterNumberException;
import memory.Registers;

public class And extends RegisterOperation {

    public And(int opcode, int destinationRegister, int firstRegister, int secondRegister, int shiftAmount) {
        super(opcode, destinationRegister, firstRegister, secondRegister, shiftAmount);
    }

    @Override
    public void execute() throws InvalidRegisterNumberException {
        Registers registers = Registers.getInstance();
        int firstOperand = registers.getRegister(getFirstRegister());
        int secondOperand = registers.getRegister(getSecondRegister());
        setResult(firstOperand & secondOperand);
    }
}
    

