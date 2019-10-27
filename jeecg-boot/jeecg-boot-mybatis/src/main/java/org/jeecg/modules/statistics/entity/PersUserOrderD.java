package org.jeecg.modules.statistics.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户工单日全量表，保存累计到统计当天用户工单量等信息。
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tw_pers_user_order_d")
public class PersUserOrderD implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 统计日期，时间格式：yyyymmdd
     */
    private Integer statDt;

    /**
     * 用户标识
     */
    private Integer consulterId;

    /**
     * 付费工单数
     */
    private Integer orderPayedCnt;

    /**
     * 工单总数
     */
    private Integer orderTotalCnt;


}
