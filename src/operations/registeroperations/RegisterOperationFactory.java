package operations.registeroperations;

import operations.Operation;
import operations.OperationFactory;
import operations.OperationType;
import utils.Decoder;

public class RegisterOperationFactory extends OperationFactory {
    private static final int OPCODE_RANGE_START = 0;
    private static final int OPCODE_RANGE_END = 3;
    private static final int DESTINATION_REGISTER_RANGE_START = 4;
    private static final int DESTINATION_REGISTER_RANGE_END = 8;
    private static final int FIRST_REGISTER_RANGE_START = 9;
    private static final int FIRST_REGISTER_RANGE_END = 13;
    private static final int SECOND_REGISTER_RANGE_START = 14;
    private static final int SECOND_REGISTER_RANGE_END = 18;
    private static final int SHIFT_AMOUNT_RANGE_START = 19;
    private static final int SHIFT_AMOUNT_RANGE_END = 31;

    @Override
    public Operation create(int binaryInstruction,int pc) {
        int opcode = Decoder.getIntValueOfBinarySegment(binaryInstruction, OPCODE_RANGE_START, OPCODE_RANGE_END);
        int destinationRegister = Decoder.getIntValueOfBinarySegment(binaryInstruction, DESTINATION_REGISTER_RANGE_START, DESTINATION_REGISTER_RANGE_END);
        int firstRegister = Decoder.getIntValueOfBinarySegment(binaryInstruction, FIRST_REGISTER_RANGE_START, FIRST_REGISTER_RANGE_END);
        int secondRegister = Decoder.getIntValueOfBinarySegment(binaryInstruction, SECOND_REGISTER_RANGE_START, SECOND_REGISTER_RANGE_END);
        int shiftAmount = Decoder.getIntValueOfBinarySegment(binaryInstruction, SHIFT_AMOUNT_RANGE_START, SHIFT_AMOUNT_RANGE_END);

        Operation operation = null;

        if (getOperationType(opcode) == OperationType.ADD) {
            operation = new Add(opcode, destinationRegister, firstRegister, secondRegister, shiftAmount);
        } else if (getOperationType(opcode) == OperationType.SUBTRACT) {
            operation = new Sub(opcode, destinationRegister, firstRegister, secondRegister, shiftAmount);
        } else if (getOperationType(opcode) == OperationType.MULTIPLY) {
            operation = new Multiply(opcode, destinationRegister, firstRegister, secondRegister, shiftAmount);
        } else if (getOperationType(opcode) == OperationType.AND) {
            operation = new And(opcode, destinationRegister, firstRegister, secondRegister, shiftAmount);
        } else if (getOperationType(opcode) == OperationType.LOGICAL_SHIFT_LEFT) {
            operation = new LogicalShiftLeft(opcode, destinationRegister, firstRegister, secondRegister, shiftAmount);
        } else if (getOperationType(opcode) == OperationType.LOGICAL_SHIFT_RIGHT) {
            operation = new LogicalShiftRight(opcode, destinationRegister, firstRegister, secondRegister, shiftAmount);
        }

        return operation;
    }
}
