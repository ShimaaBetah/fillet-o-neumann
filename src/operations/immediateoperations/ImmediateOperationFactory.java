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
    public Operation create(int binaryInstruction,int pc) {
        int opcode = Decoder.getIntValueOfBinarySegment(binaryInstruction, OPCODE_RANGE_START, OPCODE_RANGE_END);
        int destinationRegister = Decoder.getIntValueOfBinarySegment(binaryInstruction, DESTINATION_REGISTER_RANGE_START, DESTINATION_REGISTER_RANGE_END);
        int sourceRegister = Decoder.getIntValueOfBinarySegment(binaryInstruction, FIRST_OPERAND_RANGE_START, FIRST_OPERAND_RANGE_END);
        int immediateValue = Decoder.getIntValueOfBinarySegment(binaryInstruction, IMMEDIATE_VALUE_RANGE_START, IMMEDIATE_VALUE_RANGE_END, true);

        Operation operation = null;

        if (getOperationType(opcode) == OperationType.MOVE_IMMEDIATE) {
            operation = new MoveImmediate(opcode, destinationRegister, sourceRegister, immediateValue);
        } else if (getOperationType(opcode) == OperationType.JUMP_IF_EQUAL) {
            operation = new JumpIfEqual(opcode, destinationRegister, sourceRegister, immediateValue,pc);
        } else if (getOperationType(opcode) == OperationType.XOR_IMMEDIATE) {
            operation = new XORImmediate(opcode, destinationRegister, sourceRegister, immediateValue);
        } else if (getOperationType(opcode) == OperationType.MOVE_TO_REGISTER) {
            operation = new MoveToRegister(opcode, destinationRegister, sourceRegister, immediateValue);
        } else if (getOperationType(opcode) == OperationType.MOVE_TO_MEMORY) {
            operation = new MoveToMemory(opcode, destinationRegister, sourceRegister, immediateValue);
        }
        return operation;
    }
}
