package org.jeecg.modules.statistics.mapper;

import org.jeecg.modules.statistics.entity.OrderEvaluationD;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工单评价日统计表，存储工单评价明细数据。 Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-05-31
 */
@Repository
public interface OrderEvaluationDMapper extends BaseMapper<OrderEvaluationD> {

    List<Map> orderEvaluationDAnalysis(@Param("startTime")long startTime,@Param("endTime")long endTime);

}
