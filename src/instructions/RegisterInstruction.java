package instructions;

import operations.Operation;
import operations.registeroperations.RegisterOperationFactory;

public class RegisterInstruction extends Instruction {

    public RegisterInstruction(int binaryInstruction) {
        super(binaryInstruction);
        setOperationFactory(new RegisterOperationFactory());

    }

    @Override
    public void decode() {
        Operation operation = getOperationFactory().create(getBinaryInstruction());
        setOperation(operation);
    }

}
