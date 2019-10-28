package org.jeecg.modules.statistics.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.exception.InterfaceZxException;
import org.jeecg.modules.consulter.entity.ConsulterOrder;
import org.jeecg.modules.consulter.service.ConsulterOrderService;
import org.jeecg.modules.statistics.entity.OrderDtlD;
import org.jeecg.modules.statistics.entity.OrderProfitD;
import org.jeecg.modules.statistics.entity.OrderProfitM;
import org.jeecg.modules.statistics.entity.OrderRefundD;
import org.jeecg.modules.statistics.service.IOrderProfitDService;
import org.jeecg.modules.statistics.service.IOrderProfitMService;
import org.jeecg.modules.statistics.service.OrderDtlDService;
import org.jeecg.modules.statistics.service.OrderRefundDService;
import org.jeecg.util.date.DateUtil;
import org.jeecg.util.date.ZxDateUtils;
import org.jeecg.util.response.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * <p>
 * 工单明细日统计表，保存每天产生的工单明细数据。 前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-03
 */
@RestController
@RequestMapping("/orderDtlD")
@CrossOrigin
@Slf4j
public class OrderDtlDController {
    @Resource
    private OrderDtlDService orderDtlDService;

    @Resource
    private OrderRefundDService orderRefundDService;

    @Resource
    private ConsulterOrderService consulterOrderService;

    @Resource
    private IOrderProfitDService orderProfitDService;


    @Resource
    private IOrderProfitMService orderProfitMService;


