package org.jeecg.modules.sims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
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
 * @since 2019-10-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TSysLog extends Model<TSysLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 接口名称
     */
    private String methodName;

    /**
     * 入参
     */
    private String inParams;

    /**
     * 出参
     */
    private String outParams;

    /**
     * 日志类型 1、操作
     */
    private String logType;

    /**
     * 操作名称
     */
    private String operationName;

    /**
     * 操作的IP
     */
    private String operationIp;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 创建者
     */
    private Integer creator;

    /**
     * 更新日期
     */
    private Date updateTime;

    /**
     * 更新者
     */
    private Integer operator;

    /**
     * 1:删除 0:不删除
     */
    private String delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
