package fillet.operations.haltoperations;

import fillet.operations.Operation;
import fillet.operations.OperationFactory;
import fillet.operations.OperationType;
import fillet.utils.Decoder;

public class HaltOperationFactory extends OperationFactory {

    private static final int OPCODE_RANGE_START = 0;
    private static final int OPCODE_RANGE_END = 3;

    @Override
    public Operation create(int binaryInstruction, int pc) {
        int opcode = Decoder.getIntValueOfBinarySegment(binaryInstruction, OPCODE_RANGE_START, OPCODE_RANGE_END);
        Operation operation = null;
        if (getOperationType(opcode) == OperationType.HALT) {
            operation = new Halt(opcode);
        }
        return operation;
    }
}
