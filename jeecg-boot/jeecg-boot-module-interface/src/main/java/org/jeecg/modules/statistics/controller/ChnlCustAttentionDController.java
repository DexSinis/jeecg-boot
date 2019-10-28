package org.jeecg.modules.statistics.controller;


import org.jeecg.exception.InterfaceZxException;
import org.jeecg.modules.statistics.service.ChnlCustAttentionDService;
import org.jeecg.util.response.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * 渠道关注日统计表，保存每天各个渠道的医生用户关注数量。 前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-10
 */
@RestController
@RequestMapping("/chnlCustAttentionD")
@CrossOrigin
@Slf4j
public class ChnlCustAttentionDController {

    @Resource
    private ChnlCustAttentionDService chnlCustAttentionDService;

    @RequestMapping("/refreshChnlCustAttentionD")
    public ServiceResult refreshGratuityDtlDAnalysis(HttpServletRequest req){
        ServiceResult result = new ServiceResult();
        boolean flag = false;
        try {
            String time = req.getParameter("createtime");
            String[] timeStr = time.split("-");
            Date before = new Date();
            Date after = new Date();
            if(timeStr.length==2){
                before = new Date(timeStr[0]);
                after = new Date(timeStr[1]+" 23:59");
            }else{
                return result.buildError("时间格式应为：'MM/dd/yyyy HH:mm-MM/dd/yyyy HH:mm'");
            }
            if(before==null || after==null){
                return result.buildError("时间段不能为空");
            }
            flag = chnlCustAttentionDService.refreshChnlCustAttentionDByTime(before,after);
        } catch (Throwable e) {
            e.printStackTrace();
            log.error(e.toString());
            throw new InterfaceZxException(e.toString());
        } finally {
            if(flag){
                return  result.buildSuccess("刷新渠道数据成功","");
            }else{
                return result.buildError("刷新渠道数据失败");
            }
        }
    }

}

