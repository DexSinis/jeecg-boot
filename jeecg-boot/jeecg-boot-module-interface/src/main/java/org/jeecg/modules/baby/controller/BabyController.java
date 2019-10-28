package org.jeecg.modules.baby.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.modules.baby.service.BabyService;
import org.jeecg.util.response.ServiceResult;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-06
 */
@Slf4j
@RestController
@Api(tags="档案接口")
@RequestMapping("/baby")
public class BabyController {

    @Resource
    private BabyService babyService;


    /**
     * 查询咨询者本信息或者宝宝信息
     *
     * @param type
     * @param request
     * @return
     */
    @ApiOperation(value = "查询咨询者本信息或者宝宝信息", notes = "查询咨询者本信息或者宝宝信息")
    @PostMapping(value = "/queryConsulterMessage")
    @PermissionData(pageComponent="jeecg/queryConsulterMessage")
    @ApiImplicitParams({
            @ApiImplicitParam(name="type",value="类型",required=true)
    })
    @Transactional
    public Map<String,Object> queryConsulterMessage(@RequestParam("type") String type,
                                                    HttpServletRequest request){
        String consulterId=request.getAttribute("consulterId").toString();
        Map<String,Object> map=babyService.queryConsulterBabyMessage(consulterId,type);
        return map;

    }

    /**
     * 查询咨询者默认缺省消息
     * @param type
     * @param  tempOrderId 临时工单id
     * @param request
     * @return
     */
    @RequestMapping("/queryDefaultMessage")
    @ResponseBody
    public ServiceResult queryDefaultMessage(@RequestParam(value = "type",required = true) String type,
                                                  @RequestParam(value = "babyId",required = false) String babyId,
                                                  @RequestParam(value = "tempOrderId",required = true) String tempOrderId,
                                                  HttpServletRequest request) {
        String consulterId = request.getAttribute("consulterId").toString();
        Map<String, Object> map = babyService.queryDefaultMessage(type, consulterId, tempOrderId, babyId);
        ServiceResult<Map> result = new ServiceResult<>();
        boolean flag = (boolean) map.get("flag");
        result.setSuccess(flag);
        result.setData(map);
        return result;
    }

    /**
     * 保存宝宝或者妈妈信息
     * @param type
     * @param tempOrderId
     * @param itemId
     * @param itemValue
     * @param request
     * @return
     */
    @RequestMapping("/saveBabyInfo")
    @ResponseBody
    public ServiceResult saveBabyInfo(@RequestParam(value = "type", required = true) String type,
                                           @RequestParam(value = "babyId", required = true) String babyId,
                                           @RequestParam(value = "tempOrderId", required = true) String tempOrderId,
                                           @RequestParam(value = "itemId", required = true) String itemId,
                                           @RequestParam(value = "itemValue", required = true) String itemValue,
                                           HttpServletRequest request) {
        String consulterId = request.getAttribute("consulterId").toString();
        Map<String, Object> map = babyService.saveBabyInfo(type, consulterId, tempOrderId, itemId, itemValue, babyId);
        ServiceResult<Map> result = new ServiceResult<>();
        boolean flag = (boolean) map.get("flag");
        result.setSuccess(flag);
        result.setData(map);
        return result;
    }


    /**
     * 创建一个空信息宝宝
     * @param request
     * @return
     */
    @RequestMapping("/createBaby")
    @ResponseBody
    public  ServiceResult createBaby(@RequestParam(value = "tempOrderId",required = true) String tempOrderId,
                                          HttpServletRequest request){
        String consulterId = request.getAttribute("consulterId").toString();
        Map<String, Object> map = babyService.createBaby(consulterId,tempOrderId);
        ServiceResult<Map> result = new ServiceResult<>();
        boolean flag=(boolean)map.get("flag");
        result.setSuccess(flag);
        result.setData(map);
        return result;
    }


    /**
     * 创建一个空信息宝宝
     * @param request
     * @return
     */
    @RequestMapping("/chooseBaby")
    @ResponseBody
    public  ServiceResult chooseBaby(@RequestParam(value = "tempOrderId",required = true) String tempOrderId,
                                          @RequestParam(value = "babyId",required = true) String babyId,
                                          HttpServletRequest request){
        String consulterId = request.getAttribute("consulterId").toString();
        Map<String, Object> map = babyService.chooseBaby(consulterId,tempOrderId,babyId);
        ServiceResult<Map> result = new ServiceResult<>();
        result.setSuccess(true);
        result.setData(map);
        return result;
    }
}

