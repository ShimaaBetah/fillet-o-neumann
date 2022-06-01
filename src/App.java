
import exceptions.AddressOutOfRangeException;
import exceptions.InvalidInstructionException;
import exceptions.InvalidRegisterException;
import exceptions.InvalidRegisterNumberException;
import instructions.Instruction;
import instructions.InstructionFactory;
import logger.services.SegmentType;
import memory.MainMemory;
import memory.RegisterFile;
import operations.immediateoperations.JumpIfEqual;
import operations.jumpoperations.Jump;
import utils.Program;
import logger.Logger;

public class App {

    private Instruction[] pipeline;
    private int currentCycle;
    private InstructionFactory instructionFactory;
    private Program parser;
    private boolean halt = false;

    private static final int PIPELINE_SIZE = 7;
    private static final int FETCH_POSITION = 0;
    private static final int DECODE_POSITION = 1;
    private static final int READ_REGISTERS_POSITION = 2;
    private static final int EXECUTE_POSITION1 = 3;
    private static final int EXECUTE_POSITION2 = 4;
    private static final int MEMORY_POSITION = 5;
    private static final int WRITE_BACK_POSITION = 6;

    public App(String path) throws InvalidInstructionException, InvalidRegisterException, AddressOutOfRangeException {
        this.pipeline = new Instruction[PIPELINE_SIZE];
        this.currentCycle = 1;
        this.instructionFactory = new InstructionFactory();
        parser = new Program(path);
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
        if (halt) return;
        RegisterFile registerFile = RegisterFile.getInstance();
        MainMemory memory = MainMemory.getInstance();

        int pc = registerFile.getPC();
        int binaryInstruction = memory.loadInstruction(pc);
        registerFile.incrementPC();
        pipeline[FETCH_POSITION] = instructionFactory.create(binaryInstruction);
        if (pipeline[FETCH_POSITION] == null) {
            halt = true;
            return;
        }
    }

    public void decode() throws InvalidRegisterNumberException {
        if (pipeline[DECODE_POSITION] != null) {
            pipeline[DECODE_POSITION].decode();
        } else if (pipeline[READ_REGISTERS_POSITION] != null) {
            pipeline[READ_REGISTERS_POSITION].readRegisters();
        }
    }

    public void execute() throws Exception {
       if (pipeline[EXECUTE_POSITION2] != null) {

            //TODO: needs some refactor
            if (pipeline[EXECUTE_POSITION2].getOperation() instanceof Jump) {
                for (int stage = FETCH_POSITION; stage < EXECUTE_POSITION2; stage++) {
                    pipeline[stage] = null;
                }
            } else if (pipeline[EXECUTE_POSITION2].getOperation() instanceof JumpIfEqual) {
                if (((JumpIfEqual) pipeline[EXECUTE_POSITION2].getOperation()).haveJumped()) {
                    for (int stage = FETCH_POSITION; stage < EXECUTE_POSITION2; stage++) {
                        pipeline[stage] = null;
                    }
                }
            }

            pipeline[EXECUTE_POSITION2].execute();
        }
    }

    public void memoryAccess() throws InvalidRegisterNumberException, AddressOutOfRangeException {
        if (pipeline[FETCH_POSITION] != null || pipeline[MEMORY_POSITION] == null) {
            return;
        }
        pipeline[MEMORY_POSITION].memoryAccess();
    }

    public void writeBack() throws Exception {
        if (pipeline[WRITE_BACK_POSITION] == null) {
            return;
        }
        pipeline[WRITE_BACK_POSITION].writeBack();
    }

    public void updatePipeline() {
        for (int i = PIPELINE_SIZE - 1; i > 0; i--) {
            pipeline[i] = pipeline[i - 1];
        }
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

    public boolean hasMoreCycles() {
        for (Instruction instruction : pipeline) {
            if (instruction != null) {
                return true;
            }
        }
        return false;
    }

    public int getCurrentCycle() {
        return currentCycle;
    }

    public static void main(String[] args)
            throws InvalidInstructionException, InvalidRegisterException, AddressOutOfRangeException {
        String path = "src/programs/program1.txt";
        App app = new App(path);

        do {
            try {
                app.nextCycle();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        } while (app.hasMoreCycles());

        Logger.log(MainMemory.getInstance());
        Logger.log(RegisterFile.getInstance());
    }
}
