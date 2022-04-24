package tests;

import instructions.RegisterInstruction;
import memory.Registers;
import operations.registeroperations.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static utils.Decoder.binaryToInt;

public class RInstructionTest {

    private static final String TEST_ADD_INSTRUCTION = "00000000100010000110000000000000";
    private static final String TEST_SUBTRACT_INSTRUCTION = "00010000100010000110000000000001";
    private static final String TEST_MULTIPLY_INSTRUCTION = "00100000100010000110000000000010";
    private static final String TEST_AND_INSTRUCTION = "01010000100010000110000000000011";
    private static final String TEST_LOGICAL_SHIFT_LEFT_INSTRUCTION = "10000000100010000000000000000010";
    private static final String TEST_LOGICAL_SHIFT_RIGHT_INSTRUCTION = "10010001000100000000000000000001";

    @Test
    public void testDecodeOpCode() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_ADD_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        assertEquals(0, operation.getOpcode());
    }

    @Test
    public void testDecodeR1() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_ADD_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        assertEquals(1, operation.getDestinationRegister());
    }

    @Test
    public void testDecodeR2() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_ADD_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        assertEquals(2, operation.getFirstRegister());
    }

    @Test
    public void testDecodeR3() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_ADD_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        assertEquals(3, operation.getSecondRegister());
    }

    @Test
    public void testDecodeShiftAmount() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_ADD_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        assertEquals(0, operation.getShiftAmount());
    }

    @Test
    public void testDecodeAddInstruction() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_ADD_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        assertEquals(Add.class, operation.getClass());
    }

    @Test
    public void testAddInstruction() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_ADD_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        Registers registers = Registers.getInstance();
        registers.setRegister(operation.getFirstRegister(), 1);
        registers.setRegister(operation.getSecondRegister(), 2);
        instruction.execute();
        instruction.writeBack();
        assertEquals(3, registers.getRegister(operation.getDestinationRegister()));
    }

    @Test
    public void testAddInstructionWithNegativeSecondOperand() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_ADD_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        Registers registers = Registers.getInstance();
        registers.setRegister(operation.getFirstRegister(), 3);
        registers.setRegister(operation.getSecondRegister(), -8);
        instruction.execute();
        instruction.writeBack();
        assertEquals(-5, registers.getRegister(operation.getDestinationRegister()));
    }

    @Test
    public void testDecodeSubInstruction() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_SUBTRACT_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        assertEquals(Sub.class, operation.getClass());
    }

    @Test
    public void testSubInstruction() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_SUBTRACT_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        Registers registers = Registers.getInstance();
        registers.setRegister(operation.getFirstRegister(), 2);
        registers.setRegister(operation.getSecondRegister(), 1);
        instruction.execute();
        instruction.writeBack();
        assertEquals(1, registers.getRegister(operation.getDestinationRegister()));
    }

    @Test
    public void testSubInstructionWithNegativeResult() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_SUBTRACT_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        Registers registers = Registers.getInstance();
        registers.setRegister(operation.getFirstRegister(), 1);
        registers.setRegister(operation.getSecondRegister(), 2);
        instruction.execute();
        instruction.writeBack();
        assertEquals(-1, registers.getRegister(operation.getDestinationRegister()));
    }

    @Test
    public void testSubInstructionWithNegativeFirstOperand() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_SUBTRACT_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        Registers registers = Registers.getInstance();
        registers.setRegister(operation.getFirstRegister(), -80);
        registers.setRegister(operation.getSecondRegister(), 80);
        instruction.execute();
        instruction.writeBack();
        assertEquals(-160, registers.getRegister(operation.getDestinationRegister()));
    }

    @Test
    public void testSubInstructionWithNegativeOperands() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_SUBTRACT_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        Registers registers = Registers.getInstance();
        registers.setRegister(operation.getFirstRegister(), -80);
        registers.setRegister(operation.getSecondRegister(), -80);
        instruction.execute();
        instruction.writeBack();
        assertEquals(0, registers.getRegister(operation.getDestinationRegister()));
    }

    @Test
    public void testDecodeMultiplyInstruction() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_MULTIPLY_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        assertEquals(Multiply.class, operation.getClass());
    }

    @Test
    public void testMultiplyInstruction() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_MULTIPLY_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        Registers registers = Registers.getInstance();
        registers.setRegister(operation.getFirstRegister(), 89);
        registers.setRegister(operation.getSecondRegister(), 70);
        instruction.execute();
        instruction.writeBack();
        assertEquals(6230, registers.getRegister(operation.getDestinationRegister()));
    }

    @Test
    public void testMultiplyInstructionWithNegativeFirstOperand() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_MULTIPLY_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        Registers registers = Registers.getInstance();
        registers.setRegister(operation.getFirstRegister(), -8);
        registers.setRegister(operation.getSecondRegister(), 1);
        instruction.execute();
        instruction.writeBack();
        assertEquals(-8, registers.getRegister(operation.getDestinationRegister()));
    }

    @Test
    public void testMultiplyInstructionWithZero() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_MULTIPLY_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        Registers registers = Registers.getInstance();
        registers.setRegister(operation.getFirstRegister(), -2);
        registers.setRegister(operation.getSecondRegister(), 0);
        instruction.execute();
        instruction.writeBack();
        assertEquals(0, registers.getRegister(operation.getDestinationRegister()));
    }

    @Test
    public void testDecodeAndInstruction() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_AND_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        assertEquals(And.class, operation.getClass());
    }

    @Test
    public void testAndInstruction() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_AND_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        Registers registers = Registers.getInstance();
        registers.setRegister(operation.getFirstRegister(), 7);
        registers.setRegister(operation.getSecondRegister(), 11);
        instruction.execute();
        instruction.writeBack();
        assertEquals(3, registers.getRegister(operation.getDestinationRegister()));
    }

    @Test
    public void testAndInstructionWithZero() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_AND_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        Registers registers = Registers.getInstance();
        registers.setRegister(operation.getFirstRegister(), 11);
        registers.setRegister(operation.getSecondRegister(), 0);
        instruction.execute();
        instruction.writeBack();
        assertEquals(0, registers.getRegister(operation.getDestinationRegister()));
    }

    @Test
    public void testAndInstructionWithNegativeOperands() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_AND_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        Registers registers = Registers.getInstance();
        registers.setRegister(operation.getFirstRegister(), -11);
        registers.setRegister(operation.getSecondRegister(), -1);
        instruction.execute();
        instruction.writeBack();
        assertEquals(-11, registers.getRegister(operation.getDestinationRegister()));
    }

    @Test
    public void testDecodeLogicalShiftLeftInstruction() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_LOGICAL_SHIFT_LEFT_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        assertEquals(LogicalShiftLeft.class, operation.getClass());
    }

    @Test
    public void testLogicalShiftLeftInstruction() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_LOGICAL_SHIFT_LEFT_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        Registers registers = Registers.getInstance();
        registers.setRegister(operation.getFirstRegister(), 7);
        instruction.execute();
        instruction.writeBack();
        assertEquals(28, registers.getRegister(operation.getDestinationRegister()));
    }

    @Test
    public void testDecodeLogicalShiftRightInstruction() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_LOGICAL_SHIFT_RIGHT_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        assertEquals(LogicalShiftRight.class, operation.getClass());
    }

    @Test
    public void testLogicalShiftRightInstruction() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_LOGICAL_SHIFT_RIGHT_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        Registers registers = Registers.getInstance();
        registers.setRegister(operation.getFirstRegister(), 7);
        instruction.execute();
        instruction.writeBack();
        assertEquals(3, registers.getRegister(operation.getDestinationRegister()));
    }

    @Test
    public void testLogicalShiftRightInstructionWithNegativeOperand() throws Exception {
        RegisterInstruction instruction = new RegisterInstruction(binaryToInt(TEST_LOGICAL_SHIFT_RIGHT_INSTRUCTION));
        instruction.decode();
        RegisterOperation operation = (RegisterOperation) instruction.getOperation();
        Registers registers = Registers.getInstance();
        registers.setRegister(operation.getFirstRegister(), -1);
        instruction.execute();
        instruction.writeBack();
        assertEquals(Integer.MAX_VALUE, registers.getRegister(operation.getDestinationRegister()));
    }
}
