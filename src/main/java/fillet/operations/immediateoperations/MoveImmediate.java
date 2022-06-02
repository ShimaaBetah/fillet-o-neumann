package fillet.operations.immediateoperations;

import fillet.exceptions.InvalidRegisterNumberException;
import fillet.memory.RegisterFile;

public class MoveImmediate extends ImmediateOperation {


    public MoveImmediate(int opcode, int destinationRegister, int sourceRegister, int immediateValue) {
        super(opcode, destinationRegister, sourceRegister, immediateValue);
    }

    @Override
    public void execute() throws InvalidRegisterNumberException {
        // No execution execute
    }

    @Override
    public void memoryAccess() {
        // No memory access
    }

    @Override
    public void writeBack() throws InvalidRegisterNumberException {
        RegisterFile registerFile = RegisterFile.getInstance();
        registerFile.setRegister(getDestinationRegister(), getImmediateValue());
    }
}
