package org.jeecg.modules.statistics.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.consulter.service.ConsulterService;
import org.jeecg.modules.statistics.entity.PersUserD;
import org.jeecg.modules.statistics.entity.PersUserOrderD;
import org.jeecg.modules.statistics.mapper.PersUserDMapper;
import org.jeecg.modules.statistics.mapper.PersUserOrderDMapper;
import org.jeecg.modules.statistics.service.PersUserDService;
import org.jeecg.modules.statistics.service.PersUserOrderDService;
import org.jeecg.util.date.ZxDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 用户日统计表，保存最新用户数据。 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-05
 */
@Service
@Slf4j
public class PersUserDServiceImpl extends ServiceImpl<PersUserDMapper, PersUserD> implements PersUserDService {

    @Resource
    private ConsulterService consulterService;

    @Resource
    private PersUserOrderDService persUserOrderDService;

    @Resource
    private PersUserOrderDMapper persUserOrderDMapper;

    @Resource
    private PersUserDMapper persUserDMapper;

    @Override
    public boolean consulterDAnalysis(String before,String after) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());//设置当前日期
        String yearStr = calendar.get(Calendar.YEAR)+"";//获取年份
        int month = calendar.get(Calendar.MONTH) + 1;//获取月份
        String monthStr = month < 10 ? "0" + month : month + "";
        int day = calendar.get(Calendar.DATE);//获取日
        String dayStr = day < 10 ? "0" + day : day + "";

        Integer statDt = Integer.valueOf(yearStr + monthStr + dayStr);

        log.info("consulterDAnalysis--------------");
        List<Map> consulterDAnalysis = consulterService.findPersUserDList(before,after);
        List<Map> persUserOrderDAnalysis = consulterService.findOrderCountList(before,after);
        log.info(JSON.toJSONString(consulterDAnalysis));
        log.info(JSON.toJSONString(persUserOrderDAnalysis));
        log.info("consulterDAnalysis--------------");
        //插入用户信息统计表
        List<PersUserD> persUserDAnalysisData =  new ArrayList<>();
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        int consulterDAnalysisSize= consulterDAnalysis.size();
        for (int i = 0; i < consulterDAnalysisSize ; i++) {
            Map map = consulterDAnalysis.get(i);
            map.put("statDt",statDt);
            PersUserD persUserD = new PersUserD(map);
            persUserDAnalysisData.add(persUserD);
        }

        //插入用户工单量统计表
        List<PersUserOrderD> persUserOrderDAnalysisData =  new ArrayList<>();
        int OrderDSize= persUserOrderDAnalysisData.size();
        for (int i = 0; i < OrderDSize; i++) {
            Map map = persUserOrderDAnalysis.get(i);
            PersUserOrderD persUserOrderD = new PersUserOrderD();
            persUserOrderD.setStatDt(statDt);
            persUserOrderD.setConsulterId((Integer) map.get("CONSULTER_ID"));
            persUserOrderD.setOrderPayedCnt((Integer) map.get("ORDER_PAYED_CNT"));
            persUserOrderD.setOrderTotalCnt((Integer) map.get("ORDER_TOTAL_CNT"));
            persUserOrderDAnalysisData.add(persUserOrderD);
        }
        Boolean flag = this.saveBatch(persUserDAnalysisData,persUserDAnalysisData.size())
                && persUserOrderDService.saveBatch(persUserOrderDAnalysisData,persUserOrderDAnalysisData.size());
        log.info("flag--------------"+flag);
        return flag;
    }

    @Override
    public boolean clearConsulterDAnalysis(String before,String after) {
        boolean flag = false;
        log.info("开始删除要更新用户信息flag----------:{}",flag);
        try {
            persUserDMapper.deletePersUserDByLastTime(before,after);
            persUserOrderDMapper.deletePersUserOrderDByLastTime(before,after);
            flag = true;
        } catch (Throwable e) {
            flag = false;
            e.printStackTrace();
            log.error(e.toString());
        } finally {
            return flag;
        }
    }

    @Override
    public boolean clearAllConsulterAnalysis() {
        boolean flag = false;
        log.info("开始删除所有用户信息flag----------:{}",flag);
        try {
            persUserDMapper.deleteAllPersUserD();
            persUserOrderDMapper.deleteAllPersUserOrderD();
            flag = true;
        } catch (Throwable e) {
            flag = false;
            e.printStackTrace();
            log.error(e.toString());
        } finally {
            return flag;
        }
    }

    @Override
    public boolean clearOneConsulterAnalysis(String consulterId) {
        boolean flag = false;
        log.info("开始删除所有用户信息flag----------:{}",flag);
        try {
            persUserDMapper.deleteOnePersUserD(consulterId);
            persUserOrderDMapper.deleteOnePersUserOrderD(consulterId);
            flag = true;
        } catch (Throwable e) {
            flag = false;
            e.printStackTrace();
            log.error(e.toString());
        } finally {
            return flag;
        }
    }

    private boolean insertPersUserDForJob(String beforeConsulterId,String afterConsulterId) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());//设置当前日期
        String yearStr = calendar.get(Calendar.YEAR)+"";//获取年份
        int month = calendar.get(Calendar.MONTH) + 1;//获取月份
        String monthStr = month < 10 ? "0" + month : month + "";
        int day = calendar.get(Calendar.DATE);//获取日
        String dayStr = day < 10 ? "0" + day : day + "";

        Integer statDt = Integer.valueOf(yearStr + monthStr + dayStr);

        log.info("consulterDAnalysis开始查list时刻--------------:{}",ZxDateUtils.now());
        List<Map> consulterDAnalysis = consulterService.findPersUserDListByConsulterId(beforeConsulterId,afterConsulterId);
        List<Map> persUserOrderDAnalysis = consulterService.findOrderCountListByConsulterId(beforeConsulterId,afterConsulterId);
