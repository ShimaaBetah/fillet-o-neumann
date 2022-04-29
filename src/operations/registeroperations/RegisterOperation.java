package operations.registeroperations;

import exceptions.InvalidRegisterNumberException;
import memory.Registers;
import operations.Operation;

public abstract class RegisterOperation implements Operation {
    private final int opcode;
    private final int destinationRegister;
    private final int firstRegister;
    private final int secondRegister;
    private final int shiftAmount;
    private int result;

    protected RegisterOperation(int opcode, int destinationRegister, int firstRegister, int secondRegister, int shiftAmount) {
        this.opcode = opcode;
        this.destinationRegister = destinationRegister;
        this.firstRegister = firstRegister;
        this.secondRegister = secondRegister;
        this.shiftAmount = shiftAmount;
    }

    @Override
    public void memoryAccess() {
        // No memory access
    }

    @Override
    public void writeBack() throws InvalidRegisterNumberException {
        Registers registers = Registers.getInstance();
        registers.setRegister(destinationRegister, result);
    }

    public int getOpcode() {
        return opcode;
    }

    public int getDestinationRegister() {
        return destinationRegister;
    }

    public int getFirstRegister() {
        return firstRegister;
    }

    public int getSecondRegister() {
        return secondRegister;
    }

    public int getShiftAmount() {
        return shiftAmount;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

}
