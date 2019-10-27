package org.jeecg.modules.vip.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.DateAndLocalDateUtil;
import org.jeecg.constants.ConstantInterface;
import org.jeecg.modules.wxpay.entity.PayOrders;
import org.jeecg.modules.order.entity.PayTempOrder;
import org.jeecg.modules.wxpay.mapper.PayOrdersMapper;
import org.jeecg.modules.order.mapper.PayTempOrderMapper;
import org.jeecg.modules.vip.entity.*;
import org.jeecg.modules.vip.mapper.*;
import org.jeecg.modules.vip.service.ConsulterVipService;
import org.jeecg.modules.vip.vo.CheckVipVo;
import org.jeecg.modules.vip.vo.ConsulterVIPBenefitVo;
import org.jeecg.modules.vip.vo.UserVipInfo;
import org.jeecg.modules.wxpay.mapper.PayOrderRelaMapper;
import org.jeecg.util.ObjectCheckUtil;
import org.jeecg.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * VIP用户表 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@Slf4j
@Service
public class ConsulterVipServiceImpl extends ServiceImpl<ConsulterVipMapper, ConsulterVip> implements ConsulterVipService {

    @Resource
    private ConsulterVipMapper consulterVipMapper;

    @Resource
    private ConsulterOrderPkgPayMapper consulterOrderPkgPayMapper;

    @Resource
    private ConsulterPricingPackageMapper consulterPricingPackageMapper;

    @Resource
    private ConsulterPricingPkgResMapper consulterPricingPkgResMapper;

    @Resource
    private TpPricingPkgResMapper tpPricingPkgResMapper;

    @Resource
    private PayOrderRelaMapper payOrderRelaMapper;

    @Resource
    private PayOrdersMapper payOrdersMapper;


    @Resource
    private PayTempOrderMapper payTempOrderMapper;


    @Override
    public List<ConsulterVIPBenefitVo> queryConsulterVipBenefitByPhone(String mobilePhone) {
        return consulterVipMapper.queryConsulterVipBenefitByPhone(mobilePhone);
    }

    @Override
    public List<ConsulterVIPBenefitVo> queryConsulterVipBenefit(Integer consulterId) {
        return consulterVipMapper.queryConsulterVipBenefit(consulterId);
    }

    @Override
    public CheckVipVo queryVip(String consulterId) {
        Map<String, Object> map = consulterVipMapper.queryVip(consulterId);
        if (map == null) {
            return new CheckVipVo(false);
        }
        return new CheckVipVo(true);
    }

    @Override
    public List<UserVipInfo> queryVipInfo(Integer consulterId) {
        List<UserVipInfo> info=consulterVipMapper.queryVipInfo(consulterId);
        return info;
    }

