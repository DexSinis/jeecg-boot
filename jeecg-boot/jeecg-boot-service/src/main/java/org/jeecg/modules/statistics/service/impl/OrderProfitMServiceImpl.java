package org.jeecg.modules.statistics.service.impl;

import org.jeecg.modules.statistics.entity.OrderProfitM;
import org.jeecg.modules.statistics.mapper.OrderProfitMMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.statistics.service.IOrderProfitMService;
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
public class OrderProfitMServiceImpl extends ServiceImpl<OrderProfitMMapper, OrderProfitM> implements IOrderProfitMService {

    @Resource
    private OrderProfitMMapper orderProfitMMapper;

    @Override
    public List<Map> orderProfitMAnalysis(Integer statDt, Integer endDt){
        return orderProfitMMapper.orderProfitMAnalysis(statDt,endDt);
    }

    @Override
    public Map orderOrderProfitMAnalysis(Integer statDt,Integer endDt, String bMark,Integer orderType) {
        return orderProfitMMapper.orderOrderProfitMAnalysis(statDt,endDt,  bMark, orderType);
    }
}
