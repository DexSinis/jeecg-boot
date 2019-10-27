package org.jeecg.modules.qasystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 适用问答模块，用于记录问题阅读日志。
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-18
 */
@TableName("ts_e_qa_read_log")
public class QaReadLog extends Model<QaReadLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LOG_ID", type = IdType.AUTO)
    private Integer logId;

    /**
     * 阅读的用户ID
     */
    private Integer consulterId;

    /**
     * 问题唯一标识
     */
    private Integer questionId;

    /**
     * 商户标识
     */
    private String bMark;

    /**
     * 阅读时间
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

    public String getbMark() {
        return bMark;
    }

    public void setbMark(String bMark) {
        this.bMark = bMark;
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
        return "QaReadLog{" +
        "logId=" + logId +
        ", consulterId=" + consulterId +
        ", questionId=" + questionId +
        ", bMark=" + bMark +
        ", createTime=" + createTime +
        "}";
    }
}
