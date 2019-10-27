package org.jeecg.modules.statistics.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.jeecg.util.date.LocalDateTimeUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 退款工单明细日统计表，保存工单退款明细数据。
 * </p>
 *
 * @author DexSinis
 * @since 2019-05-31
 */
@TableName("TO_E_ORDER_REFUND_D")
public class OrderRefundD implements Serializable {

    private static final long serialVersionUID = 1L;


    private BigDecimal orderCharge;

    private BigDecimal orderDiscount;

    private Integer orderStatus;


    private Integer statDt;

    /**
     * 工单编号
     */
    private Integer orderId;

    /**
     * 退款时间
     */
    private Date fefundTime;

    /**
     * 工单创建时间
     */
    private Date createTime;

    /**
     * 退款结果，0：失败  1：成功
     */
    private Integer refundResult;

    private Integer consulterId;

    /**
     * 接单人标识
     */
    private Integer customerId;

    /**
     * 退款金额，单元：元。
     */
    private BigDecimal fefundFee;

    /**
     * 退款类型，0：客服退款  1：用户退款
     */
    private Integer refundType;

    private Integer aptCustomerId;

    private BigDecimal orderPrice;





    public OrderRefundD(){

    }
    public OrderRefundD(Map map) {
        this.setOrderId((Integer) map.get("afund_order_id"));
        this.setCreateTime(new Date((Long) map.get("afund_create_time")));
        this.setFefundTime((Date) map.get("afund_refund_time"));
        this.setRefundResult((Integer) map.get("afund_req_status"));
        this.setConsulterId((Integer) map.get("afund_con_id"));
        this.setCustomerId((Integer) map.get("afund_cus_id"));
        this.setStatDt((Integer) map.get("statDt"));
        this.setFefundFee((BigDecimal)map.get("afund_cus_attr_fee"));
        this.setRefundType((Integer) map.get("afund_refund_type"));
        this.setOrderCharge((BigDecimal) map.get("afund_order_charge"));
        this.setOrderDiscount((BigDecimal) map.get("afund_order_discount"));
        this.setOrderStatus((Integer) map.get("afund_status"));
        this.setAptCustomerId(map.get("point_cust_id")==null?null:Integer.valueOf(map.get("point_cust_id").toString()));
        this.setOrderPrice(map.get("fee")==null? BigDecimal.valueOf(0) : (BigDecimal) map.get("fee"));
    }


    public Integer getAptCustomerId() {
        return aptCustomerId;
    }

    public void setAptCustomerId(Integer aptCustomerId) {
        this.aptCustomerId = aptCustomerId;
    }

    public Integer getStatDt() {
        return statDt;
    }

    public void setStatDt(Integer statDt) {
        this.statDt = statDt;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getFefundTime() {
        return fefundTime;
    }

    public void setFefundTime(Date fefundTime) {
        this.fefundTime = fefundTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getRefundResult() {
        return refundResult;
    }

    public void setRefundResult(Integer refundResult) {
        this.refundResult = refundResult;
    }

    public Integer getConsulterId() {
        return consulterId;
    }

    public void setConsulterId(Integer consulterId) {
        this.consulterId = consulterId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getFefundFee() {
        return fefundFee;
    }

    public void setFefundFee(BigDecimal fefundFee) {
        this.fefundFee = fefundFee;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }


    public BigDecimal getOrderCharge() {
        return orderCharge;
    }

    public void setOrderCharge(BigDecimal orderCharge) {
        this.orderCharge = orderCharge;
    }

    public BigDecimal getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(BigDecimal orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Override
    public String toString() {
        return "OrderRefundD{" +
        "statDt=" + statDt +
        ", orderId=" + orderId +
        ", fefundTime=" + fefundTime +
        ", createTime=" + createTime +
        ", refundResult=" + refundResult +
        ", consulterId=" + consulterId +
        ", customerId=" + customerId +
        ", fefundFee=" + fefundFee +
        ", refundType=" + refundType +
        "}";
    }


}
