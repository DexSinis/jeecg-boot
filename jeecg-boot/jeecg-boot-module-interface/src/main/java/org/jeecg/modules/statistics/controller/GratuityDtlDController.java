package org.jeecg.modules.statistics.controller;


import org.jeecg.exception.InterfaceZxException;
import org.jeecg.modules.statistics.service.GratuityDtlDService;
import org.jeecg.util.response.ServiceResult;
import org.jeecg.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 心意日统计表，保存用户的送出的心意明细数据。 前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-06
 */
@RestController
@RequestMapping("/gratuityDtlD")
@CrossOrigin
@Slf4j
public class GratuityDtlDController {

    @Resource
    private GratuityDtlDService gratuityDtlDService;

    /**
     * 刷新时间段内心意
     * @param req
     * @return
     */
    @RequestMapping("/refreshGratuityDtlD")
    public ServiceResult refreshGratuityDtlDAnalysisTillNow(HttpServletRequest req){
        ServiceResult result = new ServiceResult();
        boolean flag = false;
        try {
            String time = req.getParameter("createtime");
            String[] timeStr = time.split("-");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String before = "";
            String after = "";
            if(timeStr.length==2){
                before = df.format(new Date(timeStr[0]));
                after = df.format(new Date(timeStr[1]));
            }else if(timeStr.length==1){
                before = df.format(new Date(timeStr[0]));
            }else{
                return result.buildError("时间格式应为：'MM/dd/yyyy HH:mm-MM/dd/yyyy HH:mm'");
            }
            if(StringUtil.isBlank(before)){
                return result.buildError("时间段不能为空");
            }
            flag = gratuityDtlDService.refreshGratuityDtlDByTime(before,after);
        } catch (Throwable e) {
            e.printStackTrace();
            log.error(e.toString());
            throw new InterfaceZxException(e.toString());
        } finally {
            if(flag){
                return  result.buildSuccess("刷新心意数据成功","");
            }else{
                return result.buildError("刷新心意数据失败");
            }
        }
    }
}

