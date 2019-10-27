package org.jeecg.modules.qasystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.qasystem.entity.QaQuestion;
import org.jeecg.modules.qasystem.entity.QaReadLog;
import org.jeecg.modules.qasystem.mapper.QaQuestionMapper;
import org.jeecg.modules.qasystem.mapper.QaReadLogMapper;
import org.jeecg.modules.qasystem.service.QaQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用于问答模块，记录常见问题。 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-17
 */
@Service
public class QaQuestionServiceImpl extends ServiceImpl<QaQuestionMapper, QaQuestion> implements QaQuestionService {


    @Autowired
    QaQuestionMapper qaQuestionMapper;


    @Autowired
    QaReadLogMapper qaReadLogMapper;


    @Override
    public IPage<Map> queryQuestionByParams(Page<Map> page, Map params) {
        return qaQuestionMapper.queryQuestionByParams(page,params);
    }

    @Override
    public Map queryQuestionMapByParams(Map params) {
        return qaQuestionMapper.queryQuestionMapByParams(params);
    }

    @Override
    public IPage<Map> queryQuestionByConsulterId(Page<Map> page, Map params) {
        return qaQuestionMapper.queryQuestionByConsulterId(page,params);
    }

    @Override
    @Transactional
    public Map addQaReadLog(String questionId,Integer consulterId, String bMark) {

        Map map =  new HashMap();
        boolean resultFalg = false;
        QaReadLog qaReadLogRecord = null;
        QaQuestion qaQuestionRecord = null;

        QaReadLog qaReadLog = new QaReadLog();
        qaReadLog.setQuestionId(Integer.valueOf(questionId));
        qaReadLog.setbMark(bMark);
        qaReadLog.setCreateTime(new Date());
        qaReadLog.setConsulterId(consulterId);
        resultFalg = qaReadLogMapper.insert(qaReadLog)>0?true:false;
        qaReadLogRecord = qaReadLog;

        if(resultFalg){
            QaQuestion qaQuestionQuery = new QaQuestion();
            qaQuestionQuery.setQuestionId(Integer.valueOf(questionId));
            QueryWrapper<QaQuestion> queryWrapperQuestion = new QueryWrapper<QaQuestion>(qaQuestionQuery);
            qaQuestionRecord = qaQuestionMapper.selectOne(queryWrapperQuestion);
            qaQuestionRecord.setReadCnt(qaQuestionRecord.getReadCnt()==null?1:qaQuestionRecord.getReadCnt()+ 1);
            resultFalg = qaQuestionMapper.updateById(qaQuestionRecord)>0?true:false;
        }

        map.put("qaReadLogRecord",qaReadLogRecord);
        map.put("qaQuestionRecord",qaQuestionRecord);
        map.put("resultFalg",resultFalg);
        return map;
    }


}
