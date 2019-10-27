package org.jeecg.modules.statistics.mapper;

import org.jeecg.modules.statistics.entity.OrderDtlD;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工单明细日统计表，保存每天产生的工单明细数据。 Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-03
 */
public interface OrderDtlDMapper extends BaseMapper<OrderDtlD> {

    List<Map> orderDtlDAnalysis(@Param("startTime")long startTime, @Param("endTime")long endTime);

    List<Map> bMarkOrderDtlDAnalysis(@Param("startTime")long startTime, @Param("endTime")long endTime, @Param("bMark") String bMark);

    Map orderOrderDtlDAnalysis(@Param("orderId")Integer orderId);
}
