package com.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogManager {
    
    static {
        // Auto-load log4j.properties from classpath
        PropertyConfigurator.configure(LogManager.class.getClassLoader()
                .getResource("log4j.properties"));
    }

    public static Logger getLogger(Class<?> clazz) {
        return Logger.getLogger(clazz);
    }
}
