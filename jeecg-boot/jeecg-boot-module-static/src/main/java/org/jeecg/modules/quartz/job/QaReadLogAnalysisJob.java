package org.jeecg.modules.quartz.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.constants.CommonConstant;
import org.jeecg.modules.qasystem.entity.QuestionIncmD;
import org.jeecg.modules.qasystem.service.QuestionIncmDService;
import org.jeecg.modules.quartz.entity.QuartzJob;
import org.jeecg.modules.quartz.entity.QuartzTaskLog;
import org.jeecg.modules.quartz.service.IQuartzJobService;
import org.jeecg.modules.quartz.service.QuartzTaskLogService;
import org.jeecg.util.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by DexSinis
 * 定时点击问答问题数据统计
 */
@Component("qaReadLogAnalysisJob")
@Slf4j
public class QaReadLogAnalysisJob  {

    @Resource
    QuartzTaskLogService quartzTaskLogService;

    @Resource
    IQuartzJobService quartzJobService;

    private static final Integer beforeDay = 0;

    @Resource
    private QuestionIncmDService questionIncmDService;


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

        log.error("---------------->>>>>>>QaReadLogAnalysisJob Start");
        this.clearAnalysis();
        // TODO: 2019-05-20 页面点击统计
        this.qaReadLogAnalysis();
        log.error("---------------->>>>>>>QaReadLogAnalysisJob End");


        quartzTaskLog.setEndTime(new Date());
        quartzTaskLog.setSuccessStatus(CommonConstant.SuccessStatus.SUCCESS);
        quartzTaskLogService.updateById(quartzTaskLog);
    }



    public boolean qaReadLogAnalysis(){
        Calendar calendarStart =  Calendar.getInstance();
        calendarStart.add(Calendar.DATE,beforeDay);
        calendarStart.set(Calendar.HOUR_OF_DAY,0);
        calendarStart.set(Calendar.MINUTE,0);
        calendarStart.set(Calendar.SECOND,0);

        Calendar calendarEnd =  Calendar.getInstance();
        calendarEnd.add(Calendar.DATE,beforeDay);
        calendarEnd.set(Calendar.HOUR_OF_DAY,23);
        calendarEnd.set(Calendar.MINUTE,59);
        calendarEnd.set(Calendar.SECOND,59);


        return  questionIncmDService.qaReadLogAnalysis(calendarStart.getTime().getTime(),calendarEnd.getTime().getTime());

//        return new PageAccessService().qaReadLogAnalysis(calendarStart.getTime().getTime(),calendarEnd.getTime().getTime());



    }


    public boolean clearAnalysis(){

        Calendar calendarStart =  Calendar.getInstance();
        calendarStart.add(Calendar.DATE,beforeDay);
        calendarStart.set(Calendar.HOUR_OF_DAY,0);
        calendarStart.set(Calendar.MINUTE,0);
        calendarStart.set(Calendar.SECOND,0);

        QuestionIncmD questionIncmD = new QuestionIncmD();
        Integer statDt  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMMdd"));
        questionIncmD.setStatDt(statDt);
        QueryWrapper<QuestionIncmD> queryWrapper = new QueryWrapper<QuestionIncmD>(questionIncmD);
        questionIncmDService.remove(queryWrapper);
        return true;
    }


}