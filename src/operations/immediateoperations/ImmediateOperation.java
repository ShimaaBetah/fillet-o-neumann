package operations.immediateoperations;

import exceptions.InvalidRegisterNumberException;
import memory.RegisterFile;
import operations.Operation;

public abstract class ImmediateOperation implements Operation {
    private final int opcode;
    private final int destinationRegister;
    private final int sourceRegister;
    private final int immediateValue;

    private int destinationOperand;

    private int sourceOperand;


    protected ImmediateOperation(int opcode, int destinationRegister, int sourceRegister, int immediateValue) {
        this.opcode = opcode;
        this.destinationRegister = destinationRegister;
        this.sourceRegister = sourceRegister;
        this.immediateValue = immediateValue;
    }

    @Override
    public void readRegisters() throws InvalidRegisterNumberException {
        RegisterFile registerFile = RegisterFile.getInstance();
        destinationOperand = registerFile.getRegister(destinationRegister);
        sourceOperand = registerFile.getRegister(sourceRegister);
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

    public int getDestinationOperand() {
        return destinationOperand;
    }

    public int getSourceOperand() {
        return sourceOperand;
    }
}
