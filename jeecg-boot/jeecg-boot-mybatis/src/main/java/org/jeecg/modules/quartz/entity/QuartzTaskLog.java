package org.jeecg.modules.quartz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * 定时任务执行日志表
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_quartz_task_log")
public class QuartzTaskLog extends Model<QuartzTaskLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "job_log_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 任务名称
     */
    private String jobId;

    /**
     * 执行开始时间
     */
    private Date startTime;

    /**
     * 执行结束时间
     */
    private Date endTime;

    /**
     * 是否成功 同trueOrFalse 字典
     */
    private Integer successStatus;

    /**
     * 创建管理员id
     */
    private Integer createUid;

    /**
     * 修改管理员id
     */
    private Integer updateUid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
