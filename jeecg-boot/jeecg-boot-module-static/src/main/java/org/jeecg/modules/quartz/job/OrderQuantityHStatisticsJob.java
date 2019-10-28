package org.jeecg.modules.quartz.job;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.constants.CommonConstant;
import org.jeecg.exception.InterfaceZxException;
import org.jeecg.modules.quartz.entity.QuartzJob;
import org.jeecg.modules.quartz.entity.QuartzTaskLog;
import org.jeecg.modules.quartz.service.IQuartzJobService;
import org.jeecg.modules.quartz.service.QuartzTaskLogService;
import org.jeecg.modules.statistics.entity.OrderQuantityH;
import org.jeecg.modules.statistics.service.IOrderQuantityHService;
import org.jeecg.util.date.DateUtil;
import org.jeecg.util.date.ZxDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Component("orderQuantityHStatisticsJob")
@Slf4j
public class OrderQuantityHStatisticsJob {

    @Resource
    QuartzTaskLogService quartzTaskLogService;

    @Resource
    IQuartzJobService quartzJobService;

    private String parameter;



    @Resource
    private IOrderQuantityHService iOrderQuantityHService;

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

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

        log.error(String.format("OrderQuantityHStatisticsJob >>>>开始时间:" + ZxDateUtils.now(), this.parameter));

//        if(1==1){
//            return;
//        }
        //统计前几天的数据 前三天数据把30改为2
        for (int i = -1; i <0 ; i++) {
            if(this.clearOrderEvaluationDAnalysis(i)){
                this.orderQuantityHAnalysis(i);
            }
        }

        log.error(String.format("OrderQuantityHStatisticsJob >>>>结束时间:" + ZxDateUtils.now(), this.parameter));

        quartzTaskLog.setEndTime(new Date());
        quartzTaskLog.setSuccessStatus(CommonConstant.SuccessStatus.SUCCESS);
        quartzTaskLogService.updateById(quartzTaskLog);
    }


    public boolean clearOrderEvaluationDAnalysis(Integer beforeDay){

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

        OrderQuantityH orderQuantityH = new OrderQuantityH();
        Integer statDt  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMMdd"));
        orderQuantityH.setStatDt(statDt);
        QueryWrapper<OrderQuantityH> queryWrapper = new QueryWrapper<OrderQuantityH>(orderQuantityH);
        return iOrderQuantityHService.remove(queryWrapper);
    }


    public boolean orderQuantityHAnalysis(Integer beforeDay){



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
            Integer statDt  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMMdd"));
            List<Map> orderQuantityHAnalysis = iOrderQuantityHService.orderQuantityHAnalysis(statDt);
            log.info(JSON.toJSONString(orderQuantityHAnalysis));

            List<OrderQuantityH> orderQuantityHAnalysisData =  new ArrayList<>();
            for (int i = 0; i <orderQuantityHAnalysis.size() ; i++) {
                Map map = orderQuantityHAnalysis.get(i);
                map.put("statDt",statDt);
                OrderQuantityH orderQuantityH = new OrderQuantityH(map);
                orderQuantityHAnalysisData.add(orderQuantityH);
            }
            Boolean flag = iOrderQuantityHService.saveBatch(orderQuantityHAnalysisData,orderQuantityHAnalysisData.size());
        }catch (Throwable e){
            e.printStackTrace();
            log.error(e.toString());
            throw new InterfaceZxException("执行定时任务orderQuantityHAnalysis失败");
        }

        return true;
    }


}
