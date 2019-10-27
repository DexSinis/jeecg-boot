package org.jeecg.modules.statistics.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 工单明细日统计表，保存每天产生的工单明细数据。
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-03
 */
@TableName("to_e_order_dtl_d")
public class OrderDtlD implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 工单编号
     */
    private Integer orderId;

    /**
     * 统计日期，即工单创建日期。
     */
    private Integer statDt;

    /**
     * 工单创建时间
     */
    private Long createTime;

    /**
     * 接单时间
     */
    private Long receivedTime;

    /**
     * 回答时间
     */
    private Long replyTime;

    /**
     * 工单价格，单位：元。
     */
    private BigDecimal orderPrice;

    /**
     * 工单生成类型
     */
    private Integer orderGenType;

    /**
     * 提问渠道：0：安卓APP  1：IOS APP  2：公众号
     */
    private Integer chnlId;

    /**
     * 咨询问题主题编码\n0：儿科问题 1：妇科问题 2：营养咨询  3：心理咨询
     */
    private Integer orderType;

    /**
     * 工单状态：0：待接单，1：服务中，2：转接中，3：已完成，4：用户退款，5：客服退款
     */
    private Integer orderStatus;

    /**
     * 优惠券面值
     */
    private BigDecimal couponValue;

    /**
     * 实际收费金额，单位：元。
     */
    private BigDecimal orderCharge;

    /**
     * 商户标识
     */
    private String bMark;

    /**
     * 是否退单 ，0：否  1：是
     */
