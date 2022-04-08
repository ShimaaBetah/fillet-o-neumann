package instructions;
import memory.MainMemory;
import memory.Registers;

public abstract class Instruction {
    int binaryRepresentation;
    int opcode;

    public Instruction() {
    }

    public Instruction(int binaryRepresentation) {
        this.binaryRepresentation = binaryRepresentation;
    }

    public abstract void decode(); 
    public abstract void execute(Registers registers);
    public abstract void memoryAccess(MainMemory memory);
    public abstract void writeBack(MainMemory memory);
    
}
