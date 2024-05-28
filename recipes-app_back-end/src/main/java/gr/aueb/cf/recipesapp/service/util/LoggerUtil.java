package gr.aueb.cf.recipesapp.service.util;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerUtil {
    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());
    private static ConsoleHandler consoleHandler;

    static {
        consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.SEVERE);
    }

    public static void logError(String message) {
        logger.severe(message);
    }
}

