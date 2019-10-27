package org.jeecg.modules.sims.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.sims.entity.SimsTeacher;
import org.jeecg.modules.sims.service.ISimsTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 教师  前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-03
 */
@RestController
@Api(tags="APP-教师接口")
@RequestMapping("/simsTeacher")
public class PrSimsTeacherController {



    @Autowired
    private ISimsTeacherService simsTeacherService;

    /**
     * 获取教师信息接口
     *
     * @param mobilePhone
     * @param req
     * @return
     */
    @ApiOperation(value = "获取教师信息接口", notes = "通过电话号码获取当前教师信息接口")
    @PostMapping(value = "/teacherInfo")
    @PermissionData(pageComponent="jeecg/teacherInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name="mobilePhone",value="手机号",required=true)
    })
    @Transactional
    public Result<SimsTeacher> teacherInfo(String mobilePhone ,
                                           HttpServletRequest req) {
        boolean queryFlag = false;
        Map map = new HashMap<>();
        map.put("mobilePhone",mobilePhone);
        SimsTeacher simsTeacher =  new SimsTeacher(map);
        Result<SimsTeacher> result = new Result<SimsTeacher>();
        QueryWrapper<SimsTeacher> queryWrapper = QueryGenerator.initQueryWrapper(simsTeacher, req.getParameterMap());
        SimsTeacher simsTeacherResult  = simsTeacherService.getOne(queryWrapper);
        if(simsTeacherResult!=null){
            queryFlag = true;
        }
        if(queryFlag){
            result.successInterface("获取信息成功",simsTeacherResult);
        }else{
            result.errorInterface("获取信息失败",null);
        }
        return result;
    }



    /**
     * 更新教师信息接口
     *
     * @param simsTeacher
     * @param req
     * @return
     */
    @ApiOperation(value = "更新教师信息接口", notes = "通过电话号码更新当前教师信息接口")
    @PostMapping(value = "/teacherInfoUpdate")
    @PermissionData(pageComponent="jeecg/teacherInfoUpdate")
    @Transactional
    public Result<SimsTeacher> teacherInfoUpdate(@RequestBody SimsTeacher simsTeacher ,
                                                 HttpServletRequest req) {
        boolean queryFlag = false;
        Map map = new HashMap<>();
        Result<SimsTeacher> result = new Result<SimsTeacher>();
        if(simsTeacher!=null){
            queryFlag = simsTeacherService.updateById(simsTeacher);
        }
        if(queryFlag){
            result.successInterface("更新信息成功",simsTeacher);
        }else{
            result.errorInterface("更新信息失败",null);
        }
        return result;
    }
}

