package org.jeecg.modules.baby.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 可选值参考表
 * </p>
 *
 * @author snoop
 * @since 2019-07-08
 */
public class TrRefOptional extends Model<TrRefOptional> {

    private static final long serialVersionUID = 1L;

    /**
     * 可选值参考ID
     */
    private Integer refOptionalId;

    /**
     * 值ID
     */
    private Integer refId;

    /**
     * 取值
     */
    private String refValue;

    /**
     * 排序号，取值越大排序越靠前。
     */
    private Integer sortNo;


    public Integer getRefOptionalId() {
        return refOptionalId;
    }

    public void setRefOptionalId(Integer refOptionalId) {
        this.refOptionalId = refOptionalId;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public String getRefValue() {
        return refValue;
    }

    public void setRefValue(String refValue) {
        this.refValue = refValue;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    @Override
    protected Serializable pkVal() {
        return this.refOptionalId;
    }

    @Override
    public String toString() {
        return "TrRefOptional{" +
        "refOptionalId=" + refOptionalId +
        ", refId=" + refId +
        ", refValue=" + refValue +
        ", sortNo=" + sortNo +
        "}";
    }
}
