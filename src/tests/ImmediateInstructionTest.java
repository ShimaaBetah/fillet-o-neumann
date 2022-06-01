package tests;

import instructions.ImmediateInstruction;
import memory.MainMemory;
import memory.RegisterFile;
import operations.immediateoperations.*;
import org.junit.Assert;
import org.junit.Test;

import static utils.Decoder.binaryToInt;

public class ImmediateInstructionTest {
    private static final String TEST_MOVE_IMMEDIATE_INSTRUCTION = "00110000100010000000000000000101";
    private static final String TEST_MOVE_IMMEDIATE_INSTRUCTION_WITH_NEGATIVE_VALUE = "00110000100000111111111111111111";
    private static final String TEST_JUMP_IF_EQUAL_INSTRUCTION_CASE_EQUAL = "01000000100001000000000000000101";
    private static final String TEST_JUMP_IF_EQUAL_INSTRUCTION_CASE_NOT_EQUAL = "01000001100001000000000000000101";
    private static final String TEST_XOR_IMMEDIATE_INSTRUCTION = "01100010100001000000000000000101";
    private static final String TEST_MOVE_TO_REGISTER_INSTRUCTION = "10100010100001000000000000000101";
    private static final String TEST_MOVE_TO_MEMORY_INSTRUCTION = "10110010100001000000000000000101";
    private static final String TEST_MOVE_IMMEDIATE_INSTRUCTION_WITH_EXTREME_NEGATIVE_VALUE = "00110000100000100000000000000000";

    @Test
    public void testDecodeOpcode() {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_MOVE_IMMEDIATE_INSTRUCTION));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();
        Assert.assertEquals(3, operation.getOpcode());
    }

    @Test
    public void testDecodeDestinationRegister() {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_MOVE_IMMEDIATE_INSTRUCTION));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();
        Assert.assertEquals(1, operation.getDestinationRegister());
    }

    @Test
    public void testDecodeSourceRegister() {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_MOVE_IMMEDIATE_INSTRUCTION));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();
        Assert.assertEquals(2, operation.getSourceRegister());
    }

    @Test
    public void testDecodeImmediateValue() {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_MOVE_IMMEDIATE_INSTRUCTION));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();
        Assert.assertEquals(5, operation.getImmediateValue());
    }

    @Test
    public void testDecodeMoveImmediateInstruction() {
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

        RegisterFile registerFile = RegisterFile.getInstance();
        registerFile.setRegister(destinationRegister, 0);

        instruction.execute(); // No execution
        instruction.memoryAccess(); // No memory access
        instruction.writeBack();

        Assert.assertEquals(5, registerFile.getRegister(destinationRegister));
    }

    @Test 
    public void testDecodeMoveImmediateInstructionWithNegativeValue() {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_MOVE_IMMEDIATE_INSTRUCTION_WITH_NEGATIVE_VALUE));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();
        Assert.assertEquals(MoveImmediate.class, operation.getClass());
        Assert.assertEquals(-1, operation.getImmediateValue());
    }

    @Test
    public void testMoveImmediateInstructionWitExtremeNegativeValue() throws Exception {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_MOVE_IMMEDIATE_INSTRUCTION_WITH_EXTREME_NEGATIVE_VALUE));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();
        int destinationRegister = operation.getDestinationRegister();

        RegisterFile registerFile = RegisterFile.getInstance();
        registerFile.setRegister(destinationRegister, 0);

        instruction.execute(); // No execution
        instruction.memoryAccess(); // No memory access
        instruction.writeBack();

        Assert.assertEquals(-131072, registerFile.getRegister(destinationRegister));
    }

    @Test
    public void testDecodeJumpIfEqualInstruction() {
        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_JUMP_IF_EQUAL_INSTRUCTION_CASE_EQUAL));
        instruction.decode();
        ImmediateOperation operation = (ImmediateOperation) instruction.getOperation();
        Assert.assertEquals(JumpIfEqual.class, operation.getClass());
    }

    @Test
    public void testJumpIfEqualInstructionCaseEqual() throws Exception {
        RegisterFile registerFile = RegisterFile.getInstance();
        registerFile.setPC(0);

        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_JUMP_IF_EQUAL_INSTRUCTION_CASE_EQUAL));
        instruction.decode();
        instruction.execute();
        instruction.memoryAccess();
        instruction.writeBack();

        Assert.assertEquals(5, registerFile.getPC());
    }

    @Test
    public void testJumpIfEqualInstructionCaseNotEqual() throws Exception {
        RegisterFile registerFile = RegisterFile.getInstance();
        registerFile.setPC(0);

        ImmediateInstruction instruction = new ImmediateInstruction(binaryToInt(TEST_JUMP_IF_EQUAL_INSTRUCTION_CASE_NOT_EQUAL));
        instruction.decode();
        instruction.readRegisters();
        instruction.execute();
        instruction.memoryAccess();
        instruction.writeBack();

        Assert.assertEquals(0, registerFile.getPC());
    }

    @Test
    public void testDecodeXORImmediateInstruction() {
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

        RegisterFile registerFile = RegisterFile.getInstance();

        int destinationRegister = operation.getDestinationRegister();
        int sourceRegister = operation.getSourceRegister();
        int immediateValue = operation.getImmediateValue();

        registerFile.setRegister(sourceRegister, 1);
        registerFile.setRegister(destinationRegister, 0);

        int xorResult = 1 ^ immediateValue;
        instruction.readRegisters();
        instruction.execute();
        instruction.memoryAccess(); // No memory access
        instruction.writeBack();

        Assert.assertEquals(xorResult, registerFile.getRegister(destinationRegister));
    }

    @Test
    public void testDecodeMoveToRegisterInstruction() {
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

        RegisterFile registerFile = RegisterFile.getInstance();
        MainMemory memory = MainMemory.getInstance();

        registerFile.setRegister(operation.getSourceRegister(), 0);

        int destinationRegister = operation.getDestinationRegister();
        int sourceRegisterValue = registerFile.getRegister(operation.getSourceRegister());
        int immediateValue = operation.getImmediateValue();
        int address = sourceRegisterValue + immediateValue;

        memory.storeWord(address, 5);
        instruction.execute();
        instruction.memoryAccess();
        instruction.writeBack();

        Assert.assertEquals(5, registerFile.getRegister(destinationRegister));
    }

    @Test
    public void testDecodeMoveToMemoryInstruction() {
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

        RegisterFile registerFile = RegisterFile.getInstance();
        MainMemory memory = MainMemory.getInstance();

        registerFile.setRegister(operation.getSourceRegister(), 1500);

        int destinationRegister = operation.getDestinationRegister();
        int sourceRegisterValue = registerFile.getRegister(operation.getSourceRegister());
        int immediateValue = operation.getImmediateValue();
        int address = sourceRegisterValue + immediateValue;

        System.out.println(address);

        memory.storeWord(address, 0);
        registerFile.setRegister(destinationRegister, 5);

        instruction.readRegisters();
        instruction.execute();
        instruction.memoryAccess();
        instruction.writeBack();

        Assert.assertEquals(5, memory.loadWord(address));
    }
}
