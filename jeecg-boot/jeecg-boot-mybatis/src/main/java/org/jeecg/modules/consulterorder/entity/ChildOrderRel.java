package org.jeecg.modules.consulterorder.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 记录宝宝和问题工单的关联关系
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-17
 */
@TableName("child_order_rel")
public class ChildOrderRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    private Integer childId;

    private Integer orderId;

    private Date createTime;

    private String babyId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBabyId() {
        return babyId;
    }

    public void setBabyId(String babyId) {
        this.babyId = babyId;
    }

    @Override
    public String toString() {
        return "ChildOrderRel{" +
        "id=" + id +
        ", childId=" + childId +
        ", orderId=" + orderId +
        ", createTime=" + createTime +
        ", babyId=" + babyId +
        "}";
    }
}
