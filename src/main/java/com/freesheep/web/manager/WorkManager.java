package com.freesheep.web.manager;

import com.freesheep.web.task.UnlockSheepTask;
import com.freesheep.web.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;

import java.util.Date;

public class WorkManager {

    public static void removeTask(String uidStr, long sheepId) {
        removeTask(null, uidStr, sheepId);
    }

    public static void removeTask(String name, String uidStr, long sheepId) {
        try {
            SchedulerManager manager = SchedulerManager.getInstance();
            manager.start();
            Scheduler scheduler = manager.getScheduler();
            if(StringUtils.isBlank(name)) {
                 name = uidStr + "_" + String.valueOf(sheepId);
            }

            TriggerKey triggerKey = new TriggerKey(name + "_trigger", "sheepJobGroup");
            JobKey jobKey = new JobKey(name + "_job", "sheepJobGroup");
            scheduler.deleteJob(jobKey);
            scheduler.unscheduleJob(triggerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static void unlock(String uidStr, long sheepId, Class<? extends org.quartz.Job> jobClass) {
        unlock(null, uidStr, sheepId, jobClass);
    }


    public static void unlock(String name, String uidStr, long sheepId, Class<? extends org.quartz.Job> jobClass) {
        try {
            SchedulerManager manager = SchedulerManager.getInstance();
            manager.start();
            Scheduler scheduler = manager.getScheduler();
            if(StringUtils.isBlank(name)) {
                name = uidStr + "_" + String.valueOf(sheepId);
            }
            JobDetail job = JobBuilder.newJob(jobClass)
                    .usingJobData("uid", uidStr)
                    .usingJobData("sheepId", sheepId)
                    .withIdentity(name + "_job", "sheepJobGroup")
                    .build();

            // 十分钟之后执行解锁操作
            Date start = DateUtils.getAfterMinuterDate(17);
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name + "_trigger", "sheepJobGroup")
                    .startAt(start)
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    )
                    .build();
            scheduler.scheduleJob(job, trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
