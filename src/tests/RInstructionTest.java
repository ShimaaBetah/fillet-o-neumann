package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import exceptions.InvalidRegisterNumberException;
import operations.registeroperations.RegisterOperation;
import org.junit.Test;

import instructions.RegisterInstruction;
import memory.Registers;
import utils.*;

public class RInstructionTest {
    static String instruction = "00000000100010000110000000000000";
    static int bitMask = Decoder.binaryToInt(instruction);

    @Test
    public void testDecodeOpCode() throws Exception {
        RegisterInstruction r = new RegisterInstruction(bitMask);
        r.decode();
        RegisterOperation operation = (RegisterOperation) r.getOperation();
        assertEquals(0, operation.getOpcode());
    }

    @Test
    public void testDecodeR1() throws Exception {
        RegisterInstruction r = new RegisterInstruction(bitMask);
        r.decode();
        RegisterOperation operation = (RegisterOperation) r.getOperation();
        assertEquals(1, operation.getDestinationRegister());
    }

    @Test
    public void testDecodeR2() throws Exception {
        RegisterInstruction r = new RegisterInstruction(bitMask);
        r.decode();
        RegisterOperation operation = (RegisterOperation) r.getOperation();
        assertEquals(2, operation.getFirstRegister());
    }

    @Test
    public void testDecodeR3() throws Exception {
        RegisterInstruction r = new RegisterInstruction(bitMask);
        r.decode();
        RegisterOperation operation = (RegisterOperation) r.getOperation();
        assertEquals(3, operation.getSecondRegister());
    }

    @Test
    public void testDecodeShiftAmount() throws Exception {
        RegisterInstruction r = new RegisterInstruction(bitMask);
        r.decode();
        RegisterOperation operation = (RegisterOperation) r.getOperation();
        assertEquals(0, operation.getShiftAmount());
    }

    @Test
    public void testAddExecute() throws Exception {
        RegisterInstruction r = new RegisterInstruction(bitMask);
        r.decode();
        RegisterOperation operation = (RegisterOperation) r.getOperation();

        Registers registers = Registers.getInstance();
        registers.setRegister(operation.getFirstRegister(), 1);
        registers.setRegister(operation.getSecondRegister(), 2);

        r.execute();
        assertEquals(3,operation.getResult());
        r.writeBack();
        assertEquals(3, registers.getRegister(operation.getDestinationRegister()));
    }
}
