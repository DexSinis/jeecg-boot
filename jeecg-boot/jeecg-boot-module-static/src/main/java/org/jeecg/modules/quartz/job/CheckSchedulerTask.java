//package org.jeecg.module.quartz.job;
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import org.jeecg.constants.CommonConstant;
//import QuartzJob;
//import QuartzTaskLog;
//import IQuartzJobService;
//import QuartzTaskLogService;
//import lombok.extern.slf4j.Slf4j;
//import org.quartz.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.List;
//
//@Component
//@Slf4j
//public class CheckSchedulerTask {
//
//    @Autowired
//    private QuartzTaskLogService quartzTaskLogService;
//
//
//    @Autowired
//    private IQuartzJobService quartzJobService;
//
//    @Autowired
//    private Scheduler scheduler;
//
//    private int count=0;
//    @Scheduled(cron="*/6 * * * * ?")
//    private void process() {
//        // 定时扫描执行sys_quartz_task_log的日志记录，找出失败的日志重新执行
//          QuartzTaskLog quartzTaskLog = new QuartzTaskLog();
//          quartzTaskLog.setSuccessStatus(CommonConstant.SuccessStatus.Error);
//          QueryWrapper<QuartzTaskLog> queryWrapper = new QueryWrapper<QuartzTaskLog>(quartzTaskLog);
//          List<QuartzTaskLog> jobLogs = quartzTaskLogService.list(queryWrapper);
//          for (QuartzTaskLog log : jobLogs){
//              QuartzJob quartzJob = new QuartzJob();
//              quartzJob.setStatus(CommonConstant.STATUS_NORMAL);
//              quartzJob.setId(log.getJobId());
//              QueryWrapper<QuartzJob> queryWrapperjob = new QueryWrapper<QuartzJob>(quartzJob);
//              QuartzJob job = quartzJobService.getOne(queryWrapperjob);
//              if(job!=null){
//                  Integer SuccessStatus = CommonConstant.SuccessStatus.Error;
//                  try {
//
//                      String jobClassName = job.getJobClassName();
//                      JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(job.getJobClassName().trim()).usingJobData("parameter",job.getParameter() ).build();
//                      SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
//                              .withIdentity("trigger3", "group1")
//                              .startAt(new Date())
//                              .withSchedule(
//                                      SimpleScheduleBuilder.simpleSchedule()
//                                              .withIntervalInSeconds(30)
//                                              .withRepeatCount(0))//重复执行的次数，因为加入任务的时候马上执行了，所以不需要重复，否则会多一次。
//                              .build();
//                      scheduler.scheduleJob(jobDetail, simpleTrigger);
//
////                      String jobClassName = job.getJobClassName();
////                      JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(job.getJobClassName().trim()).usingJobData("parameter",job.getParameter() ).build();
////                      // 表达式调度构建器(即任务执行的时间)
////                      CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 */1 * * * ?");
////                      // 按新的cronExpression表达式构建一个新的trigger
////                      CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName).withSchedule(scheduleBuilder).build();
//////                      scheduler.scheduleJob(jobDetail, trigger);
////                      scheduler.resumeJob(JobKey.jobKey(job.getJobClassName().trim()));
//////                      SuccessStatus = CommonConstant.SuccessStatus.SUCCESS;
//                  } catch (Exception e) {
//                      SuccessStatus = CommonConstant.SuccessStatus.Error;
//                      e.printStackTrace();
//                  }finally {
//                      log.setSuccessStatus(SuccessStatus);
//                      log.setUpdateTime(new Date());
//                      quartzTaskLogService.updateById(log);
//                  }
//              }
//
//          }
//          System.out.println("this is scheduler task runing "+(count++));
//    }
//
//    public   Job getClass(String classname) throws Exception {
//        Class<?> class1 = Class.forName(classname);
//        return (Job) class1.newInstance();
//    }
//
//}