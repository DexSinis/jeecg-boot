package org.jeecg.modules.vip.service;

import org.jeecg.modules.vip.entity.ConsulterVip;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.vip.vo.CheckVipVo;
import org.jeecg.modules.vip.vo.ConsulterVIPBenefitVo;
import org.jeecg.modules.vip.vo.UserVipInfo;

import java.util.List;


/**
 * <p>
 * VIP用户表 服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
public interface ConsulterVipService extends IService<ConsulterVip> {

    /**
     * 根据手机号码查询会员权益
     *
     * @param mobilePhone
     * @return
     */
    List<ConsulterVIPBenefitVo> queryConsulterVipBenefitByPhone(String mobilePhone);

    /**
     * 根据用户ID查询会员权益
     *
     * @param consulterId
     * @return
     */
    List<ConsulterVIPBenefitVo> queryConsulterVipBenefit(Integer consulterId);

    /**
     * 根据用户id 查询是否vip 用户
     * @param consulterId
     * @return
     */
    CheckVipVo queryVip(String consulterId);

    /**
     * 查询会员套餐详细信息
     * @param consulterId
     * @return
     */
    List<UserVipInfo> queryVipInfo(Integer consulterId);


    /**
     * 收到微信回调用更新会员卡信息
     * @param busiTradeNo
     * @param busiTradeNo
     * @return
     */
    void payNotify(String busiTradeNo,String payTradeNo);



    /**
     * 收到微信回调用更新咨询关系表
     * @param busiTradeNo
     * @param busiTradeNo
     * @return
     */
    void payNotifyConsult(String busiTradeNo,String payTradeNo);
}
