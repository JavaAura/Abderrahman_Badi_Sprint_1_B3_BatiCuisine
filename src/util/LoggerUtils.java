package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.LogManager;

public class LoggerUtils {
    public static final Logger logger = Logger.getLogger(LoggerUtils.class.getName());

    static {
        try {
            LogManager.getLogManager().reset();

            FileHandler fileHandler = new FileHandler("src/logs/app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());

            logger.addHandler(fileHandler);

            logger.setLevel(java.util.logging.Level.ALL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String stackTrace = sw.toString();

        LoggerUtils.logger.warning("Stack trace : " + stackTrace);
    }

}
