package operations.jumpoperations;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;
import memory.RegisterFile;

public class Jump extends JumpOperation {
    private int currentPC;

    public Jump(int opcode, int address,int pc) {
        super(opcode, address);
        this.currentPC = pc;
    }

    @Override
    public void execute() throws Exception {
        RegisterFile registerFile = RegisterFile.getInstance();
        int address = getAddress();
        int newPC = (currentPC & 0xF0000000) | address;
        registerFile.setPC(newPC);
    }

    @Override
    public void memoryAccess() throws InvalidRegisterNumberException, AddressOutOfRangeException {
        // No memory access
    }

    @Override
    public void writeBack() throws Exception {
        // No write back
    }
}
