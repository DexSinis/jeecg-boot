package org.jeecg.modules.quartz.job;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.constants.CommonConstant;
import org.jeecg.exception.InterfaceZxException;
import org.jeecg.modules.quartz.entity.QuartzJob;
import org.jeecg.modules.quartz.entity.QuartzTaskLog;
import org.jeecg.modules.quartz.service.IQuartzJobService;
import org.jeecg.modules.quartz.service.QuartzTaskLogService;
import org.jeecg.modules.statistics.entity.OrderRefundD;
import org.jeecg.modules.statistics.service.OrderRefundDService;
import org.jeecg.util.date.DateUtil;
import org.jeecg.util.date.ZxDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Component("orderRefundDStatisticsJob")
@Slf4j
public class OrderRefundDStatisticsJob  {

//    private static final Integer beforeDay = 0;

    /**
     * 若参数变量名修改 QuartzJobController中也需对应修改
     */
    private String parameter;

    @Resource
    QuartzTaskLogService quartzTaskLogService;

    @Resource
    IQuartzJobService quartzJobService;

    @Resource
    private OrderRefundDService orderRefundDService;

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

        log.error(String.format("OrderRefundDStatisticsJob >>>>开始时间:" + ZxDateUtils.now(), this.parameter));

        //统计前几天的数据 前三天数据把30改为2
        for (int i = -1; i <0 ; i++) {
            if(this.clearOrderEvaluationDAnalysis(i)){
                this.orderRefundDAnalysis(i);
            }
        }

        log.error(String.format("OrderRefundDStatisticsJob >>>>结束时间:" + ZxDateUtils.now(), this.parameter));

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

        OrderRefundD orderRefundD = new OrderRefundD();
        Integer statDt  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMMdd"));
        orderRefundD.setStatDt(statDt);
        QueryWrapper<OrderRefundD> queryWrapper = new QueryWrapper<OrderRefundD>(orderRefundD);
        return orderRefundDService.remove(queryWrapper);
    }


    public boolean orderRefundDAnalysis(Integer beforeDay){
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
            List<Map> orderRefundDAnalysis = orderRefundDService.orderRefundDAnalysis(calendarStart.getTime().getTime(),calendarEnd.getTime().getTime());
            log.info(JSON.toJSONString(orderRefundDAnalysis));

            List<OrderRefundD> orderRefundDAnalysisData =  new ArrayList<>();
            Integer statDt  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMMdd"));
            for (int i = 0; i <orderRefundDAnalysis.size() ; i++) {
                Map map = orderRefundDAnalysis.get(i);
                map.put("statDt",statDt);
                OrderRefundD orderRefundD = new OrderRefundD(map);
                orderRefundDAnalysisData.add(orderRefundD);
            }
            Boolean flag = orderRefundDService.saveBatch(orderRefundDAnalysisData,orderRefundDAnalysisData.size());

        }catch (Throwable e){
            log.error(e.toString());
        	throw new InterfaceZxException("执行定时任务orderRefundDAnalysis失败");
        }

        return true;
    }


}
