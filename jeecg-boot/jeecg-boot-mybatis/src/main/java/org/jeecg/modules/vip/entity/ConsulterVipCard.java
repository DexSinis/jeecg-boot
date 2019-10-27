package org.jeecg.modules.vip.entity;

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
 * 会员卡
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConsulterVipCard extends Model<ConsulterVipCard> {

    private static final long serialVersionUID = 1L;

    /**
     * 卡ID
     */
    @TableId(value = "card_id", type = IdType.AUTO)
    private Integer cardId;

    /**
     * 卡编码，用于前端展现。
     */
    private String cardCode;

    /**
     * 激活时间
     */
    private Date activatedTime;

    /**
     * 生效时间
     */
    private Date effTime;

    /**
     * 失效时间
     */
    private Date expTime;

    /**
     * 卡类型，0: 电子卡  1: 实体卡
     */
    private Integer cardType;

    /**
     * 激活渠道编码
     */
    private String activateChnl;


    @Override
    protected Serializable pkVal() {
        return this.cardId;
    }

}
