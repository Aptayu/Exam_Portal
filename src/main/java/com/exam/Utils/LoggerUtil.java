package com.exam.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerUtil.class);

    private static LoggerUtil loggerInstance;

    private LoggerUtil() {}

    public static synchronized Logger getInstance() {
        if(loggerInstance == null) {
            loggerInstance = new LoggerUtil();
        }
        return LOGGER;
    }
}
