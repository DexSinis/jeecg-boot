package org.jeecg.modules.sims.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.upload.UUIDUtils;
import org.jeecg.modules.sims.entity.SimsOpern;
import org.jeecg.modules.sims.entity.SimsRescource;
import org.jeecg.modules.sims.service.ISimsOpernService;
import org.jeecg.modules.sims.service.ISimsRescourceService;
import org.jeecg.modules.sims.vo.SimsOpernVo;
import org.jeecg.modules.sims.vo.SimsOpernVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * <p>
 * 曲谱  前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-12
 */

@Slf4j
@RestController
@Api(tags="APP-曲谱管理")
@RequestMapping("/simsOpern")
public class PrSimsOpernController {

    @Autowired
    private ISimsOpernService simsOpernService;

    @Autowired
    private ISimsRescourceService simsRescourceService;

    @Autowired
    private RedisUtil redisUtil;



    /**
     * 添加
     *
     * @param simsOpern
     * @return
     */
    @PostMapping(value = "/add")
    @AutoLog(value = "添加学生曲谱")
    @ApiOperation(value = "添加学生曲谱", notes = "添加学生曲谱")
    public Result<SimsOpern> add(@RequestBody SimsOpern simsOpern) {
        Result<SimsOpern> result = new Result<SimsOpern>();
        try {
            simsOpern.setId(UUIDUtils.getUUID());
            simsOpernService.save(simsOpern);
            result.successInterface("添加成功",simsOpern);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.errorInterface("添加失败",simsOpern);
        }
        return result;
    }

    /**
     * 编辑
     *
     * @param simsOpern
     * @return
     */
    @PostMapping(value = "/edit")
    @ApiOperation(value = "编辑更新曲谱", notes = "编辑更新曲谱")
    public Result<SimsOpern> eidt(@RequestBody SimsOpern simsOpern) {
        Result<SimsOpern> result = new Result<SimsOpern>();
        SimsOpern simsOpernEntity = simsOpernService.getById(simsOpern.getId());
        if (simsOpernEntity == null) {
            result.errorInterface("未找到对应曲谱",simsOpern);
        } else {
            boolean ok = simsOpernService.updateById(simsOpern);
            // TODO 返回false说明什么？
            if (ok) {
                result.successInterface("编辑曲谱成功",simsOpern);
            }
        }

        return result;
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "删除曲谱")
    @PostMapping(value = "/delete")
    @ApiOperation(value = "通过ID删除曲谱", notes = "通过ID删除曲谱")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        Result result = new Result();
        try {
            simsOpernService.removeById(id);
        } catch (Exception e) {
            log.error("删除失败",e.getMessage());
           return result.errorInterface("删除失败",id);
        }
        return result.successInterface("删除成功",id);
    }

    /**
     * 查询对应曲谱
     *
     * @param simsOpern
     * @return
     */
    @PostMapping(value = "/query")
    @ApiOperation(value = "查询对应曲谱", notes = "查询对应曲谱")
    public Result<SimsOpernVo> query(@RequestBody SimsOpern simsOpern) {
        Result<SimsOpernVo> result = new Result<SimsOpernVo>();
//        SimsOpern simsOpernEntity = simsOpernService.getById(simsOpern.getId());
//        SimsOpernVo simsOpernVo = (SimsOpernVo)simsOpern;
        SimsOpernVo simsOpernVo = simsOpernService.simsRescourceOpern(simsOpern);
        if (simsOpernVo == null) {
            result.errorInterface("未找到对应曲谱",simsOpernVo);

        } else {

            QueryWrapper<SimsRescource> queryWrapperResource = new QueryWrapper<SimsRescource>();
            queryWrapperResource.eq("OPERN_ID",simsOpern.getId());
            List<SimsRescource> rescourceList = simsRescourceService.list(queryWrapperResource);
            simsOpernVo.setSimsRescourceList(rescourceList);
            // TODO 返回false说明什么？
            result.successInterface("编辑曲谱成功",simsOpernVo);
        }

        return result;
    }





  
}

