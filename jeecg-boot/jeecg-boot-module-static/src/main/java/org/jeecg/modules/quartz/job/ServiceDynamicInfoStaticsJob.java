package org.jeecg.modules.quartz.job;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.constants.CommonConstant;
import org.jeecg.exception.InterfaceZxException;
import org.jeecg.modules.notice.entity.ServiceDynamicInfo;
import org.jeecg.modules.notice.service.ServiceDynamicInfoService;
import org.jeecg.modules.quartz.entity.QuartzJob;
import org.jeecg.modules.quartz.entity.QuartzTaskLog;
import org.jeecg.modules.quartz.service.IQuartzJobService;
import org.jeecg.modules.quartz.service.QuartzTaskLogService;
import org.jeecg.util.date.DateUtil;
import org.jeecg.util.date.ZxDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Component("serviceDynamicInfoStaticsJob")
@Slf4j
public class ServiceDynamicInfoStaticsJob {

    @Resource
    QuartzTaskLogService quartzTaskLogService;

    @Resource
    IQuartzJobService quartzJobService;

    @Resource
    ServiceDynamicInfoService serviceDynamicInfoService;



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

        log.error(String.format("ServiceDynamicInfoStaticsJob任务执行开始 >>>>开始时间:" + ZxDateUtils.now(), null));

//        if(1==1){
//            return;
//        }
        //统计前几天的数据 前三天数据把30改为2
//        for (int i = -1; i <0 ; i++) {
            if(this.clearServiceDynamicInfoAnalysis(0)){
                this.serviceDynamicInfoAnalysis(0);
            }
//        }

        log.error(String.format("ServiceDynamicInfoStaticsJob任务执行结束 >>>>结束时间:" + ZxDateUtils.now(), null));

        quartzTaskLog.setEndTime(new Date());
        quartzTaskLog.setSuccessStatus(CommonConstant.SuccessStatus.SUCCESS);
        quartzTaskLogService.updateById(quartzTaskLog);
    }


    public boolean clearServiceDynamicInfoAnalysis(Integer beforeDay){

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

        ServiceDynamicInfo serviceDynamicInfo = new ServiceDynamicInfo();
        Integer statDt  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMMdd"));
//        serviceDynamicInfo.setStatDt(statDt);
        QueryWrapper<ServiceDynamicInfo> queryWrapper = new QueryWrapper<ServiceDynamicInfo>(serviceDynamicInfo);
        return serviceDynamicInfoService.remove(queryWrapper);
    }


    public boolean serviceDynamicInfoAnalysis(Integer beforeDay){



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

        try {
            List<Map> serviceDynamicInfoAnalysis = serviceDynamicInfoService.serviceDynamicInfoAnalysis(calendarStart.getTime().getTime(),calendarEnd.getTime().getTime());
            log.info(JSON.toJSONString(serviceDynamicInfoAnalysis));

            List<ServiceDynamicInfo> serviceDynamicInfoAnalysisData =  new ArrayList<>();
            Integer statDt  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMMdd"));
            for (int i = 0; i <serviceDynamicInfoAnalysis.size() ; i++) {
                Map map = serviceDynamicInfoAnalysis.get(i);
                map.put("statDt",statDt);
                ServiceDynamicInfo serviceDynamicInfo = new ServiceDynamicInfo(map);
                serviceDynamicInfoAnalysisData.add(serviceDynamicInfo);
            }
            Boolean flag = serviceDynamicInfoService.saveBatch(serviceDynamicInfoAnalysisData,serviceDynamicInfoAnalysisData.size());
        }catch (Throwable e){
            e.printStackTrace();
            log.error(e.toString());
            throw new InterfaceZxException("执行定时任务serviceDynamicInfoAnalysis失败");
        }

        return true;
    }



}
