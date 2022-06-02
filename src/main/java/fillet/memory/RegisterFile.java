package fillet.memory;

import fillet.exceptions.AddressOutOfRangeException;
import fillet.exceptions.InvalidRegisterNumberException;
import fillet.logger.services.ColorStringService;
import fillet.logger.Logger;

public class RegisterFile {
    private int[] registersArray;
    private int numOfRegisters;
    private int pc;

    public static final int NUM_OF_REGISTERS = 32;

    private static RegisterFile instance = new RegisterFile(NUM_OF_REGISTERS);

    private RegisterFile(int numOfRegisters) {
        this.numOfRegisters = numOfRegisters;
        registersArray = new int[numOfRegisters];
        this.pc = 0;
    }

    public static RegisterFile getInstance() {
        return instance;
    }

    public void setRegister(int registerNum, int value) throws InvalidRegisterNumberException {
        if (!isValidRegister(registerNum)) {
            throw new InvalidRegisterNumberException(registerNum);
        }
        if (registerNum == 0) {
            return;
        }
        registersArray[registerNum] = value;
        Logger.logln(ColorStringService.color("Register " + registerNum + " set to " + value, ColorStringService.GREEN));
    }

    public int getRegister(int registerNum) throws InvalidRegisterNumberException {
        if (!isValidRegister(registerNum)) {
            throw new InvalidRegisterNumberException(registerNum);
        }
        return registersArray[registerNum];
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

    public int[] getRegistersArray() {
        return registersArray;
    }

}
