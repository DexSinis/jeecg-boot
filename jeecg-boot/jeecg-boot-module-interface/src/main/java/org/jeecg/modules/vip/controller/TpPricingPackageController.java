package org.jeecg.modules.vip.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.aspect.annotation.PermissionData;

import org.jeecg.modules.vip.service.TpPricingPackageService;
import org.jeecg.modules.vip.vo.TpPricingPackageSame;
import org.jeecg.util.response.ServiceResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 定价信息表 前端控制器
 * </p>
 *
 * @author
 * @since 2019-09-29
 */
@CrossOrigin
@Slf4j
@RestController
@Api(tags="套餐信息接口")
@RequestMapping("/tpPricingPackage")
public class TpPricingPackageController {

    @Resource
    private TpPricingPackageService tpPricingPackageService;


    @ApiOperation(value = "套餐信息查询", notes = "套餐信息查询")
    @RequestMapping(value = "/queryScaleCnt")
    @PermissionData(pageComponent = "jeecg/queryScaleCnt")
    @ApiImplicitParams({
//            @ApiImplicitParam(name="type",value="类型",required=true)
    })
    @Transactional
    public ServiceResult queryScaleCnt() {
        ServiceResult<List<TpPricingPackageSame>> result = new ServiceResult<>();
        List<TpPricingPackageSame> list = tpPricingPackageService.queryScaleCnt();
        result.setCode(0);
        result.setData(list);
        result.setSuccess(true);
        return result;
    }


//    @ApiOperation(value = "vip用户权益详细信息查询", notes = "vip用户权益详细信息查询")
//    @PostMapping(value = "/queryConsulterVipInformation")
//    @PermissionData(pageComponent = "jeecg/queryConsulterVipInformation")
//    @ApiImplicitParams({
////            @ApiImplicitParam(name="type",value="类型",required=true)
//    })
//    @Transactional
//    public ServiceResult queryConsulterVipInformation(HttpServletRequest request){
//        String consulterId = request.getAttribute("consulterId").toString();
//        String bMark = request.getAttribute("bMark").toString();
//        ServiceResult serviceResult = tpPricingPackageService.queryConsulterVipInformation(consulterId, bMark);
//        return serviceResult;
//    }



}

