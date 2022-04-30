package logger;

import logger.services.InitLoggerService;

public class Logger {
    private static final Logger instance = new Logger();
    private static LogSubject logSubject;

    private Logger() {
        logSubject = InitLoggerService.execute();
    }

    public static Logger getInstance() {
        return instance;
    }

    public void log(String message) {
        logSubject.notifyObservers(message);
    }
}
