package utlis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import instructions.InstructionType;

public class Parser {
    List<String[]> instructions = new ArrayList<>();
    List<String> binaryInstructions = new ArrayList<>();
    HashMap<String, Integer> labels = new HashMap<>();

    private static final String INVALID_INSTRUCTION = "Invalid instruction: ";

    private static final int INSTRUCTION_SIZE = 32;
    private static final int OPCODE_SIZE = 4;
    private static final int REGISTER_SIZE = 5;
    private static final int IMMEDIATE_SIZE = 18;
    private static final int ADDRESS_SIZE = 28;

    public Parser(String programPath) {
        var instructionsFile = new File(programPath);
        try (FileReader fr = new FileReader(instructionsFile)) {
            try (var br = new BufferedReader(fr)) {
                String line;
                int lineNum = 0;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    line = line.toUpperCase();

                    String[] splittedLine = line.replaceAll("\\s(\\s)+", "").split(" ");
                    Arrays.asList(splittedLine).forEach(String::trim);

                    getLabels(line, lineNum, splittedLine);
                    lineNum++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        convertStringToBinary();
    }

    private void getLabels(String line, int lineNum, String[] splittedLine) {
        String label;
        if (splittedLine.length > 2 && (label = hasLabel(splittedLine[0], splittedLine[1])) != null) {
            labels.put(label, lineNum);
            String[] splittedInstruction = line.split(":");
            if (splittedInstruction.length > 1) {
                String[] splittedFinal = splittedInstruction[1].trim().split(" ");
                this.instructions.add(splittedFinal);
            }
        } else {
            this.instructions.add(splittedLine);
        }
    }

    private String hasLabel(String word1, String word2) {
        // this can get the line we have label in
        String res = "";
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) == ':') {
                res = word1.substring(0, i);
                return res;
            }
        }
        if (word2.equals(":"))
            return word1;
        return null;
    }

    private void convertStringToBinary() throws IllegalArgumentException {
        for (String[] instruction : this.instructions) {
            if (instruction.length < 2)
                throw new IllegalArgumentException(INVALID_INSTRUCTION + instruction[0]);
            int opcode = getOpcode(getFirstOperator(instruction));
            if (opcode == -1)
                throw new IllegalArgumentException(INVALID_INSTRUCTION + instruction[0]);
            InstructionType type = getInstructionType(opcode);

            if (type == InstructionType.R) {
                this.binaryInstructions.add(getRType(instruction));
            } else if (type == InstructionType.I) {
                binaryInstructions.add(getIType(instruction));
            } else if (type == InstructionType.J) {
                binaryInstructions.add(getJType(instruction));
            } else {
                throw new IllegalArgumentException(INVALID_INSTRUCTION + instruction[0]);
            }
        }
    }

    private String getJType(String[] instruction) {
        StringBuilder binary = new StringBuilder();
        binary.append(getOpcodeString(getOpcode(instruction[0])));
        binary.append(instruction[1]);
        return binary.toString();
    }

    private String getOpcodeString(int opcode) {
        String binary = Integer.toBinaryString(opcode);
        while (binary.length() < OPCODE_SIZE) {
            binary = "0" + binary;
        }
        return binary;
    }

    private String getIType(String[] instruction) {
        int opcode = getOpcode(getFirstOperator(instruction));
        int rs = getRegister(instruction[1]);
        int rt = getRegister(instruction[2]);
        int immediate = getImmediate(instruction[3]);
        int binary = (opcode << INSTRUCTION_SIZE - OPCODE_SIZE);
        binary |= (rs << INSTRUCTION_SIZE - OPCODE_SIZE - REGISTER_SIZE);
        binary |= (rt << INSTRUCTION_SIZE - OPCODE_SIZE - 2 * REGISTER_SIZE);
        binary |= immediate;
        return getBinary(binary);
    }

    private int getImmediate(String string) {
        return Integer.parseInt(string);
    }

    private String getRType(String[] instruction) {
        var opcode = getOpcode(instruction[0].toUpperCase());
        int rs = getRegister(instruction[1]);
        int rt = getRegister(instruction[2]);
        int rd = getRegister(instruction[3]);
        int shamt;
        try {
            shamt = getShamt(instruction[4]);
        } catch (Exception e) {
            shamt = 0;
        }
        // opcode R1 R2 R3 SHAMT
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

    private int getRegister(String string) {
        // slice the string to get the register number
        // $R2 -> 2
        return Integer.parseInt(string.substring(2));
    }

    private InstructionType getInstructionType(int opcode) {
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

    private String getFirstOperator(String[] instruction) {
        return instruction[0].toUpperCase();
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
