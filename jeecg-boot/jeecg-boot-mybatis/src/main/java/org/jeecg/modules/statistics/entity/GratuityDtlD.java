package org.jeecg.modules.statistics.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 心意日统计表，保存用户的送出的心意明细数据。
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("to_e_gratuity_dtl_d")
public class GratuityDtlD implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer statDt;

    /**
     * 心意标识
     */
    private Integer gratuityId;

    /**
     * 用户标识
     */
    private Integer consulterId;

    /**
     * 医生标识
     */
    private Integer customerId;

    /**
     * 商户标识
     */
    private String bMark;

    /**
     * 工单编号
     */
    private Integer orderId;

    /**
     * 心意赠送的费用，单位：元。
     */
    private BigDecimal fee;

    /**
     * 心意类型，1：一点心意  2：感恩花束 3：白衣天使  4：医德高尚 5 ：仁心仁术   6：济世良医  -1：未知心意
     */
    private Integer gratuityType;

    /**
     * 是否赠送成功，0：失败   1：成功
     */
    private Integer isGiveSuccess;

    /**
     * 赠送时间
     */
    private Date createTime;


}
