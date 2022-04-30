package logger.destinations;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileLogger implements LogObserver {

    private final BufferedWriter bufferedWriter;

    public FileLogger(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    @Override
    public void log(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
