package org.jeecg.modules.statistics.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tm_order_quantity_h")
public class OrderQuantityH extends Model<OrderQuantityH> {

    private static final long serialVersionUID = 1L;

    /**
     * 商户标识
     */
    private String bMark;

    /**
     * 统计日期，格式：yyyymmdd
     */
    private Integer statDt;

    /**
     * 时，格式：24小时制
     */
    private String hour;

    /**
     * 工单数量
     */
    private Integer orderCnt;

    public OrderQuantityH() {
    }
    public OrderQuantityH(Map map) {
        this.setStatDt(Integer.valueOf(map.get("STAT_DT").toString()));
        this.setHour((map.get("STAT_HOUR").toString()));
        this.setBMark(map.get("B_MARK").toString());
        this.setOrderCnt(Integer.valueOf(map.get("ORDER_CNT").toString()));
    }


    @Override
    protected Serializable pkVal() {
        return this.bMark;
    }

}
