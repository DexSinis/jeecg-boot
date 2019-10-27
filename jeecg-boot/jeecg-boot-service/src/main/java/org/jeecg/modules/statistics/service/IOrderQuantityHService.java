package org.jeecg.modules.statistics.service;

import org.jeecg.modules.statistics.entity.OrderQuantityH;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-19
 */
public interface IOrderQuantityHService extends IService<OrderQuantityH> {

    List<Map> orderQuantityHAnalysis(Integer statDt);
}
