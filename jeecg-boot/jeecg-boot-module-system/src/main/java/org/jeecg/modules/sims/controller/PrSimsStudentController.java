package org.jeecg.modules.sims.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tls.tls_sigature.tls_sigature;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.constant.PetrusConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.sims.entity.SimsStudent;
import org.jeecg.modules.sims.service.ISimsStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 学生  前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-03
 */
@Slf4j
@RestController
@Api(tags="APP-学生接口")
@RequestMapping("/simsStudent")
public class PrSimsStudentController {

    @Autowired
    private ISimsStudentService simsStudentService;

    @Autowired
    private RedisUtil redisUtil;



    /**
     * 获取学生信息接口
     *
     * @param mobilePhone
     * @param req
     * @return
     */
    @ApiOperation(value = "获取学生信息接口(2.1.0)", notes = "通过电话号码获取当前学生信息接口")
    @PostMapping(value = "/studentInfo")
    @PermissionData(pageComponent="jeecg/studentInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name="mobilePhone",value="手机号",required=true)
    })
    @Transactional
    public Result<SimsStudent> studentInfo(String mobilePhone ,
                                        HttpServletRequest req) {
        boolean queryFlag = false;
        Map map = new HashMap<>();
        map.put("mobilePhone",mobilePhone);
        SimsStudent simsStudent =  new SimsStudent(map);
        Result<SimsStudent> result = new Result<SimsStudent>();
        QueryWrapper<SimsStudent> queryWrapper = QueryGenerator.initQueryWrapper(simsStudent, req.getParameterMap());
        SimsStudent simsStudentResult  = simsStudentService.getOne(queryWrapper);
        if(simsStudentResult!=null){
            queryFlag = true;
        }
        if(queryFlag){
            result.successInterface("获取信息成功",simsStudentResult);
        }else{
            result.errorInterface("获取信息失败",null);
        }
        return result;
    }



    /**
     * 获取学生信息接口
     *
     * @param simsStudent
     * @param req
     * @return
     */
    @ApiOperation(value = "获取学生信息接口", notes = "通过电话号码获取当前学生信息接口")
    @PostMapping(value = "/studentInformation")
    @PermissionData(pageComponent="jeecg/studentInformation")
    @Transactional
    public Result<SimsStudent> studentInformation(@RequestBody SimsStudent simsStudent ,
                                           HttpServletRequest req) {
        boolean queryFlag = false;
        Map map = new HashMap<>();
        Result<SimsStudent> result = new Result<SimsStudent>();
        QueryWrapper<SimsStudent> queryWrapper = QueryGenerator.initQueryWrapper(simsStudent, req.getParameterMap());
        SimsStudent simsStudentResult  = simsStudentService.getOne(queryWrapper);
        if(simsStudentResult!=null){
            queryFlag = true;
        }
        if(queryFlag){
            result.successInterface("获取信息成功",simsStudentResult);
        }else{
            result.errorInterface("获取信息失败",null);
        }
        return result;
    }


    /**
     * 更新学生信息接口
     *
     * @param simsStudent
     * @param req
     * @return
     */
    @ApiOperation(value = "更新学生信息接口", notes = "通过电话号码更新当前学生信息接口")
    @PostMapping(value = "/studentInfoUpdate")
    @PermissionData(pageComponent="jeecg/studentInfoUpdate")
    @Transactional
    public Result<SimsStudent> studentInfoUpdate(@RequestBody SimsStudent simsStudent ,
                                           HttpServletRequest req) {
        boolean queryFlag = false;
        Map map = new HashMap<>();
        Result<SimsStudent> result = new Result<SimsStudent>();
        if(simsStudent!=null){
            queryFlag = simsStudentService.updateById(simsStudent);
        }
        if(queryFlag){
            result.successInterface("更新信息成功",simsStudent);
        }else{
            result.errorInterface("更新信息失败",null);
        }
        return result;
    }

}

