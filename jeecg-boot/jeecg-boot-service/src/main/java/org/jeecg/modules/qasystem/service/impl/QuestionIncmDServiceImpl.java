package org.jeecg.modules.qasystem.service.impl;

import org.jeecg.modules.qasystem.entity.QuestionIncmD;
import org.jeecg.modules.qasystem.mapper.QuestionIncmDMapper;
import org.jeecg.modules.qasystem.service.QuestionIncmDService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.util.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 问答访问量及销售额日统计表，适用问答模块，保存每个问题的访问量以及销售额信息。 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-19
 */
@Service
@Slf4j
public class QuestionIncmDServiceImpl extends ServiceImpl<QuestionIncmDMapper, QuestionIncmD> implements QuestionIncmDService {


    @Autowired
    QuestionIncmDMapper questionIncmDMapper;

    @Override
    public boolean qaReadLogAnalysis(long startTime, long endTime) {
        Integer statDt  = Integer.valueOf(DateUtil.formatToStr(new Date(startTime),"yyyyMMdd"));
        List<Map> recordsPV = questionIncmDMapper.qaReadLogAnalysisPv(startTime,endTime);
        List<Map> recordsUV = questionIncmDMapper.qaReadLogAnalysisUv(startTime,endTime);
        try{
            for(Map recordpv:recordsPV){
                QuestionIncmD questionIncmD = new QuestionIncmD();
                questionIncmD.setStatDt(statDt);
                questionIncmD.setQuestionId(Integer.valueOf(recordpv.get("question_id").toString()));
                questionIncmD.setIncome(BigDecimal.ZERO);
                questionIncmD.setPv(Integer.valueOf(recordpv.get("qa_pv").toString()));
                for(Map recorduv:recordsUV){
                    if(recorduv.get("question_id").equals(recordpv.get("question_id"))){
                        questionIncmD.setUv(Integer.valueOf(recorduv.get("qa_uv").toString()));
                        break;
                    }
                }
                questionIncmDMapper.insert(questionIncmD);
            }

        }catch (Exception e){
             e.printStackTrace();
        }
        return true;

    }
}
