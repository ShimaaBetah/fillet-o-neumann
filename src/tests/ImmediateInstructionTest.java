package tests;

import instructions.ImmediateInstruction;
import org.junit.Assert;
import org.junit.Test;

import static utils.Decoder.binaryToInt;

public class ImmediateInstructionTest {
    private static final String TEST_INSTRUCTION = "01000000100010000000000000000101";
    @Test
    public void testDecodeOpcode() {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_INSTRUCTION));
        instruction.decode();
        Assert.assertEquals(instruction.getOpcode(), 4);
    }

    @Test
    public void testDecodeDestinationRegister() {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_INSTRUCTION));
        instruction.decode();
        Assert.assertEquals(instruction.getDestinationRegister(), 1);
    }

    @Test
    public void testDecodeSourceRegister() {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_INSTRUCTION));
        instruction.decode();
        Assert.assertEquals(instruction.getSourceRegister(), 2);
    }

    @Test
    public void testDecodeImmediateValue() {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_INSTRUCTION));
        instruction.decode();
        Assert.assertEquals(instruction.getImmediateValue(), 5);
    }
}
