package operations.immediateoperations;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;
import memory.MainMemory;
import memory.Registers;

public class MoveToMemory extends ImmediateOperation {
    public MoveToMemory(int opcode, int destinationRegister, int sourceRegister, int immediateValue) {
        super(opcode, destinationRegister, sourceRegister, immediateValue);
    }

    @Override
    public void execute() throws Exception {
        // No execution
    }

    @Override
    public void memoryAccess() throws InvalidRegisterNumberException, AddressOutOfRangeException {
        Registers registers = Registers.getInstance();
        MainMemory memory = MainMemory.getInstance();

        int destinationRegisterValue = registers.getRegister(getDestinationRegister());
        int sourceRegisterValue = registers.getRegister(getSourceRegister());
        int immediateValue = getImmediateValue();
        int address = sourceRegisterValue + immediateValue;

        memory.storeWord(address, destinationRegisterValue);
    }

    @Override
    public void writeBack() throws Exception {
        // No write back
    }
}
