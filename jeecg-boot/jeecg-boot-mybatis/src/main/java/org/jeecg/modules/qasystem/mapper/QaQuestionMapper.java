package org.jeecg.modules.qasystem.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.qasystem.entity.QaQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 用于问答模块，记录常见问题。 Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-17
 */
public interface QaQuestionMapper extends BaseMapper<QaQuestion> {

    IPage<Map> queryQuestionByParams(Page<Map> page,@Param("params")Map params);

    Map queryQuestionMapByParams(@Param("params")Map params);

    IPage<Map> queryQuestionByConsulterId(Page<Map> page,@Param("params")Map params);
}
