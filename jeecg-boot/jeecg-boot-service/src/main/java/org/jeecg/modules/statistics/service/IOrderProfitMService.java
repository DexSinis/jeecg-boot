package org.jeecg.modules.statistics.service;

import org.jeecg.modules.statistics.entity.OrderProfitM;
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
public interface IOrderProfitMService extends IService<OrderProfitM> {

    List<Map> orderProfitMAnalysis(Integer statDt, Integer endDt);

    Map orderOrderProfitMAnalysis(Integer statDt,Integer endDt, String bMark,Integer orderType);
}
