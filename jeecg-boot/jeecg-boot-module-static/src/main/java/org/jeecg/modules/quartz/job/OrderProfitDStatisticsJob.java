package org.jeecg.modules.quartz.job;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.constants.CommonConstant;
import org.jeecg.exception.InterfaceZxException;
import org.jeecg.modules.quartz.entity.QuartzJob;
import org.jeecg.modules.quartz.entity.QuartzTaskLog;
import org.jeecg.modules.quartz.service.IQuartzJobService;
import org.jeecg.modules.quartz.service.QuartzTaskLogService;
import org.jeecg.modules.statistics.entity.OrderProfitD;
import org.jeecg.modules.statistics.service.IOrderProfitDService;
import org.jeecg.util.date.DateUtil;
import org.jeecg.util.date.ZxDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Component("orderProfitDStatisticsJob")
@Slf4j
public class OrderProfitDStatisticsJob {

    @Resource
    QuartzTaskLogService quartzTaskLogService;

    @Resource
    IQuartzJobService quartzJobService;

    private String parameter;



    @Resource
    private IOrderProfitDService iOrderProfitDService;

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

        log.error(String.format("OrderProfitDStatisticsJob >>>>开始时间:" + ZxDateUtils.now(), this.parameter));

//        if(1==1){
//            return;
//        }
        //统计前几天的数据 前三天数据把30改为2
        for (int i = -1; i <0 ; i++) {
            if(this.clearOrderEvaluationDAnalysis(i)){
                this.orderProfitDAnalysis(i);
            }
        }

        log.error(String.format("OrderProfitDStatisticsJob >>>>结束时间:" + ZxDateUtils.now(), this.parameter));

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

        OrderProfitD orderProfitD = new OrderProfitD();
        Integer statDt  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMMdd"));
        orderProfitD.setStatDt(statDt);
        QueryWrapper<OrderProfitD> queryWrapper = new QueryWrapper<OrderProfitD>(orderProfitD);
        return iOrderProfitDService.remove(queryWrapper);
    }


    public boolean orderProfitDAnalysis(Integer beforeDay){



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
            List<Map> orderProfitDAnalysis = iOrderProfitDService.orderProfitDAnalysis(statDt);
            log.info(JSON.toJSONString(orderProfitDAnalysis));

            List<OrderProfitD> orderProfitDAnalysisData =  new ArrayList<>();
            for (int i = 0; i <orderProfitDAnalysis.size() ; i++) {
                Map map = orderProfitDAnalysis.get(i);
                map.put("statDt",statDt);
                OrderProfitD orderProfitD = new OrderProfitD(map);
                orderProfitDAnalysisData.add(orderProfitD);
            }
            Boolean flag = iOrderProfitDService.saveBatch(orderProfitDAnalysisData,orderProfitDAnalysisData.size());
        }catch (Throwable e){
            e.printStackTrace();
            log.error(e.toString());
        	throw new InterfaceZxException("执行定时任务orderProfitDAnalysis失败");
        }

        return true;
    }


}
