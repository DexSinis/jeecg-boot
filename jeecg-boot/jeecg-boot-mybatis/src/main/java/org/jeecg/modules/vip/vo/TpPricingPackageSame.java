package org.jeecg.modules.vip.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import org.jeecgframework.poi.excel.annotation.Excel;

import java.util.List;

/**
 * @Date: Created in 2019/9/29 15:30
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="套餐包实例对象", description="套餐实例对象")
public class TpPricingPackageSame  {

    @Excel(name = "pkgInstId", width = 11)
    @ApiModelProperty(value = "套餐实例ID")
    private String  pricingPkgId;

    /**
     * 定价名称
     */
    @Excel(name = "pricingPkgName", width = 11)
    @ApiModelProperty(value = "定价名称")
    private String pricingPkgName;

    /**
     * 收费类型：1：按次收费  2：按周收费 3：按月收费  4：按年收费
     */
    @Excel(name = "chargeType", width = 11)
    @ApiModelProperty(value = "收费类型：1：按次收费  2：按周收费 3：按月收费  4：按年收费")
    private String  chargeType;

    /**
     * 创建时间
     */
    @Excel(name = "createTime", width = 11)
    @ApiModelProperty(value = "创建时间")
    private String  createTime;

    /**
     * 定价状态：0：失效  1：有效
     */
    @Excel(name = "status", width = 11)
    @ApiModelProperty(value = "定价状态：0：失效  1：有效")
    private String  status;

    /**
     * 修改时间
     */
    @Excel(name = "changeTime", width = 11)
    @ApiModelProperty(value = " 修改时间")
    private String  changeTime;

    /**
     * 生效时间
     */
    @Excel(name = "effTime", width = 11)
    @ApiModelProperty(value = "生效时间")
    private String  effTime;

    /**
     * 失效时间
     */
    @Excel(name = " expTime", width = 11)
    @ApiModelProperty(value = "失效时间")
    private String  expTime;

    /**
     * 定价说明
     */
    @Excel(name = "pricingDesc", width = 11)
    @ApiModelProperty(value = "定价说明")
    private String  pricingDesc;

    /**
     * 定价权益说明
     */
    @Excel(name = "pricingDescS", width = 11)
    @ApiModelProperty(value = "定价说明")
    private String  pricingDescS;

    /**
     * 定价权益说明
     */
    @Excel(name = "pricingDescE", width = 11)
    @ApiModelProperty(value = "定价说明")
    private String  pricingDescE;

    /**
     * 起价，单位：元
     */
    @Excel(name = "minPrice", width = 11)
    @ApiModelProperty(value = "起价，单位：元")
    private String  minPrice;

    /**
     * 该套餐限售数量，超过该数量不能继续出售。
     */
    @Excel(name = "saleCntLimit", width = 11)
    @ApiModelProperty(value = "该套餐限售数量，超过该数量不能继续出售。")
    private String  saleCntLimit;

    /**
     * 出售价，单位：元。
     */
    @Excel(name = "salePrice", width = 11)
    @ApiModelProperty(value = "出售价，单位：元。")
    private String  salePrice;

    /**
     * 划线价格，单位：元。
     */
    @Excel(name = " markedPrice", width = 11)
    @ApiModelProperty(value = "划线价格，单位：元。")
    private String  markedPrice;

    /**
     * 套餐类型， 0：次卡  1：无限卡
     */
    @Excel(name = "pkgType", width = 11)
    @ApiModelProperty(value = "套餐类型， 0：次卡  1：无限卡")
    private String  pkgType;

    /**
     * 生效方式，0: 立即生效
     */
    @Excel(name = "effType", width = 11)
    @ApiModelProperty(value = "生效方式，0: 立即生效")
    private String effType;

    /**
     * 已购次数
     */
    @Excel(name = "buyNum", width = 11)
    @ApiModelProperty(value = "已购次数")
    private String buyNum;

    @Excel(name = "manMadeBuyNum", width = 11)
    @ApiModelProperty(value = "人造已购次数")
    private String manMadeBuyNum;

    private List<TpPackageResSameVo> list;


    public List<TpPackageResSameVo> getList() {
        return list;
    }

    public void setList(List<TpPackageResSameVo> list) {
        this.list = list;
    }

    public String getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(String buyNum) {
        this.buyNum = buyNum;
    }


    public String getManMadeBuyNum() {
        if(org.jeecg.util.string.StringUtil.isEmpty(this.manMadeBuyNum)){
            this.manMadeBuyNum="0";
        }
        return manMadeBuyNum;
    }

    public void setManMadeBuyNum(String manMadeBuyNum) {
        this.manMadeBuyNum = manMadeBuyNum;
    }

    public String getPricingPkgId() {
        return pricingPkgId;
    }

    public void setPricingPkgId(String pricingPkgId) {
        this.pricingPkgId = pricingPkgId;
    }

    public String getPricingPkgName() {
        return pricingPkgName;
    }

    public void setPricingPkgName(String pricingPkgName) {
        this.pricingPkgName = pricingPkgName;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getCreateTime() {
        int index=this.createTime.lastIndexOf(".");
        if (index != -1) {
            return this.createTime.substring(0, index);
        }
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChangeTime() {
        int index=this.changeTime.lastIndexOf(".");
        if (index != -1) {
            return this.changeTime.substring(0, index);
        }
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public String getEffTime() {
        int index=this.effTime.lastIndexOf(".");
        if (index != -1) {
            return this.effTime.substring(0, index);
        }
        return effTime;
    }

    public void setEffTime(String effTime) {
        this.effTime = effTime;
    }

    public String getExpTime() {
        int index=this.expTime.lastIndexOf(".");
        if (index != -1) {
            return this.expTime.substring(0, index);
        }
        return expTime;
    }

    public void setExpTime(String expTime) {
        this.expTime = expTime;
    }

    public String getPricingDesc() {
        return pricingDesc;
    }

    public void setPricingDesc(String pricingDesc) {
        this.pricingDesc = pricingDesc;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getSaleCntLimit() {
        return saleCntLimit;
    }

    public void setSaleCntLimit(String saleCntLimit) {
        this.saleCntLimit = saleCntLimit;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getMarkedPrice() {
        return markedPrice;
    }

    public void setMarkedPrice(String markedPrice) {
        this.markedPrice = markedPrice;
    }

    public String getPkgType() {
        return pkgType;
    }

    public void setPkgType(String pkgType) {
        this.pkgType = pkgType;
    }

    public String getEffType() {
        return effType;
    }

    public void setEffType(String effType) {
        this.effType = effType;
    }


    public String getPricingDescS() {
        return pricingDescS;
    }

    public void setPricingDescS(String pricingDescS) {
        this.pricingDescS = pricingDescS;
    }

    public String getPricingDescE() {
        return pricingDescE;
    }

    public void setPricingDescE(String pricingDescE) {
        this.pricingDescE = pricingDescE;
    }
}
