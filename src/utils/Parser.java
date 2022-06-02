package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import exceptions.InvalidInstructionException;
import exceptions.InvalidRegisterException;
import instructions.InstructionType;

public class Parser {
    /*
     * A class that parses the input file and returns a list of binary instructions.
     */
    private List<String[]> instructions = new ArrayList<>();
    private List<String> binaryInstructions = new ArrayList<>();
    private HashMap<String, Integer> labels = new HashMap<>();

    private static final String INVALID_INSTRUCTION = "Invalid instruction: ";
    private static final int INSTRUCTION_SIZE = 32;
    private static final int OPCODE_SIZE = 4;
    private static final int REGISTER_SIZE = 5;
    private static final int MAX_REGISTER_VALUE = 31;

    public Parser(String programPath) throws InvalidInstructionException, InvalidRegisterException {
        parse(programPath);
    }

    private void parse(String programPath) throws InvalidInstructionException, InvalidRegisterException {
        /*
         * Reads the file and parses the instructions.
         * comments (#) are ignored.
         */
        var instructionsFile = new File(programPath);
        try (var fr = new FileReader(instructionsFile)) {
            try (var br = new BufferedReader(fr)) {
                String line;
                while ((line = br.readLine()) != null) {
                    // skip empty lines and comments `use Logger`
                    if (line.isBlank() || line.startsWith("#")) {
                        continue;
                    }
                    line = trimLine(line);
                    if (line.contains(":") && (!line.contains("#") || (line.indexOf("#") > line.lastIndexOf(":")))) {
                        var label = line.split(":")[0].trim();
                        var address = instructions.size();
                        labels.put(label, address);
                    } else {
                        // instruction
                        String[] splittedLine = splitLines(line);
                        this.instructions.add(splittedLine);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Logger
        }
        convertStringToBinary();
    }

    private String[] splitLines(String line) {
        /*
         * This method is used to split lines of the file.
         * It removes the spaces and tabs from the line.
         * It also removes the comments from the line.
         */
        String[] splittedLine = line.replaceAll("\\s(\\s)+", " ").split(" ");
        Arrays.asList(splittedLine).forEach(String::trim);
        // remove all strings after #
        int index = Arrays.asList(splittedLine).indexOf("#");
        return (index == -1) ? splittedLine : Arrays.copyOf(splittedLine, index);
    }

    private String trimLine(String line) {
        line = line.trim();
        line = line.toUpperCase();
        return line;
    }

    private void convertStringToBinary() throws InvalidInstructionException, InvalidRegisterException {
        /*
         * This method converts the string instructions to binary instructions.
         * It also checks if the instruction is valid.
         */
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
                case R_TYPE->
                    this.binaryInstructions.add(getRType(instruction));
                case I_TYPE ->
                    this.binaryInstructions.add(getIType(instruction));
                case J_TYPE ->
                    this.binaryInstructions.add(getJType(instruction));
                default ->
                    throw new InvalidInstructionException(INVALID_INSTRUCTION + instruction[0]);
            }
        }
    }

    private String getJType(String[] instruction) throws InvalidInstructionException {
        /*
         * map the opcode to the type J
         * format:
         * opcode address(28)
         * JMP 9
         * convert the address to unsigned binary
         */
        StringBuilder binary = new StringBuilder();
        binary.append(getOpcodeString(getOpcode(instruction[0])));
        // check if It's a label
        if (labels.containsKey(instruction[1])) {
            instruction[1] = "" + labels.get(instruction[1]);
        }
        if (instruction[1].startsWith("-")) {
            throw new InvalidInstructionException(INVALID_INSTRUCTION + instruction[0]);
        }
        String address = getImmediate(instruction[1], OPCODE_SIZE - 1, INSTRUCTION_SIZE - OPCODE_SIZE);
        binary.append(address);
        return binary.toString();
    }

    private String getImmediate(String stringImmediate, int offset, int size) {
        /*
         * This method converts the immediate to binary.
         * It also checks if the immediate is valid.
         * The immediate is a signed integer.
         */
        String address;
        if (stringImmediate.startsWith("-")) {
            address = Integer.toBinaryString(Integer.parseInt(stringImmediate)).substring(1);
            // address is {size} bits, remove leading bits from the sign bit
            address = address.substring(offset);
        } else {
            address = Integer.toBinaryString(Integer.parseInt(stringImmediate));
            // pad with 0's to make it {size} bits
            for (int i = address.length(); i < size; i++) {
                address = "0" + address;
            }
        }
        return address;
    }

    private String getOpcodeString(int opcode) {
        /*
         * map the opcode to the binary string
         * format:
         * opcode(4)
         */
        StringBuilder binary = new StringBuilder();
        binary.append(Integer.toBinaryString(opcode));
        while (binary.length() < OPCODE_SIZE) {
            binary.insert(0, "0");
        }
        return binary.toString();
    }

    private String getIType(String[] instruction) throws InvalidRegisterException {
        /*
         * map the instruction to binary of type I
         * format:
         * opcode rs rt immediate
         * convert the immediate to signed binary
         */
        int opcode = getOpcode(instruction[0]);
        int rs = getRegister(instruction[1]);
        int rt;
        int immediate;
        int immediateSize = INSTRUCTION_SIZE - OPCODE_SIZE - 2 * REGISTER_SIZE;
        if (opcode == 3) { // If opcode is 3, then the instruction is `MOVI` which has no rt, always 0
            rt = 0;
            immediate = getImmediateInt(instruction[2], OPCODE_SIZE + 2 * REGISTER_SIZE - 1,
                    immediateSize);
        } else {
            rt = getRegister(instruction[2]);
            immediate = getImmediateInt(instruction[3], OPCODE_SIZE + 2 * REGISTER_SIZE - 1,
                    immediateSize);
        }
        int binary = (opcode << INSTRUCTION_SIZE - OPCODE_SIZE);
        binary |= (rs << INSTRUCTION_SIZE - OPCODE_SIZE - REGISTER_SIZE);
        binary |= (rt << immediateSize);
        binary |= immediate;
        return Binary.intToBinaryString(binary);
    }

    private int getImmediateInt(String string, int i, int j) {
        return Binary.binaryStringtoInt(getImmediate(string, i, j));
    }

    private String getRType(String[] instruction) throws InvalidRegisterException {
        /*
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
                shamt = Integer.parseInt(instruction[3]);
            } catch (ArrayIndexOutOfBoundsException e) {
                shamt = 0;
            }
        } else {
            rd = getRegister(instruction[3]);
            try {
                shamt = Integer.parseInt(instruction[4]);
            } catch (ArrayIndexOutOfBoundsException e) {
                shamt = 0;
            }
        }
        int binary = (opcode << INSTRUCTION_SIZE - OPCODE_SIZE);

        binary |= rs << INSTRUCTION_SIZE - OPCODE_SIZE - REGISTER_SIZE;
        binary |= rt << INSTRUCTION_SIZE - OPCODE_SIZE - 2 * REGISTER_SIZE;
        binary |= rd << INSTRUCTION_SIZE - OPCODE_SIZE - 3 * REGISTER_SIZE;
        binary |= shamt;
        return Binary.intToBinaryString(binary);
    }

    private int getRegister(String string) throws InvalidRegisterException {
        /*
         * slice the string to get the register number
         * format:
         * R[0-31]
         */
        if (!string.startsWith("R")) {
            throw new InvalidRegisterException("Invalid register: " + string);
        }
        int registerNumber = Integer.parseInt(string.substring(1));
        if (registerNumber < 0 || registerNumber > MAX_REGISTER_VALUE) {
            throw new InvalidRegisterException("Invalid register: " + string);
        }
        return registerNumber;
    }

    private InstructionType getInstructionType(int opcode) {
        /*
         * map opcode to instruction type (R, I, J)
         */
        switch (opcode) {
            case 3:
                return InstructionType.I_TYPE;
            case 4:
                return InstructionType.I_TYPE;
            case 6:
                return InstructionType.I_TYPE;
            case 7:
                return InstructionType.J_TYPE;
            case 9:
                return InstructionType.I_TYPE;
            case 10:
                return InstructionType.I_TYPE;
            case 11:
                return InstructionType.I_TYPE;
            default:
                return InstructionType.R_TYPE;
        }
    }

    private int getOpcode(String instruction) {
        /*
         * map String instruction to opcode
         */
        switch (instruction) {
            case ("ADD"):
                return 0;
            case "SUB":
                return 1;
            case "MUL":
                return 2;
            case "MOVI":
                return 3;
            case "JEQ":
                return 4;
            case "AND":
                return 5;
            case "XORI":
                return 6;
            case "JMP":
                return 7;
            case "LSL":
                return 8;
            case "LSR":
                return 9;
            case "MOVR":
                return 10;
            case "MOVM":
                return 11;
            default:
                return -1;
        }
    }

    public List<String> getBinaryInstructions() {
        return this.binaryInstructions;
    }

}
