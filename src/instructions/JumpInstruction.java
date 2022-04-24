package instructions;

import operations.Operation;
import operations.jumpoperations.JumpOperation;
import utils.Decoder;

import java.lang.reflect.Constructor;

public class JumpInstruction extends Instruction {
    private static final int OPCODE_RANGE_START = 0;
    private static final int OPCODE_RANGE_END = 3;
    private static final int ADDRESS_RANGE_START = 4;
    private static final int ADDRESS_RANGE_END = 31;

    public JumpInstruction(int binaryInstruction) {
        super(binaryInstruction);
    }

    @Override
    public void decode() throws Exception {
        int opcode = Decoder.getIntValueOfBinarySegment(getBinaryInstruction(), OPCODE_RANGE_START, OPCODE_RANGE_END);
        int address = Decoder.getIntValueOfBinarySegment(getBinaryInstruction(), ADDRESS_RANGE_START, ADDRESS_RANGE_END);
        setOperation(opcode, address);
    }

    private void setOperation(int opcode, int address) throws Exception {
        Class<JumpOperation> operationClass = getOperationsMap().get(opcode);
        Constructor<JumpOperation> operationConstructor = operationClass.getConstructor(int.class, int.class);
        Operation operation = operationConstructor.newInstance(opcode, address);
        setOperation(operation);
    }
}