//    private String isWithdraw;

    private String refundKind;


    /**
     * 用户标识
     */
    private Integer consulterId;

    /**
     * 接单者ID
     */
    private Integer revCustomerId;

    /**
     * 指定咨询者ID，即指定问的谁。
     */
    private Integer aptCustomerId;

    private String refundFlag;

    private Integer evalId;

    private Integer cancelType;

    private Integer questionTopicCode;

    private BigDecimal orderProfit; //ORDER_PROFIT

    /**
     * 是否VIP咨询
     */
    private Integer isVipAsk;
    /**
     * 是否使用VIP权益
     */
    private Integer isUsedVipBenifit;


    public OrderDtlD() {
    }
    public OrderDtlD(Map map) {
        this.setQuestionTopicCode(map.get("question_topic_code")==null?null:Integer.valueOf(map.get("question_topic_code").toString()));
        this.setOrderId((Integer)map.get("id"));
        this.setStatDt((Integer) map.get("statDt"));
        this.setChnlId(Integer.valueOf(map.get("chnl_type").toString()+""));
        this.setAptCustomerId(map.get("point_cust_id")==null?null:Integer.valueOf(map.get("point_cust_id").toString()));
        this.setbMark((String) map.get("b_mark"));
        this.setConsulterId(Integer.valueOf(map.get("consulter_id").toString()));
        this.setCouponValue(map.get("DISCOUNT_CHARGE")==null? BigDecimal.valueOf(0) : (BigDecimal) map.get("DISCOUNT_CHARGE"));
        this.setOrderCharge(map.get("realfee")==null? BigDecimal.valueOf(0) : (BigDecimal) map.get("realfee"));
        this.setOrderProfit(map.get("ORDER_PROFIT")==null? BigDecimal.valueOf(0) : (BigDecimal) map.get("ORDER_PROFIT"));
        this.setOrderPrice(map.get("fee")==null? BigDecimal.valueOf(0) : (BigDecimal) map.get("fee"));
        this.setCreateTime((Long) map.get("create_time"));
        this.setReplyTime((Long)map.get("ack_time"));
        this.setRefundKind(map.get("refund_type")==null?null:map.get("refund_type")+"");
        this.setOrderGenType(Integer.valueOf(map.get("order_gen_type").toString()+""));
        this.setOrderType(map.get("order_type")==null?0:Integer.valueOf(map.get("order_type").toString()+""));
        this.setOrderStatus( Integer.valueOf(map.get("order_status")+""));
        this.setReceivedTime((Long) map.get("ack_time"));
        this.setRevCustomerId(map.get("accept_cust_id")==null?null:Integer.valueOf(map.get("accept_cust_id").toString()));
        this.setRefundFlag((String) map.get("rel_channel_type"));
        this.setEvalId(map.get("evaluation_id")==null?null:(Integer)map.get("evaluation_id"));
        this.setCancelType(map.get("cr_type")==null?null:Integer.valueOf(map.get("cr_type").toString()));
        this.setIsVipAsk(map.get("is_vip_ask")==null?0:Integer.valueOf(map.get("is_vip_ask").toString()));
        this.setIsUsedVipBenifit(map.get("is_used_vip_benifit")==null?0:Integer.valueOf(map.get("is_used_vip_benifit").toString()));
    }

    public Integer getIsVipAsk() {
        return isVipAsk;
    }

    public void setIsVipAsk(Integer isVipAsk) {
        this.isVipAsk = isVipAsk;
    }

    public Integer getIsUsedVipBenifit() {
        return isUsedVipBenifit;
    }

    public void setIsUsedVipBenifit(Integer isUsedVipBenifit) {
        this.isUsedVipBenifit = isUsedVipBenifit;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStatDt() {
        return statDt;
    }

    public void setStatDt(Integer statDt) {
        this.statDt = statDt;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Long receivedTime) {
        this.receivedTime = receivedTime;
    }

    public Long getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Long replyTime) {
        this.replyTime = replyTime;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderGenType() {
        return orderGenType;
    }

    public void setOrderGenType(Integer orderGenType) {
        this.orderGenType = orderGenType;
    }

    public Integer getChnlId() {
        return chnlId;
    }

    public void setChnlId(Integer chnlId) {
        this.chnlId = chnlId;
    }


    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(BigDecimal couponValue) {
        this.couponValue = couponValue;
    }

    public BigDecimal getOrderCharge() {
        return orderCharge;
    }

    public void setOrderCharge(BigDecimal orderCharge) {
        this.orderCharge = orderCharge;
    }

    public String getbMark() {
        return bMark;
    }

    public void setbMark(String bMark) {
        this.bMark = bMark;
    }


    public String getRefundKind() {
        return refundKind;
    }

    public void setRefundKind(String refundKind) {
        this.refundKind = refundKind;
    }

    public Integer getConsulterId() {
        return consulterId;
    }

    public void setConsulterId(Integer consulterId) {
        this.consulterId = consulterId;
    }

    public Integer getRevCustomerId() {
        return revCustomerId;
    }

    public void setRevCustomerId(Integer revCustomerId) {
        this.revCustomerId = revCustomerId;
    }

    public Integer getAptCustomerId() {
        return aptCustomerId;
    }

    public void setAptCustomerId(Integer aptCustomerId) {
        this.aptCustomerId = aptCustomerId;
    }

    public String getRefundFlag() {
        return refundFlag;
    }

    public void setRefundFlag(String refundFlag) {
        this.refundFlag = refundFlag;
    }

    public Integer getEvalId() {
        return evalId;
    }

    public void setEvalId(Integer evalId) {
        this.evalId = evalId;
    }

    public Integer getCancelType() {
        return cancelType;
    }

    public void setCancelType(Integer cancelType) {
        this.cancelType = cancelType;
    }


    public Integer getQuestionTopicCode() {
        return questionTopicCode;
    }

    public void setQuestionTopicCode(Integer questionTopicCode) {
        this.questionTopicCode = questionTopicCode;
    }

    public BigDecimal getOrderProfit() {
        return orderProfit;
    }

    public void setOrderProfit(BigDecimal orderProfit) {
        this.orderProfit = orderProfit;
    }

    @Override
    public String toString() {
        return "OrderDtlD{" +
        "orderId=" + orderId +
        ", statDt=" + statDt +
        ", createTime=" + createTime +
        ", receivedTime=" + receivedTime +
        ", replyTime=" + replyTime +
        ", orderPrice=" + orderPrice +
        ", orderGenType=" + orderGenType +
        ", chnlId=" + chnlId +
        ", orderType=" + orderType +
        ", orderStatus=" + orderStatus +
        ", couponValue=" + couponValue +
        ", orderCharge=" + orderCharge +
        ", bMark=" + bMark +
        ", refundKind=" + refundKind +
        ", consulterId=" + consulterId +
        ", revCustomerId=" + revCustomerId +
        ", aptCustomerId=" + aptCustomerId +
        "}";
    }
}
