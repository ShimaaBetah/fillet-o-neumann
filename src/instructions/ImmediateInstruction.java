package instructions;

import operations.immediateoperations.ImmediateOperationFactory;

public class ImmediateInstruction extends Instruction {

    public ImmediateInstruction(int binaryInstruction) {
        super(binaryInstruction);
        setOperationFactory(new ImmediateOperationFactory());
    }

}
