package org.jeecg.modules.vip.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Date: Created in 2019/10/11 15:06
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="套餐包具体权益对象", description="套餐包具体权益")
public class TpPackageResSameVo {

    @Excel(name = "serviceId", width = 11)
    @ApiModelProperty(value = "服务ID")
    private String serviceId;

    @Excel(name = "freeTimes", width = 11)
    @ApiModelProperty(value = "剩余免费次数")
    private String  freeTimes;

    /**
     * 折扣，是指实际要付款的部分。
     */
    @Excel(name = "discountRate", width = 11)
    @ApiModelProperty(value = "折扣，是指实际要付款的部分。")
    private String discountRate;

    /**
     * 折扣次数上限
     */
    @Excel(name = "discountLimitTimes", width = 11)
    @ApiModelProperty(value = "折扣次数上限")
    private String discountLimitTimes;

    /**
     * 优惠描述
     */
    @Excel(name = "discountDesc", width = 11)
    @ApiModelProperty(value = "优惠描述")
    private String  discountdesc;


    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getFreeTimes() {
        return freeTimes;
    }

    public void setFreeTimes(String freeTimes) {
        this.freeTimes = freeTimes;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getDiscountLimitTimes() {
        return discountLimitTimes;
    }

    public void setDiscountLimitTimes(String discountLimitTimes) {
        this.discountLimitTimes = discountLimitTimes;
    }

    public String getDiscountdesc() {
        return discountdesc;
    }

    public void setDiscountdesc(String discountdesc) {
        this.discountdesc = discountdesc;
    }
}
