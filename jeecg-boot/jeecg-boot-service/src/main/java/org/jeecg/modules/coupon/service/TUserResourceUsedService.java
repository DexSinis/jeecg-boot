package org.jeecg.modules.coupon.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.coupon.entity.TUserResourceUsed;
import org.jeecg.modules.vip.vo.TUserResourceVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 记录用户对营销资源的领用、消费使用情况 服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
public interface TUserResourceUsedService extends IService<TUserResourceUsed> {

    /**
     * 我的优惠券查询接口
     * @param consulterId
     * @param page
     * @param type
     * @return
     */
    List<List<TUserResourceVo>> myCoupons(String consulterId,
                                       Page<TUserResourceVo> page,
                                       String type, String serviceId,String consultFee );

}
