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
    /*
     * A class that parses the input file and returns a list of binary instructions.
     */
    private List<String[]> instructions = new ArrayList<>();
    private List<String> binaryInstructions = new ArrayList<>();

    private static final String INVALID_INSTRUCTION = "Invalid instruction: ";
    private static final int INSTRUCTION_SIZE = 32;
    private static final int OPCODE_SIZE = 4;
    private static final int REGISTER_SIZE = 5;

    public Parser(String programPath) {
        parse(programPath);
    }

    private void parse(String programPath) {
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
                    String[] splittedLine = splitTrimLines(line);
                    this.instructions.add(splittedLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Logger
        }
        try {
            convertStringToBinary();
        } catch (InvalidInstructionException | InvalidRegisterException e) {
            System.err.println(e.getMessage()); // use logger instead
            System.exit(1);
        }
    }

    private String[] splitTrimLines(String line) {
        /*
         * This method is used to split and trim the lines of the file.
         * It removes the spaces and tabs from the line.
         * It also removes the comments from the line.
         */
        line = line.trim();
        line = line.toUpperCase();
        String[] splittedLine = line.replaceAll("\\s(\\s)+", " ").split(" ");
        Arrays.asList(splittedLine).forEach(String::trim);
        // remove all strings after #
        int index = Arrays.asList(splittedLine).indexOf("#");
        return (index == -1) ? splittedLine : Arrays.copyOf(splittedLine, index);
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
        /*
         * map the opcode to the type J
         * format:
         * opcode address(28)
         * JMP -2
         * convert the address to 2's complement binary
         */
        StringBuilder binary = new StringBuilder();
        binary.append(getOpcodeString(getOpcode(instruction[0])));
        // 2's complement of instruction[1]
        String address;
        if (instruction[1].startsWith("-")) {
            address = Integer.toBinaryString(Integer.parseInt(instruction[1])).substring(1);
            // address is 28 bits, remove leading bits from the sign bit
            address = address.substring(3);
        } else {
            address = Integer.toBinaryString(Integer.parseInt(instruction[1]));
            // pad with 0's to make it 28 bits
            address = String.format("%0" + (INSTRUCTION_SIZE - OPCODE_SIZE) + "d", Integer.parseInt(address));
        }
        binary.append(address);
        return binary.toString();
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
        return getBinary(binary);
    }

    private int getRegister(String string) throws InvalidRegisterException {
        /*
         * slice the string to get the register number
         * format:
         * $R[0-31]
         */
        if (!string.startsWith("$R")) {
            throw new InvalidRegisterException("Invalid register: " + string);
        }
        int registerNumber = Integer.parseInt(string.substring(2));
        if (registerNumber < 0 || registerNumber > 31) {
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

    private String getBinary(int sID) {
        /*
         * java's toBinaryString() method doesn't return the whole binary
         * so this a workaround to get the binary string
         */
        return Long.toBinaryString(sID & 0xffffffffL | 0x100000000L).substring(1);
    }

}
