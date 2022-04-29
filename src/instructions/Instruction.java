package instructions;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;
import operations.Operation;
import operations.registeroperations.*;
import operations.immediateoperations.*;
import operations.jumpoperations.Jump;
import java.util.HashMap;
import java.util.Map;

public abstract class Instruction {
    private final int binaryInstruction;
    private Operation operation;

    private static final HashMap<Integer, Class<? extends Operation>> operationsMap = new HashMap<>();

    static {
        operationsMap.put(0, Add.class);
        operationsMap.put(1, Sub.class);
        operationsMap.put(2, Multiply.class);
        operationsMap.put(3, MoveImmediate.class);
        operationsMap.put(4, JumpIfEqual.class);
        operationsMap.put(5, And.class);
        operationsMap.put(6, XORImmediate.class);
        operationsMap.put(7, Jump.class);
        operationsMap.put(8, LogicalShiftLeft.class);
        operationsMap.put(9, LogicalShiftRight.class);
        operationsMap.put(10, MoveToRegister.class);
        operationsMap.put(11, MoveToMemory.class);
    }

    protected Instruction(int binaryInstruction) {
        this.binaryInstruction = binaryInstruction;
    }

    public abstract void decode();

    public void execute() throws Exception {
        operation.execute();
    }

    public void memoryAccess() throws InvalidRegisterNumberException, AddressOutOfRangeException {
        operation.memoryAccess();
    }

    public void writeBack() throws Exception {
        operation.writeBack();
    }

    public int getBinaryInstruction() {
        return binaryInstruction;
    }

    public Map<Integer, Class<? extends Operation>> getOperationsMap() {
        return operationsMap;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

}
