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
            bufferedWriter.write(removeColor(message));
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String removeColor(String message) {
        return message.replaceAll("(\\033\\[[0-1];3[0-9]m)|(\033\\[0m)", "");
    }
}
