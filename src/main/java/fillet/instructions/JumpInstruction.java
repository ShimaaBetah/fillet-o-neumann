package fillet.instructions;

import fillet.operations.jumpoperations.JumpOperationFactory;

public class JumpInstruction extends Instruction {

    public JumpInstruction(int binaryInstruction) {
        super(binaryInstruction);
        setOperationFactory(new JumpOperationFactory());
    }

}
