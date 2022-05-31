import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;
import instructions.Instruction;
import instructions.InstructionFactory;
import memory.MainMemory;
import memory.Registers;

public class App {

    private Instruction[] pipeline;
    private int currentCycle;
    private InstructionFactory instructionFactory;

    private static final int PIPELINE_SIZE = 7;
    private static final int FETCH_POSITION = 0;
    private static final int DECODE_POSITION = 2;
    private static final int EXECUTE_POSITION = 4;
    private static final int MEMORY_POSITION = 5;
    private static final int WRITE_BACK_POSITION = 6;

    public App() {
        this.pipeline = new Instruction[PIPELINE_SIZE];
        this.currentCycle = 0;
        this.instructionFactory = new InstructionFactory();
    }

    public void fetch() throws AddressOutOfRangeException {
        if (pipeline[MEMORY_POSITION] == null) {
            return;
        }
        Registers registers = Registers.getInstance();
        MainMemory memory = MainMemory.getInstance();

        int pc = registers.getPC();
        int binaryInstruction = memory.loadInstruction(pc);

        registers.incrementPC();
        pipeline[FETCH_POSITION] = instructionFactory.create(binaryInstruction);
    }

    public void decode() {
        if (pipeline[DECODE_POSITION] == null) {
            return;
        }
        pipeline[DECODE_POSITION].decode();
    }

    public void execute() throws Exception {
        if (pipeline[EXECUTE_POSITION] == null) {
            return;
        }
        pipeline[EXECUTE_POSITION].execute();
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
        fetch();
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

    public static void main(String[] args) {
        App app = new App();
        do {
            try {
                app.nextCycle();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (app.hasMoreCycles());
    }
}
