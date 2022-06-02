package tests;

import fillet.instructions.Instruction;
import fillet.instructions.InstructionFactory;
import org.junit.Assert;
import org.junit.Test;


public class InstructionFactoryTest {
    int TEST_NULL_INSTRUCTION = -1;

    @Test
    public void testCreate() {
        InstructionFactory instructionFactory = new InstructionFactory();
        Instruction instruction = instructionFactory.create(TEST_NULL_INSTRUCTION);

        Assert.assertNull(instruction);    }
}
