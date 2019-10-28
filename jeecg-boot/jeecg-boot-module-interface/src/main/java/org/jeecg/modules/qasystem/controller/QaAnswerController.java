package org.jeecg.modules.qasystem.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.modules.qasystem.entity.QaUsefulLog;
import org.jeecg.modules.qasystem.service.QaAnswerService;
import org.jeecg.modules.sims.entity.SimsTeacher;
import org.jeecg.util.response.ServiceResult;
import org.jeecg.util.string.StringUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-17
 */
@Slf4j
@RestController
@Api(tags="问答模块")
@RequestMapping("/qaAnswer")
public class QaAnswerController {

    @Resource
    private QaAnswerService qaAnswerService;

    /**
     *
     * @param questionId 问题ID
     * @param answererId 回答者ID
     * @param req  http请求
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "热门回答点赞", notes = "热门回答点赞")
    @PostMapping(value = "/useful")
    @PermissionData(pageComponent="jeecg/useful")
    @ApiImplicitParams({
            @ApiImplicitParam(name="questionId",value="问题ID",required=true),
            @ApiImplicitParam(name="answererId",value="回答者ID",required=true),
    })
    @Transactional
    public ServiceResult useful(@RequestParam(name="questionId") String questionId,
                                @RequestParam(name="answererId" ,required=false) String answererId,
                                @RequestParam(name="usefulFlag") String usefulFlag,
                                HttpServletRequest req) throws  Exception {
        Map map = new HashMap();
        ServiceResult result = new ServiceResult();

        if(!StringUtil.isBlank(questionId)) {
            Integer consulterId = Integer.valueOf(req.getAttribute("consulterId").toString());
            map = qaAnswerService.useful(questionId,answererId,usefulFlag,consulterId);
        }

        if((Boolean) map.get("resultFalg"))
        {
            return result.successInterface("有用成功",map);
        } else{
            return result.errorInterface("有用失败",map);
        }

    }


    /**
     *
     * @param questionId 问题ID
     * @param answererId 回答者ID
     * @param req http请求
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "热门回答点赞状态", notes = "热门回答点赞状态")
    @PostMapping(value = "/usefulCheck")
    @PermissionData(pageComponent="jeecg/usefulCheck")
    @ApiImplicitParams({
            @ApiImplicitParam(name="questionId",value="问题ID",required=true),
            @ApiImplicitParam(name="answererId",value="回答者ID",required=true),
    })
    @Transactional
    public ServiceResult usefulCheck(@RequestParam(name="questionId") String questionId,
                                    @RequestParam(name="answererId" ,required=false) String answererId,
                                    HttpServletRequest req) throws  Exception {
        ServiceResult result = new ServiceResult();
        QaUsefulLog qaUsefulLogUpdate = null;
        if(!StringUtil.isBlank(questionId)) {
          Integer consulterId = Integer.valueOf(req.getAttribute("consulterId").toString());
          qaUsefulLogUpdate =   qaAnswerService.usefulCheck(questionId,answererId,consulterId);

        }
        return result.successInterface("查询成功",qaUsefulLogUpdate);

    }



}

