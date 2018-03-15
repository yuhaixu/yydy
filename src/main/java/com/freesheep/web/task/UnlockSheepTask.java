package com.freesheep.web.task;

import com.freesheep.biz.service.StSheepService;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UnlockSheepTask implements Job {

    @Autowired
    private StSheepService sheepService;
    private static UnlockSheepTask task;

    public void setTask(UnlockSheepTask sheepTask){
        task = sheepTask;
    }

    @PostConstruct
    public void init(){
        task = this;
        task.sheepService = this.sheepService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail detail = jobExecutionContext.getJobDetail();
        long sheepId = detail.getJobDataMap().getLong("sheepId");
        int row = task.sheepService.unLockSheepById(sheepId);
        System.out.println("=========================");
        System.out.println("定时任务执行成功 " + row);
        System.out.println("=========================");

    }
}
