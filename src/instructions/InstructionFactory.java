package instructions;

import utils.Decoder;

import java.util.HashMap;

public class InstructionFactory {
    private static final int OPCODE_RANGE_START = 0;
    private static final int OPCODE_RANGE_END = 3;
    private static final HashMap<Integer, InstructionType> opcodeMap = new HashMap<>();

    static {
        opcodeMap.put(0, InstructionType.RType);
        opcodeMap.put(1, InstructionType.RType);
        opcodeMap.put(2, InstructionType.RType);
        opcodeMap.put(3, InstructionType.IType);
        opcodeMap.put(4, InstructionType.IType);
        opcodeMap.put(5, InstructionType.RType);
        opcodeMap.put(6, InstructionType.IType);
        opcodeMap.put(7, InstructionType.JType);
        opcodeMap.put(8, InstructionType.RType);
        opcodeMap.put(9, InstructionType.RType);
        opcodeMap.put(10, InstructionType.IType);
        opcodeMap.put(11, InstructionType.IType);
    }

    public Instruction create(int binaryInstruction) {
        int opcode = getOpcode(binaryInstruction);
        InstructionType instructionType = opcodeMap.get(opcode);
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
