package org.jeecg.modules.statistics.service;

import org.jeecg.modules.statistics.entity.OrderRefundD;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退款工单明细日统计表，保存工单退款明细数据。 服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-05-31
 */
public interface OrderRefundDService extends IService<OrderRefundD> {

    List<Map> orderRefundDAnalysis(long startTime, long endTime);

    Map orderOrderRefundDAnalysis(Integer orderId);
}
