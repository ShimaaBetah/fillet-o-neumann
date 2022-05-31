package instructions;

import operations.registeroperations.RegisterOperationFactory;

public class RegisterInstruction extends Instruction {

    public RegisterInstruction(int binaryInstruction) {
        super(binaryInstruction);
        setOperationFactory(new RegisterOperationFactory());

    }

}
