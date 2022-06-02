package operations.immediateoperations;

import operations.Operation;
import operations.OperationFactory;
import operations.OperationType;
import utils.Decoder;

public class ImmediateOperationFactory extends OperationFactory {
    private static final int OPCODE_RANGE_START = 0;
    private static final int OPCODE_RANGE_END = 3;
    private static final int DESTINATION_REGISTER_RANGE_START = 4;
    private static final int DESTINATION_REGISTER_RANGE_END = 8;
    private static final int FIRST_OPERAND_RANGE_START = 9;
    private static final int FIRST_OPERAND_RANGE_END = 13;
    private static final int IMMEDIATE_VALUE_RANGE_START = 14;
    private static final int IMMEDIATE_VALUE_RANGE_END = 31;

    @Override
    public Operation create(int binaryInstruction, int pc) {
        int opcode = Decoder.getIntValueOfBinarySegment(binaryInstruction, OPCODE_RANGE_START, OPCODE_RANGE_END);
        int destinationRegister = Decoder.getIntValueOfBinarySegment(binaryInstruction,
                DESTINATION_REGISTER_RANGE_START, DESTINATION_REGISTER_RANGE_END);
        int sourceRegister = Decoder.getIntValueOfBinarySegment(binaryInstruction, FIRST_OPERAND_RANGE_START,
                FIRST_OPERAND_RANGE_END);
        int immediateValue = Decoder.getIntValueOfBinarySegment(binaryInstruction, IMMEDIATE_VALUE_RANGE_START,
                IMMEDIATE_VALUE_RANGE_END, true);

        return getOperation(pc, opcode, destinationRegister, sourceRegister, immediateValue);
    }

    private Operation getOperation(int pc, int opcode, int destinationRegister, int sourceRegister,
            int immediateValue) {

        if (getOperationType(opcode) == OperationType.MOVE_IMMEDIATE) {
            return new MoveImmediate(opcode, destinationRegister, sourceRegister, immediateValue);
        }
        if (getOperationType(opcode) == OperationType.JUMP_IF_EQUAL) {
            return new JumpIfEqual(opcode, destinationRegister, sourceRegister, immediateValue, pc);
        }
        if (getOperationType(opcode) == OperationType.XOR_IMMEDIATE) {
            return new XORImmediate(opcode, destinationRegister, sourceRegister, immediateValue);
        }
        if (getOperationType(opcode) == OperationType.MOVE_TO_REGISTER) {
            return new MoveToRegister(opcode, destinationRegister, sourceRegister, immediateValue);
        }
        if (getOperationType(opcode) == OperationType.MOVE_TO_MEMORY) {
            return new MoveToMemory(opcode, destinationRegister, sourceRegister, immediateValue);
        }
        return null;
    }
}
