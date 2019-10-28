package org.jeecg.modules.quartz.job;

import org.jeecg.modules.consulter.service.ConsulterService;
import org.jeecg.modules.statistics.service.PersUserDService;
import org.jeecg.util.date.ZxDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by yujianqiang on 2019/6/21.
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestJob {
    @Resource
    private ConsulterService consulterService;

    @Resource
    private PersUserDService persUserDService;

    @RequestMapping("/job")
    public boolean testJob(){

        log.info(String.format("ConsulterDStatisticsJob >>>>开始时间:" + ZxDateUtils.now()));
        Map consulter = consulterService.findMaxConsulterId();
        Integer maxConsulterId = (Integer)consulter.get("id");
        Integer beforeConsulterId = 0;
        Integer afterConsulterId = 0;
        boolean flag = persUserDService.clearAllConsulterAnalysis();
        if(!flag){
            return flag;
        }
        for (int i = 0; i < maxConsulterId; i = i + 200000) {
            beforeConsulterId = i;
            afterConsulterId = i + 200000;
            if(afterConsulterId>maxConsulterId){
                afterConsulterId = maxConsulterId;
            }
//            persUserDService.consulterDAnalysisForJob(beforeConsulterId.toString(),afterConsulterId.toString());
            persUserDService.insertconsulterDForJob(beforeConsulterId.toString(),afterConsulterId.toString());
            if(afterConsulterId>=maxConsulterId){
                break;
            }
        }
//        flag = persUserDService.consulterDAnalysisForJob("2000000","5000000");
//        flag = persUserDService.insertconsulterDForJob("0","5000000");
        log.info("ConsulterDStatisticsJob >>>>结束时间:{}",ZxDateUtils.now());
        return flag;
    }
}
