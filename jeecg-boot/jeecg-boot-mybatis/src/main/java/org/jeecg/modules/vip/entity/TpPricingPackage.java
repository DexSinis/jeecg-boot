package org.jeecg.modules.vip.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 定价信息表
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TpPricingPackage extends Model<TpPricingPackage> {

    private static final long serialVersionUID = 1L;

    /**
     * 定价唯一标识
     */
    @TableId(value = "PRICING_PKG_ID", type = IdType.AUTO)
    private Integer pricingPkgId;

    /**
     * 定价名称
     */
    private String pricingPkgName;

    /**
     * 收费类型：1：按次收费  2：按周收费 3：按月收费  4：按年收费
     */
    private Integer chargeType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 定价状态：0：失效  1：有效 
     */
    private Integer status;

    /**
     * 修改时间
     */
    private Date changeTime;

    /**
     * 生效时间
     */
    private Date effTime;

    /**
     * 失效时间
     */
    private Date expTime;

    /**
     * 定价说明
     */
    private String pricingDesc;

    /**
     * 起价，单位：元
     */
    private BigDecimal minPrice;

    /**
     * 该套餐限售数量，超过该数量不能继续出售。
     */
    private Integer saleCntLimit;

    /**
     * 出售价，单位：元。
     */
    private BigDecimal salePrice;

    /**
     * 划线价格，单位：元。
     */
    private BigDecimal markedPrice;

    /**
     * 套餐类型， 0：次卡  1：无限卡
     */
    private Integer pkgType;

    /**
     * 生效方式，0: 立即生效   
     */
    private String effType;


    @Override
    protected Serializable pkVal() {
        return this.pricingPkgId;
    }

}
