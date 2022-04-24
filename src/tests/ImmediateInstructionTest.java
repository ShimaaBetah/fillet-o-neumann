package tests;

import instructions.ImmediateInstruction;
import memory.MainMemory;
import memory.Registers;
import operations.immediateoperations.*;
import org.junit.Assert;
import org.junit.Test;

import static utils.Decoder.binaryToInt;

public class ImmediateInstructionTest {
    private static final String TEST_MOVE_IMMEDIATE_INSTRUCTION = "00110000100010000000000000000101";
    private static final String TEST_JUMP_IF_EQUAL_INSTRUCTION_CASE_EQUAL = "01000000100001000000000000000101";
    private static final String TEST_JUMP_IF_EQUAL_INSTRUCTION_CASE_NOT_EQUAL = "01000001100001000000000000000101";
    private static final String TEST_XOR_IMMEDIATE_INSTRUCTION = "01100010100001000000000000000101";
    private static final String TEST_MOVE_TO_REGISTER_INSTRUCTION = "10100010100001000000000000000101";
    private static final String TEST_MOVE_TO_MEMORY_INSTRUCTION = "10110010100001000000000000000101";

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
    public void testJumpIfEqualInstructionCaseEqual() throws Exception {
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
    public void testJumpIfEqualInstructionCaseNotEqual() throws Exception {
        Registers registers = Registers.getInstance();
        registers.setPC(0);

        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_JUMP_IF_EQUAL_INSTRUCTION_CASE_NOT_EQUAL));
        instruction.decode();
        instruction.execute();
        instruction.memoryAccess();
        instruction.writeBack();

        Assert.assertEquals(registers.getPC(), 0);
    }

    @Test
    public void testDecodeXORImmediateInstruction() throws Exception {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_XOR_IMMEDIATE_INSTRUCTION));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();
        Assert.assertEquals(XORImmediate.class, operation.getClass());
    }

    @Test
    public void setTestXorImmediateInstruction() throws Exception {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_XOR_IMMEDIATE_INSTRUCTION));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();

        Registers registers = Registers.getInstance();

        int destinationRegister = operation.getDestinationRegister();
        int sourceRegister = operation.getSourceRegister();
        int immediateValue = operation.getImmediateValue();

        registers.setRegister(sourceRegister, 1);
        registers.setRegister(destinationRegister, 0);

        int xorResult = 1 ^ immediateValue;

        instruction.execute();
        instruction.memoryAccess(); // No memory access
        instruction.writeBack();

        Assert.assertEquals(registers.getRegister(destinationRegister), xorResult);
    }

    @Test
    public void testDecodeMoveToRegisterInstruction() throws Exception {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_MOVE_TO_REGISTER_INSTRUCTION));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();
        Assert.assertEquals(MoveToRegister.class, operation.getClass());
    }

    @Test
    public void testMoveToRegisterInstruction() throws Exception{
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_MOVE_TO_REGISTER_INSTRUCTION));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();

        Registers registers = Registers.getInstance();
        MainMemory memory = MainMemory.getInstance();

        int destinationRegister = operation.getDestinationRegister();
        int sourceRegisterValue = registers.getRegister(operation.getSourceRegister());
        int immediateValue = operation.getImmediateValue();
        int address = sourceRegisterValue + immediateValue;

        memory.storeWord(address, 5);
        instruction.execute();
        instruction.memoryAccess();
        instruction.writeBack();

        Assert.assertEquals(registers.getRegister(destinationRegister), 5);
    }

    @Test
    public void testDecodeMoveToMemoryInstruction() throws Exception {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_MOVE_TO_MEMORY_INSTRUCTION));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();
        Assert.assertEquals(MoveToMemory.class, operation.getClass());
    }

    @Test
    public void testMoveToMemoryInstruction() throws Exception{
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_MOVE_TO_MEMORY_INSTRUCTION));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();

        Registers registers = Registers.getInstance();
        MainMemory memory = MainMemory.getInstance();

        int destinationRegister = operation.getDestinationRegister();
        int sourceRegisterValue = registers.getRegister(operation.getSourceRegister());
        int immediateValue = operation.getImmediateValue();
        int address = sourceRegisterValue + immediateValue;

        memory.storeWord(address, 0);
        registers.setRegister(destinationRegister, 5);

        instruction.execute();
        instruction.memoryAccess();
        instruction.writeBack();

        Assert.assertEquals(memory.loadWord(address), 5);
    }
}
