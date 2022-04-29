package operations.immediateoperations;

import operations.Operation;

public abstract class ImmediateOperation implements Operation {
    private final int opcode;
    private final int destinationRegister;
    private final int sourceRegister;
    private final int immediateValue;


    protected ImmediateOperation(int opcode, int destinationRegister, int sourceRegister, int immediateValue) {
        this.opcode = opcode;
        this.destinationRegister = destinationRegister;
        this.sourceRegister = sourceRegister;
        this.immediateValue = immediateValue;
    }

    public int getOpcode() {
        return opcode;
    }

    public int getDestinationRegister() {
        return destinationRegister;
    }

    public int getSourceRegister() {
        return sourceRegister;
    }

    public int getImmediateValue() {
        return immediateValue;
    }

}
