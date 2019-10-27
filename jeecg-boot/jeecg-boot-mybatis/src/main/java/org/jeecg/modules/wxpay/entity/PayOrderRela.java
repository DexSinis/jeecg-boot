package org.jeecg.modules.wxpay.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author DexSinis
 * @since 2019-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PayOrderRela extends Model<PayOrderRela> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 内部支付订单号
     */
    private String busiTradeNo;

    private Integer orderId;

    private Integer payUser;

    private Integer consulterId;

    private Integer customerId;

    private BigDecimal fee;

    private Date createTime;

    /**
     * 订单状态
     */
    private Integer isClose;

    /**
     * 是否已经提问 0 - 未提问，1 - 已提问
     */
    private Integer isAsked;

    private String channelType;

    private Date refundTime;

    /**
     * 请求状态
0-成功
1-失败
     */
    private Integer reqStatus;

    /**
     * 0 客服退款 1用户退款
     */
    private Integer refundType;

    private String wxAppid;

    private String wxSecret;

    private Integer resourceInstId;

    private BigDecimal discountCharge;

    private BigDecimal cusAttrFee;

    /**
     * 非明星医生id，指定非明星医生咨询时记录
     */
    private Integer commonCustomerId;


    public PayOrderRela(Map map) {
        this.setBusiTradeNo( map.get("busiTradeNo").toString());
        this.setConsulterId(Integer.valueOf( map.get("consulterId").toString()));
        this.setCustomerId(Integer.valueOf( map.get("customerId").toString()));
        this.setFee(new BigDecimal(map.get("fee").toString()));
        this.setIsClose(Integer.valueOf( map.get("isClose").toString()));
        this.setIsAsked(Integer.valueOf( map.get("isAsked").toString()));
        this.setCreateTime(new Date());
    }

    public PayOrderRela() {
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
