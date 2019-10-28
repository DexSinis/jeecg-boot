package org.jeecg.modules.quartz.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.constants.CommonConstant;
import org.jeecg.modules.consulter.service.ConsulterService;
import org.jeecg.modules.quartz.entity.QuartzJob;
import org.jeecg.modules.quartz.entity.QuartzTaskLog;
import org.jeecg.modules.quartz.service.IQuartzJobService;
import org.jeecg.modules.quartz.service.QuartzTaskLogService;
import org.jeecg.modules.statistics.service.PersUserDService;
import org.jeecg.util.date.ZxDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * 定时任务统计用户数据
 */
@Component("consulterDStatisticsJob")
@Slf4j
public class ConsulterDStatisticsJob  {
    @Resource
    QuartzTaskLogService quartzTaskLogService;

    @Resource
    IQuartzJobService quartzJobService;

    @Resource
    private ConsulterService consulterService;

    @Resource
    private PersUserDService persUserDService;


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

        log.error(String.format("ConsulterDStatisticsJob >>>>开始时间:" + ZxDateUtils.now()));
        Map consulter = consulterService.findMaxConsulterId();
        Integer maxConsulterId = (Integer)consulter.get("id");
        Integer beforeConsulterId = 0;
        Integer afterConsulterId = 0;
        boolean flag = persUserDService.clearAllConsulterAnalysis();
        if(!flag){
            return;
        }
        for (int i = 0; i < maxConsulterId; i = i + 200000) {
            beforeConsulterId = i;
            afterConsulterId = i + 200000;
            if(afterConsulterId>maxConsulterId){
                afterConsulterId = maxConsulterId;
            }
            persUserDService.insertconsulterDForJob(beforeConsulterId.toString(),afterConsulterId.toString());
            if(afterConsulterId>=maxConsulterId){
                break;
            }
        }
        log.error("ConsulterDStatisticsJob >>>>结束时间:{}",ZxDateUtils.now());

        quartzTaskLog.setEndTime(new Date());
        quartzTaskLog.setSuccessStatus(CommonConstant.SuccessStatus.SUCCESS);
        quartzTaskLogService.updateById(quartzTaskLog);
    }


//    public static void main(String[] args) {
//        Integer maxConsulterId = 4017298;
//        Integer beforeConsulterId = 0;
//        Integer afterConsulterId = 0;
//        for (int i = 0; i < maxConsulterId; i = i + 200000) {
//            beforeConsulterId = i;
//            afterConsulterId = i + 200000;
//            if(afterConsulterId>maxConsulterId){
//                afterConsulterId = maxConsulterId;
//            }
//            System.out.println("before======::" + beforeConsulterId);
//            System.out.println("afterConsulterId======::" + afterConsulterId);
//            if(afterConsulterId>=maxConsulterId){
//                break;
//            }
//        }
//
//    }

}
