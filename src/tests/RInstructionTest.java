package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import exceptions.InvalidRegisterNumberException;
import org.junit.Test;

import instructions.RegisterInstruction;
import memory.Registers;
import utils.*;

public class RInstructionTest {
    static String instruction = "00000000100010000110000000000000";
    static int bitMask = Decoder.binaryToInt(instruction);

    @Test
    public void testDecodeOpCode() {
        RegisterInstruction r = new RegisterInstruction(bitMask);
        r.decode();
        assertEquals(0, r.getOpcode());
    }

    @Test
    public void testDecodeR1() {
        RegisterInstruction r = new RegisterInstruction(bitMask);
        r.decode();
        assertEquals(1, r.getDestinationRegister());
    }

    @Test
    public void testDecodeR2() {
        RegisterInstruction r = new RegisterInstruction(bitMask);
        r.decode();
        assertEquals(2, r.getFirstRegister());
    }

    @Test
    public void testDecodeR3() {
        RegisterInstruction r = new RegisterInstruction(bitMask);
        r.decode();
        assertEquals(3, r.getSecondRegister());
    }

    @Test
    public void testDecodeShiftAmount() {
        RegisterInstruction r = new RegisterInstruction(bitMask);
        r.decode();
        assertEquals(0, r.getShiftAmount());
    }

    @Test
    public void testAdd() throws InvalidRegisterNumberException {
        RegisterInstruction r = new RegisterInstruction(bitMask);
        r.decode();
        Registers registers = Registers.getInstance();
        registers.setRegister(r.getFirstRegister(), 1);
        registers.setRegister(r.getSecondRegister(), 2);
        r.execute();
        assertEquals(3,r.getResult());
        r.writeBack();
        assertEquals(3, registers.getRegister(r.getDestinationRegister()));
    }
}
