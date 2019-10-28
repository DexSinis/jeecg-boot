package org.jeecg.modules.coupon.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.modules.coupon.service.TUserResourceUsedService;
import org.jeecg.modules.vip.vo.ResourceVo;
import org.jeecg.modules.vip.vo.TUserResourceVo;
import org.jeecg.util.response.ServiceResult;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;



/**
 * <p>
 * 记录用户对营销资源的领用、消费使用情况 前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@CrossOrigin
@Slf4j
@RestController
@Api(tags="我的优惠券接口")
@RequestMapping("/tUserResourceUsed")
public class TUserResourceUsedController {

    @Resource
    private TUserResourceUsedService tUserResourceUsedService;


    /**
     * 优惠券查询接口
     * @param pageNo
     * @param type 代表优惠券有效或失效
     * @return
     */
    @ApiOperation(value = "我的优惠券查询", notes = "我的优惠券查询")
    @RequestMapping(value = "/myCoupons")
    @PermissionData(pageComponent = "jeecg/myCoupons")
    @ApiImplicitParams({
            @ApiImplicitParam(name="type",value="优惠券类型",required=true),
            @ApiImplicitParam(name="pageNo",value="页码",required=true),
            @ApiImplicitParam(name="pageSize",value="每页的数量",required=false),
            @ApiImplicitParam(name="serviceId",value="页面类型",required=false),
            @ApiImplicitParam(name="consultFee",value="优惠券面值",required=false)
    })
    @Transactional
    public ServiceResult myCoupons(HttpServletRequest request,
                                   @RequestParam(value = "type", required = true) String type,
                                   @RequestParam(value = "pageNo", required = true, defaultValue = "1") String pageNo,
                                   @RequestParam(value = "pageSize",required = false) String pageSize,
                                   @RequestParam(value = "serviceId",required = false) String serviceId,
                                   @RequestParam(value="consultFee",required = false,defaultValue = "0") String consultFee
    ) {
        String consulterId = request.getAttribute("consulterId").toString();
        Page<TUserResourceVo> page;
        if (StringUtils.isNotEmpty(pageSize)) {
            page = new Page<>(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
        } else {
            page = new Page<>(Integer.valueOf(pageNo), 1000000);
        }
        List<List<TUserResourceVo>> list = tUserResourceUsedService.myCoupons(consulterId, page, type,serviceId,consultFee);
        ServiceResult<ResourceVo> serviceResult = new ServiceResult<>();
        ResourceVo vo = new ResourceVo();
        vo.setPageNo(pageNo);
        if (list == null || list.size() <= 0) {
            serviceResult.setMsg("暂无优惠券");
            serviceResult.setCode(0);
            serviceResult.setSuccess(false);
            serviceResult.setData(vo);
            list=new ArrayList<>();
            vo.setList(list);
            return serviceResult;
        }
        serviceResult.setSuccess(true);
        serviceResult.setCode(0);
        vo.setList(list);
        serviceResult.setData(vo);
        return serviceResult;

    }

}

