package operations.immediateoperations;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;
import memory.MainMemory;
import memory.Registers;

public class MoveToRegister extends ImmediateOperation {
    private static int result;

    public MoveToRegister(int opcode, int destinationRegister, int sourceRegister, int immediateValue) {
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

        int sourceRegisterValue = registers.getRegister(getSourceRegister());
        int immediateValue = getImmediateValue();
        int address = sourceRegisterValue + immediateValue;

        result = memory.loadWord(address);
    }

    @Override
    public void writeBack() throws Exception {
        Registers registers = Registers.getInstance();
        registers.setRegister(getDestinationRegister(), result);
    }
}
