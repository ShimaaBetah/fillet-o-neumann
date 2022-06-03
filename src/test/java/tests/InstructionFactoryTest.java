package tests;

import fillet.instructions.HaltInstruction;
import fillet.instructions.Instruction;
import fillet.instructions.InstructionFactory;
import fillet.operations.haltoperations.Halt;
import org.junit.Assert;
import org.junit.Test;


public class InstructionFactoryTest {
    int TEST_HALT_INSTRUCTION = -1;

    @Test
    public void testCreate() {
        InstructionFactory instructionFactory = new InstructionFactory();
        Instruction instruction = instructionFactory.create(TEST_HALT_INSTRUCTION);

        Assert.assertEquals( HaltInstruction.class, instruction.getClass());
    }
}
