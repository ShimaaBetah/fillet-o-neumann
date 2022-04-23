package memory;

import exceptions.AddressOutOfRange;

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

    public void storeInstruction(int address, int instruction) throws AddressOutOfRange {
        if (inInstructionRange(address)) {
            memory[address] = instruction;
        } else {
            throw new AddressOutOfRange();
        }
    }

    public int loadInstruction(int address) throws AddressOutOfRange {
        if (inInstructionRange(address)) {
            return memory[address];
        } else {
            throw new AddressOutOfRange();
        }
    }

    public void storeData(int address, int data) throws AddressOutOfRange {
        if (inDataRange(address)) {
            memory[address] = data;
        } else {
            throw new AddressOutOfRange();
        }
    }

    public int loadData(int address) throws AddressOutOfRange {
        if (inDataRange(address)) {
            return memory[address];
        } else {
            throw new AddressOutOfRange();
        }
    }

    public boolean inInstructionRange(int address) {
        return instructionRangeStart <= address && address <= instructionRangeEnd;
    }

    public boolean inDataRange(int address) {
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
