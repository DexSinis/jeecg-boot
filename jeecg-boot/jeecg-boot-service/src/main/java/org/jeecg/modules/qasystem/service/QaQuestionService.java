package org.jeecg.modules.qasystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.qasystem.entity.QaQuestion;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 用于问答模块，记录常见问题。 服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-17
 */
public interface QaQuestionService extends IService<QaQuestion> {

    IPage<Map> queryQuestionByParams(Page<Map> page, Map params);

    Map queryQuestionMapByParams(Map params);

    IPage<Map> queryQuestionByConsulterId(Page<Map> page, Map params);

    Map addQaReadLog(String questionId,Integer consulterId, String bMark);
}
