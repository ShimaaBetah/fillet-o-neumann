package operations.immediateoperations;

import memory.RegisterFile;

public class XORImmediate extends ImmediateOperation {
    private int result;

    public XORImmediate(int opcode, int destinationRegister, int sourceRegister, int immediateValue) {
        super(opcode, destinationRegister, sourceRegister, immediateValue);
    }

    @Override
    public void execute() throws Exception {
        result = getSourceOperand() ^ getImmediateValue();
    }

    @Override
    public void memoryAccess() {
        // No memory access
    }

    @Override
    public void writeBack() throws Exception {
        RegisterFile registerFile = RegisterFile.getInstance();
        registerFile.setRegister(getDestinationRegister(), result);
    }
}
