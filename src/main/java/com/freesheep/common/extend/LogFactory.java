package com.freesheep.common.extend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFactory {
    public static Logger getLogger(Class<?> clazz) {
        Logger logger = LoggerFactory.getLogger(clazz);
        return logger;
    }
}
