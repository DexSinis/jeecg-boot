package org.jeecg.modules.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * H5支付中间表
 * </p>
 *
 * @author DexSinis
 * @since 2019-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PayTempOrder extends Model<PayTempOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer consulterId;

    private String label;

    private String problem;

    private Integer consulteType;

    private Integer isdefault;

    private String preorder;

    private Integer labelid;

    private String babyid;

    private String flag;

    private Integer status;

    private Date createTime;

    private String payType;

    private String customerId;

    private String busiTradeNo;

    private Date updateTime;

    private Integer resourceInstId;

    private BigDecimal discountCharge;

    private String clickPrama;

    private Integer orderId;

    private String allSrc;

    private Integer isPayed;

    private BigDecimal fee;

    private String goodId;

    private BigDecimal cusAttrFee;

    /**
     * 非明星医生id，指定非明星医生咨询时才记录本字段
     */
    private Integer commonCustomerId;

    private String mobileInfo;

    private String bMark;

    /**
     * 工单类型编码，0：快速咨询工单 1：明星医生工单 2：营养师工单 3：心理咨询工单
     */
    private Integer orderType;

    /**
     * 咨询问题主题编码，0：儿科问题 1：妇科问题 2：营养师咨询 3：心理咨询

     */
    private Integer questionTopicCode;

    /**
     * 首次分发类型：
0：定向分发  1：非定向分发
     */
    private Integer firstDistributeType;

    /**
     * 是否VIP会员咨询  1：是 0：否
     */
    private Integer isVipAsk;

    /**
     * 是否使用会员权益，1：是  0：否
     */
    private Integer isUsedVipBenifit;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
