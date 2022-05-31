package logger;

import logger.services.InitLoggerService;
import logger.services.LogEntityService;
import logger.services.SegmentType;
import memory.MainMemory;
import memory.Registers;

public class Logger {
    private static final LogSubject logSubject = InitLoggerService.execute();

    private Logger() {
    }

    public static void log(String message) {
        logSubject.notifyObservers(message);
    }

    public static void logln(String message) {
        log(message + "\n");
    }

    public static void log(MainMemory memory, int address, SegmentType segmentType) {
        if (segmentType == SegmentType.DATA) {
            log(LogEntityService.logMainMemoryData(memory, address, address));
        } else {
            log(LogEntityService.logMainMemoryInstructions(memory, address, address));
        }
    }

    public static void log(MainMemory memory, int startAddress, int endAddress,SegmentType segmentType) {
        if (segmentType == SegmentType.DATA) {
            log(LogEntityService.logMainMemoryData(memory, startAddress, endAddress));
        } else {
            log(LogEntityService.logMainMemoryInstructions(memory, startAddress, endAddress));
        }
    }

    public static void log(Registers registers, int[] registersToLog) {
        log(LogEntityService.logRegisters(registers, registersToLog));
    }
}
