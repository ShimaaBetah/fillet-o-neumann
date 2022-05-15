package instructions;

import utils.Decoder;

import java.util.HashMap;

public class InstructionFactory {
    private static final int OPCODE_RANGE_START = 0;
    private static final int OPCODE_RANGE_END = 3;
    private static final HashMap<Integer, InstructionType> opcodeToInstruction = new HashMap<>();

    static {
        opcodeToInstruction.put(0, InstructionType.RType);
        opcodeToInstruction.put(1, InstructionType.RType);
        opcodeToInstruction.put(2, InstructionType.RType);
        opcodeToInstruction.put(3, InstructionType.IType);
        opcodeToInstruction.put(4, InstructionType.IType);
        opcodeToInstruction.put(5, InstructionType.RType);
        opcodeToInstruction.put(6, InstructionType.IType);
        opcodeToInstruction.put(7, InstructionType.JType);
        opcodeToInstruction.put(8, InstructionType.RType);
        opcodeToInstruction.put(9, InstructionType.RType);
        opcodeToInstruction.put(10, InstructionType.IType);
        opcodeToInstruction.put(11, InstructionType.IType);
    }

    public Instruction create(int binaryInstruction) {
        int opcode = getOpcode(binaryInstruction);
        InstructionType instructionType = opcodeToInstruction.get(opcode);
        Instruction instruction = null;
        if (instructionType == InstructionType.RType) {
            instruction = new RegisterInstruction(binaryInstruction);
        } else if (instructionType == InstructionType.IType) {
            instruction = new ImmediateInstruction(binaryInstruction);
        } else if (instructionType == InstructionType.JType) {
            instruction = new JumpInstruction(binaryInstruction);
        }
        return instruction;
    }

    private int getOpcode(int binaryInstruction) {
        return Decoder.getIntValueOfBinarySegment(binaryInstruction, OPCODE_RANGE_START, OPCODE_RANGE_END);
    }
}
