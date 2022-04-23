package memory;

import exceptions.AddressOutOfRange;

public class MainMemory {
    private static MainMemory instance;

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

    private MainMemory(int size, int instructionRangeStart, int instructionRangeEnd, int dataRangeStart, int dataRangeEnd) {
        memory = new int[size];
        this.instructionRangeStart = instructionRangeStart;
        this.instructionRangeEnd = instructionRangeEnd;
        this.dataRangeStart = dataRangeStart;
        this.dataRangeEnd = dataRangeEnd;
    }

    public static MainMemory getInstance() {
        if (instance == null) {
            instance = new MainMemory(MEMORY_SIZE, INSTRUCTION_RANGE_START, INSTRUCTION_RANGE_END, DATA_RANGE_START, DATA_RANGE_END);
        }
        return instance;
    }

    public void storeInstruction(int address, int instruction) throws AddressOutOfRange {
        if (isAddressInInstructionRange(address)) {
            memory[address] = instruction;
        } else {
            throw new AddressOutOfRange();
        }
    }

    public int loadInstruction(int address) throws AddressOutOfRange {
        if (isAddressInInstructionRange(address)) {
            return memory[address];
        } else {
            throw new AddressOutOfRange();
        }
    }

    public void storeData(int address, int data) throws AddressOutOfRange {
        if (isAddressInDataRange(address)) {
            memory[address] = data;
        } else {
            throw new AddressOutOfRange();
        }
    }

    public int loadData(int address) throws AddressOutOfRange {
        if (isAddressInDataRange(address)) {
            return memory[address];
        } else {
            throw new AddressOutOfRange();
        }
    }

    private boolean isAddressInInstructionRange(int address) {
        return instructionRangeStart <= address && address <= instructionRangeEnd;
    }

    private boolean isAddressInDataRange(int address) {
        return dataRangeStart <= address && address <= dataRangeEnd;
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
