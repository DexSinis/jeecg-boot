package org.jeecg.modules.vip.service;

import org.jeecg.modules.vip.entity.ConsulterOrderPkgPay;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.vip.vo.VipServiceDevelopmentInfoVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
public interface ConsulterOrderPkgPayService extends IService<ConsulterOrderPkgPay> {

    /**
     * 查询套餐动态数据
     * @param pageSize
     * @return
     */
    List<VipServiceDevelopmentInfoVo> queryVipServiceDevelopment(String pageSize);

}
