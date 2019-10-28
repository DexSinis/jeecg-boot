package org.jeecg.modules.vip.controller;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.modules.vip.service.ConsulterOrderPkgPayService;

import org.jeecg.modules.vip.vo.VipServiceDevelopmentInfoVo;
import org.jeecg.util.response.ServiceResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@CrossOrigin
@Slf4j
@RestController
@Api(tags="vip套餐订购动态接口")
@RequestMapping("/consulterOrderPkgPay")
public class ConsulterOrderPkgPayController {

    @Resource
    private ConsulterOrderPkgPayService consulterOrderPkgPayService;


    @ApiOperation(value = "vip套餐订购动态信息查询", notes = "vip套餐订购动态信息查询")
    @RequestMapping(value = "/queryVipServiceDevelopment")
    @PermissionData(pageComponent = "jeecg/queryVipServiceDevelopment")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageSize",value="查询数量",required=false)
    })
    @Transactional
    public ServiceResult queryVipServiceDevelopment(HttpServletRequest request,
                                                    @RequestParam(value = "pageSize",defaultValue = "200",required =false )
                                                            String pageSize) {
//        long startTime=System.currentTimeMillis();
        List<VipServiceDevelopmentInfoVo> list=consulterOrderPkgPayService.queryVipServiceDevelopment(pageSize);
        ServiceResult<List<VipServiceDevelopmentInfoVo>> vip= new ServiceResult<>();
        if(list==null || list.size()<=0){
            vip.setSuccess(false);
            vip.setCode(1);
            vip.setData(list);
            return vip;
        }
        vip.setSuccess(true);
        vip.setCode(0);
        vip.setData(list);
//        long endTime=System.currentTimeMillis();
//        System.out.println("一共用时:"+(endTime-startTime));
        return vip;
    }





}

