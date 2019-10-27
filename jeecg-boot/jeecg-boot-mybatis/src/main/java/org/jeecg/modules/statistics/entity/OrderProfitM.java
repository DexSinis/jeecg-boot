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
@TableName("tm_order_profit_m")
public class OrderProfitM extends Model<OrderProfitM> {

    private static final long serialVersionUID = 1L;

    /**
     * 统计月份，即统计的数据月份，日期格式：YYYYMM.
     */
    private Integer statMo;

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

    public OrderProfitM() {
    }

    public OrderProfitM(Map map) {
        this.setStatMo(Integer.valueOf(map.get("STAT_MO").toString()));
        this.setBMark(map.get("B_MARK").toString());
        this.setOrderType(Integer.valueOf(map.get("ORDER_TYPE").toString()));
        this.setOrderCnt(Integer.valueOf(map.get("ORDER_CNT").toString()));
        this.setOrderProfit((BigDecimal) map.get("ORDER_PROFIT"));
        this.setRefundAmount((BigDecimal) map.get("REFUND_AMOUNT"));
    }


    @Override
    protected Serializable pkVal() {
        return this.statMo;
    }

}
