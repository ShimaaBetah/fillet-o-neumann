package fillet.logger.services;

import fillet.logger.LogSubject;
import fillet.logger.destinations.ConsoleLogger;
import fillet.logger.destinations.FileLogger;

public class InitLoggerService {

    private InitLoggerService() {
        throw new IllegalStateException("Utility class");
    }

    public static LogSubject execute() {
        LogSubject logSubject = new LogSubject();

        logSubject.attachObserver(new ConsoleLogger());
        logSubject.attachObserver(new FileLogger(CreateLogFileService.execute()));

        return logSubject;
    }
}
