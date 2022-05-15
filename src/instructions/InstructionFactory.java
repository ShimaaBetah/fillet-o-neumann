package instructions;

import utils.Decoder;

import java.util.HashMap;

public class InstructionFactory {
    private static final int OPCODE_RANGE_START = 0;
    private static final int OPCODE_RANGE_END = 3;
    private static final HashMap<Integer, InstructionType> opcodeToInstruction = new HashMap<>();

    static {
        opcodeToInstruction.put(0, InstructionType.R_TYPE);
        opcodeToInstruction.put(1, InstructionType.R_TYPE);
        opcodeToInstruction.put(2, InstructionType.R_TYPE);
        opcodeToInstruction.put(3, InstructionType.I_TYPE);
        opcodeToInstruction.put(4, InstructionType.I_TYPE);
        opcodeToInstruction.put(5, InstructionType.R_TYPE);
        opcodeToInstruction.put(6, InstructionType.I_TYPE);
        opcodeToInstruction.put(7, InstructionType.J_TYPE);
        opcodeToInstruction.put(8, InstructionType.R_TYPE);
        opcodeToInstruction.put(9, InstructionType.R_TYPE);
        opcodeToInstruction.put(10, InstructionType.I_TYPE);
        opcodeToInstruction.put(11, InstructionType.I_TYPE);
    }

    public Instruction create(int binaryInstruction) {
        int opcode = getOpcode(binaryInstruction);
        InstructionType instructionType = opcodeToInstruction.get(opcode);
        Instruction instruction = null;
        if (instructionType == InstructionType.R_TYPE) {
            instruction = new RegisterInstruction(binaryInstruction);
        } else if (instructionType == InstructionType.I_TYPE) {
            instruction = new ImmediateInstruction(binaryInstruction);
        } else if (instructionType == InstructionType.J_TYPE) {
            instruction = new JumpInstruction(binaryInstruction);
        }
        return instruction;
    }

    private int getOpcode(int binaryInstruction) {
        return Decoder.getIntValueOfBinarySegment(binaryInstruction, OPCODE_RANGE_START, OPCODE_RANGE_END);
    }
}
