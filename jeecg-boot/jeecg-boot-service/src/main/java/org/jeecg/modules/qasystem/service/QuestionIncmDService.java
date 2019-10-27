package org.jeecg.modules.qasystem.service;

import org.jeecg.modules.qasystem.entity.QuestionIncmD;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 问答访问量及销售额日统计表，适用问答模块，保存每个问题的访问量以及销售额信息。 服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-19
 */
public interface QuestionIncmDService extends IService<QuestionIncmD> {

    boolean qaReadLogAnalysis(long startTime, long endTime);
}
