package instructions;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;
import operations.Operation;
import operations.OperationFactory;


public abstract class Instruction {
    private final int binaryInstruction;
    private Operation operation;
    private OperationFactory operationFactory;


    protected Instruction(int binaryInstruction) {
        this.binaryInstruction = binaryInstruction;
    }

    public void decode() {
        this.operation = this.operationFactory.create(this.binaryInstruction);
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
