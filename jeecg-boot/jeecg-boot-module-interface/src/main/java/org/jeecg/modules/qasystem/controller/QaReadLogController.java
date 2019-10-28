package org.jeecg.modules.qasystem.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.jeecg.aop.PassToken;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.modules.qasystem.service.QaQuestionService;
import org.jeecg.modules.qasystem.service.QaReadLogService;
import org.jeecg.util.response.ServiceResult;
import org.jeecg.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 适用问答模块，用于记录问题阅读日志。 前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-18
 */
@Slf4j
@RestController
@Api(tags="问答模块")
@RequestMapping("/qaReadLog")
@CrossOrigin
public class QaReadLogController {

    @Resource
    private QaReadLogService qaReadLogService;

    @Resource
    private QaQuestionService qaQuestionService;

    /**
     *
     * @param questionId
     * @param answererId
     * @param req
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "新增问答阅读日志", notes = "新增问答阅读日志")
    @PostMapping(value = "/addQaReadLog")
    @PermissionData(pageComponent="jeecg/addQaReadLog")
    @ApiImplicitParams({
            @ApiImplicitParam(name="questionId",value="问题ID",required=true),
            @ApiImplicitParam(name="questionId",value="回答ID",required=false),
    })
    @Transactional
    @PassToken(required=true)
    public ServiceResult addQaReadLog(@RequestParam(name="questionId") String questionId,
                                    @RequestParam(name="answererId" ,required=false) String answererId,
                                    HttpServletRequest req) throws  Exception {
        Map map =  new HashMap();
        if(!StringUtil.isBlank(questionId)) {


            Integer consulterId = Integer.valueOf(req.getAttribute("consulterId").toString());
            String bMark = req.getAttribute("bMark").toString();
            map = qaQuestionService.addQaReadLog(questionId,consulterId,bMark);


        }

        if((boolean)map.get("resultFalg"))
        {

            return ServiceResult.buildSuccess("新增问答阅读日志成功",map);
        } else{
            return ServiceResult.buildError("新增问答阅读日志失败","");
        }

    }
}

