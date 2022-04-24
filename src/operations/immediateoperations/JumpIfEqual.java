package operations.immediateoperations;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;
import memory.Registers;

public class JumpIfEqual extends ImmediateOperation {
    public JumpIfEqual(int opcode, int destinationRegister, int sourceRegister, int immediateValue) {
        super(opcode, destinationRegister, sourceRegister, immediateValue);
    }

    @Override
    public void execute() throws InvalidRegisterNumberException, AddressOutOfRangeException {
        Registers registers = Registers.getInstance();
        if (registers.getRegister(getSourceRegister()) == registers.getRegister(getDestinationRegister())) {
            registers.setPC(registers.getPC() + getImmediateValue());
        }
    }

    @Override
    public void memoryAccess() {
        // No memory access
    }

    @Override
    public void writeBack() throws InvalidRegisterNumberException {
        // No write back
    }
}
