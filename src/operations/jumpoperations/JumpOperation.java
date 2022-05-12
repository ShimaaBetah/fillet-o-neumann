package operations.jumpoperations;

import operations.Operation;

public abstract class JumpOperation implements Operation {
    private final int opcode;
    private final int address;

    protected JumpOperation(int opcode, int address) {
        this.opcode = opcode;
        this.address = address;
    }

    public int getOpcode() {
        return opcode;
    }

    public int getAddress() {
        return address;
    }

}
