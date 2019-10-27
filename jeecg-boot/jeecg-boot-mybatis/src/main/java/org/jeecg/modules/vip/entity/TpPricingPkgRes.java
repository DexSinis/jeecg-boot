package org.jeecg.modules.vip.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用于保存套餐里面包含的资源，资源可以是服务者资源，用户资源，定价优惠等等。
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TpPricingPkgRes extends Model<TpPricingPkgRes> {

    private static final long serialVersionUID = 1L;

    /**
     * 定价包ID
     */
    private Integer pricingPkgId;

    /**
     * 服务ID
     */
    private Integer serviceId;

    /**
     * 免费次数
     */
    private Integer freeTimes;

    /**
     * 折扣，是指实际要付款的部分。
     */
    private BigDecimal discountRate;

    /**
     * 折扣次数上限
     */
    private Integer discountLimitTimes;

    /**
     * 创建时间。
     */
    private Date createTime;

    /**
     * 限制次数
     */
    private Integer isLimit;


    @Override
    protected Serializable pkVal() {
        return this.pricingPkgId;
    }

}
