package tests;

import instructions.JumpInstruction;
import memory.Registers;
import operations.jumpoperations.Jump;
import operations.jumpoperations.JumpOperation;
import org.junit.Assert;
import org.junit.Test;

import static utils.Decoder.binaryToInt;

public class JumpInstructionTest {
    private static final String TEST_JUMP_INSTRUCTION = "01110000000000000000000000000101";
    private static final String OLD_PC_VALUE = "00000000000000000000000000100001";
    private static final String NEW_PC_VALUE = "00000000000000000000000000000101";

    @Test
    public void testDecodeOpcode() throws Exception {
        JumpInstruction instruction = new JumpInstruction(binaryToInt(TEST_JUMP_INSTRUCTION));
        instruction.decode();
        JumpOperation operation = (JumpOperation) instruction.getOperation();
        Assert.assertEquals(7, operation.getOpcode());
    }

    @Test
    public void testDecodeAddress() throws Exception {
        JumpInstruction instruction = new JumpInstruction(binaryToInt(TEST_JUMP_INSTRUCTION));
        instruction.decode();
        JumpOperation operation = (JumpOperation) instruction.getOperation();
        Assert.assertEquals(5, operation.getAddress());
    }

    @Test
    public void testDecodeJumpInstruction() throws Exception {
        JumpInstruction instruction = new JumpInstruction(binaryToInt(TEST_JUMP_INSTRUCTION));
        instruction.decode();
        JumpOperation operation = (JumpOperation) instruction.getOperation();
        Assert.assertEquals(Jump.class, operation.getClass());
    }

    @Test
    public void testJumpInstruction() throws Exception {
        JumpInstruction instruction = new JumpInstruction(binaryToInt(TEST_JUMP_INSTRUCTION));
        instruction.decode();
        Registers registers = Registers.getInstance();
        registers.setPC(binaryToInt(OLD_PC_VALUE));
        instruction.execute();
        instruction.memoryAccess();
        instruction.writeBack();
        Assert.assertEquals(binaryToInt(NEW_PC_VALUE), registers.getPC());
    }
}
