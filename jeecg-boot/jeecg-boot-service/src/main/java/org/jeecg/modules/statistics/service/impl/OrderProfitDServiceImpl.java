package org.jeecg.modules.statistics.service.impl;

import org.jeecg.modules.statistics.entity.OrderProfitD;
import org.jeecg.modules.statistics.mapper.OrderProfitDMapper;
import org.jeecg.modules.statistics.service.IOrderProfitDService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工单量及收益日统计表 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-19
 */
@Service
public class OrderProfitDServiceImpl extends ServiceImpl<OrderProfitDMapper, OrderProfitD> implements IOrderProfitDService {


    @Resource
    private OrderProfitDMapper orderProfitDMapper;

    @Override
    public List<Map> orderProfitDAnalysis(int statDt) {
        return orderProfitDMapper.orderProfitDAnalysis(statDt);
    }

    @Override
    public Map orderOrderProfitDAnalysis(Integer statDtRemove, String bMark, Integer orderType) {
        return orderProfitDMapper.orderOrderProfitDAnalysis( statDtRemove,  bMark,  orderType);
    }


}
