package org.jeecg.modules.vip.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.constants.ConstantInterface;

/**
 * <p>
 * 
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConsulterOrderPkgPay extends Model<ConsulterOrderPkgPay> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pay_seq_no",type = IdType.AUTO)
    private Integer paySeqNo;

    /**
     * vip标识
     */
    private String moiblePhone;

    private Integer pricingPkgId;

    /**
     * 支付费用，单位：元
     */
    private BigDecimal payFee;

    /**
     * 交易流水号
     */
    private String busiTradeNo;

    /**
     * 支付时间
     */
    private Date createTime;

    /**
     * 支付状态  -1:未支付  0:支付失败  1:支付成功
     */
    private Integer payStauts;

    /**
     * 支付渠道编码，填写终端类型  0：公众号  1：小程序  2：IOS-APP  3：ANDROID-APP
     */
    private String payChnl;

    /**
     * 支付失败描述
     */
    private String payFailDesc;

    /**
     * 商户标识
     */
    private String bMark;

    private String payTradeNo;



    public ConsulterOrderPkgPay(Map mapConsulterOrderPkgPay) {
        this.setMoiblePhone(mapConsulterOrderPkgPay.get("moible_phone").toString());
        this.setPricingPkgId(Integer.valueOf(mapConsulterOrderPkgPay.get("pricing_pkg_id")+""));
        this.setPayFee(new BigDecimal(mapConsulterOrderPkgPay.get("pay_fee").toString()));
        this.setCreateTime((Date) mapConsulterOrderPkgPay.get("create_time"));
        this.setPayStauts(Integer.valueOf(mapConsulterOrderPkgPay.get("pay_stauts")+""));
        this.setBMark(String.valueOf(mapConsulterOrderPkgPay.get("b_mark")+""));
        this.setBusiTradeNo(String.valueOf(mapConsulterOrderPkgPay.get("busi_trade_no")+""));
        this.setPayChnl(String.valueOf(mapConsulterOrderPkgPay.get("pay_chnl")+""));
        this.setPayFailDesc(mapConsulterOrderPkgPay.get("pay_fail_desc").toString());

    }
    public ConsulterOrderPkgPay() {
    }


    @Override
    protected Serializable pkVal() {
        return this.moiblePhone;
    }

}
