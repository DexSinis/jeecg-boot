package org.jeecg.modules.vip.mapper;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.vip.entity.ConsulterVip;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.vip.vo.ConsulterVIPBenefitVo;
import org.jeecg.modules.vip.vo.UserVipInfo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * VIP用户表 Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
public interface ConsulterVipMapper extends BaseMapper<ConsulterVip> {
    /**
     * 根据会员手机号码查询会员权益
     *
     * @param mobilePhone
     * @return List<ConsulterVIPBenefitVo>
     */
    List<ConsulterVIPBenefitVo> queryConsulterVipBenefitByPhone(String mobilePhone);

    /**
     * 根据用户ID查询会员权益
     *
     * @param consulterId
     * @return List<ConsulterVIPBenefitVo>
     */
    List<ConsulterVIPBenefitVo> queryConsulterVipBenefit(Integer consulterId);


    Map<String,Object> queryVip(@Param("consulterId") String consulterId);

    List<UserVipInfo> queryVipInfo(@Param("consulterId") Integer  consulterId);
}
