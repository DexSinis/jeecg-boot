package org.jeecg.modules.vip.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 记录实例化的套餐资源，即套餐包含的权益。
 * </p>
 *
 * @author DexSinis
 * @since 2019-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConsulterPricingPkgRes extends Model<ConsulterPricingPkgRes> {

    private static final long serialVersionUID = 1L;

    /**
     * 套餐实例ID，与tp_consulter_pricing_pkg 的pkg_inst_id对应。
     */
    private String pkgInstId;

    /**
     * 服务ID
     */
    private Integer serviceId;

    /**
     * 定价包ID
     */
    private Integer pricingPkgId;

    /**
     * 剩余免费次数
     */
    private Integer freeTimes;

    /**
     * 折扣，是指实际要付款的部分。为空表示无折扣。
     */
    private BigDecimal discountRate;

    /**
     * 折扣次数上限，不含免费。
     */
    private Integer discountLimitTimes;

    /**
     * 创建时间。
     */
    private Date createTime;

    /**
     * 权益使用次数，每使用一次增加一次，退款一次减一次。
     */
    private Integer usedTimes;


    /**
     * 限制次数
     */
    private Integer isLimit;

    public ConsulterPricingPkgRes(Map params) {

        this.setPricingPkgId(Integer.valueOf(params.get("pricing_pkg_id").toString()));
        this.setServiceId(Integer.valueOf(params.get("service_id").toString()));
        this.setPkgInstId(String.valueOf(params.get("pkg_inst_id").toString()));
        this.setFreeTimes(Integer.valueOf(params.get("free_times").toString()));
        this.setDiscountRate(new BigDecimal(params.get("discount_rate").toString()));
        this.setDiscountLimitTimes(Integer.valueOf(params.get("discount_limit_times").toString()));
        this.setCreateTime((Date) params.get("create_time"));
        this.setUsedTimes(Integer.valueOf(params.get("used_times").toString()));
        this.setIsLimit(Integer.valueOf(params.get("is_limit").toString()));

    }

    public ConsulterPricingPkgRes() {
    }

    @Override
    protected Serializable pkVal() {
        return this.pkgInstId;
    }

}
