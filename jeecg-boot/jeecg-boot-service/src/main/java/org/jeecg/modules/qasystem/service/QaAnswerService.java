package org.jeecg.modules.qasystem.service;

import org.jeecg.modules.qasystem.entity.QaAnswer;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.qasystem.entity.QaUsefulLog;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-17
 */
public interface QaAnswerService extends IService<QaAnswer> {
    QaUsefulLog usefulCheck(String questionId, String answererId, Integer consulterId);

    Map useful(String questionId, String answererId, String usefulFlag, Integer consulterId);
}
