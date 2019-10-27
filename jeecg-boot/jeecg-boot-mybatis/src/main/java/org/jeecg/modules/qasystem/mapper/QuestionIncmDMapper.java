package org.jeecg.modules.qasystem.mapper;

import org.jeecg.modules.qasystem.entity.QuestionIncmD;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 问答访问量及销售额日统计表，适用问答模块，保存每个问题的访问量以及销售额信息。 Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-19
 */
public interface QuestionIncmDMapper extends BaseMapper<QuestionIncmD> {

    List<Map> qaReadLogAnalysisPv(@Param("startTime")long startTime, @Param("endTime")long endTime);

    List<Map> qaReadLogAnalysisUv(@Param("startTime")long startTime, @Param("endTime")long endTime);
}
