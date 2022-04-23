package instructions;

import exceptions.InvalidRegisterNumberException;

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

    public abstract void execute() throws InvalidRegisterNumberException;

    public abstract void memoryAccess();

    public abstract void writeBack() throws InvalidRegisterNumberException;

}
