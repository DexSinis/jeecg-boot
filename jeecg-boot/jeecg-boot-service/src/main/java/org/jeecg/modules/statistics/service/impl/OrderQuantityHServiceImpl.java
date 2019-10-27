package org.jeecg.modules.statistics.service.impl;

import org.jeecg.modules.statistics.entity.OrderQuantityH;
import org.jeecg.modules.statistics.mapper.OrderQuantityHMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.statistics.service.IOrderQuantityHService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-19
 */
@Service
public class OrderQuantityHServiceImpl extends ServiceImpl<OrderQuantityHMapper, OrderQuantityH> implements IOrderQuantityHService {

    @Resource
    private OrderQuantityHMapper orderQuantityHMapper;

    @Override
    public List<Map> orderQuantityHAnalysis(Integer statDt){
        return orderQuantityHMapper.orderQuantityHAnalysis(statDt);
    }



}
