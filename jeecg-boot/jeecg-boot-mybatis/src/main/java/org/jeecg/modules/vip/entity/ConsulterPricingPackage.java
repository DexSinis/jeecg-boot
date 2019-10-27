package org.jeecg.modules.vip.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.util.UUIDUtil;

/**
 * <p>
 * 记录会员购买的套餐包
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConsulterPricingPackage extends Model<ConsulterPricingPackage> {

    private static final long serialVersionUID = 1L;

    private Integer vipId;

    /**
     * 交易流水号
     */
    private String busiTradeNo;

    /**
     * 套餐实例ID
     */
    private String pkgInstId;

    /**
     * 套餐ID
     */
    private Integer pricingPkgId;

    /**
     * 购买时间
     */
    private Date createTime;

    /**
     * 套餐实例生效时间
     */
    private Date effTime;

    /**
     * 套餐实例失效时间
     */
    private Date expTime;

    public ConsulterPricingPackage(Map params) {
        this.setVipId(Integer.valueOf(params.get("vip_id").toString()));
        this.setBusiTradeNo(params.get("busi_trade_no").toString());
        this.setPkgInstId(params.get("pkg_inst_id").toString());
        this.setPricingPkgId(Integer.valueOf(params.get("pricing_pkg_id").toString()));
        this.setCreateTime((Date) params.get("create_time"));
        this.setEffTime((Date) params.get("eff_time"));
        this.setExpTime((Date) params.get("exp_time"));
    }

    public ConsulterPricingPackage() {

    }
    @Override
    protected Serializable pkVal() {
        return this.vipId;
    }

}
