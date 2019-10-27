package org.jeecg.modules.sims.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.constant.PetrusConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.sims.entity.SimsLesson;
import org.jeecg.modules.sims.entity.SimsLesson;
import org.jeecg.modules.sims.entity.SimsRescource;
import org.jeecg.modules.sims.entity.SimsTeacher;
import org.jeecg.modules.sims.service.ISimsLessonService;
import org.jeecg.modules.sims.service.ISimsTeacherService;
import org.jeecg.modules.sims.vo.SimsLessonVo;
import org.jeecg.modules.sims.vo.SimsOpernVo;
import org.jeecg.modules.sims.vo.SimsTeacherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 课程  前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-07
 */
@Slf4j
@RestController
@Api(tags="APP-课程接口")
@RequestMapping("/simsLesson")
public class PrSimsLessonController {




    @Autowired
    private ISimsLessonService simsLessonService;


//    @Autowired
//    private ISimsLessonService simsLessonService;


    @Autowired
    private ISimsTeacherService simsTeacherService;


    /**
     * 分页列表查询
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "获取首页课程数据列表", notes = "获取首页课程数据列表")
    @PostMapping(value = "/simsLessonAllList")
    @PermissionData(pageComponent="jeecg/simsLessonAllList")
    public Result<List<SimsTeacherVo>> simsLessonAllList(HttpServletRequest req) {

        Result<List<SimsTeacherVo>> result = new Result<List<SimsTeacherVo>>();
        Map params = new HashMap();
        List<SimsTeacherVo> simsTeacherVoList = simsTeacherService.querySimsTeacherBySort3();
        for(SimsTeacherVo simsTeacherVo:simsTeacherVoList)
        {
            QueryWrapper<SimsLesson> queryWrapperSimsLesson = new QueryWrapper<SimsLesson>();
            queryWrapperSimsLesson.eq("TEACHER_ID",simsTeacherVo.getId());
            queryWrapperSimsLesson.eq("RESERVE", PetrusConstant.RESERVE_TYPE.NONE);
            queryWrapperSimsLesson.gt("LESSON_START",new Date());
            queryWrapperSimsLesson.orderByAsc("LESSON_START");
            List<SimsLesson> simsLessonList = simsLessonService.list(queryWrapperSimsLesson);
            simsTeacherVo.setSimsLessonList(simsLessonList);
        }

        List<SimsTeacherVo> simsTeacherVoResultList  =new ArrayList<>();
        for(SimsTeacherVo simsTeacherVo:simsTeacherVoList)
        {
          if(simsTeacherVo.getSimsLessonList().size()>0){
              simsTeacherVoResultList.add(simsTeacherVo);
          }
        }



        result.setSuccess(true);
        result.setResult(simsTeacherVoResultList);
        return result;

    }



    /**
     * 分页列表查询
     *
     * @param simsLesson
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @ApiOperation(value = "获取课程数据列表", notes = "获取所有课程数据列表")
    @PostMapping(value = "/simsLessonList")
    @PermissionData(pageComponent="jeecg/simsLessonList")
    public Result<IPage<SimsLessonVo>> simsLessonList(@RequestBody SimsLesson simsLesson, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                      HttpServletRequest req) {

        log.error("simsLesson------"+ JSONObject.toJSONString(simsLesson));
        log.error("simsLesson------"+ JSONObject.toJSONString(simsLesson));
        log.error("simsLesson------"+ JSONObject.toJSONString(simsLesson));
        log.error("simsLesson------"+ JSONObject.toJSONString(simsLesson));
        Result<IPage<SimsLessonVo>> result = new Result<IPage<SimsLessonVo>>();
        Map params = new HashMap();
        SimsLessonVo simsLessonVo = new SimsLessonVo();
        Page<SimsLessonVo> page = new Page<SimsLessonVo>(pageNo, pageSize);
        QueryWrapper<SimsLessonVo> queryWrapper = QueryGenerator.initQueryWrapper(simsLessonVo, req.getParameterMap());
        IPage<SimsLessonVo> pageList = simsLessonService.querySimsLessonByParams(page, simsLesson);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;


    }



    /**
     * 更新编辑
     *
     * @param simsLesson
     * @return
     */
    @PostMapping(value = "/edit")
    @ApiOperation(value = "学生更新课程", notes = "学生更新课程")
    public Result<SimsLesson> eidt(@RequestBody SimsLesson simsLesson) {
        Result<SimsLesson> result = new Result<SimsLesson>();
        SimsLesson simsLessonEntity = simsLessonService.getById(simsLesson.getId());
        if (simsLessonEntity == null) {
            result.errorInterface("未找到对应课程",simsLesson);
        } else {
            boolean ok = simsLessonService.updateById(simsLesson);
            // TODO 返回false说明什么？
            if (ok) {
                result.successInterface("更新课程成功",simsLesson);
            }
        }

        return result;
    }





}

