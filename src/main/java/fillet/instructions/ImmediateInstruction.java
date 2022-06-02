package fillet.instructions;

import fillet.operations.immediateoperations.ImmediateOperationFactory;

public class ImmediateInstruction extends Instruction {

    public ImmediateInstruction(int binaryInstruction) {
        super(binaryInstruction);
        setOperationFactory(new ImmediateOperationFactory());
    }

}
