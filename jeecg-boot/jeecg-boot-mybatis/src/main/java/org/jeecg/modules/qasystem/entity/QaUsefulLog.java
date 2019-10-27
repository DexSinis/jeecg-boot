package org.jeecg.modules.qasystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-24
 */
@TableName("ts_e_qa_useful_log")
public class QaUsefulLog extends Model<QaUsefulLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LOG_ID", type = IdType.AUTO)
    private Integer logId;

    /**
     * 用户ID
     */
    private Integer consulterId;

    /**
     * 问题ID
     */
    private Integer questionId;

    /**
     * 是否有用，0：否  1：是
     */
    private Integer isUseful;

    /**
     * 创建时间，时间格式：yyyy-mm-dd hh:mm:ss。
     */
    private Date createTime;


    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getConsulterId() {
        return consulterId;
    }

    public void setConsulterId(Integer consulterId) {
        this.consulterId = consulterId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getIsUseful() {
        return isUseful;
    }

    public void setIsUseful(Integer isUseful) {
        this.isUseful = isUseful;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.logId;
    }

    @Override
    public String toString() {
        return "QaUsefulLog{" +
        "logId=" + logId +
        ", consulterId=" + consulterId +
        ", questionId=" + questionId +
        ", isUseful=" + isUseful +
        ", createTime=" + createTime +
        "}";
    }
}
