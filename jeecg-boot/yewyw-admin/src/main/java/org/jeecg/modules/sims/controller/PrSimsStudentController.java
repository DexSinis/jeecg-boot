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
//
//    /**
//     * 分页列表查询
//     *
//     * @param simsStudent
//     * @param pageNo
//     * @param pageSize
//     * @param req
//     * @return
//     */
//    @ApiOperation(value = "获取学生数据列表", notes = "获取所有学生数据列表")
//    @GetMapping(value = "/list")
//    @PermissionData(pageComponent="jeecg/SimsStudentList")
//    public Result<IPage<SimsStudent>> list(SimsStudent simsStudent, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
//                                           HttpServletRequest req) {
//        Result<IPage<SimsStudent>> result = new Result<IPage<SimsStudent>>();
//        /*
//         * QueryWrapper<SimsStudent> queryWrapper = null;
//         * //===========================================================================
//         * ===== //高级组合查询 try { String superQueryParams =
//         * req.getParameter("superQueryParams");
//         * if(oConvertUtils.isNotEmpty(superQueryParams)) { // 解码 superQueryParams =
//         * URLDecoder.decode(superQueryParams, "UTF-8"); List<QueryRuleVo> userList =
//         * JSON.parseArray(superQueryParams, QueryRuleVo.class);
//         * log.info(superQueryParams); queryWrapper = new QueryWrapper<SimsStudent>(); for
//         * (QueryRuleVo rule : userList) { if(oConvertUtils.isNotEmpty(rule.getField())
//         * && oConvertUtils.isNotEmpty(rule.getRule()) &&
//         * oConvertUtils.isNotEmpty(rule.getVal())){
//         * ObjectParseUtil.addCriteria(queryWrapper, rule.getField(),
//         * QueryRuleEnum.getByValue(rule.getRule()), rule.getVal()); } } } } catch
//         * (UnsupportedEncodingException e) { e.printStackTrace(); }
//         * //===========================================================================
//         * =====
//         *
//         * // 手工转换实体驼峰字段为下划线分隔表字段 queryWrapper = queryWrapper==null?new
//         * QueryWrapper<SimsStudent>(simsStudent):queryWrapper; Page<SimsStudent> page = new
//         * Page<SimsStudent>(pageNo, pageSize);
//         *
//         * // 排序逻辑 处理 String column = req.getParameter("column"); String order =
//         * req.getParameter("order"); if (oConvertUtils.isNotEmpty(column) &&
//         * oConvertUtils.isNotEmpty(order)) { if ("asc".equals(order)) {
//         * queryWrapper.orderByAsc(oConvertUtils.camelToUnderline(column)); } else {
//         * queryWrapper.orderByDesc(oConvertUtils.camelToUnderline(column)); } }
//         */
//
//        QueryWrapper<SimsStudent> queryWrapper = QueryGenerator.initQueryWrapper(simsStudent, req.getParameterMap());
//        Page<SimsStudent> page = new Page<SimsStudent>(pageNo, pageSize);
//
//        IPage<SimsStudent> pageList = simsStudentService.page(page, queryWrapper);
////		log.info("查询当前页：" + pageList.getCurrent());
////		log.info("查询当前页数量：" + pageList.getSize());
////		log.info("查询结果数量：" + pageList.getRecords().size());
////		log.info("数据总数：" + pageList.getTotal());
//        result.setSuccess(true);
//        result.setResult(pageList);
//        return result;
//    }


    /**
     * 获取学生信息接口
     *
     * @param mobilePhone
     * @param req
     * @return
     */
    @ApiOperation(value = "获取学生信息接口", notes = "通过电话号码获取当前学生信息接口")
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

