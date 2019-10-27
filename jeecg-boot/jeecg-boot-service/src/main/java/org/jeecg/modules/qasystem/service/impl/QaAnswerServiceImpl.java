package org.jeecg.modules.qasystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.constants.ConstantInterface;
import org.jeecg.modules.qasystem.entity.QaAnswer;
import org.jeecg.modules.qasystem.entity.QaUsefulLog;
import org.jeecg.modules.qasystem.mapper.QaAnswerMapper;
import org.jeecg.modules.qasystem.mapper.QaQuestionMapper;
import org.jeecg.modules.qasystem.mapper.QaUsefulLogMapper;
import org.jeecg.modules.qasystem.service.QaAnswerService;
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
 *  服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-17
 */
@Service
public class QaAnswerServiceImpl extends ServiceImpl<QaAnswerMapper, QaAnswer> implements QaAnswerService {

    @Autowired
    QaUsefulLogMapper qaUsefulLogMapper;


    @Autowired
    QaQuestionMapper qaQuestionMapper;


    @Autowired
    QaAnswerMapper qaAnswerMapper;

    @Override
    public QaUsefulLog usefulCheck(String questionId, String answererId, Integer consulterId) {

        Map params = new HashMap();
        params.put("questionId",questionId);
        Map data = qaQuestionMapper.queryQuestionMapByParams(params);


        QaUsefulLog qaUsefulLog = new QaUsefulLog();
        qaUsefulLog.setQuestionId(Integer.valueOf(data.get("QUESTION_ID")+""));
        qaUsefulLog.setConsulterId(consulterId);
        QueryWrapper<QaUsefulLog> qaUsefulLogQueryWrapper = new QueryWrapper<QaUsefulLog>(qaUsefulLog);

        //查询用户是否已经点击过
        QaUsefulLog  qaUsefulLogUpdate = qaUsefulLogMapper.selectOne(qaUsefulLogQueryWrapper);

        return  qaUsefulLogUpdate;
    }

    @Override
    @Transactional
    public Map useful(String questionId, String answererId, String usefulFlag, Integer consulterId) {
        QaUsefulLog qaUsefulLogGloble = null;
        QaAnswer qaAnswerUpdate = null;
        Map map = new HashMap();
        Boolean resultFalg = false;


        Map params = new HashMap();
        params.put("questionId",questionId);
        Map data = qaQuestionMapper.queryQuestionMapByParams(params);


        QaUsefulLog qaUsefulLog = new QaUsefulLog();
        qaUsefulLog.setQuestionId(Integer.valueOf(data.get("QUESTION_ID")+""));
        qaUsefulLog.setConsulterId(consulterId);
        QueryWrapper<QaUsefulLog> qaUsefulLogQueryWrapper = new QueryWrapper<QaUsefulLog>(qaUsefulLog);

        //查询用户是否已经点击过
        QaUsefulLog qaUsefulLogUpdate = qaUsefulLogMapper.selectOne(qaUsefulLogQueryWrapper);
        if(qaUsefulLogUpdate!=null){
            qaUsefulLogUpdate.setIsUseful(Integer.valueOf(usefulFlag));
            resultFalg = qaUsefulLogMapper.updateById(qaUsefulLogUpdate)>0?true:false;
            if(resultFalg){
                qaUsefulLogGloble = qaUsefulLogUpdate;
                QaAnswer qaAnswer = new QaAnswer();
                qaAnswer.setQuestionId(Integer.valueOf(data.get("QUESTION_ID")+""));
                qaAnswer.setAnswererId(Integer.valueOf(data.get("ANSWERER_ID")+""));
                QueryWrapper<QaAnswer> queryWrapper = new QueryWrapper<QaAnswer>(qaAnswer);
                qaAnswerUpdate = qaAnswerMapper.selectOne(queryWrapper);
                if(usefulFlag.equals(ConstantInterface.UsefulFlag.UnUseful)){
                    qaAnswerUpdate.setUsefulCnt(qaAnswerUpdate.getUsefulCnt()==null?0:qaAnswerUpdate.getUsefulCnt()-1);
                }else{
                    qaAnswerUpdate.setUsefulCnt(qaAnswerUpdate.getUsefulCnt()==null?0:qaAnswerUpdate.getUsefulCnt()+1);

                }
                resultFalg = qaAnswerMapper.updateById(qaAnswerUpdate)>0?true:false;
            }
        }else{
            QaUsefulLog qaUsefulLogAdd =  new QaUsefulLog();
            qaUsefulLogAdd.setConsulterId(consulterId);
            qaUsefulLogAdd.setCreateTime(new Date());
            qaUsefulLogAdd.setIsUseful(Integer.valueOf(usefulFlag));
            qaUsefulLogAdd.setQuestionId(Integer.valueOf(questionId));
            resultFalg = qaUsefulLogMapper.insert(qaUsefulLogAdd)>0?true:false;
            if(resultFalg){
                qaUsefulLogGloble = qaUsefulLogAdd;
                QaAnswer qaAnswer = new QaAnswer();
                qaAnswer.setQuestionId(Integer.valueOf(data.get("QUESTION_ID")+""));
                qaAnswer.setAnswererId(Integer.valueOf(data.get("ANSWERER_ID")+""));
                QueryWrapper<QaAnswer> queryWrapper = new QueryWrapper<QaAnswer>(qaAnswer);
                qaAnswerUpdate = qaAnswerMapper.selectOne(queryWrapper);
                qaAnswerUpdate.setUsefulCnt(qaAnswerUpdate.getUsefulCnt()==null?1:qaAnswerUpdate.getUsefulCnt()+ 1);
                resultFalg = qaAnswerMapper.updateById(qaAnswerUpdate)>0?true:false;
            }
        }

        map.put("qaUsefulLog",qaUsefulLogGloble);
        map.put("qaAnswer",qaAnswerUpdate);
        map.put("resultFalg",resultFalg);
        return map;
    }
}
