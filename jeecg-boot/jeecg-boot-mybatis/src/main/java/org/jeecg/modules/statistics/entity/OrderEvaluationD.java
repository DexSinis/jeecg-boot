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
 * 工单评价日统计表，存储工单评价明细数据。
 * </p>
 *
 * @author DexSinis
 * @since 2019-05-31
 */
@TableName("TO_E_ORDER_EVALUATION_D")
public class OrderEvaluationD implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal orderCharge;

    private BigDecimal orderDiscount;

    private Integer evalId;

    /**
     * 统计日期，即评价日期。格式：YYYYMMDD
     */
    private Integer statDt;

    private Integer orderId;

    /**
     * 工单价格，单位：元，0.00元表示免费
     */
    private BigDecimal orderPrice;

    /**
     * 用户标识
     */
    private Integer consulterId;

    /**
     * 接单者标识
     */
    private Integer customerId;

    /**
     * 评价时间
     */
    private Date evalTime;

    /**
     * 评价等级，取值含义如下
0 ：比较满意，有改进的地方
1 ：一般，需要改进3星
2 ：不满意，比较差
3 ：非常不满意，各方面都差
4 ：非常满意，都很棒
     */
    private Integer evalLevel;

    /**
     * 评价内容
     */
    private String evalContent;

    /**
     * 商户标识
     */
    private String bMark;

    /**
     * 是否设置为动态评论  0：否  1：是
     */
    private Integer isShow;


    public OrderEvaluationD() {
    }

    public OrderEvaluationD(Map map) {
        this.setbMark(map.get("aev_b_mark")==null?"-1":map.get("aev_b_mark")+"");
        this.setConsulterId((Integer) map.get("aev_con_id"));
        this.setEvalContent((String) map.get("aev_content"));
        this.setCustomerId((Integer) map.get("aev_cus_id"));
        this.setEvalLevel((Integer) map.get("aev_type"));
        this.setEvalTime(new Date((Long) map.get("aev_create_time")));
        this.setIsShow( map.get("aev_is_show")==null?0: (Integer) map.get("aev_is_show"));
        this.setStatDt((Integer) map.get("statDt"));
        this.setOrderId((Integer) map.get("aev_order_id"));
        this.setOrderPrice((BigDecimal) map.get("aev_cus_attr_fee"));
        this.setOrderCharge((BigDecimal) map.get("aev_order_charge"));
        this.setOrderDiscount((BigDecimal) map.get("aev_order_discount"));
        this.setEvalId((Integer) map.get("aev_id"));
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

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
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

    public Date getEvalTime() {
        return evalTime;
    }

    public void setEvalTime(Date evalTime) {
        this.evalTime = evalTime;
    }

    public Integer getEvalLevel() {
        return evalLevel;
    }

    public void setEvalLevel(Integer evalLevel) {
        this.evalLevel = evalLevel;
    }

    public String getEvalContent() {
        return evalContent;
    }

    public void setEvalContent(String evalContent) {
        this.evalContent = evalContent;
    }

    public String getbMark() {
        return bMark;
    }

    public void setbMark(String bMark) {
        this.bMark = bMark;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
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

    public Integer getEvalId() {
        return evalId;
    }

    public void setEvalId(Integer evalId) {
        this.evalId = evalId;
    }

    @Override
    public String toString() {
        return "OrderEvaluationD{" +
        "statDt=" + statDt +
        ", orderId=" + orderId +
        ", orderPrice=" + orderPrice +
        ", consulterId=" + consulterId +
        ", customerId=" + customerId +
        ", evalTime=" + evalTime +
        ", evalLevel=" + evalLevel +
        ", evalContent=" + evalContent +
        ", bMark=" + bMark +
        ", isShow=" + isShow +
        "}";
    }
}
