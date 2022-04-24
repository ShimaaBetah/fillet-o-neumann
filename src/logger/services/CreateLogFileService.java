package logger.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class CreateLogFileService {
    private static final String LOG_FILE_PATH = "./src/logger/logs/log.txt";

    public static BufferedWriter createLogFile() {
        try {
            File logFile = new File(LOG_FILE_PATH);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }

            return new BufferedWriter(new FileWriter(logFile, true));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
