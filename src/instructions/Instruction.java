package instructions;

import memory.MainMemory;
import memory.Registers;

public abstract class Instruction {
    private int bitMask;
    private int opcode;

    public Instruction() {
    }

    public int getBitMask() {
        return bitMask;
    }

    public void setBitMask(int bitMask) {
        this.bitMask = bitMask;
    }

    public int getOpcode() {
        return opcode;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public Instruction(int bitMask) {
        this.setBitMask(bitMask);
    }

    public abstract void decode();

    public abstract void execute(Registers registers);

    public abstract void memoryAccess(MainMemory memory);

    public abstract void writeBack(Registers registers);

}
