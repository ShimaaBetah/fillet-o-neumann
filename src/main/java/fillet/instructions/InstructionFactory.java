package fillet.instructions;

import fillet.utils.Decoder;

import fillet.memory.RegisterFile;
import org.jetbrains.annotations.Nullable;

public class InstructionFactory {
    private static final int OPCODE_RANGE_START = 0;
    private static final int OPCODE_RANGE_END = 3;

    public Instruction create(int binaryInstruction) {
        InstructionType instructionType = getInstructionType(binaryInstruction);
        Instruction instruction = getInstruction(binaryInstruction, instructionType);
        if (instruction != null)
            instruction.setPC(RegisterFile.getInstance().getPC());
        return instruction;
    }

    private InstructionType getInstructionType(int binaryInstruction) {
        int opcode = getOpcode(binaryInstruction);
        switch (opcode) {
            case 0, 1, 2, 5, 8, 9:
                return InstructionType.R_TYPE;
            case 3, 4, 6, 10, 11:
                return InstructionType.I_TYPE;
            case 7:
                return InstructionType.J_TYPE;
            case 15:
                return InstructionType.HALT_TYPE;
            default:
                throw new IllegalArgumentException("Unknown opcode: " + opcode);
        }
    }

    @Nullable
    private Instruction getInstruction(int binaryInstruction, InstructionType instructionType) {
        if (instructionType == InstructionType.R_TYPE) {
            return new RegisterInstruction(binaryInstruction);
        }
        if (instructionType == InstructionType.I_TYPE) {
            return new ImmediateInstruction(binaryInstruction);
        }
        if (instructionType == InstructionType.J_TYPE) {
            return new JumpInstruction(binaryInstruction);
        }
        if (instructionType == InstructionType.HALT_TYPE) {
            return new HaltInstruction(binaryInstruction);
        }
        return null;
    }

    private int getOpcode(int binaryInstruction) {
        return Decoder.getIntValueOfBinarySegment(binaryInstruction, OPCODE_RANGE_START, OPCODE_RANGE_END);
    }
}
