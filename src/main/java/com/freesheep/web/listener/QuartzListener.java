package com.freesheep.web.listener;

import com.freesheep.web.manager.SchedulerManager;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class QuartzListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            SchedulerManager manager = SchedulerManager.getInstance();
            Scheduler scheduler = manager.getScheduler();
            scheduler.shutdown(true);
            Thread.sleep(1000);
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
