package org.jeecg.modules.order.service.impl;

import org.jeecg.modules.wxpay.entity.PayOrders;
import org.jeecg.modules.wxpay.mapper.PayOrdersMapper;
import org.jeecg.modules.order.service.PayOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-10-23
 */
@Service
public class PayOrdersServiceImpl extends ServiceImpl<PayOrdersMapper, PayOrders> implements PayOrdersService {

}
