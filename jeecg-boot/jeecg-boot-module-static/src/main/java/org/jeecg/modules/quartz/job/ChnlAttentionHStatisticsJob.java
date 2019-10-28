package org.jeecg.modules.quartz.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.constants.CommonConstant;
import org.jeecg.modules.quartz.entity.QuartzJob;
import org.jeecg.modules.quartz.entity.QuartzTaskLog;
import org.jeecg.modules.quartz.service.IQuartzJobService;
import org.jeecg.modules.quartz.service.QuartzTaskLogService;
import org.jeecg.modules.statistics.service.ChnlAttentionHService;
import org.jeecg.util.date.ZxDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * 定时任务统计每2小时的渠道数据（按小时统计）
 */
@Component("chnlAttentionHStatisticsJob")
@Slf4j
public class ChnlAttentionHStatisticsJob  {

    @Resource
    QuartzTaskLogService quartzTaskLogService;

    @Resource
    IQuartzJobService quartzJobService;

    @Resource
    private ChnlAttentionHService chnlAttentionHService;


    public void execute() throws JobExecutionException {


        QuartzTaskLog quartzTaskLog = new QuartzTaskLog();
        quartzTaskLog.setSuccessStatus(CommonConstant.SuccessStatus.Error);
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setJobClassName(this.getClass().getName());
        QueryWrapper<QuartzJob> queryWrapper = new QueryWrapper<QuartzJob>(quartzJob);
        QuartzJob quartzJobRecord  = quartzJobService.getOne(queryWrapper);
        if(quartzJobRecord!=null){
            quartzTaskLog.setJobId(quartzJobRecord.getId());
        }
        quartzTaskLog.setCreateTime(new Date());
        quartzTaskLog.setStartTime(new Date());
        quartzTaskLog.setUpdateTime(new Date());
        quartzTaskLog.setJobId(this.getClass().getName());
        quartzTaskLogService.save(quartzTaskLog);


        log.error(String.format("ChnlAttentionHStatisticsJob >>>>开始时间:" + ZxDateUtils.now()));
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Date after = calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY)-2);
        Date before = calendar.getTime();

        chnlAttentionHService.refreshChnlAttentionHByTime(before,after);

        log.error("ChnlAttentionHStatisticsJob >>>>结束时间:{}",ZxDateUtils.now());

        quartzTaskLog.setEndTime(new Date());
        quartzTaskLog.setSuccessStatus(CommonConstant.SuccessStatus.SUCCESS);
        quartzTaskLogService.updateById(quartzTaskLog);
    }


//    public static void main(String[] args){
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        System.out.println("当前小时======::"+((Integer)(new Date().getHours())).toString());
//        System.out.println("calendar.getTime()当前时间======::"+calendar.getTime().getTime());
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        calendar.set(Calendar.HOUR_OF_DAY,0);
//        calendar.set(Calendar.MINUTE,0);
//        calendar.set(Calendar.SECOND,0);
//        Date before = calendar.getTime();
//        System.out.println("calendar.get(Calendar.HOUR_OF_DAY)======::"+calendar.getTime().getTime());
//        log.info("ConsulterDStatisticsJob >>>>开始时间::[{};{}]",df.format(before));
//        calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY)-2);
//        log.info("ConsulterDStatisticsJob >>>>减时后时间::[{};{}]",df.format(calendar.getTime()));
//        System.out.println("calendar.getTime()当前时间减2小时======::"+before.getHours());
//        calendar.set(Calendar.HOUR_OF_DAY,23);
//        calendar.set(Calendar.MINUTE,59);
//        calendar.set(Calendar.SECOND,59);
//        Date after = calendar.getTime();
//
//        log.info("ConsulterDStatisticsJob >>>>开始时间::[{};{}]",df.format(before),df.format(after));
//        log.info("ConsulterDStatisticsJob >>>>结束时间:{}",df.format(after));
//    }

}
