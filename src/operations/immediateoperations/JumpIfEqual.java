package operations.immediateoperations;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;
import memory.Registers;

public class JumpIfEqual extends ImmediateOperation {
    private int pc;
    public JumpIfEqual(int opcode, int destinationRegister, int sourceRegister, int immediateValue,int pc) {
        super(opcode, destinationRegister, sourceRegister, immediateValue);
        this.pc = pc;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    @Override
    public void execute() throws InvalidRegisterNumberException, AddressOutOfRangeException {
        Registers registers = Registers.getInstance();
        if (registers.getRegister(getSourceRegister()) == registers.getRegister(getDestinationRegister())) {
            registers.setPC( getPc() + getImmediateValue());
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
