package fillet.operations;

import java.util.HashMap;

public abstract class OperationFactory {
    private static HashMap<Integer, OperationType> opcodeToOperation = new HashMap<>();

    static {
        opcodeToOperation.put(0, OperationType.ADD);
        opcodeToOperation.put(1, OperationType.SUBTRACT);
        opcodeToOperation.put(2, OperationType.MULTIPLY);
        opcodeToOperation.put(3, OperationType.MOVE_IMMEDIATE);
        opcodeToOperation.put(4, OperationType.JUMP_IF_EQUAL);
        opcodeToOperation.put(5, OperationType.AND);
        opcodeToOperation.put(6, OperationType.XOR_IMMEDIATE);
        opcodeToOperation.put(7, OperationType.JUMP);
        opcodeToOperation.put(8, OperationType.LOGICAL_SHIFT_LEFT);
        opcodeToOperation.put(9, OperationType.LOGICAL_SHIFT_RIGHT);
        opcodeToOperation.put(10, OperationType.MOVE_TO_REGISTER);
        opcodeToOperation.put(11, OperationType.MOVE_TO_MEMORY);
        opcodeToOperation.put(15, OperationType.HALT);
    }

    public abstract Operation create(int binaryInstruction, int pc);

    public OperationType getOperationType(int opcode) {
        return opcodeToOperation.get(opcode);
    }
}