    @Override
    public void payNotify(String busiTradeNo , String payTradeNo) {

        QueryWrapper<ConsulterOrderPkgPay> wrapper = new QueryWrapper<ConsulterOrderPkgPay>();
        wrapper.eq("busi_trade_no",busiTradeNo);
        ConsulterOrderPkgPay consulterOrderPkgPay = consulterOrderPkgPayMapper.selectOne(wrapper);
        if(consulterOrderPkgPay!=null){
            consulterOrderPkgPay.setPayTradeNo(payTradeNo); //购买或者续费之后调用逻辑
            consulterOrderPkgPay.setPayStauts(Integer.valueOf(ConstantInterface.payStauts.Pay));
            consulterOrderPkgPayMapper.updateById(consulterOrderPkgPay);

            Date effTime = new Date();  				//更新会员的权益
            Date expTime = new Date();
            QueryWrapper<ConsulterVip> wrapperVip = new QueryWrapper<ConsulterVip>();
            wrapperVip.eq("vip_phone",consulterOrderPkgPay.getMoiblePhone());
            ConsulterVip consulterVip = consulterVipMapper.selectOne(wrapperVip);


            if((consulterVip.getStatus().toString()).equals(ConstantInterface.Status.OFF)){  //首次开通 或者 过期再开通

                LocalDate today = LocalDate.now(ZoneId.of("Asia/Shanghai"));
                effTime = DateAndLocalDateUtil.localDate2Date(today);
                expTime = DateAndLocalDateUtil.localDate2Date(today.plus(1, ChronoUnit.YEARS).plus(1, ChronoUnit.DAYS));
                consulterVip.setEffTime(effTime);
                consulterVip.setExpTime(expTime);
                consulterVip.setStatus(Integer.valueOf(ConstantInterface.Status.ON));
                consulterVipMapper.updateById(consulterVip);
            }else{
                log.error("---------------------------------续费");

                LocalDate today = LocalDate.now(ZoneId.of("Asia/Shanghai"));
                effTime = DateAndLocalDateUtil.localDate2Date(today);
                LocalDate lastTimeEnd  = DateAndLocalDateUtil.date2LocalDate(consulterVip.getExpTime()).plus(1, ChronoUnit.YEARS).plus(1, ChronoUnit.DAYS);
                expTime = DateAndLocalDateUtil.localDate2Date(lastTimeEnd);
				consulterVip.setEffTime(effTime);
                consulterVip.setExpTime(expTime);
                consulterVip.setStatus(Integer.valueOf(ConstantInterface.Status.ON));
                consulterVipMapper.updateById(consulterVip);
            }

            Map params = new HashMap();
            params.put("vip_id",consulterVip.getVipId());
            params.put("busi_trade_no",busiTradeNo);
            params.put("pkg_inst_id", UUIDUtil.createUUId());
            params.put("pricing_pkg_id",consulterOrderPkgPay.getPricingPkgId());
            params.put("create_time",new Date());
            params.put("eff_time",effTime);
            params.put("exp_time",expTime);
            ConsulterPricingPackage consulterPricingPackage	= new ConsulterPricingPackage(params);
            consulterPricingPackageMapper.insert(consulterPricingPackage);

            //生成对应的consulter_pricing_pkg_res
            log.error("consulterOrderPkgPay------"+ JSONObject.toJSONString(consulterOrderPkgPay));
            QueryWrapper<TpPricingPkgRes> wrapperTpPricingPkgRes = new QueryWrapper<TpPricingPkgRes>();
            wrapperTpPricingPkgRes.eq("pricing_pkg_id",consulterOrderPkgPay.getPricingPkgId());
            List<TpPricingPkgRes> tpPricingPkgResList =  tpPricingPkgResMapper.selectList(wrapperTpPricingPkgRes);
            for (int i = 0; i <tpPricingPkgResList.size() ; i++) {
                TpPricingPkgRes tpPricingPkgRes = tpPricingPkgResList.get(i);
                Map param = new HashMap();
                param.put("pricing_pkg_id",tpPricingPkgRes.getPricingPkgId());
                param.put("service_id",tpPricingPkgRes.getServiceId());
                param.put("pkg_inst_id",consulterPricingPackage.getPkgInstId());
                param.put("free_times",tpPricingPkgRes.getFreeTimes());
                param.put("discount_rate",tpPricingPkgRes.getDiscountRate());
                param.put("discount_limit_times",tpPricingPkgRes.getDiscountLimitTimes());
                param.put("create_time",new Date());
                param.put("used_times",0);
                param.put("is_limit",tpPricingPkgRes.getIsLimit());
                ConsulterPricingPkgRes consulterPricingPkgRes = new ConsulterPricingPkgRes(param);
                consulterPricingPkgResMapper.insert(consulterPricingPkgRes);
            }


        }



    }


    @Override
    public void payNotifyConsult(String busiTradeNo,String payTradeNo){

        log.error("payNotifyConsult-------------start");

        QueryWrapper<PayOrders> wrapper = new QueryWrapper<PayOrders>();
        wrapper.eq("busi_trade_no",busiTradeNo);
        PayOrders payOrders = payOrdersMapper.selectOne(wrapper);
        if(payOrders!=null){
            log.error("payOrders-------------start");

            payOrders.setPayTradeNo(payTradeNo);
            payOrdersMapper.updateById(payOrders);


            QueryWrapper<PayTempOrder> payTempOrderWrapper = new QueryWrapper<PayTempOrder>();
            payTempOrderWrapper.eq("busi_trade_no",busiTradeNo);
            PayTempOrder payTempOrder = payTempOrderMapper.selectOne(payTempOrderWrapper);
            if(payOrders!=null){
                log.error("payOrders-------------start");
                payTempOrder.setPreorder(payOrders.getPrePayNo());
                payTempOrderMapper.updateById(payTempOrder);
            }

            log.error("payNotifyConsult-------------end");

        }


    }

}
