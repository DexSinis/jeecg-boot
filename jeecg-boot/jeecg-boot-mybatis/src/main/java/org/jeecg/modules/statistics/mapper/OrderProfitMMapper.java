package org.jeecg.modules.statistics.mapper;

import org.jeecg.modules.statistics.entity.OrderProfitM;
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
public interface OrderProfitMMapper extends BaseMapper<OrderProfitM> {

    List<Map> orderProfitMAnalysis(@Param("statDt")Integer statDt, @Param("endDt")Integer endDt);

    Map orderOrderProfitMAnalysis(@Param("statDt")Integer statDt,@Param("endDt")Integer endDt, @Param("bMark")String bMark, @Param("orderType")Integer orderType);
}
