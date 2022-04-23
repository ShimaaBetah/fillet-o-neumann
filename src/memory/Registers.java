package memory;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;

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

    public void setRegister(int registerNum, int value) throws InvalidRegisterNumberException {
        if (!isValidRegister(registerNum)) {
            throw new InvalidRegisterNumberException(registerNum);
        }
        registers[registerNum] = value;
    }

    public int getRegister(int registerNum) throws InvalidRegisterNumberException {
        if (!isValidRegister(registerNum)) {
            throw new InvalidRegisterNumberException(registerNum);
        }
        return registers[registerNum];
    }

    private boolean isValidRegister(int registerNum) {
        return 0 <= registerNum && registerNum < numOfRegisters;
    }

    public void incrementPC() throws AddressOutOfRangeException {
        if (!MainMemory.getInstance().inInstructionRange(pc + 1)) {
            throw new AddressOutOfRangeException("Can't increment PC, value reached the end of the instructions range");
        }
        pc++;
    }

    public void setPC(int address) throws AddressOutOfRangeException {
        if (!MainMemory.getInstance().inInstructionRange(address)) {
            throw new AddressOutOfRangeException("Can't set PC, value is out of the instructions range");
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
