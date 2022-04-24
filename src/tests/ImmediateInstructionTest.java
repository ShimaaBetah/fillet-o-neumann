package tests;

import instructions.ImmediateInstruction;
import memory.Registers;
import operations.immediateoperations.*;
import org.junit.Assert;
import org.junit.Test;

import static utils.Decoder.binaryToInt;

public class ImmediateInstructionTest {
    private static final String TEST_MOVE_IMMEDIATE_INSTRUCTION = "00110000100010000000000000000101";
    private static final String TEST_JUMP_IF_EQUAL_INSTRUCTION_CASE_EQUAL = "01000000100001000000000000000101";
    private static final String TEST_JUMP_IF_EQUAL_INSTRUCTION_CASE_NOT_EQUAL = "01000001100001000000000000000101";

    @Test
    public void testDecodeOpcode() throws Exception {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_MOVE_IMMEDIATE_INSTRUCTION));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();
        Assert.assertEquals(operation.getOpcode(), 3);
    }

    @Test
    public void testDecodeDestinationRegister() throws Exception {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_MOVE_IMMEDIATE_INSTRUCTION));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();
        Assert.assertEquals(operation.getDestinationRegister(), 1);
    }

    @Test
    public void testDecodeSourceRegister() throws Exception {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_MOVE_IMMEDIATE_INSTRUCTION));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();
        Assert.assertEquals(operation.getSourceRegister(), 2);
    }

    @Test
    public void testDecodeImmediateValue() throws Exception {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_MOVE_IMMEDIATE_INSTRUCTION));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();
        Assert.assertEquals(operation.getImmediateValue(), 5);
    }

    @Test
    public void testDecodeMoveImmediateInstruction() throws Exception {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_MOVE_IMMEDIATE_INSTRUCTION));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();
        Assert.assertEquals(MoveImmediate.class, operation.getClass());
    }

    @Test
    public void testMoveImmediateInstruction() throws Exception {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_MOVE_IMMEDIATE_INSTRUCTION));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();
        int destinationRegister = operation.getDestinationRegister();

        Registers registers = Registers.getInstance();
        registers.setRegister(destinationRegister, 0);

        instruction.execute(); // No execution
        instruction.memoryAccess(); // No memory access
        instruction.writeBack();

        Assert.assertEquals(registers.getRegister(destinationRegister), 5);
    }

    @Test
    public void testDecodeJumpIfEqualInstruction() throws Exception {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_JUMP_IF_EQUAL_INSTRUCTION_CASE_EQUAL));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();
        Assert.assertEquals(JumpIfEqual.class, operation.getClass());
    }

    @Test
    public void setTestJumpIfEqualInstructionCaseEqual() throws Exception {
        Registers registers = Registers.getInstance();
        registers.setPC(0);

        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_JUMP_IF_EQUAL_INSTRUCTION_CASE_EQUAL));
        instruction.decode();
        instruction.execute();
        instruction.memoryAccess();
        instruction.writeBack();

        Assert.assertEquals(registers.getPC(), 5);
    }

    @Test
    public void setTestJumpIfEqualInstructionCaseNotEqual() throws Exception {
        Registers registers = Registers.getInstance();
        registers.setPC(0);

        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_JUMP_IF_EQUAL_INSTRUCTION_CASE_NOT_EQUAL));
        instruction.decode();
        instruction.execute();
        instruction.memoryAccess();
        instruction.writeBack();

        Assert.assertEquals(registers.getPC(), 0);
    }
}
