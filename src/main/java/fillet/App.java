package fillet;

import fillet.exceptions.AddressOutOfRangeException;
import fillet.exceptions.InvalidInstructionException;
import fillet.exceptions.InvalidRegisterException;
import fillet.exceptions.InvalidRegisterNumberException;
import fillet.instructions.Instruction;
import fillet.instructions.InstructionFactory;
import fillet.logger.Logger;
import fillet.memory.MainMemory;
import fillet.memory.RegisterFile;
import fillet.operations.immediateoperations.JumpIfEqual;
import fillet.operations.jumpoperations.Jump;
import fillet.signals.Signals;
import fillet.utils.Parser;
import fillet.utils.Program;

public class App {

    private Instruction[] pipeline;
    private int currentCycle;
    private InstructionFactory instructionFactory;
    private Program parser;

    private static final int PIPELINE_SIZE = 7;
    private static final int FETCH_POSITION = 0;
    private static final int DECODE_POSITION = 1;
    private static final int READ_REGISTERS_POSITION = 2;
    private static final int EXECUTE_POSITION2 = 4;
    private static final int MEMORY_POSITION = 5;
    private static final int WRITE_BACK_POSITION = 6;

    public App(String path) throws AddressOutOfRangeException {
        this.pipeline = new Instruction[PIPELINE_SIZE];
        this.currentCycle = 1;
        this.instructionFactory = new InstructionFactory();
        try {
            parser = new Program(new Parser(path));
        } catch (InvalidInstructionException e) {
            throw new RuntimeException(e);
        } catch (InvalidRegisterException e) {
            throw new RuntimeException(e);
        }
        loadProgramToMemory();
    }

    public void loadProgramToMemory() throws AddressOutOfRangeException {
        int[] program = parser.getIntegerInstructions().stream().mapToInt(i -> i).toArray();
        for (int i = 0; i < program.length; i++) {
            MainMemory.getInstance().storeInstruction(i, program[i]);

        }
        // adding halt instruction
        MainMemory.getInstance().storeInstruction(program.length, -1);
    }

    public void fetch() throws AddressOutOfRangeException {
        RegisterFile registerFile = RegisterFile.getInstance();
        MainMemory memory = MainMemory.getInstance();

        int pc = registerFile.getPC();
        int binaryInstruction = memory.loadInstruction(pc);
        registerFile.incrementPC();
        pipeline[FETCH_POSITION] = instructionFactory.create(binaryInstruction);
    }

    public void decode() throws InvalidRegisterNumberException {
        if (pipeline[DECODE_POSITION] != null) {
            pipeline[DECODE_POSITION].decode();
        } else if (pipeline[READ_REGISTERS_POSITION] != null) {
            pipeline[READ_REGISTERS_POSITION].readRegisters();
        }
    }

    public void execute() throws Exception {
        if (pipeline[EXECUTE_POSITION2] == null) return;
        pipeline[EXECUTE_POSITION2].execute();
        if (isJumpingActionExecuting()) {
            handlePipelineInCaseOfJumpAction();
        }
    }

    private boolean isJumpingActionExecuting() {
        return pipeline[EXECUTE_POSITION2].getOperation() instanceof Jump || pipeline[EXECUTE_POSITION2].getOperation() instanceof JumpIfEqual jumpIfEqual && jumpIfEqual.haveJumped();
    }

    private void handlePipelineInCaseOfJumpAction() {
        for (int stage = FETCH_POSITION; stage < EXECUTE_POSITION2; stage++) {
            pipeline[stage] = null;
        }
    }

    public void memoryAccess() throws InvalidRegisterNumberException, AddressOutOfRangeException {
        if (pipeline[FETCH_POSITION] != null || pipeline[MEMORY_POSITION] == null) return;
        pipeline[MEMORY_POSITION].memoryAccess();
    }

    public void writeBack() throws Exception {
        if (pipeline[WRITE_BACK_POSITION] == null) return;
        pipeline[WRITE_BACK_POSITION].writeBack();
    }

    public void updatePipeline() {
        System.arraycopy(pipeline, 0, pipeline, 1, PIPELINE_SIZE - 1);
        pipeline[0] = null;
    }

    public void nextCycle() throws Exception {
        if (currentCycle % 2 == 1) {
            fetch();
        }
        Logger.log(currentCycle, pipeline);
        decode();
        execute();
        memoryAccess();
        writeBack();
        updatePipeline();
        currentCycle++;
        Logger.logln("");
    }

    public static void main(String[] args)
            throws AddressOutOfRangeException {
        String path = "src/main/java/fillet/programs/test-sum.txt";
        App app = new App(path);
        do {
            try {
                app.nextCycle();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        } while (!Signals.getInstance().getHaltSignal());

        Logger.log(MainMemory.getInstance());
        Logger.log(RegisterFile.getInstance());
    }
}
