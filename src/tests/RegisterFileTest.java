package tests;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;
import memory.MainMemory;
import memory.RegisterFile;
import org.junit.Assert;
import org.junit.Test;

public class RegisterFileTest {
    private static final int TEST_REGISTER_VALUE = 0x1234;
    private static final int VALID_TEST_REGISTER_NUMBER = 1;
    private static final int VALID_TEST_ZERO_REGISTER = 0;
    private static final int INVALID_TEST_REGISTER_NUMBER = -1;
    private static final int VALID_TEST_PC_VALUE = MainMemory.getInstance().getInstructionRangeStart();
    private static final int MAX_VALID_TEST_PC_VALUE = MainMemory.getInstance().getInstructionRangeEnd();
    private static final int INVALID_TEST_PC_VALUE = MainMemory.getInstance().getDataRangeStart();

    @Test
    public void testSetRegister1() throws InvalidRegisterNumberException {
        RegisterFile registerFile = RegisterFile.getInstance();
        registerFile.setRegister(VALID_TEST_REGISTER_NUMBER, TEST_REGISTER_VALUE);
        Assert.assertEquals(TEST_REGISTER_VALUE, registerFile.getRegistersArray()[VALID_TEST_REGISTER_NUMBER]);
    }

    @Test
    public void testSetRegister2() {
        RegisterFile registerFile = RegisterFile.getInstance();
        Assert.assertThrows(InvalidRegisterNumberException.class, () -> registerFile.setRegister(INVALID_TEST_REGISTER_NUMBER, TEST_REGISTER_VALUE));
    }

    @Test
    public void testSetRegister3() throws InvalidRegisterNumberException {
        RegisterFile registerFile = RegisterFile.getInstance();
        registerFile.setRegister(VALID_TEST_ZERO_REGISTER, TEST_REGISTER_VALUE);
        Assert.assertEquals(0, registerFile.getRegistersArray()[VALID_TEST_ZERO_REGISTER]);
    }

    @Test
    public void testGetRegister1() throws InvalidRegisterNumberException {
        RegisterFile registerFile = RegisterFile.getInstance();
        registerFile.getRegistersArray()[VALID_TEST_REGISTER_NUMBER] = TEST_REGISTER_VALUE;
        Assert.assertEquals(TEST_REGISTER_VALUE, registerFile.getRegister(VALID_TEST_REGISTER_NUMBER));
    }

    @Test
    public void testGetRegister2() {
        RegisterFile registerFile = RegisterFile.getInstance();
        Assert.assertThrows(InvalidRegisterNumberException.class, () -> registerFile.getRegister(INVALID_TEST_REGISTER_NUMBER));
    }

    @Test
    public void testSetPC1() throws AddressOutOfRangeException {
        RegisterFile registerFile = RegisterFile.getInstance();
        registerFile.setPC(VALID_TEST_PC_VALUE);
        Assert.assertEquals(VALID_TEST_PC_VALUE, registerFile.getPC());
    }


    @Test
    public void testSetPC2() {
        RegisterFile registerFile = RegisterFile.getInstance();
        Assert.assertThrows(AddressOutOfRangeException.class, () -> registerFile.setPC(INVALID_TEST_PC_VALUE));
    }

    @Test
    public void testIncrementPC1() throws AddressOutOfRangeException {
        RegisterFile registerFile = RegisterFile.getInstance();
        registerFile.setPC(VALID_TEST_PC_VALUE);
        registerFile.incrementPC();
        Assert.assertEquals(VALID_TEST_PC_VALUE + 1, registerFile.getPC());
    }

    @Test
    public void testIncrementPC2() throws AddressOutOfRangeException {
        RegisterFile registerFile = RegisterFile.getInstance();
        registerFile.setPC(MAX_VALID_TEST_PC_VALUE);
        Assert.assertThrows(AddressOutOfRangeException.class, () -> registerFile.incrementPC());
    }

}
