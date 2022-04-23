package memory;

import exceptions.AddressOutOfRange;
import exceptions.InvalidRegisterNumber;

public class Registers {
    private int[] registers;
    private int numOfRegisters;
    private int pc;

    private static final int NUM_OF_REGISTERS = 32;

    private static Registers instance = new Registers(NUM_OF_REGISTERS);

    private Registers(int numOfRegisters) {
        this.numOfRegisters = numOfRegisters;
        registers = new int[numOfRegisters];
        this.pc = 0;
    }

    public static Registers getInstance() {
        return instance;
    }

    public void setRegister(int registerNum, int value) throws InvalidRegisterNumber {
        if (!isValidRegister(registerNum)) {
            throw new InvalidRegisterNumber(registerNum);
        }
        registers[registerNum] = value;
    }

    public int getRegister(int registerNum) throws InvalidRegisterNumber {
        if (!isValidRegister(registerNum)) {
            throw new InvalidRegisterNumber(registerNum);
        }
        return registers[registerNum];
    }

    private boolean isValidRegister(int registerNum) {
        return 0 <= registerNum && registerNum < numOfRegisters;
    }

    public void incrementPC() throws AddressOutOfRange {
        if (!MainMemory.getInstance().inInstructionRange(pc + 1)) {
            throw new AddressOutOfRange("Can't increment PC, value reached the end of the instructions range");
        }
        pc++;
    }

    public void setPC(int address) throws AddressOutOfRange {
        if (!MainMemory.getInstance().inInstructionRange(address)) {
            throw new AddressOutOfRange("Can't set PC, value is out of the instructions range");
        }
        pc = address;
    }

    public int getPC() {
        return pc;
    }

    public int[] getRegisters() {
        return registers;
    }

    public int getNumOfRegisters() {
        return numOfRegisters;
    }

}
