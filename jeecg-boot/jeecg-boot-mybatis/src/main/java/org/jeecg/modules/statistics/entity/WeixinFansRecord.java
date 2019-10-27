package org.jeecg.modules.statistics.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("weixin_fans_record")
public class WeixinFansRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Long createTime;

    private String sceneid;

    private String location;

    /**
     * 0 取消关注 1 新关注 2 旧关注
     */
    private Integer status;

    private String openid;

    /**
     * 记录关注状态0 未关注 1 关注
     */
    private Integer type;

    /**
     * 关注：0：未关注，1：已关注:2：重复关注
     */
    private Integer flags;

    /**
     * 动作来源，0-服务号，1-开放平台
     */
    private Integer actionSrc;

    /**
     * 微信公众号ID
     */
    private String appid;

    /**
     * 回复次数
     */
    private Integer attendsCount;


}
