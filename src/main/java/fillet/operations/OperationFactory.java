package fillet.operations;

import java.util.HashMap;

public abstract class OperationFactory {

    public abstract Operation create(int binaryInstruction, int pc);

    public OperationType getOperationType(int opcode) {
        switch (opcode) {
            case 0:
                return OperationType.ADD;
            case 1:
                return OperationType.SUBTRACT;
            case 2:
                return OperationType.MULTIPLY;
            case 3:
                return OperationType.MOVE_IMMEDIATE;
            case 4:
                return OperationType.JUMP_IF_EQUAL;
            case 5:
                return OperationType.AND;
            case 6:
                return OperationType.XOR_IMMEDIATE;
            case 7:
                return OperationType.JUMP;
            case 8:
                return OperationType.LOGICAL_SHIFT_LEFT;
            case 9:
                return OperationType.LOGICAL_SHIFT_RIGHT;
            case 10:
                return OperationType.MOVE_TO_REGISTER;
            case 11:
                return OperationType.MOVE_TO_MEMORY;
            case 15:
                return OperationType.HALT;
            default:
                throw new IllegalArgumentException("Unknown opcode: " + opcode);
        }
    }
}
