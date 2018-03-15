package com.freesheep.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public final class Setting {
    private static Logger logger = LoggerFactory.getLogger(Setting.class);
    private static Properties properties = new Properties();

    static {
        String webSiteUrl = Setting.class.getResource("/").getPath();
        String filePath = webSiteUrl + "conf/setting.properties";
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream(filePath), "utf-8");
            properties.load(in);
            in.close();
        } catch (IOException e) {
            logger.error("[ot-error]-setting.properties init fail", e);
            System.exit(1);
        }
        for (Object key : properties.keySet()) {
            if (StringUtils.isBlank(properties.getProperty((String) key))) {
                logger.error("setting.properties key:" + key + " not value");
                System.exit(1);
                break;
            }
        }
    }

     public static String getValue(String key) {
        if (StringUtils.isNotBlank(key)) {
            Object o = properties.getProperty(key);
            return String.valueOf(o).trim();
        } else {
            logger.error("[ot-error]-setting.properties init fail");
            return "";
        }
    }
}
