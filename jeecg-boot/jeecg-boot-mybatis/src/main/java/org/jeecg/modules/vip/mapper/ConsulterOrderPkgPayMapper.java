package org.jeecg.modules.vip.mapper;

import org.jeecg.modules.vip.entity.ConsulterOrderPkgPay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.vip.vo.VipServiceDevelopmentInfoVo;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
public interface ConsulterOrderPkgPayMapper extends BaseMapper<ConsulterOrderPkgPay> {

    List<VipServiceDevelopmentInfoVo> queryVipServiceDevelopment(Integer pageSize);

}
