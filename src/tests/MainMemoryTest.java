package tests;

import exceptions.AddressOutOfRange;
import memory.MainMemory;
import org.junit.*;

public class MainMemoryTest {

    @Test
    public void testStoreInstruction1() throws AddressOutOfRange {
        MainMemory memory = MainMemory.getInstance();
        int address = memory.getInstructionRangeStart();
        int instruction = 0x1234;
        memory.storeInstruction(address, instruction);
        Assert.assertEquals(instruction, memory.getMemory()[address]);
    }

    @Test
    public void testStoreInstruction2() {
        MainMemory memory = MainMemory.getInstance();
        int address = memory.getInstructionRangeEnd() + 1;
        int instruction = 0x1234;
        Assert.assertThrows(AddressOutOfRange.class, () -> memory.storeInstruction(address, instruction));
    }

    @Test
    public void testLoadInstruction1() throws AddressOutOfRange {
        MainMemory memory = MainMemory.getInstance();
        int address = memory.getInstructionRangeStart();
        int instruction = 0x1234;
        memory.getMemory()[address] = instruction;
        Assert.assertEquals(instruction, memory.loadInstruction(address));
    }

    @Test
    public void testLoadInstruction2() {
        MainMemory memory = MainMemory.getInstance();
        int address = memory.getInstructionRangeEnd() + 1;
        Assert.assertThrows(AddressOutOfRange.class, () -> memory.loadInstruction(address));
    }

    @Test
    public void testStoreData1() throws AddressOutOfRange {
        MainMemory memory = MainMemory.getInstance();
        int address = memory.getDataRangeStart();
        int data = 0x1234;
        memory.storeData(address, data);
        Assert.assertEquals(data, memory.getMemory()[address]);
    }

    @Test
    public void testStoreData2() {
        MainMemory memory = MainMemory.getInstance();
        int address = memory.getDataRangeEnd() + 1;
        int data = 0x1234;
        Assert.assertThrows(AddressOutOfRange.class, () -> memory.storeData(address, data));
    }

    @Test
    public void testLoadData1() throws AddressOutOfRange {
        MainMemory memory = MainMemory.getInstance();
        int address = memory.getDataRangeStart();
        int data = 0x1234;
        memory.getMemory()[address] = data;
        Assert.assertEquals(data, memory.loadData(address));
    }

    @Test
    public void testLoadData2() {
        MainMemory memory = MainMemory.getInstance();
        int address = memory.getDataRangeEnd() + 1;
        Assert.assertThrows(AddressOutOfRange.class, () -> memory.loadData(address));
    }
}
