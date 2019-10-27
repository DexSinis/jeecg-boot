package org.jeecg.modules.statistics.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 工单量及收益日统计表
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tm_order_profit_d")
public class OrderProfitD extends Model<OrderProfitD> {

    private static final long serialVersionUID = 1L;

    /**
     * 统计日期，即统计的数据日期，日期格式：YYYYMMDD.
     */
    private Integer statDt;

    private String bMark;

    /**
     * 工单类型编码\n0：快速咨询工单 1：明星医生工单 2：营养师工单 3：心理咨询工单
     */
    private Integer orderType;

    /**
     * 工单数量
     */
    private Integer orderCnt;

    /**
     * 工单收益，单位：元。
     */
    private BigDecimal orderProfit;

    /**
     * 退款金额，单位：元。
     */
    private BigDecimal refundAmount;


    public OrderProfitD() {
    }

    public OrderProfitD(Map map) {
        this.setStatDt(Integer.valueOf(map.get("STAT_DT").toString()));
        this.setBMark((String) map.get("B_MARK"));
        this.setOrderType(Integer.valueOf(map.get("ORDER_TYPE").toString()));
        this.setOrderCnt(Integer.valueOf(map.get("ORDER_CNT").toString()));
        this.setOrderProfit((BigDecimal) map.get("ORDER_PROFIT"));
        this.setRefundAmount((BigDecimal) map.get("REFUND_AMOUNT"));
    }


    @Override
    protected Serializable pkVal() {
        return this.statDt;
    }

}
