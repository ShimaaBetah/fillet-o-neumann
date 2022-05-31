package operations.immediateoperations;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;
import memory.Registers;

public class JumpIfEqual extends ImmediateOperation {
    private int currentPC;
    public JumpIfEqual(int opcode, int destinationRegister, int sourceRegister, int immediateValue, int pc) {
        super(opcode, destinationRegister, sourceRegister, immediateValue);
        this.currentPC = pc;
    }

    @Override
    public void execute() throws InvalidRegisterNumberException, AddressOutOfRangeException {
        Registers registers = Registers.getInstance();
        if (getSourceOperand() == getDestinationOperand()) {
            registers.setPC(this.currentPC + getImmediateValue());
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
