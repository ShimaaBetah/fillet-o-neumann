package fillet.logger;

import fillet.instructions.Instruction;
import fillet.logger.services.InitLoggerService;
import fillet.logger.services.LogEntityService;
import fillet.memory.MainMemory;
import fillet.memory.RegisterFile;

public class Logger {
    private static final LogSubject logSubject = InitLoggerService.execute();

    private Logger() {
        throw new IllegalStateException("Utility class");
    }

    public static void log(String message) {
        logSubject.notifyObservers(message);
    }

    public static void logln(String message) {
        log(message + "\n");
    }

    public static void log(MainMemory memory, int address) {
        log(LogEntityService.logMainMemoryWord(memory, address, address));
    }

    public static void log(MainMemory memory, int startAddress, int endAddress) {
        log(LogEntityService.logMainMemoryWord(memory, startAddress, endAddress));
    }

    public static void log(MainMemory memory) {
        logln(LogEntityService.logMainMemoryWord(memory, MainMemory.getInstance().getInstructionRangeStart(), MainMemory.getInstance().getDataRangeEnd()));
    }

    public static void log(RegisterFile registerFile, int[] registersToLog) {
        logln(LogEntityService.logRegisters(registerFile, registersToLog));
    }

    public static void log(RegisterFile registerFile) {
        int[] registersToLog = new int[RegisterFile.NUM_OF_REGISTERS];
        for (int i = 0; i < RegisterFile.NUM_OF_REGISTERS; i++) {
            registersToLog[i] = i;
        }
        log(registerFile, registersToLog);
    }

    public static void log(int cycle, Instruction[] pipeline) {
        logln(LogEntityService.logPipeline(cycle, pipeline));
    }
}
