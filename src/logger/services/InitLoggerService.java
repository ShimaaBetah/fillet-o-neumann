package logger.services;

import logger.LogSubject;
import logger.destinations.ConsoleLogger;
import logger.destinations.FileLogger;

public class InitLoggerService {
    public static LogSubject execute() {
        LogSubject logSubject = new LogSubject();

        logSubject.attachObserver(new ConsoleLogger());
        logSubject.attachObserver(new FileLogger(CreateLogFileService.execute()));

        return logSubject;
    }
}
