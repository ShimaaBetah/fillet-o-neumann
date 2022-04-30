package logger.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateLogFileService {
    private static final String LOG_FILE_PATH = "./src/logger/outputs/log.txt";

    private CreateLogFileService() {
        throw new IllegalStateException("Utility class");
    }

    public static BufferedWriter execute() {
        try {
            File logFile = new File(LOG_FILE_PATH);
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
