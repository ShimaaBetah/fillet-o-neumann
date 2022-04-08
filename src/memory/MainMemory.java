package memory;

public class MainMemory {
    int[] memory;
    int instructionRange;
    int dataRange;

    public MainMemory(int size, int instructionRange, int dataRange) {
        memory = new int[size];
        this.instructionRange = instructionRange;
        this.dataRange = dataRange;
    }

    public void storeInstruction(int index, int value) {
        if (index <= instructionRange) {
            memory[index] = value;
        }
    }

    public int loadInstruction(int index) {
        if (index <= instructionRange) {
            return memory[index];
        } else {
            return -1;
        }
    }

    public void storeData(int index, int value) {
        if (index <= dataRange) {
            memory[index] = value;
        }
        memory[index] = value;
    }

    public int loadData(int index) {
        if (index <= dataRange) {
            return memory[index];
        } else {
            return -1;
        }
    }
}
