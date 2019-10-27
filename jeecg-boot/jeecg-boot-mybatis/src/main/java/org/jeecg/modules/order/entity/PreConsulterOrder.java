package org.jeecg.modules.order.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2019-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PreConsulterOrder extends Model<PreConsulterOrder> {

    private static final long serialVersionUID = 1L;

    /**
     *  临时表id
     */
    private Integer payTempOrderId;

    /**
     * 医生ID
     */
    private Integer customerId;

    /**
     * 用户ID
     */
    private Integer consulterId;

    /**
     * 工单ID，ID生成后回填。
     */
    private Integer orderId;

    /**
     * 是否已经填写完信息并且已经生成工单 0未生成工单
     */
    private Integer orderStatus;

    /**
     * 妈妈档案，宝宝档案
     */
    private Integer consulterType;

    /**
     * 跳转到页面的时候 创建一个档案记录(这个记录在点击确定的时候绑定到对应的consulter)
     */
    private String babyId;

    private Long createTime;

    /**
     * 是否新创建档案信息
     */
    private Integer isNew;

    private Integer isComplete;


    @Override
    protected Serializable pkVal() {
        return this.payTempOrderId;
    }

}
