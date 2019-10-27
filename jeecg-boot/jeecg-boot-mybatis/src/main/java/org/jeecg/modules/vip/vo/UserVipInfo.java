package org.jeecg.modules.vip.vo;

/**
 * @Date: Created in 2019/10/10 15:02
 * 用户会员详细信息
 **/
public class UserVipInfo  {
    private String nickName;

    private String vipEffDate;

    private String vipExpDate;

    private String headUrl;

    private String babyHeadUrl;

    private String  expDate;


    private String  pricingPkgId;

    /**
     * 定价名称
     */
    private String pricingPkgName;

    /**
     * 定价说明
     */
    private String  pricingDesc;


    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }


    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }


    public String getBabyHeadUrl() {
        return babyHeadUrl;
    }

    public void setBabyHeadUrl(String babyHeadUrl) {
        this.babyHeadUrl = babyHeadUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getVipEffDate() {
        int index=this.vipEffDate.lastIndexOf(".");
        if (index != -1) {
            return this.vipEffDate.substring(0, index);
        }
        return vipEffDate;
    }

    public void setVipEffDate(String vipEffDate) {
        this.vipEffDate = vipEffDate;
    }

    public String getVipExpDate() {
        int index=this.vipExpDate.lastIndexOf(".");
        if (index != -1) {
            return this.vipExpDate.substring(0, index);
        }
        return vipExpDate;
    }

    public void setVipExpDate(String vipExpDate) {
        this.vipExpDate = vipExpDate;
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

    public String getPricingDesc() {
        return pricingDesc;
    }

    public void setPricingDesc(String pricingDesc) {
        this.pricingDesc = pricingDesc;
    }
}
