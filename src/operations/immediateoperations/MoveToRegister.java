package operations.immediateoperations;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;
import memory.MainMemory;
import memory.Registers;

public class MoveToRegister extends ImmediateOperation {
    private int result;

    private int memoryData;

    public MoveToRegister(int opcode, int destinationRegister, int sourceRegister, int immediateValue) {
        super(opcode, destinationRegister, sourceRegister, immediateValue);
    }

    @Override
    public void execute() throws Exception {
        result = getSourceOperand() + getImmediateValue();
    }

    @Override
    public void memoryAccess() throws InvalidRegisterNumberException, AddressOutOfRangeException {
        MainMemory memory = MainMemory.getInstance();
        memoryData = memory.loadWord(result);
    }

    @Override
    public void writeBack() throws Exception {
        Registers registers = Registers.getInstance();
        registers.setRegister(getDestinationRegister(), memoryData);
    }
}
