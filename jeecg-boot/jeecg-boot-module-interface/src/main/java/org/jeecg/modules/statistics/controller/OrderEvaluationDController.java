package org.jeecg.modules.statistics.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.exception.InterfaceZxException;
import org.jeecg.modules.statistics.entity.OrderEvaluationD;
import org.jeecg.modules.statistics.service.OrderEvaluationDService;
import org.jeecg.util.date.DateUtil;
import org.jeecg.util.date.ZxDateUtils;
import org.jeecg.util.response.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * <p>
 * 工单评价日统计表，存储工单评价明细数据。 前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-05-31
 */
@RestController
@RequestMapping("/orderEvaluationD")
@CrossOrigin
@Slf4j
public class OrderEvaluationDController {

    @Resource
    private OrderEvaluationDService orderEvaluationDService;



    @RequestMapping("/clearOrderEvaluationDAnalysis")
    public ServiceResult orderEvaluationDAnalysis(HttpServletRequest req) throws InterfaceZxException, ParseException {


        String searchtime = req.getParameter("searchtime");
        Date searchDate= ZxDateUtils.parseDate(searchtime, "MM/dd/yyyy");
        Integer beforeDay = 0;
        Calendar calendarStart =  Calendar.getInstance();
        calendarStart.setTime(searchDate);
        calendarStart.add(Calendar.DATE,beforeDay);
        calendarStart.set(Calendar.HOUR_OF_DAY,0);
        calendarStart.set(Calendar.MINUTE,0);
        calendarStart.set(Calendar.SECOND,0);

        Calendar calendarEnd =  Calendar.getInstance();
        calendarEnd.setTime(searchDate);
        calendarEnd.add(Calendar.DATE,beforeDay);
        calendarEnd.set(Calendar.HOUR_OF_DAY,23);
        calendarEnd.set(Calendar.MINUTE,59);
        calendarEnd.set(Calendar.SECOND,59);
        ServiceResult result = new ServiceResult();
        Boolean flag = false;
        try {
            List<Map> orderEvaluationDAnalysis = orderEvaluationDService.orderEvaluationDAnalysis(calendarStart.getTime().getTime(),calendarEnd.getTime().getTime());
            log.info(JSON.toJSONString(orderEvaluationDAnalysis));
            List<OrderEvaluationD> orderEvaluationDAnalysisData =  new ArrayList<>();
            Integer statDt  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMMdd"));
            for (int i = 0; i <orderEvaluationDAnalysis.size() ; i++) {
                Map map = orderEvaluationDAnalysis.get(i);
                map.put("statDt",statDt);
                OrderEvaluationD orderEvaluationD = new OrderEvaluationD(map);
                orderEvaluationDAnalysisData.add(orderEvaluationD);
            }
             flag = clearOrderEvaluationDAnalysis(beforeDay,searchDate);
             if(flag){
                 flag =false;
                 flag = orderEvaluationDService.saveBatch(orderEvaluationDAnalysisData,orderEvaluationDAnalysisData.size());
             }

        }catch(Exception e){
            e.printStackTrace();
            log.error(e.toString());
            throw new InterfaceZxException(e.toString());
        }finally {
            if(flag){
                return  result.buildSuccess("刷新报表数据成功","");
            }else{
                return result.buildError("刷新报表数据失败");
            }
        }
    }

    public boolean clearOrderEvaluationDAnalysis(Integer beforeDay,Date searchDate){

        Calendar calendarStart =  Calendar.getInstance();
        calendarStart.setTime(searchDate);
        calendarStart.add(Calendar.DATE,beforeDay);
        calendarStart.set(Calendar.HOUR_OF_DAY,0);
        calendarStart.set(Calendar.MINUTE,0);
        calendarStart.set(Calendar.SECOND,0);

        Calendar calendarEnd =  Calendar.getInstance();
        calendarEnd.setTime(searchDate);
        calendarEnd.add(Calendar.DATE,beforeDay);
        calendarEnd.set(Calendar.HOUR_OF_DAY,23);
        calendarEnd.set(Calendar.MINUTE,59);
        calendarEnd.set(Calendar.SECOND,59);

        OrderEvaluationD orderEvaluationD = new OrderEvaluationD();
        Integer statDt  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMMdd"));
        orderEvaluationD.setStatDt(statDt);
        QueryWrapper<OrderEvaluationD> queryWrapper = new QueryWrapper<OrderEvaluationD>(orderEvaluationD);
        orderEvaluationDService.remove(queryWrapper);
        return true;
    }


}