//        log.info(JSON.toJSONString(consulterDAnalysis));
//        log.info(JSON.toJSONString(persUserOrderDAnalysis));
        log.info("consulterDAnalysis查完list时刻--------------:{}",ZxDateUtils.now());
        //插入用户信息统计表
        List<PersUserD> persUserDAnalysisData =  new ArrayList<>();
        int consulterDAnalysisSize= consulterDAnalysis.size();
        for (int i = 0; i < consulterDAnalysisSize ; i++) {
            Map map = consulterDAnalysis.get(i);
            map.put("statDt",statDt);
            PersUserD persUserD = new PersUserD(map);
            persUserDAnalysisData.add(persUserD);
        }

        //插入用户工单量统计表
        List<PersUserOrderD> persUserOrderDAnalysisData =  new ArrayList<>();
        int OrderDSize= persUserOrderDAnalysisData.size();
        for (int i = 0; i < OrderDSize; i++) {
            Map map = persUserOrderDAnalysis.get(i);
            PersUserOrderD persUserOrderD = new PersUserOrderD();
            persUserOrderD.setStatDt(statDt);
            persUserOrderD.setConsulterId((Integer) map.get("CONSULTER_ID"));
            persUserOrderD.setOrderPayedCnt((Integer) map.get("ORDER_PAYED_CNT"));
            persUserOrderD.setOrderTotalCnt((Integer) map.get("ORDER_TOTAL_CNT"));
            persUserOrderDAnalysisData.add(persUserOrderD);
        }
        Boolean flag = this.saveBatch(persUserDAnalysisData,persUserDAnalysisData.size())
                && persUserOrderDService.saveBatch(persUserOrderDAnalysisData,persUserOrderDAnalysisData.size());
        log.info("flag--------------"+flag);
        log.info("插入完数据时刻--------------:{}", ZxDateUtils.now());
        return flag;
    }

    /**
     * 无用接口（执行较慢）
     * @param beforeConsulterId
     * @param afterConsulterId
     * @return
     */
    @Override
    public boolean consulterDAnalysisForJob(String beforeConsulterId,String afterConsulterId) {
        boolean flag = false;
        log.info("开始更新用户信息当前时刻----------:{}", ZxDateUtils.now());
        try {
            insertPersUserDForJob(beforeConsulterId,afterConsulterId);
            flag = true;
        } catch (Throwable e) {
            flag = false;
            e.printStackTrace();
            log.error(e.toString());
        } finally {
            return flag;
        }
    }

    @Override
    public boolean insertconsulterDForJob(String beforeConsulterId,String afterConsulterId) {
        boolean flag = false;
        log.info("开始更新用户信息当前时刻----------:{}", ZxDateUtils.now());
        try {
            persUserDMapper.insertPersUserD(beforeConsulterId,afterConsulterId);
            persUserOrderDMapper.insertPersUserOrderD(beforeConsulterId,afterConsulterId);
            flag = true;
        } catch (Throwable e) {
            flag = false;
            e.printStackTrace();
            log.error(e.toString());
        } finally {
            return flag;
        }
    }
}
