package instructions;

import operations.jumpoperations.JumpOperationFactory;

public class JumpInstruction extends Instruction {

    public JumpInstruction(int binaryInstruction) {
        super(binaryInstruction);
        setOperationFactory(new JumpOperationFactory());
    }

}
