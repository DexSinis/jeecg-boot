package org.jeecg.modules.sims.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.sims.entity.SimsRescource;
import org.jeecg.modules.sims.entity.SimsRescource;
import org.jeecg.modules.sims.service.ISimsRescourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 课程资料表 前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-10
 */
@Slf4j
@RestController
//@Api(tags="资料管理")
@RequestMapping("/simsRescource")
public class PrSimsRescourceController {

    @Autowired
    private ISimsRescourceService SimsRescourceService;

    /**
     * 分页列表查询
     *
     * @param simsRescource
     * @param pageNo
     * @param pageSize
     * @param request
     * @return
     */
//    @ApiOperation(value = "获取资料数据列表", notes = "获取所有资料数据列表")
    @PostMapping(value = "/simsRescourceList")
    @PermissionData(pageComponent="jeecg/simsRescourceList")
    public Result<IPage<SimsRescource>> simsRescourceList(@RequestBody SimsRescource simsRescource, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                          HttpServletRequest request) {



        String id=request.getAttribute("id").toString();
        String mobilePhone=request.getAttribute("mobilePhone").toString();
        String version=request.getAttribute("version").toString();
        String simsPassword=request.getAttribute("simsPassword").toString();
        simsRescource.setSource(id);

        Result<IPage<SimsRescource>> result = new Result<IPage<SimsRescource>>();
        Map params = new HashMap();
        SimsRescource simsRescourceVo = new SimsRescource();
        Page<SimsRescource> page = new Page<SimsRescource>(pageNo, pageSize);
        QueryWrapper<SimsRescource> queryWrapper = QueryGenerator.initQueryWrapper(simsRescourceVo, request.getParameterMap());
        IPage<SimsRescource> pageList = SimsRescourceService.querySimsRescourceByParams(page, simsRescource);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;


    }
}

