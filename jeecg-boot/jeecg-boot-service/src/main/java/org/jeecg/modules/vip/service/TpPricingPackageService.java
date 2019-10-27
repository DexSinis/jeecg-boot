package org.jeecg.modules.vip.service;

import org.jeecg.modules.vip.entity.TpPricingPackage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.vip.vo.TpPricingPackageSame;
import org.jeecg.util.response.ServiceResult;

import java.util.List;


/**
 * <p>
 * 定价信息表 服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
public interface TpPricingPackageService extends IService<TpPricingPackage> {

    /**
     * 查询套餐信息接口
     * @return
     */
    List<TpPricingPackageSame> queryScaleCnt();


    /**
     * 查询会员权益接口
     * @param consulterId
     * @param bMark
     * @return
     */
   ServiceResult queryConsulterVipInformation(String consulterId, String bMark);

}
