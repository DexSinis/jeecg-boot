package org.jeecg.modules.statistics.mapper;

import org.jeecg.modules.statistics.entity.OrderProfitD;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工单量及收益日统计表 Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-19
 */
public interface OrderProfitDMapper extends BaseMapper<OrderProfitD> {

    List<Map> orderProfitDAnalysis(@Param("statDt")int statDt);

    Map orderOrderProfitDAnalysis(@Param("statDtRemove")Integer statDtRemove, @Param("bMark")String bMark, @Param("orderType")Integer orderType);
}
