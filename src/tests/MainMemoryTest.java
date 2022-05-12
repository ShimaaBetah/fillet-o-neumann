package tests;

import exceptions.AddressOutOfRangeException;
import memory.MainMemory;
import org.junit.*;

public class MainMemoryTest {
    private static final int TEST_INSTRUCTION = 0x1234;
    private static final int TEST_DATA = 0x5678;
    private static final int TEST_INSTRUCTION_ADDRESS = MainMemory.getInstance().getInstructionRangeStart();
    private static final int TEST_DATA_ADDRESS = MainMemory.getInstance().getDataRangeStart();

    @Test
    public void testStoreInstruction1() throws AddressOutOfRangeException {
        MainMemory memory = MainMemory.getInstance();
        memory.storeInstruction(TEST_INSTRUCTION_ADDRESS, TEST_INSTRUCTION);
        Assert.assertEquals(TEST_INSTRUCTION, memory.getMemory()[TEST_INSTRUCTION_ADDRESS]);
    }

    @Test
    public void testStoreInstruction2() {
        MainMemory memory = MainMemory.getInstance();
        Assert.assertThrows(AddressOutOfRangeException.class, () -> memory.storeInstruction(TEST_DATA_ADDRESS, TEST_INSTRUCTION));
    }

    @Test
    public void testStoreInstruction3() {
        MainMemory memory = MainMemory.getInstance();
        memory.getMemory()[TEST_DATA_ADDRESS] = TEST_DATA;
        try {
            memory.storeInstruction(TEST_INSTRUCTION_ADDRESS, TEST_INSTRUCTION);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(TEST_DATA, memory.getMemory()[TEST_DATA_ADDRESS]);
    }

        @Test
    public void testLoadInstruction1() throws AddressOutOfRangeException {
        MainMemory memory = MainMemory.getInstance();
        memory.getMemory()[TEST_INSTRUCTION_ADDRESS] = TEST_INSTRUCTION;
        Assert.assertEquals(TEST_INSTRUCTION, memory.loadInstruction(TEST_INSTRUCTION_ADDRESS));
    }

    @Test
    public void testLoadInstruction2() {
        MainMemory memory = MainMemory.getInstance();
        Assert.assertThrows(AddressOutOfRangeException.class, () -> memory.loadInstruction(TEST_DATA_ADDRESS));
    }

    @Test
    public void testStoreData1() throws AddressOutOfRangeException {
        MainMemory memory = MainMemory.getInstance();
        memory.storeData(TEST_DATA_ADDRESS, TEST_DATA);
        Assert.assertEquals(TEST_DATA, memory.getMemory()[TEST_DATA_ADDRESS]);
    }


    @Test
    public void testStoreData2() {
        MainMemory memory = MainMemory.getInstance();
        Assert.assertThrows(AddressOutOfRangeException.class, () -> memory.storeData(TEST_INSTRUCTION_ADDRESS, TEST_DATA));
    }

    @Test
    public void testStoreData3() {
        MainMemory memory = MainMemory.getInstance();
        memory.getMemory()[TEST_INSTRUCTION_ADDRESS] = TEST_INSTRUCTION;
        try {
            memory.storeData(TEST_DATA_ADDRESS, TEST_DATA);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(TEST_INSTRUCTION, memory.getMemory()[TEST_INSTRUCTION_ADDRESS]);
    }

    @Test
    public void testLoadData1() throws AddressOutOfRangeException {
        MainMemory memory = MainMemory.getInstance();
        memory.getMemory()[TEST_DATA_ADDRESS] = TEST_DATA;
        Assert.assertEquals(TEST_DATA, memory.loadData(TEST_DATA_ADDRESS));
    }

    @Test
    public void testLoadData2() {
        MainMemory memory = MainMemory.getInstance();
        Assert.assertThrows(AddressOutOfRangeException.class, () -> memory.loadData(TEST_INSTRUCTION_ADDRESS));
    }
}
