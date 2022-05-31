package operations.jumpoperations;

import operations.Operation;
import operations.OperationFactory;
import operations.OperationType;
import utils.Decoder;

public class JumpOperationFactory extends OperationFactory {
    private static final int OPCODE_RANGE_START = 0;
    private static final int OPCODE_RANGE_END = 3;
    private static final int ADDRESS_RANGE_START = 4;
    private static final int ADDRESS_RANGE_END = 31;

    @Override
    public Operation create(int binaryInstruction) {
        int opcode = Decoder.getIntValueOfBinarySegment(binaryInstruction, OPCODE_RANGE_START, OPCODE_RANGE_END);
        int address = Decoder.getIntValueOfBinarySegment(binaryInstruction, ADDRESS_RANGE_START, ADDRESS_RANGE_END);

        Operation operation = null;

        if (getOperationType(opcode) == OperationType.JUMP) {
            operation = new Jump(opcode, address);
        }

        return operation;
    }
}
