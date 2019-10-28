package org.jeecg.modules.quartz.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.constants.CommonConstant;
import org.jeecg.modules.quartz.entity.QuartzJob;
import org.jeecg.modules.quartz.entity.QuartzTaskLog;
import org.jeecg.modules.quartz.service.IQuartzJobService;
import org.jeecg.modules.quartz.service.QuartzTaskLogService;
import org.jeecg.modules.statistics.service.ChnlCustAttentionDService;
import org.jeecg.util.date.ZxDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * 定时任务统计前一天的渠道数据（按天统计）
 */
@Component("chnlCustAttentionDStatisticsJob")
@Slf4j
public class ChnlCustAttentionDStatisticsJob  {

    @Resource
    QuartzTaskLogService quartzTaskLogService;

    @Resource
    IQuartzJobService quartzJobService;
    @Resource
    private ChnlCustAttentionDService chnlCustAttentionDService;


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

        log.error(String.format("ChnlCustAttentionDStatisticsJob >>>>开始时间:" + ZxDateUtils.now()));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        Date before = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        Date after = calendar.getTime();

        chnlCustAttentionDService.refreshChnlCustAttentionDByTime(before,after);

        log.error("ChnlCustAttentionDStatisticsJob >>>>结束时间:{}",ZxDateUtils.now());

        quartzTaskLog.setEndTime(new Date());
        quartzTaskLog.setSuccessStatus(CommonConstant.SuccessStatus.SUCCESS);
        quartzTaskLogService.updateById(quartzTaskLog);
    }


//    public static void main(String[] args){
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.DAY_OF_MONTH,-1);
//        calendar.set(Calendar.HOUR_OF_DAY,0);
//        calendar.set(Calendar.MINUTE,0);
//        calendar.set(Calendar.SECOND,0);
//        Date before = calendar.getTime();
//
//        calendar.set(Calendar.HOUR_OF_DAY,23);
//        calendar.set(Calendar.MINUTE,59);
//        calendar.set(Calendar.SECOND,59);
//        Date after = calendar.getTime();
//
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        log.info("ConsulterDStatisticsJob >>>>开始时间::[{};{}]",df.format(before),df.format(after));
//        log.info("ConsulterDStatisticsJob >>>>结束时间:{}",df.format(after));
//        System.out.println("before======::"+before.getTime());
//        System.out.println("after======::"+after.getTime());
//
//        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
//        log.info("ConsulterDStatisticsJob >>>>开始时间::[{};{}]",sd.format(before),sd.format(after));
//        log.info("ConsulterDStatisticsJob >>>>开始时间::[{};{}]",Integer.valueOf(sd.format(before)),Integer.valueOf(sd.format(after)));
//
//    }

}
