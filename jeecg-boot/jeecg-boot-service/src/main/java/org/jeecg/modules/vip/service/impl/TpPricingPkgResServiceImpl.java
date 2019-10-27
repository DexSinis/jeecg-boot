package org.jeecg.modules.vip.service.impl;

import org.jeecg.modules.vip.entity.TpPricingPkgRes;
import org.jeecg.modules.vip.mapper.TpPricingPkgResMapper;
import org.jeecg.modules.vip.service.TpPricingPkgResService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用于保存套餐里面包含的资源，资源可以是服务者资源，用户资源，定价优惠等等。 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@Service
public class TpPricingPkgResServiceImpl extends ServiceImpl<TpPricingPkgResMapper, TpPricingPkgRes> implements TpPricingPkgResService {

}
