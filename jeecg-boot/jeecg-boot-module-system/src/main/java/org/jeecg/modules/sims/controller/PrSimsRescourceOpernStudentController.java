package org.jeecg.modules.sims.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.api.scripting.JSObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.sims.entity.*;
import org.jeecg.modules.sims.service.ISimsOpernService;
import org.jeecg.modules.sims.service.ISimsRescourceOpernStudentService;
import org.jeecg.modules.sims.service.ISimsRescourceService;
import org.jeecg.modules.sims.vo.SimsOpernVo;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资料学生关系 前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-12
 */
@Slf4j
@RestController
@Api(tags="APP-曲谱管理")
@RequestMapping("/simsRescourceOpernStudent")
public class PrSimsRescourceOpernStudentController {
    @Resource
    ISimsRescourceOpernStudentService simsRescourceOpernStudentService;

    @Resource
    ISimsOpernService simsOpernService;


    @Resource
    ISimsRescourceService simsRescourceService;


    /**
     * 分页列表查询
     *
     * @param simsStudent
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @ApiOperation(value = "获取学生曲谱数据列表", notes = "获取学生所有曲谱数据列表")
    @PostMapping(value = "/list")
    @PermissionData(pageComponent="jeecg/SimsRescourceOpernStudentList")
    public Result<IPage<SimsOpernVo>> list(@RequestBody SimsStudent simsStudent, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                           HttpServletRequest req) {
        log.error("simsStudent---------"+ JSONObject.toJSONString(simsStudent));
        log.error("simsStudent---------"+ JSONObject.toJSONString(simsStudent));
        log.error("simsStudent---------"+ JSONObject.toJSONString(simsStudent));
        log.error("simsStudent---------"+ JSONObject.toJSONString(simsStudent));
        Result<IPage<SimsOpernVo>> result = new Result<IPage<SimsOpernVo>>();
        Map params = new HashMap();
        SimsOpernVo simsOpernVo = new SimsOpernVo();
        Page<SimsOpernVo> page = new Page<SimsOpernVo>(pageNo, pageSize);
        QueryWrapper<SimsOpernVo> queryWrapper = QueryGenerator.initQueryWrapper(simsOpernVo, req.getParameterMap());
        IPage<SimsOpernVo> pageList = simsOpernService.simsRescourceOpernStudentList(page, simsStudent);
        for(SimsOpernVo simsOpernVo1:pageList.getRecords()){
            QueryWrapper<SimsRescource> queryWrapperResource = new QueryWrapper<SimsRescource>();
            queryWrapperResource.eq("OPERN_ID",simsOpernVo1.getId());
            List<SimsRescource> rescourceList = simsRescourceService.list(queryWrapperResource);
            simsOpernVo1.setSimsRescourceList(rescourceList);
        }
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }
}

