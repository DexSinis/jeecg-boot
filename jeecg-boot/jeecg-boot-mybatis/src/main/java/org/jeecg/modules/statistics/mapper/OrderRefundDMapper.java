package org.jeecg.modules.statistics.mapper;

import org.jeecg.modules.statistics.entity.OrderRefundD;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退款工单明细日统计表，保存工单退款明细数据。 Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-05-31
 */
public interface OrderRefundDMapper extends BaseMapper<OrderRefundD> {

    List<Map> orderRefundDAnalysis(@Param("startTime")long startTime, @Param("endTime")long endTime);

    Map orderOrderRefundDAnalysis(@Param("orderId")Integer orderId);
}
