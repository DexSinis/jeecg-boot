package org.jeecg.modules.baby.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 用于记录症状所指对象需要提供的基本资料
 * </p>
 *
 * @author snoop
 * @since 2019-07-08
 */
public class TrTargetInfoItem extends Model<TrTargetInfoItem> {

    private static final long serialVersionUID = 1L;

    /**
     * 信息项唯一标识
     */
    @TableId(value = "ITEM_ID")
    private Integer itemId;

    /**
     * 信息项名称
     */
    private String itemName;

    /**
     * 目标对象类型， 0：妈妈  1：宝宝
     */
    private Integer targetType;

    /**
     * 信息项取值类型，0：固定值  1：枚举值
     */
    private Integer valueType;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 参考枚举值ID，该信息项的可选值通过此ID从 TR_REF_OPTIONAL 获取。
     */
    private Integer refOptionalId;

    /**
     * 是否必选  0：否  1：是
     */
    private Integer isRequired;

    /**
     * 取值对应baby表字段名
     */
    private String refColumn;


    public String getRefColumn() {
        return refColumn;
    }

    public void setRefColumn(String refColumn) {
        this.refColumn = refColumn;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public Integer getValueType() {
        return valueType;
    }

    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Integer getRefOptionalId() {
        return refOptionalId;
    }

    public void setRefOptionalId(Integer refOptionalId) {
        this.refOptionalId = refOptionalId;
    }

    public Integer getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Integer isRequired) {
        this.isRequired = isRequired;
    }

    @Override
    protected Serializable pkVal() {
        return this.itemId;
    }

    @Override
    public String toString() {
        return "TrTargetInfoItem{" +
        "itemId=" + itemId +
        ", itemName=" + itemName +
        ", targetType=" + targetType +
        ", valueType=" + valueType +
        ", defaultValue=" + defaultValue +
        ", refOptionalId=" + refOptionalId +
        ", isRequired=" + isRequired +
        "}";
    }
}
