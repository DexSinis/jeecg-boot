package org.jeecg.modules.vip.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.jeecg.aop.PassToken;
import org.jeecg.modules.vip.service.ConsulterVipService;
import org.jeecg.modules.vip.vo.CheckVipVo;
import org.jeecg.modules.vip.vo.ConsulterVIPBenefitVo;
import org.jeecg.modules.vip.vo.UserVipInfo;
import org.jeecg.modules.vip.vo.VipBenefitVo;
import org.jeecg.util.response.ServiceResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * VIP用户表 前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@RestController
@Api(tags = "会员信息接口")
@RequestMapping("/consulterVip")
public class ConsulterVipController {
    @Resource
    private ConsulterVipService consulterVipService;


    @ApiOperation(value = "会员权益查询", notes = "根据手机号码查询会员权益")
    @PostMapping(value = "/qryBenefitByPhone")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobilePhone", value = "手机号码", required = true)
    })
    @PassToken(required = true)
    public ServiceResult queryConsulterVipBenefitByPhone(HttpServletRequest request,
                                                         @RequestParam(value = "mobilePhone", required = true) String mobilePhone) {
        ServiceResult<List<ConsulterVIPBenefitVo>> serviceResult = new ServiceResult<>();
        try {
            List<ConsulterVIPBenefitVo> vipBenefitVos = consulterVipService.queryConsulterVipBenefitByPhone(mobilePhone);
            Optional oVipBenefit = Optional.ofNullable(vipBenefitVos);
            if (oVipBenefit.isPresent()) {
//                serviceResult.setData(calculateBenefit(vipBenefitVos));
                serviceResult.setData(vipBenefitVos);
                serviceResult.setSuccess(true);
                serviceResult.setCode(ServiceResult.ServiceResultCode.SUCCESS);
            } else {
                serviceResult.setMsg("没有查询到会员权益");
                serviceResult.setCode(ServiceResult.ServiceResultCode.SUCCESS);
                serviceResult.setSuccess(false);
            }
        } catch (Exception e) {
            serviceResult.setMsg("查询到会员权益失败");
            serviceResult.setCode(ServiceResult.ServiceResultCode.ERROR);
            serviceResult.setSuccess(false);
        } finally {
            return serviceResult;
        }
    }

    @ApiOperation(value = "会员权益查询", notes = "根据用户ID查询会员权益")
    @PostMapping(value = "/qryBenefit")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "consulterId", value = "用户ID", required = true)
    })
//    @PassToken(required = true)
    public ServiceResult queryConsulterVipBenefit(HttpServletRequest request
//                                                  @RequestParam(value = "consulterId", required = true) Integer consulterId
    ) {
        Integer consulterId = Integer.valueOf(request.getAttribute("consulterId").toString());
        ServiceResult<VipBenefitVo> serviceResult = new ServiceResult<>();
        try {
            List<ConsulterVIPBenefitVo> vipBenefitVos = consulterVipService.queryConsulterVipBenefit(consulterId);
            Optional oVipBenefit = Optional.ofNullable(vipBenefitVos);
            if (oVipBenefit.isPresent()) {
                //查询会员套餐信息
                List<UserVipInfo> info = consulterVipService.queryVipInfo(consulterId);
                VipBenefitVo vo = new VipBenefitVo();
                vo.setInfo(info);
//                vo.setList(calculateBenefit(vipBenefitVos));
                List<List<ConsulterVIPBenefitVo>> list = new ArrayList<>();
                int i = 0;
                List<ConsulterVIPBenefitVo> vos;
                for (ConsulterVIPBenefitVo vipBenefitVo : vipBenefitVos) {
                    if (i == 0) {
                        vos = new ArrayList<>();
                        vos.add(vipBenefitVo);
                        list.add(vos);
                        i++;
                    } else {
                        boolean flag = false;
                        String pkgInstId = vipBenefitVo.getPkgInstId();
                        for (List<ConsulterVIPBenefitVo> voss : list) {
                            String pkgInstIdSame = voss.get(0).getPkgInstId();
                            if (pkgInstId.equals(pkgInstIdSame)) {
                                voss.add(vipBenefitVo);
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            vos = new ArrayList<>();
                            vos.add(vipBenefitVo);
                            list.add(vos);
                        }
                    }

                }
                vo.setList(list);
                serviceResult.setData(vo);
                serviceResult.setSuccess(true);
                serviceResult.setCode(ServiceResult.ServiceResultCode.SUCCESS);
            } else {
                serviceResult.setMsg("没有查询到会员权益");
                serviceResult.setCode(ServiceResult.ServiceResultCode.SUCCESS);
                serviceResult.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setMsg("查询到会员权益失败");
            serviceResult.setCode(ServiceResult.ServiceResultCode.ERROR);
            serviceResult.setSuccess(false);
        } finally {
            return serviceResult;
        }
    }


    /**
     * VIP 权益叠加计算
     *
     * @param vipBenefitVos
     * @return
     */
    private List<ConsulterVIPBenefitVo> calculateBenefit(List<ConsulterVIPBenefitVo> vipBenefitVos) {
        Map<Integer, ConsulterVIPBenefitVo> benefitVoMap = new HashMap<>();
        ConsulterVIPBenefitVo consulterVipBenefit;
        for (ConsulterVIPBenefitVo vipBenefitVo : vipBenefitVos) {
            consulterVipBenefit = benefitVoMap.get(vipBenefitVo.getServiceId());
            if (consulterVipBenefit == null) {
                benefitVoMap.put(vipBenefitVo.getServiceId(), vipBenefitVo);
            } else {
                //免费次数叠加
                consulterVipBenefit.setFreeTimes(consulterVipBenefit.getFreeTimes() + vipBenefitVo.getFreeTimes());
                //优惠次数叠加
                consulterVipBenefit.setDiscountLimitTimes(consulterVipBenefit.getDiscountLimitTimes() + vipBenefitVo.getDiscountLimitTimes());
                //折扣率按最新折扣计算
                if (vipBenefitVo.getEffTime().after(consulterVipBenefit.getEffTime())) {
                    consulterVipBenefit.setDiscountRate(vipBenefitVo.getDiscountRate());
                }
                //已经使用次数叠加
                consulterVipBenefit.setUsedTimes(consulterVipBenefit.getUsedTimes() + vipBenefitVo.getUsedTimes());
            }
        }
        List<ConsulterVIPBenefitVo> vipBenefitResult = new ArrayList<>(benefitVoMap.size());
        for (Iterator it = benefitVoMap.keySet().iterator(); it.hasNext(); ) {
            vipBenefitResult.add(benefitVoMap.get(it.next()));
        }
        return vipBenefitResult;

    }

    @ApiOperation(value = "是否会员查询", notes = "是否会员查询")
    @PostMapping(value = "/queryVip")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "consulterId", value = "用户ID", required = true)
    })
    public ServiceResult queryVip(HttpServletRequest request) {
        String consulterId = request.getAttribute("consulterId").toString();
//        String bMark=request.getAttribute("bMark").toString();
        ServiceResult<CheckVipVo> serviceResult = new ServiceResult();
        CheckVipVo vo = consulterVipService.queryVip(consulterId);
        serviceResult.setCode(0);
        serviceResult.setSuccess(true);
        serviceResult.setData(vo);
        return serviceResult;
    }

}

