package org.jeecg.modules.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.statistics.entity.GratuityDtlD;
import org.jeecg.modules.statistics.mapper.GratuityDtlDMapper;
import org.jeecg.modules.statistics.service.GratuityDtlDService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 * 心意日统计表，保存用户的送出的心意明细数据。 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-06
 */
@Service
@Slf4j
public class GratuityDtlDServiceImpl extends ServiceImpl<GratuityDtlDMapper, GratuityDtlD> implements GratuityDtlDService {

    @Resource
    private GratuityDtlDMapper gratuityDtlDMapper;

    @Override
    public boolean refreshGratuityDtlDByTime(String before, String after) {
        log.info("refreshGratuityDtlDByTime接口--------------");
        boolean flag = cleanGratuityDtlDByTime(before,after);
        if(flag){
            flag = gratuityDtlDAnalysisByTime(before, after);
        }
        log.info("refreshGratuityDtlDByTime接口--------flag------"+flag);
        return flag;
    }

    /**
     *  删除时间段内的心意统计数据
     * @param before 起始时间
     * @param after 终止时间(可传null)
     * @return
     */
    private boolean cleanGratuityDtlDByTime(String before, String after){
        boolean flag = false;
        log.info("cleanGratuityDtlDByTime--------------");
        QueryWrapper<GratuityDtlD> queryWrapperD = new QueryWrapper<GratuityDtlD>();
        if(after != null){
            queryWrapperD = new QueryWrapper<GratuityDtlD>().between("CREATE_TIME",before,after);
        }else{
            queryWrapperD = new QueryWrapper<GratuityDtlD>().gt("CREATE_TIME",before);
        }
        try{
            this.remove(queryWrapperD);
            flag = true;
        }catch (Exception e){
            flag = false;
            log.error(e.getMessage());
        }

        log.info("cleanGratuityDtlDByTime-------flag-------:{}",flag);
        return flag;
    }

    /**
     *  统计时间段内的心意数据
     * @param before 起始时间
     * @param after 终止时间(可传null)
     * @return
     */
    private boolean gratuityDtlDAnalysisByTime(String before, String after){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());//设置当前日期
        String yearStr = calendar.get(Calendar.YEAR)+"";//获取年份
        int month = calendar.get(Calendar.MONTH) + 1;//获取月份
        String monthStr = month < 10 ? "0" + month : month + "";
        int day = calendar.get(Calendar.DATE);//获取日
        String dayStr = day < 10 ? "0" + day : day + "";
        Integer statDt = Integer.valueOf(yearStr + monthStr + dayStr);
        log.info("gratuityDtlDAnalysisByTime--------------:{}",statDt);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        boolean flag = false;
        List<Map> analysisDataList = gratuityDtlDMapper.findGratuityDtlDByTime(before, after);
        int len = analysisDataList.size();
        List<GratuityDtlD> buildAnalysisDataList = new ArrayList<>();
        for (int i = 0; i <len ; i++) {
            Map map = analysisDataList.get(i);
            GratuityDtlD gratuityDtlD = new GratuityDtlD();
            gratuityDtlD.setStatDt(statDt);
            gratuityDtlD.setBMark((String) map.get("B_MARK"));
            gratuityDtlD.setConsulterId((Integer) map.get("CONSULTER_ID"));
            gratuityDtlD.setCreateTime(new Date(((Timestamp) map.get("CREATE_TIME")).getTime()));
            gratuityDtlD.setCustomerId((Integer) map.get("CUSTOMER_ID"));
            gratuityDtlD.setFee(new BigDecimal((String)map.get("FEE")));
            gratuityDtlD.setGratuityId((Integer) map.get("GRATUITY_ID"));
            gratuityDtlD.setGratuityType(Integer.valueOf((String)map.get("GRATUITY_TYPE")));
            gratuityDtlD.setOrderId((Integer) map.get("ORDER_ID"));
            gratuityDtlD.setIsGiveSuccess(((Long) map.get("IS_GIVE_SUCCESS")).intValue());
            buildAnalysisDataList.add(gratuityDtlD);
        }
        flag = this.saveBatch(buildAnalysisDataList);
        log.info("flag--------------:{}",flag);
        return flag;
    }
}
