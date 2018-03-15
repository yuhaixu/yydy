package com.freesheep.web.manager;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerManager {

    private static SchedulerManager instance;
    private Scheduler scheduler;

    private SchedulerManager() throws SchedulerException {
        scheduler = StdSchedulerFactory.getDefaultScheduler();
    }

    public static SchedulerManager getInstance() throws SchedulerException {
        if( instance == null ){
            synchronized (SchedulerManager.class){
                if(instance == null){
                    instance = new SchedulerManager();
                }
            }
        }
        return instance;
    }

    public Scheduler getScheduler(){
        return scheduler;
    }

    public void start() throws SchedulerException {
        if(!scheduler.isStarted()){
            scheduler.start();
        }
    }

    /**
     * 关闭调度信息
     * @throws SchedulerException
     */
    public void shutDown() throws SchedulerException {
        if (!scheduler.isShutdown()){
            scheduler.shutdown();
        }
    }



}
