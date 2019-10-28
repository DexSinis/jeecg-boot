package org.jeecg.modules.quartz;

import org.jeecg.modules.quartz.entity.ScheduleJob;
import org.jeecg.util.SpringUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.lang.reflect.Method;

public class QuartzFactory implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //获取调度数据
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");

        //获取对应的Bean
        Object object = SpringUtil.getBean(scheduleJob.getBeanName());
        try {
            //利用反射执行对应方法
            Method method = object.getClass().getMethod(scheduleJob.getMethodName());
            method.invoke(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
