package org.jeecg.modules.consulter.service.impl;

import org.jeecg.modules.consulter.entity.ConsulterOrder;
import org.jeecg.modules.consulter.mapper.ConsulterOrderMapper;
import org.jeecg.modules.consulter.service.ConsulterOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 工单表 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-05-14
 */
@Service
public class ConsulterOrderServiceImpl extends ServiceImpl<ConsulterOrderMapper, ConsulterOrder> implements ConsulterOrderService {

    @Autowired
    private ConsulterOrderMapper consulterOrderMapper;

    @Override
    public List<ConsulterOrder> queryConsulterList() {
        List<ConsulterOrder> consulterOrders = consulterOrderMapper.queryConsulterList();
        return consulterOrders;
    }
}
