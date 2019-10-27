package org.jeecg.modules.sims.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 网易云信账号 
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SimsTokenVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表ID
     */
    private String id;


    /**
     * 版本号
     */
    private Integer version;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;


}
