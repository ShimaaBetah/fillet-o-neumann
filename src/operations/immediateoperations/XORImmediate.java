package operations.immediateoperations;

import memory.Registers;

public class XORImmediate extends ImmediateOperation {
    private static int result;

    public XORImmediate(int opcode, int destinationRegister, int sourceRegister, int immediateValue) {
        super(opcode, destinationRegister, sourceRegister, immediateValue);
    }

    @Override
    public void execute() throws Exception {
        Registers registers = Registers.getInstance();
        int sourceRegisterValue = registers.getRegister(getSourceRegister());
        result = sourceRegisterValue ^ getImmediateValue();
    }

    @Override
    public void memoryAccess() {
        // No memory access
    }

    @Override
    public void writeBack() throws Exception {
        Registers registers = Registers.getInstance();
        registers.setRegister(getDestinationRegister(), result);
    }
}