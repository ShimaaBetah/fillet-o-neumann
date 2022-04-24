package tests;

import instructions.JumpInstruction;
import operations.jumpoperations.JumpOperation;
import org.junit.Assert;
import org.junit.Test;

import static utils.Decoder.binaryToInt;

public class JumpInstructionTest {
    private static final String TEST_JUMP_INSTRUCTION = "01110000000000000000000000000101";

    @Test
    public void testDecodeOpcode() throws Exception {
        JumpInstruction instruction = new JumpInstruction(binaryToInt(TEST_JUMP_INSTRUCTION));
        instruction.decode();
        JumpOperation operation = (JumpOperation) instruction.getOperation();
        Assert.assertEquals(operation.getOpcode(), 7);
    }

    @Test
    public void testDecodeAddress() throws Exception {
        JumpInstruction instruction = new JumpInstruction(binaryToInt(TEST_JUMP_INSTRUCTION));
        instruction.decode();
        JumpOperation operation = (JumpOperation) instruction.getOperation();
        Assert.assertEquals(operation.getAddress(), 5);
    }
}
