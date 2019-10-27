package org.jeecg.modules.vip.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.constants.ConstantInterface;

/**
 * <p>
 * VIP用户表
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConsulterVip extends Model<ConsulterVip> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员ID
     */
    @TableId(value = "vip_id", type = IdType.AUTO)
    private Integer vipId;

    /**
     * 会员客户手机号码
     */
    private String vipPhone;

    /**
     * 创建时间，时间格式：yyyy-mm-dd hh:mm:ss
     */
    private Date createTime;

    /**
     * 生效时间，时间格式：yyyy-mm-dd hh:mm:ss
     */
    private Date effTime;

    /**
     * 失效时间，时间格式：yyyy-mm-dd hh:mm:ss
     */
    private Date expTime;

    /**
     * 有效状态  1：有效   0：无效
     */
    private Integer status;

    /**
     * 注册渠道，填写商户标识。
     */
    private String regChnl;

    public ConsulterVip(Map mapConsulterVip) {
        this.setVipPhone(String.valueOf(mapConsulterVip.get("vip_phone")));
        this.setCreateTime((Date) mapConsulterVip.get("create_time"));
        this.setEffTime((Date) mapConsulterVip.get("eff_time"));
        this.setExpTime((Date) mapConsulterVip.get("exp_time"));
        this.setStatus(Integer.valueOf(mapConsulterVip.get("status")+""));
        this.setRegChnl(String.valueOf(mapConsulterVip.get("regChnl")));


    }

    public ConsulterVip() {
    }

    @Override
    protected Serializable pkVal() {
        return this.vipId;
    }

}