    @RequestMapping("/orderDtlDAnalysis")
    public ServiceResult orderDtlDAnalysis(HttpServletRequest req) throws InterfaceZxException, ParseException {

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
            List<Map> orderDtlDAnalysis = orderDtlDService.orderDtlDAnalysis(calendarStart.getTime().getTime(),calendarEnd.getTime().getTime());
            log.info(JSON.toJSONString(orderDtlDAnalysis));

            List<OrderDtlD> orderDtlDAnalysisData =  new ArrayList<>();
            Integer statDt  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMMdd"));
            for (int i = 0; i <orderDtlDAnalysis.size() ; i++) {
                Map map = orderDtlDAnalysis.get(i);
                map.put("statDt",statDt);
                OrderDtlD orderDtlD = new OrderDtlD(map);
                orderDtlDAnalysisData.add(orderDtlD);
            }

            flag = clearOrderEvaluationDAnalysis(beforeDay,searchDate);
            if(flag){
                flag =false;
                flag = orderDtlDService.saveBatch(orderDtlDAnalysisData,orderDtlDAnalysisData.size());
            }
        }catch (Throwable e){
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


    @RequestMapping("/bMarkOrderDtlDAnalysis")
    public ServiceResult bMarkOrderDtlDAnalysis(HttpServletRequest req) throws InterfaceZxException, ParseException {

        String bMark = req.getParameter("bMark");
        Date searchDate = new Date();
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

            OrderDtlD orderDtlDRemove = new OrderDtlD();
            Integer statDtRemove  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMMdd"));
            orderDtlDRemove.setStatDt(statDtRemove);
            orderDtlDRemove.setbMark(bMark);
            QueryWrapper<OrderDtlD> queryWrapper = new QueryWrapper<OrderDtlD>(orderDtlDRemove);
            orderDtlDService.remove(queryWrapper);

            List<Map> orderDtlDAnalysis = orderDtlDService.bMarkOrderDtlDAnalysis(calendarStart.getTime().getTime(),calendarEnd.getTime().getTime(),bMark);
            log.info(JSON.toJSONString(orderDtlDAnalysis));

            List<OrderDtlD> orderDtlDAnalysisData =  new ArrayList<>();
            Integer statDt  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMMdd"));
            for (int i = 0; i <orderDtlDAnalysis.size() ; i++) {
                Map map = orderDtlDAnalysis.get(i);
                map.put("statDt",statDt);
                OrderDtlD orderDtlD = new OrderDtlD(map);
                orderDtlDAnalysisData.add(orderDtlD);
            }

            flag = clearOrderEvaluationDAnalysis(beforeDay,searchDate);
            if(flag){
                flag =false;
                flag = orderDtlDService.saveBatch(orderDtlDAnalysisData,orderDtlDAnalysisData.size());
            }
        }catch (Throwable e){
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


    @RequestMapping("/refleshOrderDtlDAnalysis")
    @Transactional
    public ServiceResult refleshOrderDtlDAnalysis(HttpServletRequest req) throws InterfaceZxException, ParseException {

        String orderId = req.getParameter("orderId");
        ConsulterOrder consulterOrder = consulterOrderService.getById(orderId);
        String  bMark = consulterOrder.getbMark();
        Integer  orderType = consulterOrder.getOrderType();
        //查询对应的工单的信息
//        退款成功后按照如下顺序刷新报表
//        1. 退款工单报表 to_e_order_refund_d （按照工单维度刷新,即限制order_id）
//        2. 工单明细报表 to_e_order_dtl_d  （按照工单维度刷新,即限制order_id）
//        3. 工单日收益报表 tm_order_profit_d （按照商户维度刷新，即限制b_mark）
//        4. 工单月收益报表 tm_order_profit_m  (按照商户维度刷新,即限制b_mark）

        long dataTime = consulterOrder.getCreateTime();
        Date searchDate = new Date(dataTime);
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
            Integer statDtRemove  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMMdd"));


            boolean chainflag = false;

            //清除对应的退款表，重新刷新对应的退款工单
            OrderRefundD orderRefundDRemove = new OrderRefundD();
            orderRefundDRemove.setOrderId(Integer.valueOf(orderId));
//            orderRefundDRemove.setStatDt(statDtRemove);
            QueryWrapper<OrderRefundD> queryWrapperOrderRefundD = new QueryWrapper<OrderRefundD>(orderRefundDRemove);


            orderRefundDService.remove(queryWrapperOrderRefundD);
            Map orderOrderRefundDMap = orderRefundDService.orderOrderRefundDAnalysis(Integer.valueOf(orderId));
            if(orderOrderRefundDMap!=null){
                OrderRefundD orderOrderRefundD = new OrderRefundD(orderOrderRefundDMap);
                orderOrderRefundD.setStatDt(Integer.valueOf(DateUtil.formatToStr(new Date(),"yyyyMMdd")));
                orderRefundDService.save(orderOrderRefundD);
            }





            //清除对应的工单表，重新刷新日统计表
            OrderDtlD orderDtlDRemove = new OrderDtlD();
            orderDtlDRemove.setOrderId(Integer.valueOf(orderId));
            orderDtlDRemove.setStatDt(statDtRemove);
            QueryWrapper<OrderDtlD> queryWrapperOrderDtlD = new QueryWrapper<OrderDtlD>(orderDtlDRemove);
            orderDtlDService.remove(queryWrapperOrderDtlD);
            Map orderOrderDtlDMap = orderDtlDService.orderOrderDtlDAnalysis(Integer.valueOf(orderId));
            if(orderOrderDtlDMap!=null){
                OrderDtlD orderOrderDtlD = new OrderDtlD(orderOrderDtlDMap);
                orderOrderDtlD.setStatDt(statDtRemove);
                orderDtlDService.save(orderOrderDtlD);
            }




            //清除对应的日收益统计表，重新日收益统计表
            OrderProfitD orderProfitDRemove = new OrderProfitD();
//            Integer statDtRemove  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMMdd"));
            orderProfitDRemove.setStatDt(statDtRemove);
            orderProfitDRemove.setBMark(bMark);
            orderProfitDRemove.setOrderType(orderType);
            QueryWrapper<OrderProfitD> queryWrapperProfitD = new QueryWrapper<OrderProfitD>(orderProfitDRemove);
            orderProfitDService.remove(queryWrapperProfitD);
            Map orderProfitDMap = orderProfitDService.orderOrderProfitDAnalysis(statDtRemove,bMark,orderType);
            if(orderProfitDMap!=null){
                OrderProfitD orderProfitDtlD = new OrderProfitD(orderProfitDMap);
                orderProfitDtlD.setStatDt(statDtRemove);
                orderProfitDService.save(orderProfitDtlD);
            }




            Calendar calendarStartMo =  Calendar.getInstance();
            calendarStartMo.set(Calendar.DATE,1);
            calendarStartMo.set(Calendar.HOUR_OF_DAY,0);
            calendarStartMo.set(Calendar.MINUTE,0);
            calendarStartMo.set(Calendar.SECOND,0);

            Calendar calendarEndMo =  Calendar.getInstance();
            calendarEndMo.set(Calendar.DATE,1);
            calendarEndMo.set(Calendar.HOUR_OF_DAY,0);
            calendarEndMo.set(Calendar.MINUTE,0);
            calendarEndMo.set(Calendar.SECOND,0);
            calendarEndMo.add(Calendar.MONTH,1);

            Integer statDt  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStartMo.getTime().getTime()),"yyyyMMdd"));
            Integer endDt  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarEndMo.getTime().getTime()),"yyyyMMdd"));


            //清除对应的月收益统计表，重新月收益统计表
            OrderProfitM orderProfitMRemove = new OrderProfitM();
            Integer statMoRemove  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMM"));
            orderProfitMRemove.setStatMo(statMoRemove);
            orderProfitMRemove.setBMark(bMark);
            orderProfitMRemove.setOrderType(orderType);
            QueryWrapper<OrderProfitM> queryWrapperProfitM = new QueryWrapper<OrderProfitM>(orderProfitMRemove);
            orderProfitMService.remove(queryWrapperProfitM);
            Map OrderProfitMMap = orderProfitMService.orderOrderProfitMAnalysis(statDt,endDt,bMark,orderType);
            if(OrderProfitMMap!=null){

                OrderProfitM orderProfitDtlM = new OrderProfitM(OrderProfitMMap);
                orderProfitDtlM.setStatMo(statMoRemove);
                orderProfitMService.save(orderProfitDtlM);
            }



            flag = true;

        }catch (Throwable e){
            e.printStackTrace();
            log.error(e.toString());
            throw new InterfaceZxException(e.toString());
        }finally {
            if(flag){
                return  result.buildSuccess("刷新工单数据成功","");
            }else{
                return result.buildError("刷新工单数据失败");
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

        OrderDtlD orderDtlD = new OrderDtlD();
        Integer statDt  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMMdd"));
        orderDtlD.setStatDt(statDt);
        QueryWrapper<OrderDtlD> queryWrapper = new QueryWrapper<OrderDtlD>(orderDtlD);
        orderDtlDService.remove(queryWrapper);
        return true;
    }
}

