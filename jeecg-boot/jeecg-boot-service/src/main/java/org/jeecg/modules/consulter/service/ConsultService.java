package org.jeecg.modules.consulter.service;

import org.jeecg.util.response.ServiceResult;

/**
 * Created by yujianqiang on 2019/3/25.
 */
public interface ConsultService {

    //获取工单状态
    ServiceResult getOrderStatus(Integer orderId);
}
