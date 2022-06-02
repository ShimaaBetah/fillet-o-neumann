package fillet.operations.jumpoperations;

import fillet.exceptions.InvalidRegisterNumberException;
import fillet.operations.Operation;

public abstract class JumpOperation implements Operation {
    private final int opcode;
    private final int address;

    protected JumpOperation(int opcode, int address) {
        this.opcode = opcode;
        this.address = address;
    }


    @Override
    public void readRegisters() throws InvalidRegisterNumberException {
        // No read registers
    }

    public int getOpcode() {
        return opcode;
    }

    public int getAddress() {
        return address;
    }

}
