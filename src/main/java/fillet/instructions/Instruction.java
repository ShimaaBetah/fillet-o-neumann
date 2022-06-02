package fillet.instructions;

import fillet.exceptions.AddressOutOfRangeException;
import fillet.exceptions.InvalidRegisterNumberException;
import fillet.operations.Operation;
import fillet.operations.OperationFactory;


public abstract class Instruction {
    private final int binaryInstruction;
    private Operation operation;
    private OperationFactory operationFactory;
    private int pc;


    protected Instruction(int binaryInstruction) {
        this.binaryInstruction = binaryInstruction;
    }

    public int getPC() {
        return pc;
    }

    public void setPC(int pc) {
        this.pc = pc;
    }

    public void decode() {
        this.operation = this.operationFactory.create(this.binaryInstruction, this.pc);
    }

    public void readRegisters() throws InvalidRegisterNumberException {
        this.operation.readRegisters();
    }

    public void execute() throws Exception {
        operation.execute();
    }

    public void memoryAccess() throws InvalidRegisterNumberException, AddressOutOfRangeException {
        operation.memoryAccess();
    }

    public void writeBack() throws Exception {
        operation.writeBack();
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperationFactory(OperationFactory operationFactory) {
        this.operationFactory = operationFactory;
    }


}
