package org.jeecg.modules.statistics.service.impl;

import org.jeecg.modules.statistics.entity.OrderRefundD;
import org.jeecg.modules.statistics.mapper.OrderRefundDMapper;
import org.jeecg.modules.statistics.service.OrderRefundDService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退款工单明细日统计表，保存工单退款明细数据。 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-05-31
 */
@Service
public class OrderRefundDServiceImpl extends ServiceImpl<OrderRefundDMapper, OrderRefundD> implements OrderRefundDService {

    @Resource
    private OrderRefundDMapper orderRefundDMapper;

    @Override
    public List<Map> orderRefundDAnalysis(long startTime, long endTime) {
        return orderRefundDMapper.orderRefundDAnalysis(startTime, endTime);
    }

    @Override
    public Map orderOrderRefundDAnalysis(Integer orderId) {
        return orderRefundDMapper.orderOrderRefundDAnalysis(orderId);
    }
}
