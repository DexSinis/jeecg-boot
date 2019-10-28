package org.jeecg.modules.qasystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.aop.PassToken;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.modules.blsystem.entity.SysSystemParam;
import org.jeecg.modules.blsystem.service.SysSystemParamService;
import org.jeecg.modules.qasystem.service.QaQuestionService;
import org.jeecg.util.response.ServiceResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用于问答模块，记录常见问题。 前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-17
 */

@Slf4j
@RestController
@Api(tags="问答模块")
@RequestMapping("/qaQuestion")
@CrossOrigin
public class QaQuestionController {


    @Resource
    private QaQuestionService qaQuestionService;


    @Resource
    private SysSystemParamService sysSystemParamService;

//    @RequestMapping(value = "/qaQuestionList/{pageNo}/{pageSize}", method = RequestMethod.POST)
//    @ResponseBody
//    @ApiOperation(value = "根据汽车id查询汽车", notes = "根据车辆编号查询", code = 200, produces = "application/json")
//    public ServiceResult<IPage<Map>> qaQuestionList(
//        @ApiParam(name = "pageNo", value = "页码", required = true) @PathVariable("pageNo") int pageNo,
//        @ApiParam(name = "pageSize", value = "分页数", required = true) @PathVariable("pageSize") int pageSize) {
//        ServiceResult<IPage<Map>> result = new ServiceResult<IPage<Map>>();
//        Page<Map> page = new Page<Map>(pageNo,pageSize);
//
//        Map params = new HashMap();
//        IPage<Map> pageList = qaQuestionService.queryQuestionByParams(page,params);
//        result.setSuccess(true);
//        result.setData(pageList);
//        return result;
//    }


    /**
     * 热门提问列表
     * @param pageNo
     * @param pageSize
     * @param questionId
     * @param labelId
     * @param answererId
     * @param req
     * @return
     */

    @ApiOperation(value = "热门回答点赞", notes = "热门回答点赞")
    @RequestMapping(value = "/qaQuestionList")
    @PermissionData(pageComponent="jeecg/qaQuestionList")
    @ApiImplicitParams({
            @ApiImplicitParam(name="questionId",value="问题ID",required=false),
            @ApiImplicitParam(name="labelId",value="标签ID",required=false),
            @ApiImplicitParam(name="answererId",value="回答者ID",required=false),
    })
    @Transactional
    @PassToken(required=true)
    public ServiceResult qaQuestionList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                    @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                    @RequestParam(name="questionId",  required=false) Integer questionId,
                                                    @RequestParam(name="labelId",  required=false) Integer labelId,
                                                    @RequestParam(name="answererId",  required=false) Integer answererId,
                                                    HttpServletRequest req) {
        ServiceResult<IPage<Map>> result = new ServiceResult<IPage<Map>>();
        Page<Map> page = new Page<Map>(pageNo,pageSize);
        String column = req.getParameter("column");
        String order = req.getParameter("order");

        Map params = new HashMap();
        params.put("questionId",questionId);
        params.put("labelId",labelId);
        params.put("answererId",answererId);
        IPage<Map> pageList = qaQuestionService.queryQuestionByParams(page,params);
        result.setSuccess(true);
        result.setData(pageList);
        return result;
    }

//    @ApiOperation("接口描述：根据分页查询问答列表数据")
//    @ApiResponses({
//            @ApiResponse(code = 0, message = "返回失败"),
//            @ApiResponse(code = 1, message = "返回成功"),
//            @ApiResponse(code = 40001, message = "参数不合法"),
//            @ApiResponse(code = 50001, message = "系统异常"),})
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, defaultValue = "1"),
//            @ApiImplicitParam(name = "pageSize", value = "分页数", required = true, defaultValue = "10"),
//    })
//    @RequestMapping(value = "/qaQuestionList/{pageNo}/{pageSize}", method = RequestMethod.POST)
//    public ServiceResult valiVin(@PathVariable String pageNo, @PathVariable String pageSize) {
//
//        ServiceResult<IPage<Map>> result = new ServiceResult<IPage<Map>>();
//        Page<Map> page = new Page<Map>(Integer.valueOf(pageNo),Integer.valueOf(pageSize));
//
//        Map params = new HashMap();
//        IPage<Map> pageList = qaQuestionService.queryQuestionByParams(page,params);
//        result.setSuccess(true);
//        result.setData(pageList);
//        return result;
//
//    }



    /**
     * 有用问题的列表
     * @param pageNo
     * @param pageSize
     * @param questionId
     * @param labelId
     * @param answererId
     * @param req
     * @return
     */
    @ApiOperation(value = "有用问题的列表", notes = "有用问题的列表")
    @PostMapping(value = "/qaQuestionMyList")
    @PermissionData(pageComponent="jeecg/qaQuestionMyList")
    @ApiImplicitParams({
            @ApiImplicitParam(name="questionId",value="问题ID",required=false),
            @ApiImplicitParam(name="labelId",value="标签ID",required=false),
            @ApiImplicitParam(name="answererId",value="回答者ID",required=false),
    })
    @Transactional
    @PassToken(required=true)
    public ServiceResult qaQuestionMyList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                      @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                      @RequestParam(name="questionId",  required=false) Integer questionId,
                                                      @RequestParam(name="labelId",  required=false) Integer labelId,
                                                      @RequestParam(name="answererId",  required=false) Integer answererId,
                                                      HttpServletRequest req) {
        ServiceResult<IPage<Map>> result = new ServiceResult<IPage<Map>>();
        Page<Map> page = new Page<Map>(pageNo,pageSize);
        String column = req.getParameter("column");
        String order = req.getParameter("order");

        Map params = new HashMap();
        Integer consulterId = Integer.valueOf(req.getAttribute("consulterId").toString());
        params.put("questionId",questionId);
        params.put("labelId",labelId);
        params.put("answererId",answererId);
        params.put("consulterId",consulterId);
        IPage<Map> pageList = qaQuestionService.queryQuestionByConsulterId(page,params);
        result.setSuccess(true);
        result.setData(pageList);
        return result;
    }


    /**
     *
     * @param questionId 问题ID
     * @param req  http请求
     * @return
     * @throws Exception
     */

    @ApiOperation(value = "查询单个问题", notes = "查询单个问题")
    @PostMapping(value = "/questionById")
    @PermissionData(pageComponent="jeecg/questionById")
    @ApiImplicitParams({
            @ApiImplicitParam(name="questionId",value="问题ID",required=true),
    })
    @Transactional
    @PassToken(required=true)
    public ServiceResult refleshJob(@RequestParam(name="questionId") String questionId,
                                    HttpServletRequest req) throws  Exception {

        ServiceResult<Map> result = new ServiceResult<Map>();
        Map params = new HashMap();
        params.put("questionId",questionId);
        Map data = qaQuestionService.queryQuestionMapByParams(params);
        SysSystemParam sysSystemParamQuery = new SysSystemParam();
        sysSystemParamQuery.setParamName("QUESTION_INTO_REMART");
        SysSystemParam sysSystemRecord = sysSystemParamService.getOne(new QueryWrapper<SysSystemParam>(sysSystemParamQuery));
        data.put("sysSystemRecord",sysSystemRecord);
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

}

