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
        pc = 0;
    }

    public static Registers getInstance() {
        return instance;
    }

    public void setRegister(int registerNum, int value) throws InvalidRegisterNumber {
        if (isRegister(registerNum)) {
            registers[registerNum] = value;
        } else {
            throw new InvalidRegisterNumber(registerNum);
        }
    }

    public int getRegister(int registerNum) throws InvalidRegisterNumber {
        if (isRegister(registerNum)) {
            return registers[registerNum];
        } else {
            throw new InvalidRegisterNumber(registerNum);
        }
    }

    private boolean isRegister(int registerNum) {
        return 0 <= registerNum && registerNum < numOfRegisters;
    }

    public void incrementPC() throws AddressOutOfRange {
        if (MainMemory.getInstance().inInstructionRange(pc + 1)) {
            pc++;
        } else {
            throw new AddressOutOfRange("Can't increment PC, value reached the end of the instructions range");
        }
    }

    public void setPC(int address) throws AddressOutOfRange {
        if (MainMemory.getInstance().inInstructionRange(address)) {
            pc = address;
        } else {
            throw new AddressOutOfRange("Can't set PC, value is out of the instructions range");
        }
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
