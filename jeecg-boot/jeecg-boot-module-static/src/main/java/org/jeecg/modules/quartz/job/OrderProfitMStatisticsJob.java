package org.jeecg.modules.quartz.job;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.constants.CommonConstant;
import org.jeecg.exception.InterfaceZxException;
import org.jeecg.modules.quartz.entity.QuartzJob;
import org.jeecg.modules.quartz.entity.QuartzTaskLog;
import org.jeecg.modules.quartz.service.IQuartzJobService;
import org.jeecg.modules.quartz.service.QuartzTaskLogService;
import org.jeecg.modules.statistics.entity.OrderProfitM;
import org.jeecg.modules.statistics.service.IOrderProfitMService;
import org.jeecg.util.date.DateUtil;
import org.jeecg.util.date.ZxDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Component("orderProfitMStatisticsJob")
@Slf4j
public class OrderProfitMStatisticsJob {

    @Resource
    QuartzTaskLogService quartzTaskLogService;

    @Resource
    IQuartzJobService quartzJobService;

    private String parameter;



    @Resource
    private IOrderProfitMService iOrderProfitMService;

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

        log.error(String.format("OrderProfitMStatisticsJob >>>>开始时间:" + ZxDateUtils.now(), this.parameter));

//        if(1==1){
//            return;
//        }
        //统计前几天的数据 前三天数据把30改为2
        for (int i = -1; i < 0 ; i++) {
            if(this.clearOrderEvaluationDAnalysis(i)){
                this.orderProfitMAnalysis(i);
            }
        }

        log.error(String.format("OrderProfitMStatisticsJob >>>>结束时间:" + ZxDateUtils.now(), this.parameter));

        quartzTaskLog.setEndTime(new Date());
        quartzTaskLog.setSuccessStatus(CommonConstant.SuccessStatus.SUCCESS);
        quartzTaskLogService.updateById(quartzTaskLog);
    }


    public boolean clearOrderEvaluationDAnalysis(Integer beforeDay){

        Calendar calendarStart =  Calendar.getInstance();
        calendarStart.set(Calendar.DATE,1);
        calendarStart.set(Calendar.HOUR_OF_DAY,0);
        calendarStart.set(Calendar.MINUTE,0);
        calendarStart.set(Calendar.SECOND,0);

        OrderProfitM orderProfitM = new OrderProfitM();
        Integer statMo  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMM"));
        orderProfitM.setStatMo(statMo);
        QueryWrapper<OrderProfitM> queryWrapper = new QueryWrapper<OrderProfitM>(orderProfitM);
        return iOrderProfitMService.remove(queryWrapper);
    }


    public boolean orderProfitMAnalysis(Integer beforeDay){



        Calendar calendarStart =  Calendar.getInstance();
        calendarStart.set(Calendar.DATE,1);
        calendarStart.set(Calendar.HOUR_OF_DAY,0);
        calendarStart.set(Calendar.MINUTE,0);
        calendarStart.set(Calendar.SECOND,0);

        Calendar calendarEnd =  Calendar.getInstance();
        calendarEnd.set(Calendar.DATE,1);
        calendarEnd.set(Calendar.HOUR_OF_DAY,0);
        calendarEnd.set(Calendar.MINUTE,0);
        calendarEnd.set(Calendar.SECOND,0);
        calendarEnd.add(Calendar.MONTH,1);

        try {
            Integer statMo  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMM"));
            Integer statDt  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMMdd"));
            Integer endDt  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarEnd.getTime().getTime()),"yyyyMMdd"));

            List<Map> orderProfitMAnalysis = iOrderProfitMService.orderProfitMAnalysis(statDt,endDt);
            log.info(JSON.toJSONString(orderProfitMAnalysis));

            List<OrderProfitM> orderProfitMAnalysisData =  new ArrayList<>();
            for (int i = 0; i <orderProfitMAnalysis.size() ; i++) {
                Map map = orderProfitMAnalysis.get(i);
                map.put("statMo",statMo);
                OrderProfitM orderProfitM = new OrderProfitM(map);
                orderProfitMAnalysisData.add(orderProfitM);
            }
            Boolean flag = iOrderProfitMService.saveBatch(orderProfitMAnalysisData,orderProfitMAnalysisData.size());
        }catch (Throwable e){
            e.printStackTrace();
            log.error(e.toString());
            throw new InterfaceZxException("执行定时任务orderProfitMAnalysis失败");
        }

        return true;
    }


}
