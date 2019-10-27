package org.jeecg.modules.statistics.service;

import org.jeecg.modules.statistics.entity.OrderEvaluationD;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工单评价日统计表，存储工单评价明细数据。 服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-05-31
 */
public interface OrderEvaluationDService extends IService<OrderEvaluationD> {

    List<Map> orderEvaluationDAnalysis(long startTime, long endTime);

}
