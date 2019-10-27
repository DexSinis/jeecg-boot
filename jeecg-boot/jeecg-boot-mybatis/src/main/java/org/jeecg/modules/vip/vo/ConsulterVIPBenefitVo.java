package org.jeecg.modules.vip.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.constants.Constants;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员权益
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="会员权益对象", description="会员权益")
public class ConsulterVIPBenefitVo implements  Comparable<ConsulterVIPBenefitVo> {
    @Excel(name = "vipId ", width = 11)
    @ApiModelProperty(value = "vipId")
    private Integer vipId;

    /**
     * 交易流水号
     */
    @Excel(name = "busiTradeNo ", width = 11)
    @ApiModelProperty(value = "交易流水号")
    private String busiTradeNo;

    /**
     * 套餐实例ID
     */
    @Excel(name = "pkgInstId", width = 11)
    @ApiModelProperty(value = "套餐实例ID")
    private String pkgInstId;

    /**
     * 套餐ID
     */
    @Excel(name = " pricingPkgId", width = 11)
    @ApiModelProperty(value = "套餐ID")
    private Integer pricingPkgId;

    /**
     * 购买时间
     */
    @Excel(name = "createTime", width = 11)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "购买时间")
    private Date createTime;

    /**
     * 套餐实例生效时间
     */
    @Excel(name = "effTime", width = 11)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "套餐实例生效时间")
    private Date effTime;

    /**
     * 套餐实例失效时间
     */
    @Excel(name = "expTime", width = 11)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "套餐实例失效时间")
    private Date expTime;

    /**
     * 服务ID
     */
    @Excel(name = "serviceId", width = 11)
    @ApiModelProperty(value = "服务ID")
    private Integer serviceId;

    /**
     * 剩余免费次数
     */
    @Excel(name = "freeTimes", width = 11)
    @ApiModelProperty(value = "剩余免费次数")
    private Integer freeTimes;

    /**
     * 折扣，是指实际要付款的部分。
     */
    @Excel(name = "discountRate", width = 11)
    @ApiModelProperty(value = "折扣，是指实际要付款的部分。")
    private BigDecimal discountRate;

    /**
     * 折扣次数上限
     */
    @Excel(name = "discountLimitTimes", width = 11)
    @ApiModelProperty(value = "折扣次数上限")
    private Integer discountLimitTimes;
    /**
     * 已使用次数
     */
    @Excel(name = "usedTimes", width = 11)
    @ApiModelProperty(value = "已使用次数")
    private Integer usedTimes;
    /**
     * 是否限制免费次数
     */
    @Excel(name = "isLimit", width = 11)
    @ApiModelProperty(value = "是否限制免费次数")
    private Integer isLimit;

    /**
     * 优惠描述
     */
    @Excel(name = "discountDesc", width = 11)
    @ApiModelProperty(value = "优惠描述")
    private String  discountDesc;

    public Integer getIsLimit() {
        return isLimit;
    }

    public void setIsLimit(Integer isLimit) {
        this.isLimit = isLimit;
    }

    public String getDiscountDesc() {
        return discountDesc;
    }

    public void setDiscountDesc(String discountDesc) {
        this.discountDesc = discountDesc;
    }

    public Integer getUsedTimes() {
        return usedTimes;
    }

    public void setUsedTimes(Integer usedTimes) {
        this.usedTimes = usedTimes;
    }

    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public String getBusiTradeNo() {
        return busiTradeNo;
    }

    public void setBusiTradeNo(String busiTradeNo) {
        this.busiTradeNo = busiTradeNo;
    }

    public String getPkgInstId() {
        return pkgInstId;
    }

    public void setPkgInstId(String pkgInstId) {
        this.pkgInstId = pkgInstId;
    }

    public Integer getPricingPkgId() {
        return pricingPkgId;
    }

    public void setPricingPkgId(Integer pricingPkgId) {
        this.pricingPkgId = pricingPkgId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEffTime() {
        return effTime;
    }

    public void setEffTime(Date effTime) {
        this.effTime = effTime;
    }

    public Date getExpTime() {
        return expTime;
    }

    public void setExpTime(Date expTime) {
        this.expTime = expTime;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getFreeTimes() {
        if(this.freeTimes==null){
            this.freeTimes=0;
        }
        return freeTimes;
    }

    public void setFreeTimes(Integer freeTimes) {
        this.freeTimes = freeTimes;
    }

    public BigDecimal getDiscountRate() {
        if(this.discountRate==null || this.discountRate.compareTo(BigDecimal.ZERO)==0) {
            this.discountRate = new BigDecimal("0.00");
        }
//        } else {
//            BigDecimal big = this.discountRate.divide(new BigDecimal(10)).setScale(1,BigDecimal.ROUND_HALF_UP);
//            String price=big.toString();
//            String point=price.substring(price.indexOf(".")+1,price.length());
//            if(point.equals("0")){
//                String newPrice=price.substring(0,price.indexOf("."));
//                big=new BigDecimal(newPrice);
//
//            }
//            this.discountRate=big;
//        }
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public Integer getDiscountLimitTimes() {
        if(this.discountLimitTimes==null){
            this.discountLimitTimes=0;
        }
        return discountLimitTimes;
    }

    public void setDiscountLimitTimes(Integer discountLimitTimes) {
        this.discountLimitTimes = discountLimitTimes;
    }


    @Override
    public int compareTo(ConsulterVIPBenefitVo o) {
        //4 ,1,2,3
        Integer serviceIdSame=o.getServiceId();
        if(this.serviceId==Constants.TRServiceType.QUICK){
            if(this.serviceId==serviceIdSame){
                return 0;
            } else if(serviceIdSame==Constants.TRServiceType.START
                    || serviceIdSame==Constants.TRServiceType.DIETITIAN
                    || serviceIdSame==Constants.TRServiceType.PSYCHOLOGIST
                    || serviceIdSame==Constants.TRServiceType.DIETITIAN_QUICK
                    || serviceIdSame==Constants.TRServiceType.PSYCHOLOGIST_QUICK){
                return -1;
            }

        } else if(this.serviceId==Constants.TRServiceType.START){
            if(this.serviceId==serviceIdSame){
                return 0;
            } else if(serviceIdSame==Constants.TRServiceType.DIETITIAN
                    || serviceIdSame==Constants.TRServiceType.PSYCHOLOGIST
                    || serviceIdSame==Constants.TRServiceType.DIETITIAN_QUICK
                    || serviceIdSame==Constants.TRServiceType.PSYCHOLOGIST_QUICK){
                return -1;
            } else if(serviceIdSame==Constants.TRServiceType.QUICK){
                return 1;
            }

        } else if(this.serviceId==Constants.TRServiceType.DIETITIAN){
            if(this.serviceId==serviceIdSame){
                return 0;
            } else if(serviceIdSame==Constants.TRServiceType.PSYCHOLOGIST
                    || serviceIdSame==Constants.TRServiceType.DIETITIAN_QUICK
                    || serviceIdSame==Constants.TRServiceType.PSYCHOLOGIST_QUICK){
                return -1;
            } else if(serviceIdSame==Constants.TRServiceType.QUICK
                    || serviceIdSame==Constants.TRServiceType.START ){
                return 1;
            }

        } else if(this.serviceId==Constants.TRServiceType.PSYCHOLOGIST){
            if(this.serviceId==serviceIdSame){
                return 0;
            } else if(serviceIdSame==Constants.TRServiceType.DIETITIAN_QUICK
                    || serviceIdSame==Constants.TRServiceType.PSYCHOLOGIST_QUICK){
                return -1;
            } else if(serviceIdSame==Constants.TRServiceType.QUICK
                    || serviceIdSame==Constants.TRServiceType.START
                    || serviceIdSame==Constants.TRServiceType.DIETITIAN){
                return 1;
            }

        } else {
            return 1;
        }

        return 0;
    }
}
