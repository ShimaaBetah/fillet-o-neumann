
import exceptions.AddressOutOfRangeException;
import exceptions.InvalidInstructionException;
import exceptions.InvalidRegisterException;
import exceptions.InvalidRegisterNumberException;
import instructions.Instruction;
import instructions.InstructionFactory;
import memory.MainMemory;
import memory.Registers;
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
        if (halt == true)
            return;
        Registers registers = Registers.getInstance();
        MainMemory memory = MainMemory.getInstance();

        int pc = registers.getPC();
        int binaryInstruction = memory.loadInstruction(pc);
        registers.incrementPC();
        pipeline[FETCH_POSITION] = instructionFactory.create(binaryInstruction);
        if (pipeline[FETCH_POSITION] == null) {
            halt = true;
            return;
        }
        System.out.println("Fetching instruction: " + (pipeline[FETCH_POSITION].getPC()));

    }

    public void decode() throws InvalidRegisterNumberException {
        if (pipeline[DECODE_POSITION] != null) {
            System.out.println("Decoding instruction: " + (pipeline[DECODE_POSITION].getPC()));
            pipeline[DECODE_POSITION].decode();
        } else if (pipeline[READ_REGISTERS_POSITION] != null) {
            System.out.println("Decoding instruction: " + (pipeline[READ_REGISTERS_POSITION].getPC()));
            pipeline[READ_REGISTERS_POSITION].readRegisters();
        }
    }

    public void execute() throws Exception {
        if (pipeline[EXECUTE_POSITION1] != null) {
            System.out.println("Executing instruction: " + (pipeline[EXECUTE_POSITION1].getPC()));
            pipeline[EXECUTE_POSITION1].execute();
        } else if (pipeline[EXECUTE_POSITION2] != null) {
            System.out.println("Executing instruction: " + (pipeline[EXECUTE_POSITION2].getPC()));
            pipeline[EXECUTE_POSITION2].execute();
        }
    }

    public void memoryAccess() throws InvalidRegisterNumberException, AddressOutOfRangeException {
        if (pipeline[FETCH_POSITION] != null || pipeline[MEMORY_POSITION] == null) {
            return;
        }
        pipeline[MEMORY_POSITION].memoryAccess();
        System.out.println("Memory accessing instruction: " + (pipeline[MEMORY_POSITION].getPC()));
    }

    public void writeBack() throws Exception {
        if (pipeline[WRITE_BACK_POSITION] == null) {
            return;
        }
        pipeline[WRITE_BACK_POSITION].writeBack();
        System.out.println("Writing back instruction: " + (pipeline[WRITE_BACK_POSITION].getPC()));
    }

    public void updatePipeline() {
        for (int i = PIPELINE_SIZE - 1; i > 0; i--) {
            pipeline[i] = pipeline[i - 1];
        }
        pipeline[0] = null;
    }

    public void nextCycle() throws Exception {
        System.out.println("Cycle number: " + currentCycle);
        if (currentCycle % 2 == 1) {
            fetch();
        }
        decode();
        execute();
        memoryAccess();
        writeBack();
        updatePipeline();
        currentCycle++;
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
            }
        } while (app.hasMoreCycles());

//        Logger.logMainMemory(MainMemory.getInstance());
        Logger.log(MainMemory.getInstance(), 0, 5);
        Logger.logRegisters(Registers.getInstance());
    }
}
