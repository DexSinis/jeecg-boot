package org.jeecg.modules.vip.mapper;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.vip.entity.TpPricingPackage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.vip.vo.TpPricingPackageSame;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定价信息表 Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@Component
public interface TpPricingPackageMapper extends BaseMapper<TpPricingPackage> {

    List<Map<String,Object>> queryScaleCnt();


    List<Map<String,Object>> queryConsulterVipInformation(@Param("consulterId")String consulterId, @Param("bMark") String bMark);

}
