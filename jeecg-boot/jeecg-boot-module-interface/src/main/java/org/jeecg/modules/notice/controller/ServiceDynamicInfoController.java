package org.jeecg.modules.notice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.aop.PassToken;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.modules.notice.service.ServiceDynamicInfoService;
import org.jeecg.util.response.ServiceResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-05
 */
@Slf4j
@RestController
@Api(tags="服务动态")
@RequestMapping("/serviceDynamicInfo")
@CrossOrigin
public class ServiceDynamicInfoController {


    @Resource
    private ServiceDynamicInfoService serviceDynamicInfoService;


    @ApiOperation(value = "服务动态", notes = "服务动态")
    @PostMapping(value = "/serviceDynamicInfoList")
    @PermissionData(pageComponent="jeecg/serviceDynamicInfoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNo",value="页码",required=false),
            @ApiImplicitParam(name="pageSize",value="页数",required=false),
            @ApiImplicitParam(name="order",value="排序",required=false),
    })
    @Transactional
    @PassToken(required=true)
    public ServiceResult serviceDynamicInfoList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                    @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                    @RequestParam(name="order",required=false) Integer order,
                                                    HttpServletRequest req) {
        ServiceResult<IPage<Map>> result = new ServiceResult<IPage<Map>>();
        Page<Map> page = new Page<Map>(pageNo,pageSize);
        Map params = new HashMap();
        params.put("order",order);
        IPage<Map> pageList = serviceDynamicInfoService.serviceDynamicInfoList(page,params);
        if(pageList.getTotal()==0){

            Calendar calendarStart =  Calendar.getInstance();
            calendarStart.add(Calendar.DATE,0);
            calendarStart.set(Calendar.HOUR_OF_DAY,0);
            calendarStart.set(Calendar.MINUTE,0);
            calendarStart.set(Calendar.SECOND,0);

            Calendar calendarEnd =  Calendar.getInstance();
            calendarEnd.add(Calendar.DATE,0);
            calendarEnd.set(Calendar.HOUR_OF_DAY,23);
            calendarEnd.set(Calendar.MINUTE,59);
            calendarEnd.set(Calendar.SECOND,59);

            List<Map> serviceDynamicInfoAnalysis = serviceDynamicInfoService.serviceDynamicInfoAnalysis(calendarStart.getTime().getTime(),calendarEnd.getTime().getTime());
            pageList.setRecords(serviceDynamicInfoAnalysis);
            pageList.setSize(pageSize);
            pageList.setTotal(Long.valueOf(serviceDynamicInfoAnalysis.size()+""));
//            List<ServiceDynamicInfo> serviceDynamicInfoAnalysisData =  new ArrayList<>();
//            Integer statDt  = Integer.valueOf(DateUtil.formatToStr(new Date(calendarStart.getTime().getTime()),"yyyyMMdd"));
//            for (int i = 0; i <serviceDynamicInfoAnalysis.size() ; i++) {
//                Map map = serviceDynamicInfoAnalysis.get(i);
//                map.put("statDt",statDt);
//                ServiceDynamicInfo serviceDynamicInfo = new ServiceDynamicInfo(map);
//                serviceDynamicInfoAnalysisData.add(serviceDynamicInfo);
//            }
//            Boolean flag = serviceDynamicInfoService.saveBatch(serviceDynamicInfoAnalysisData,serviceDynamicInfoAnalysisData.size());
        }
        result.setSuccess(true);
        result.setData(pageList);
        return result;
    }



}

