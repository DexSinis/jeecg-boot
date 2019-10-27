package org.jeecg.modules.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.statistics.entity.ChnlCustAttentionD;
import org.jeecg.modules.statistics.mapper.ChnlCustAttentionDMapper;
import org.jeecg.modules.statistics.mapper.WeixinFansRecordMapper;
import org.jeecg.modules.statistics.service.ChnlCustAttentionDService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 渠道关注日统计表，保存每天各个渠道的医生用户关注数量。 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-10
 */
@Service
@Slf4j
public class ChnlCustAttentionDServiceImpl extends ServiceImpl<ChnlCustAttentionDMapper, ChnlCustAttentionD> implements ChnlCustAttentionDService {

    @Resource
    WeixinFansRecordMapper weixinFansRecordMapper;

    @Override
    public boolean refreshChnlCustAttentionDByTime(Date start, Date end) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        boolean flag = cleanChnlCustAttentionDByTime(Integer.valueOf(sd.format(start)),Integer.valueOf(sd.format(end)));
        if(flag){
            flag = analysisChnlCustAttentionDByTime(start.getTime(),end.getTime());
        }
        return flag;
    }

    /**
     * 清空之前统计的数据
     * @param start 起始时刻
     * @param end   终止时刻
     * @return
     */
    private boolean cleanChnlCustAttentionDByTime(long start,long end){
        boolean flag = false;
        log.info("cleanChnlCustAttentionDByTime--------------");
        QueryWrapper<ChnlCustAttentionD> queryWrapperD = new QueryWrapper<ChnlCustAttentionD>();
        queryWrapperD = new QueryWrapper<ChnlCustAttentionD>().between("STAT_DT",start,end);
        try {
            this.remove(queryWrapperD);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
            flag = false;
        }
        log.info("cleanChnlCustAttentionDByTime-------flag-------:[{}]",flag);
        return flag;
    }

    /**
     * 插入统计数据
     * @param start 起始时刻
     * @param end   终止时刻
     * @return
     */
    private boolean analysisChnlCustAttentionDByTime(long start,long end){
        boolean flag = false;
        log.info("analysisChnlCustAttentionDByTime----flag---------:{}",flag);
        List<Map> analysisDataList = weixinFansRecordMapper.findWeixinRecordByCreateTime(start,end);
        int len = analysisDataList.size();
        List<ChnlCustAttentionD> buildAnalysisDataList = new ArrayList<>();
        for (int i = 0; i <len ; i++) {
            Map map = analysisDataList.get(i);
            ChnlCustAttentionD chnlCustAttentionD = new ChnlCustAttentionD();
            chnlCustAttentionD.setStatDt(Integer.valueOf((String) map.get("STAT_DT")));
            chnlCustAttentionD.setAttenTotalCnt(((Long) map.get("ATTEN_TOTAL_CNT")).intValue());
            chnlCustAttentionD.setAttenLostCnt(((Long) map.get("ATTEN_LOST_CNT")).intValue());
            chnlCustAttentionD.setAttenIncreCnt(((Long) map.get("ATTEN_INCRE_CNT")).intValue());
            chnlCustAttentionD.setAttenAddCnt(((Long) map.get("ATTEN_ADD_CNT")).intValue());
            chnlCustAttentionD.setSceneId((String) map.get("SCENE_ID"));
            buildAnalysisDataList.add(chnlCustAttentionD);
        }
        flag = this.saveBatch(buildAnalysisDataList);
        log.info("analysisChnlCustAttentionDByTime----return--flag----------:{}",flag);
        return flag;
    }
}
