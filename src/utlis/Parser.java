package utlis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.InvalidInstructionException;
import exceptions.InvalidRegisterException;
import instructions.InstructionType;

public class Parser {
    private List<String[]> instructions = new ArrayList<>();
    private List<String> binaryInstructions = new ArrayList<>();

    private static final String INVALID_INSTRUCTION = "Invalid instruction: ";

    private static final int INSTRUCTION_SIZE = 32;
    private static final int OPCODE_SIZE = 4;
    private static final int REGISTER_SIZE = 5;

    public Parser(String programPath) {
        var instructionsFile = new File(programPath);
        try (var fr = new FileReader(instructionsFile)) {
            try (var br = new BufferedReader(fr)) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] splittedLine = trimLines(line);
                    this.instructions.add(splittedLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            convertStringToBinary();
        } catch (InvalidInstructionException | InvalidRegisterException e) {
            System.err.println(e.getMessage()); // use logger instead
            System.exit(1);
        }
    }

    private String[] trimLines(String line) {
        line = line.trim();
        line = line.toUpperCase();
        String[] splittedLine = line.replaceAll("\\s(\\s)+", " ").split(" ");
        Arrays.asList(splittedLine).forEach(String::trim);
        return splittedLine;
    }

    private void convertStringToBinary() throws InvalidInstructionException, InvalidRegisterException {
        for (String[] instruction : this.instructions) {
            if (instruction.length < 2) {
                throw new InvalidInstructionException(INVALID_INSTRUCTION + instruction[0]);
            }

            int opcode = getOpcode(instruction[0].toUpperCase());
            if (opcode == -1) {
                throw new InvalidInstructionException(INVALID_INSTRUCTION + instruction[0]);
            }

            InstructionType instructionType = getInstructionType(opcode);
            switch (instructionType) {
                case R:
                    this.binaryInstructions.add(getRType(instruction));
                    break;
                case I:
                    this.binaryInstructions.add(getIType(instruction));
                    break;
                case J:
                    this.binaryInstructions.add(getJType(instruction));
                    break;
                default:
                    throw new InvalidInstructionException(INVALID_INSTRUCTION + instruction[0]);
            }
        }
    }

    private String getJType(String[] instruction) {
        /**
         * map the opcode to the type J
         * format:
         * opcode address should be fixed
         * TODO
         */
        StringBuilder binary = new StringBuilder();
        binary.append(getOpcodeString(getOpcode(instruction[0])));
        binary.append(instruction[1]);
        return binary.toString();
    }

    private String getOpcodeString(int opcode) {
        StringBuilder binary = new StringBuilder();
        binary.append(Integer.toBinaryString(opcode));
        while (binary.length() < OPCODE_SIZE) {
            binary.insert(0, "0");
        }
        return binary.toString();
    }

    private String getIType(String[] instruction) throws InvalidRegisterException {
        /**
         * map the instruction to binary of type I
         * format:
         * opcode rs rt immediate
         */
        int opcode = getOpcode(instruction[0]);
        int rs = getRegister(instruction[1]);
        int rt;
        int immediate;
        if (opcode == 3) { // If opcode is 3, then the instruction is `MOVI` which has no rt, always 0
            rt = 0;
            immediate = getImmediate(instruction[2]);
        } else {
            rt = getRegister(instruction[2]);
            immediate = getImmediate(instruction[3]);
        }
        int binary = (opcode << INSTRUCTION_SIZE - OPCODE_SIZE);
        binary |= (rs << INSTRUCTION_SIZE - OPCODE_SIZE - REGISTER_SIZE);
        binary |= (rt << INSTRUCTION_SIZE - OPCODE_SIZE - 2 * REGISTER_SIZE);
        binary |= immediate;
        return getBinary(binary);
    }

    private int getImmediate(String string) {
        return Integer.parseInt(string);
    }

    private String getRType(String[] instruction) throws InvalidRegisterException {
        /**
         * map the instruction to binary of type R
         * format:
         * opcode rs rt rd shamt
         */
        var opcode = getOpcode(instruction[0]);
        int rs = getRegister(instruction[1]);
        int rt = getRegister(instruction[2]);
        int rd;
        int shamt;
        if (opcode == 7 || opcode == 8) { // If opcode is 7, 8, then the instruction is `shifting` which has no rd,
            rd = 0; // always 0
            try {
                shamt = getShamt(instruction[3]);
            } catch (ArrayIndexOutOfBoundsException e) {
                shamt = 0;
            }
        } else {
            rd = getRegister(instruction[3]);
            try {
                shamt = getShamt(instruction[4]);
            } catch (ArrayIndexOutOfBoundsException e) {
                shamt = 0;
            }
        }
        int binary = (opcode << INSTRUCTION_SIZE - OPCODE_SIZE);
        binary |= rs << INSTRUCTION_SIZE - OPCODE_SIZE - REGISTER_SIZE;
        binary |= rt << INSTRUCTION_SIZE - OPCODE_SIZE - 2 * REGISTER_SIZE;
        binary |= rd << INSTRUCTION_SIZE - OPCODE_SIZE - 3 * REGISTER_SIZE;
        binary |= shamt;
        return getBinary(binary);
    }

    private int getShamt(String string) {
        return Integer.parseInt(string);
    }

    private int getRegister(String string) throws InvalidRegisterException {
        /**
         * slice the string to get the register number
         * format:
         * $R[0-31]
         */
        int registerNumber = Integer.parseInt(string.substring(2));
        if (registerNumber < 0 || registerNumber > 31) {
            throw new InvalidRegisterException(INVALID_INSTRUCTION + string);
        }
        return registerNumber;
    }

    private InstructionType getInstructionType(int opcode) throws InvalidInstructionException {
        /**
         * map opcode to instruction type (R, I, J)
         */
        switch (opcode) {
            case 3:
                return InstructionType.I;
            case 4:
                return InstructionType.I;
            case 6:
                return InstructionType.I;
            case 7:
                return InstructionType.J;
            case 9:
                return InstructionType.I;
            case 10:
                return InstructionType.I;
            default:
                return InstructionType.R;
        }
    }

    private int getOpcode(String instruction) {
        if (instruction.equals("ADD"))
            return 0;
        else if (instruction.equals("SUB"))
            return 1;
        else if (instruction.equals("MUL"))
            return 2;
        else if (instruction.equals("MOVI"))
            return 3;
        else if (instruction.equals("JEQ"))
            return 4;
        else if (instruction.equals("AND"))
            return 5;
        else if (instruction.equals("XORI"))
            return 6;
        else if (instruction.equals("JMP"))
            return 7;
        else if (instruction.equals("LSL"))
            return 8;
        else if (instruction.equals("LSR"))
            return 9;
        else if (instruction.equals("MOVR"))
            return 10;
        else if (instruction.equals("MOVM"))
            return 11;
        else
            return -1;
    }

    public List<String> getBinaryInstructions() {
        return this.binaryInstructions;
    }

    public String getBinary(int sID) {
        /**
         * java's toBinaryString() method doesn't return the whole binary
         * so this a workaround to get the binary string
         */
        return Long.toBinaryString(sID & 0xffffffffL | 0x100000000L).substring(1);
    }
}
