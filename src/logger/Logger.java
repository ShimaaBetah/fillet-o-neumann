package logger;

import logger.services.InitLoggerService;

public class Logger {
    private static Logger instance;
    private static LogSubject logSubject;

    private Logger() {
        logSubject = InitLoggerService.execute();
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }

        return instance;
    }

    public void log(String message) {
        logSubject.notifyObservers(message);
    }
}
