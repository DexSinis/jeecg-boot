package org.jeecg.modules.statistics.service;

import org.jeecg.modules.statistics.entity.OrderDtlD;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工单明细日统计表，保存每天产生的工单明细数据。 服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-03
 */
public interface OrderDtlDService extends IService<OrderDtlD> {

    List<Map> orderDtlDAnalysis(long startTime, long endTime);

    List<Map> bMarkOrderDtlDAnalysis(long startTime, long endTime,String bMark);

    Map orderOrderDtlDAnalysis(Integer valueOf);
}
