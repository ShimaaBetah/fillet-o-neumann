package fillet.instructions;

import fillet.operations.haltoperations.HaltOperationFactory;

public class HaltInstruction extends Instruction {

    public HaltInstruction(int binaryInstruction) {
        super(binaryInstruction);
        setOperationFactory(new HaltOperationFactory());
    }

}
