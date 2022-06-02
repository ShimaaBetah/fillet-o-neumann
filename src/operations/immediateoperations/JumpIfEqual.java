package operations.immediateoperations;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;
import memory.RegisterFile;

public class JumpIfEqual extends ImmediateOperation {
    private int currentPC;
    private boolean jumped;
    public JumpIfEqual(int opcode, int destinationRegister, int sourceRegister, int immediateValue, int pc) {
        super(opcode, destinationRegister, sourceRegister, immediateValue);
        this.currentPC = pc;
    }

    @Override
    public void execute() throws InvalidRegisterNumberException, AddressOutOfRangeException {
        RegisterFile registerFile = RegisterFile.getInstance();

        if (getSourceOperand() == getDestinationOperand()) {
            registerFile.setPC(this.currentPC + getImmediateValue());
            jumped = true;
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

    public boolean haveJumped() {
        return jumped;
    }

}
