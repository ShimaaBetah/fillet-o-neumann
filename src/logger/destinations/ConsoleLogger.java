package logger.destinations;

public class ConsoleLogger implements LogObserver {
    public void log(String message) {
        System.out.print(message);
    }
}
