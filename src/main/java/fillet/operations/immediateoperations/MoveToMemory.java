package fillet.operations.immediateoperations;

import fillet.exceptions.AddressOutOfRangeException;
import fillet.exceptions.InvalidRegisterNumberException;
import fillet.memory.MainMemory;

public class MoveToMemory extends ImmediateOperation {
    private int address;

    public MoveToMemory(int opcode, int destinationRegister, int sourceRegister, int immediateValue) {
        super(opcode, destinationRegister, sourceRegister, immediateValue);
    }

    @Override
    public void execute() throws Exception {
        address = getSourceOperand() + getImmediateValue();
    }

    @Override
    public void memoryAccess() throws InvalidRegisterNumberException, AddressOutOfRangeException {
        MainMemory memory = MainMemory.getInstance();
        memory.storeData(address, getDestinationOperand());
    }

    @Override
    public void writeBack() throws Exception {
        // No write back
    }
}
