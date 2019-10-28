package org.jeecg.modules.statistics.controller;


import org.jeecg.exception.InterfaceZxException;
import org.jeecg.modules.consulter.service.ConsulterService;
import org.jeecg.modules.statistics.service.PersUserDService;
import org.jeecg.util.response.ServiceResult;
import org.jeecg.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * 用户日统计表，保存最新用户数据。 前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-05
 */
@RestController
@RequestMapping("/persUserD")
@CrossOrigin
@Slf4j
public class PersUserDController {

    @Resource
    private PersUserDService persUserDService;

    @Resource
    private ConsulterService consulterService;

    @RequestMapping("/refleshOneConsulterAnalysis")
    public ServiceResult refleshOneConsulterAnalysis(HttpServletRequest req){
        String consulterId = req.getParameter("CONSULTER_ID");
        ServiceResult result = new ServiceResult();
        if(StringUtil.isBlank(consulterId)){
            return result.buildError("用户Id为空");
        }
        boolean flag = false;
        try {
            flag = persUserDService.clearOneConsulterAnalysis(consulterId);
            if(!flag){
                return result.buildError("更新有误");
            }
            persUserDService.insertconsulterDForJob(String.valueOf (Integer.valueOf(consulterId)-1),String.valueOf (Integer.valueOf(consulterId)));
        } catch (Throwable e) {
            e.printStackTrace();
            log.error(e.toString());
            throw new InterfaceZxException(e.toString());
        } finally {
            if(flag){
                return  result.buildSuccess("刷新用户数据成功",consulterId);
            }else{
                return result.buildError("刷新用户数据失败");
            }
        }
    }

    @RequestMapping("/refleshConsulterAnalysis")
    public ServiceResult refleshConsulterAnalysis(HttpServletRequest req){

        ServiceResult result = new ServiceResult();
        boolean flag = false;
        try {
            String time = req.getParameter("createtime");
//            String[] timeStr = time.split("-");
            Date before = new Date();
            Date after = new Date();
            before = new Date(time);
            after = new Date(time+" 23:59");
            if(before==null || after==null){
                return result.buildError("时间段不能为空");
            }
            flag = persUserDService.clearConsulterDAnalysis(String.valueOf(before.getTime()),String.valueOf(after.getTime()));
            if(flag){
                flag = false;
                flag = persUserDService.consulterDAnalysis(String.valueOf(before.getTime()),String.valueOf(after.getTime()));
            }
        } catch (Throwable e) {
            e.printStackTrace();
            log.error(e.toString());
            throw new InterfaceZxException(e.toString());
        } finally {
            if(flag){
                return  result.buildSuccess("刷新用户数据成功","");
            }else{
                return result.buildError("刷新用户数据失败");
            }
        }
    }

//    public static void main(String[] args){
////        String consulterIdStr = null;
////        System.out.println("before======::"+consulterIdStr);
////        if (StringUtils.isNotBlank(consulterIdStr) && consulterIdStr.startsWith(",")) {
////            consulterIdStr = consulterIdStr.substring(1);
////        }
////        System.out.println("before======::"+consulterIdStr);
////        if (StringUtils.isNotBlank(consulterIdStr) && consulterIdStr.endsWith(",")) {
////            consulterIdStr = consulterIdStr.substring(0,consulterIdStr.length() - 1);
////        }
//        Date da = new Date();
//        System.out.println("before======::"+da.getTime());
//    }

}

