package org.jeecg.modules.statistics.service.impl;

import org.jeecg.modules.statistics.entity.OrderEvaluationD;
import org.jeecg.modules.statistics.mapper.OrderEvaluationDMapper;
import org.jeecg.modules.statistics.service.OrderEvaluationDService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工单评价日统计表，存储工单评价明细数据。 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-05-31
 */
@Service
public class OrderEvaluationDServiceImpl extends ServiceImpl<OrderEvaluationDMapper, OrderEvaluationD> implements OrderEvaluationDService {

    @Resource
    private OrderEvaluationDMapper orderEvaluationDMapper;

    @Override
    public List<Map>  orderEvaluationDAnalysis(long startTime, long endTime) {


        return orderEvaluationDMapper.orderEvaluationDAnalysis(startTime, endTime);

    }



}
