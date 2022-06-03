package fillet.operations.haltoperations;

import fillet.operations.Operation;

public abstract class HaltOperation implements Operation {

    private final int opcode;

    protected HaltOperation(int opcode) {
        this.opcode = opcode;
    }

    @Override
    public void readRegisters() {
        // No read registers
    }

    public int getOpcode() {
        return opcode;
    }
}
