package org.jeecg.modules.statistics.service;

import org.jeecg.modules.statistics.entity.OrderProfitD;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工单量及收益日统计表 服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-19
 */
public interface IOrderProfitDService extends IService<OrderProfitD> {

    List<Map> orderProfitDAnalysis(int statDt);

    Map orderOrderProfitDAnalysis(Integer statDtRemove, String bMark, Integer orderType);

}
