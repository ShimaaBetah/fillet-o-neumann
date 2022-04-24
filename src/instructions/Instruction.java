package instructions;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;
import operations.Operation;
import operations.immediateoperations.*;
import operations.registeroperations.*;

import java.util.HashMap;

public abstract class Instruction {
    private int binaryInstruction;
    private Operation operation;

    private static final HashMap<Integer, Class> operationsMap = new HashMap<>(){{
        put(0, Add.class);
        put(1, Sub.class);
        put(2, Multiply.class);
        put(3, MoveImmediate.class);
        put(4, JumpIfEqual.class);
        put(5, And.class);
        put(6, XORImmediate.class);
        put(8, ShiftLeft.class);
        put(9, ShiftRight.class);
        put(10, MoveToRegister.class);
    }};

    public Instruction(int binaryInstruction) {
        this.binaryInstruction = binaryInstruction;
    }

    public abstract void decode() throws Exception;

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

    public HashMap<Integer, Class> getOperationsMap() {
        return operationsMap;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

}
