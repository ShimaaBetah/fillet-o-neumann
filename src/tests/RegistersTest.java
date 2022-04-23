package tests;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;
import memory.MainMemory;
import memory.Registers;
import org.junit.Assert;
import org.junit.Test;

public class RegistersTest {
    private static final int TEST_REGISTER_VALUE = 0x1234;
    private static final int VALID_TEST_REGISTER_NUMBER = 1;
    private static final int INVALID_TEST_REGISTER_NUMBER = -1;
    private static final int VALID_TEST_PC_VALUE = MainMemory.getInstance().getInstructionRangeStart();
    private static final int MAX_VALID_TEST_PC_VALUE = MainMemory.getInstance().getInstructionRangeEnd();
    private static final int INVALID_TEST_PC_VALUE = MainMemory.getInstance().getDataRangeStart();

    @Test
    public void testSetRegister1() throws InvalidRegisterNumberException {
        Registers registers = Registers.getInstance();
        registers.setRegister(VALID_TEST_REGISTER_NUMBER, TEST_REGISTER_VALUE);
        Assert.assertEquals(TEST_REGISTER_VALUE, registers.getRegisters()[VALID_TEST_REGISTER_NUMBER]);
    }

    @Test
    public void testSetRegister2() {
        Registers registers = Registers.getInstance();
        Assert.assertThrows(InvalidRegisterNumberException.class, () -> registers.setRegister(INVALID_TEST_REGISTER_NUMBER, TEST_REGISTER_VALUE));
    }

    @Test
    public void testGetRegister1() throws InvalidRegisterNumberException {
        Registers registers = Registers.getInstance();
        registers.getRegisters()[VALID_TEST_REGISTER_NUMBER] = TEST_REGISTER_VALUE;
        Assert.assertEquals(TEST_REGISTER_VALUE, registers.getRegister(VALID_TEST_REGISTER_NUMBER));
    }

    @Test
    public void testGetRegister2() {
        Registers registers = Registers.getInstance();
        Assert.assertThrows(InvalidRegisterNumberException.class, () -> registers.getRegister(INVALID_TEST_REGISTER_NUMBER));
    }

    @Test
    public void testSetPC1() throws AddressOutOfRangeException {
        Registers registers = Registers.getInstance();
        registers.setPC(VALID_TEST_PC_VALUE);
        Assert.assertEquals(VALID_TEST_PC_VALUE, registers.getPC());
    }


    @Test
    public void testSetPC2() {
        Registers registers = Registers.getInstance();
        Assert.assertThrows(AddressOutOfRangeException.class, () -> registers.setPC(INVALID_TEST_PC_VALUE));
    }

    @Test
    public void testIncrementPC1() throws AddressOutOfRangeException {
        Registers registers = Registers.getInstance();
        registers.setPC(VALID_TEST_PC_VALUE);
        registers.incrementPC();
        Assert.assertEquals(VALID_TEST_PC_VALUE + 1, registers.getPC());
    }

    @Test
    public void testIncrementPC2() throws AddressOutOfRangeException {
        Registers registers = Registers.getInstance();
        registers.setPC(MAX_VALID_TEST_PC_VALUE);
        Assert.assertThrows(AddressOutOfRangeException.class, () -> registers.incrementPC());
    }

}
