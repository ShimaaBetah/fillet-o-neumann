package tests;

import exceptions.AddressOutOfRange;
import exceptions.InvalidRegisterNumber;
import memory.MainMemory;
import memory.Registers;
import org.junit.Assert;
import org.junit.Test;

public class RegistersTest {
    @Test
    public void testSetRegister1() throws InvalidRegisterNumber {
        Registers registers = Registers.getInstance();
        int registerNum = 1;
        int value = 0x1234;
        registers.setRegister(registerNum, value);
        Assert.assertEquals(value, registers.getRegisters()[registerNum]);
    }

    @Test
    public void testSetRegister2() {
        Registers registers = Registers.getInstance();
        int registerNum = registers.getNumOfRegisters();
        int value = 0x1234;
        Assert.assertThrows(InvalidRegisterNumber.class, () -> registers.setRegister(registerNum, value));
    }

    @Test
    public void testGetRegister1() throws InvalidRegisterNumber {
        Registers registers = Registers.getInstance();
        int registerNum = 1;
        int value = 0x1234;
        registers.getRegisters()[registerNum] = value;
        Assert.assertEquals(value, registers.getRegister(registerNum));
    }

    @Test
    public void testGetRegister2() {
        Registers registers = Registers.getInstance();
        int registerNum = registers.getNumOfRegisters();
        Assert.assertThrows(InvalidRegisterNumber.class, () -> registers.getRegister(registerNum));
    }

    @Test
    public void testSetPC1() throws AddressOutOfRange {
        Registers registers = Registers.getInstance();
        int address = 0;
        registers.setPC(address);
        Assert.assertEquals(address, registers.getPC());
    }


    @Test
    public void testSetPC2() {
        Registers registers = Registers.getInstance();
        int address = MainMemory.getInstance().getInstructionRangeEnd() + 1;
        Assert.assertThrows(AddressOutOfRange.class, () -> registers.setPC(address));
    }

    @Test
    public void testIncrementPC1() throws AddressOutOfRange {
        Registers registers = Registers.getInstance();
        int oldAddress = registers.getPC();
        registers.incrementPC();
        Assert.assertEquals(oldAddress + 1, registers.getPC());
    }

    @Test
    public void testIncrementPC2() throws AddressOutOfRange {
        Registers registers = Registers.getInstance();
        registers.setPC(MainMemory.getInstance().getInstructionRangeEnd());
        Assert.assertThrows(AddressOutOfRange.class, () -> registers.incrementPC());
    }

}
