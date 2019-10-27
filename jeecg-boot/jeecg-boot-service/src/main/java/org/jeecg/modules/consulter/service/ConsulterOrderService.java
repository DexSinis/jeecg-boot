package org.jeecg.modules.consulter.service;

import org.jeecg.modules.consulter.entity.ConsulterOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 工单表 服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-05-14
 */
public interface ConsulterOrderService extends IService<ConsulterOrder> {

    List<ConsulterOrder> queryConsulterList();

}
