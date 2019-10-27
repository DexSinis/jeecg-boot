package org.jeecg.modules.baby.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户记录症状所指对象相关资料细项
 * </p>
 *
 * @author snoop
 * @since 2019-07-08
 */
public class TargetInfoItemInst extends Model<TargetInfoItemInst> {

    private static final long serialVersionUID = 1L;

    private Integer consulterId;

    /**
     * 临时工单ID，与pay_temp_order表中的ID一致。
     */
    private Integer tmpOrderId;

    /**
     * 信息项唯一标识，与TR_TARGET_INFO_ITEM对应。
     */
    private Integer itemId;

    /**
     * 信息项取值。取值为空表示尚未收集到资料。
     */
    private String itemValue;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 该信息项是否已完成 ，0：否  1：是。
     */
    private Integer isCompleted;


    public Integer getConsulterId() {
        return consulterId;
    }

    public void setConsulterId(Integer consulterId) {
        this.consulterId = consulterId;
    }

    public Integer getTmpOrderId() {
        return tmpOrderId;
    }

    public void setTmpOrderId(Integer tmpOrderId) {
        this.tmpOrderId = tmpOrderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Integer isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    protected Serializable pkVal() {
        return this.consulterId;
    }

    @Override
    public String toString() {
        return "TargetInfoItemInst{" +
        "consulterId=" + consulterId +
        ", tmpOrderId=" + tmpOrderId +
        ", itemId=" + itemId +
        ", itemValue=" + itemValue +
        ", createTime=" + createTime +
        ", isCompleted=" + isCompleted +
        "}";
    }
}
