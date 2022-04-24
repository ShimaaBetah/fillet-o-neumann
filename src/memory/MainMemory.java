package memory;

import exceptions.AddressOutOfRangeException;

public class MainMemory {
    private int[] memory;
    private int instructionRangeStart;
    private int instructionRangeEnd;
    private int dataRangeStart;
    private int dataRangeEnd;

    private static final int MEMORY_SIZE = 2048;
    private static final int INSTRUCTION_RANGE_START = 0;
    private static final int INSTRUCTION_RANGE_END = 1023;
    private static final int DATA_RANGE_START = 1024;
    private static final int DATA_RANGE_END = 2047;

    private static MainMemory instance = new MainMemory(MEMORY_SIZE, INSTRUCTION_RANGE_START, INSTRUCTION_RANGE_END, DATA_RANGE_START, DATA_RANGE_END);

    private MainMemory(int size, int instructionRangeStart, int instructionRangeEnd, int dataRangeStart, int dataRangeEnd) {
        memory = new int[size];
        this.instructionRangeStart = instructionRangeStart;
        this.instructionRangeEnd = instructionRangeEnd;
        this.dataRangeStart = dataRangeStart;
        this.dataRangeEnd = dataRangeEnd;
    }

    public static MainMemory getInstance() {
        return instance;
    }

    public void storeInstruction(int address, int instruction) throws AddressOutOfRangeException {
        if (!inInstructionRange(address)) {
            throw new AddressOutOfRangeException();
        }
        memory[address] = instruction;
    }

    public int loadInstruction(int address) throws AddressOutOfRangeException {
        if (!inInstructionRange(address)) {
            throw new AddressOutOfRangeException();
        }
        return memory[address];
    }

    public void storeData(int address, int data) throws AddressOutOfRangeException {
        if (!inDataRange(address)) {
            throw new AddressOutOfRangeException();
        }
        memory[address] = data;
    }

    public int loadData(int address) throws AddressOutOfRangeException {
        if (!inDataRange(address)) {
            throw new AddressOutOfRangeException();
        }
        return memory[address];
    }

    //TODO: add test for storeWord method
    public void storeWord(int address, int word) throws AddressOutOfRangeException {
        if (!inMemoryRange(address)) {
            throw new AddressOutOfRangeException();
        }
        memory[address] = word;
    }

    //TODO: add test for loadWord method
    public int loadWord(int address) throws AddressOutOfRangeException {
        if (!inMemoryRange(address)) {
            throw new AddressOutOfRangeException();
        }
        return memory[address];
    }

    public boolean inInstructionRange(int address) {
        return instructionRangeStart <= address && address <= instructionRangeEnd;
    }

    public boolean inDataRange(int address) {
        return dataRangeStart <= address && address <= dataRangeEnd;
    }

    public boolean inMemoryRange(int address) {
        return 0 <= address && address < MEMORY_SIZE;
    }

    public int[] getMemory() {
        return memory;
    }

    public int getInstructionRangeStart() {
        return instructionRangeStart;
    }

    public int getInstructionRangeEnd() {
        return instructionRangeEnd;
    }

    public int getDataRangeStart() {
        return dataRangeStart;
    }

    public int getDataRangeEnd() {
        return dataRangeEnd;
    }
}
