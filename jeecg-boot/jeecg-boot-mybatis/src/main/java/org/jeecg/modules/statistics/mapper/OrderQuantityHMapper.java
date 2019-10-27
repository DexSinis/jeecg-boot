package org.jeecg.modules.statistics.mapper;

import org.jeecg.modules.statistics.entity.OrderQuantityH;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-19
 */
public interface OrderQuantityHMapper extends BaseMapper<OrderQuantityH> {

    List<Map> orderQuantityHAnalysis(Integer statDt);
}
