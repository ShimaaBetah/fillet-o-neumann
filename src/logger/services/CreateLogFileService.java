package logger.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateLogFileService {
    private static final String LOG_FILE_PATH = "./src/logger/outputs/";
    static LocalDateTime current = LocalDateTime.now();
    static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
    private static final String LOG_FILE_NAME = "run-" + format.format(current) + ".log";

    private CreateLogFileService() {
        throw new IllegalStateException("Utility class");
    }

    public static BufferedWriter execute() {
        try {
            File dir = new File(LOG_FILE_PATH);
            if (!dir.exists()) {
                dir.mkdir();
            }

            File logFile = new File(LOG_FILE_PATH + LOG_FILE_NAME);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }

            return new BufferedWriter(new FileWriter(logFile, true));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
