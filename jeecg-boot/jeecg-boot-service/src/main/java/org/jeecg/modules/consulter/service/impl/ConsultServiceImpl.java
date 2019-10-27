package org.jeecg.modules.consulter.service.impl;

import org.jeecg.modules.consulter.entity.ConsulterOrder;
import org.jeecg.modules.consulter.mapper.ConsulterOrderMapper;
import org.jeecg.modules.consulter.service.ConsultService;
import org.jeecg.util.response.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yujianqiang on 2019/3/25.
 */
@Service
public class ConsultServiceImpl implements ConsultService {

    @Autowired
    private ConsulterOrderMapper consulterOrderMapper;

    @Override
    public ServiceResult getOrderStatus(Integer orderId) {
        ConsulterOrder consulterOrder = consulterOrderMapper.getOrderStatusById(orderId);
        Map<String, Object> map = new HashMap<>();
        if (consulterOrder != null) {
            map.put("orderId", consulterOrder.getId());
            map.put("status", consulterOrder.getStatus());
            map.put("server_status", consulterOrder.getServerStatus());
        }
        return ServiceResult.buildSuccess(map);
    }
}
