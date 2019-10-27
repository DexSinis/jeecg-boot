package org.jeecg.modules.statistics.service.impl;

import org.jeecg.modules.statistics.entity.OrderDtlD;
import org.jeecg.modules.statistics.mapper.OrderDtlDMapper;
import org.jeecg.modules.statistics.service.OrderDtlDService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工单明细日统计表，保存每天产生的工单明细数据。 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-03
 */
@Service
public class OrderDtlDServiceImpl extends ServiceImpl<OrderDtlDMapper, OrderDtlD> implements OrderDtlDService {

    @Resource
    private OrderDtlDMapper orderDtlDMapper;

    @Override
    public List<Map> orderDtlDAnalysis(long startTime, long endTime) {
        return orderDtlDMapper.orderDtlDAnalysis(startTime, endTime);
    }

    @Override
    public List<Map> bMarkOrderDtlDAnalysis(long startTime, long endTime,String bMark) {
        return orderDtlDMapper.bMarkOrderDtlDAnalysis(startTime, endTime,bMark );
    }

    @Override
    public Map orderOrderDtlDAnalysis(Integer orderId) {
        return orderDtlDMapper.orderOrderDtlDAnalysis(orderId);
    }


}
