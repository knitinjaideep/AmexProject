package com.amex.school.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * The GlobalLogger class provides logging functionalities for the application.
 * It encapsulates a logger instance from the SLF4J library for logging informational,
 * warning, and error messages.
 */
@Component
public class GlobalLogger {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void logInfo(String message) {
        logger.info(message);
    }

    public void logInfo(String message, Object... args) {
        logger.info(message, args);
    }

    public void logWarning(String message) {
        logger.warn(message);
    }

    public void logWarning(String message, Object... args) {
        logger.warn(message, args);
    }

    public void logError(String message) {
        logger.error(message);
    }

    public void logError(String message, Throwable throwable, Object... args) {
        logger.error(message, throwable, args);
    }
}
