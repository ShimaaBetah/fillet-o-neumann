public abstract class Instruction {
    int binaryRepresentation;
    int opcode;

    public Instruction() {
    }

    public Instruction(int binaryRepresentation) {
        this.binaryRepresentation = binaryRepresentation;
    }

    public abstract void decode(); 
    public abstract void execute();
    public abstract void memoryAccess();
    public abstract void writeBack();
    
}
