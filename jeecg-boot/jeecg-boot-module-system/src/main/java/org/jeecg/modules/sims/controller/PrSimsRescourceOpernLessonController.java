package org.jeecg.modules.sims.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.sims.entity.SimsLesson;
import org.jeecg.modules.sims.entity.SimsRescource;
import org.jeecg.modules.sims.entity.SimsRescourceOpernLesson;
import org.jeecg.modules.sims.service.ISimsOpernService;
import org.jeecg.modules.sims.service.ISimsRescourceOpernLessonService;
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
 * 资料课程关系 前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-13
 */
@Slf4j
@RestController
@Api(tags="APP-曲谱管理")
@RequestMapping("/simsRescourceOpernLesson")
public class PrSimsRescourceOpernLessonController {



    @Resource
    ISimsRescourceOpernLessonService simsRescourceOpernLessonService;


    @Resource
    ISimsOpernService simsOpernService;


    @Resource
    ISimsRescourceService simsRescourceService;

    /**
     * 分页列表查询
     *
     * @param simsLesson
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @ApiOperation(value = "获取课程曲谱数据列表", notes = "获取课室所有曲谱数据列表")
    @PostMapping(value = "/list")
    @PermissionData(pageComponent="jeecg/SimsRescourceOpernLessonList")
    public Result<IPage<SimsOpernVo>> list(@RequestBody SimsLesson simsLesson, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                           HttpServletRequest req) {
        Result<IPage<SimsOpernVo>> result = new Result<IPage<SimsOpernVo>>();
        Map params = new HashMap();
        SimsOpernVo simsOpernVo = new SimsOpernVo();
        Page<SimsOpernVo> page = new Page<SimsOpernVo>(pageNo, pageSize);
        QueryWrapper<SimsOpernVo> queryWrapper = QueryGenerator.initQueryWrapper(simsOpernVo, req.getParameterMap());
        IPage<SimsOpernVo> pageList = simsOpernService.simsRescourceOpernLessonList(page, simsLesson);
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

    /**
     * 添加课程资料关联关系
     *
     * @param simsRescourceOpernLesson
     * @return
     */
    @PostMapping(value = "/add")
    @AutoLog(value = "添加课程曲谱")
    @ApiOperation(value = "添加课程曲谱", notes = "添加课程曲谱")
    public Result<SimsRescourceOpernLesson> add(@RequestBody SimsRescourceOpernLesson simsRescourceOpernLesson) {
        Result<SimsRescourceOpernLesson> result = new Result<SimsRescourceOpernLesson>();
        try {
            simsRescourceOpernLessonService.save(simsRescourceOpernLesson);
            result.successInterface("添加成功",simsRescourceOpernLesson);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.errorInterface("添加失败",simsRescourceOpernLesson);
        }
        return result;
    }


}

