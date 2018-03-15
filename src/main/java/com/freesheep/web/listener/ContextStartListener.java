package com.freesheep.web.listener;


import com.freesheep.common.util.ExecutorsUtil;
import com.freesheep.web.config.WebConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextStartListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("ENV", WebConfig.ENVIRONMENT);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        ExecutorsUtil.shutdown();
    }
}
