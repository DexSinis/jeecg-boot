package org.jeecg.modules.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.statistics.entity.ChnlAttentionH;
import org.jeecg.modules.statistics.mapper.ChnlAttentionHMapper;
import org.jeecg.modules.statistics.mapper.WeixinFansRecordMapper;
import org.jeecg.modules.statistics.service.ChnlAttentionHService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * 渠道关注时统计表，存储每日偶数时点关注数据。每偶数整点增量统计。 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-11
 */
@Service
@Slf4j
public class ChnlAttentionHServiceImpl extends ServiceImpl<ChnlAttentionHMapper, ChnlAttentionH> implements ChnlAttentionHService {
    @Resource
    WeixinFansRecordMapper weixinFansRecordMapper;

    @Override
    public boolean refreshChnlAttentionHByTime(Date start, Date end) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        boolean flag = cleanChnlAttentionHByTime(Integer.valueOf(sd.format(start)),Integer.valueOf(sd.format(end)),new Date().getHours());
        if(flag){
            flag = analysisChnlAttentionHByTime(start.getTime(),end.getTime());
        }
        return flag;
    }

    /**
     * 清空之前统计的数据
     * @param start 起始时刻
     * @param end   终止时刻
     * @param hour  当前统计小时数（可不传）
     * @return
     */
    private boolean cleanChnlAttentionHByTime(long start,long end,Integer hour){
        boolean flag = false;
        log.info("cleanChnlAttentionHByTime--------------");
        QueryWrapper<ChnlAttentionH> queryWrapperD = new QueryWrapper<ChnlAttentionH>();
        if(hour != null){
            queryWrapperD = new QueryWrapper<ChnlAttentionH>().between("STAT_DT",start,end).eq("HOUR",hour);
        }else{
            queryWrapperD = new QueryWrapper<ChnlAttentionH>().between("STAT_DT",start,end);
        }

        flag = this.remove(queryWrapperD);
        log.info("cleanChnlAttentionHByTime-------flag-------:[{}]",flag);
        return flag;
    }

    /**
     * 插入统计数据
     * @param start 起始时刻
     * @param end   终止时刻
     * @return
     */
    private boolean analysisChnlAttentionHByTime(long start,long end){
        boolean flag = false;
        log.info("analysisChnlAttentionHByTime----flag---------:{}",flag);
        List<Map> analysisDataList = weixinFansRecordMapper.findWeixinRecordByCreateTime(start,end);
        int len = analysisDataList.size();
        List<ChnlAttentionH> buildAnalysisDataList = new ArrayList<>();
        for (int i = 0; i <len ; i++) {
            Map map = analysisDataList.get(i);
            ChnlAttentionH chnlAttentionH = new ChnlAttentionH();
            chnlAttentionH.setStatDt(Integer.valueOf(map.get("STAT_DT").toString()));
            chnlAttentionH.setHour(((Integer)(new Date().getHours())).toString());
            chnlAttentionH.setPointTime((start+end)/2);
            chnlAttentionH.setAttenTotalCnt(Integer.valueOf(map.get("ATTEN_TOTAL_CNT")+""));
            chnlAttentionH.setAttenLostCnt(Integer.valueOf(map.get("ATTEN_LOST_CNT")+""));
            chnlAttentionH.setAttenIncreCnt(Integer.valueOf(map.get("ATTEN_INCRE_CNT").toString()));
            chnlAttentionH.setAttenAddCnt(Integer.valueOf(map.get("ATTEN_ADD_CNT").toString()));
            chnlAttentionH.setSceneId((String) map.get("SCENE_ID"));
            buildAnalysisDataList.add(chnlAttentionH);
        }
        flag = this.saveBatch(buildAnalysisDataList);
        log.info("analysisChnlAttentionHByTime----return--flag----------:{}",flag);
        return flag;
    }
}
