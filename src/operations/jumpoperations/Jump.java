package operations.jumpoperations;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;
import memory.Registers;

public class Jump extends JumpOperation {
    public Jump(int opcode, int address) {
        super(opcode, address);
    }

    @Override
    public void execute() throws Exception {
        Registers registers = Registers.getInstance();
        int pc = registers.getPC();
        int address = getAddress();
        int newPC = (pc & 0xF0000000) | address;
        registers.setPC(newPC);
    }

    @Override
    public void memoryAccess() throws InvalidRegisterNumberException, AddressOutOfRangeException {

    }

    @Override
    public void writeBack() throws Exception {

    }
}
