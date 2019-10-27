package org.jeecg.modules.vip.vo;


import org.apache.commons.lang3.StringUtils;
import org.jeecg.util.string.StringUtil;


/**
 * @Date: Created in 2019/9/30 9:52
 **/
public class TUserResourceVo {

    private String uStatus;

    private String id;

    private String status;

    private String expDate;

    private String effDate;

    private String usingTime;

    private String resourceId;

    private String resourceType;

    private String resourceName;

    private String resValue;

    private String transPage;

    private String resValueType;

    private String useDesc;

    private String fullValue;

    private String resourceInstId;

    private String suitServiceType;

    private String couponsDesc;


    public String getuStatus() {
        return uStatus;
    }

    public void setuStatus(String uStatus) {
        this.uStatus = uStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpDate() {
        if (StringUtil.isEmpty(this.expDate)) {
            this.expDate = "";
        }
        return expDate;
    }

    public void setExpDate(String expDate) {

        this.expDate = expDate;
    }

    public String getEffDate() {
        if (StringUtil.isEmpty(this.effDate)) {
            this.effDate = "";
        }
        return effDate;
    }

    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    public String getUsingTime() {
        return usingTime;
    }

    public void setUsingTime(String usingTime) {
        this.usingTime = usingTime;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResValue() {
        if (StringUtil.isEmpty(this.resValue)) {
            this.resValue = "";
        }
        return resValue;
    }

    public void setResValue(String resValue) {
        this.resValue = resValue;
    }

    public String getTransPage() {
        return transPage;
    }

    public void setTransPage(String transPage) {
        this.transPage = transPage;
    }

    public String getResValueType() {
        if (StringUtil.isEmpty(this.resValueType)) {
            this.resValueType = "";
        }
        return resValueType;
    }

    public void setResValueType(String resValueType) {
        this.resValueType = resValueType;
    }

    public String getUseDesc() {
        return useDesc;
    }

    public void setUseDesc(String useDesc) {
        this.useDesc = useDesc;
        this.couponsDesc = stripHtml(useDesc);

    }

    public String getFullValue() {
        if (StringUtil.isEmpty(this.fullValue)) {
            this.fullValue = "";
        }
        return fullValue;
    }

    public void setFullValue(String fullValue) {
        this.fullValue = fullValue;
    }

    public String getResourceInstId() {
        return resourceInstId;
    }

    public void setResourceInstId(String resourceInstId) {
        this.resourceInstId = resourceInstId;
    }

    public String getSuitServiceType() {
        if (StringUtil.isEmpty(this.suitServiceType)) {
            this.suitServiceType = "";
        }
        return suitServiceType;
    }

    public void setSuitServiceType(String suitServiceType) {
        this.suitServiceType = suitServiceType;
    }

    public String getCouponsDesc() {
        return couponsDesc;
    }

    public void setCouponsDesc(String couponsDesc) {
        this.couponsDesc = couponsDesc;
    }


    /**
     * 去除富文本内容的html标签
     *
     * @param content
     * @return
     */
    public static String stripHtml(String content) {

        if (!StringUtils.isBlank(content)) {
            // <p>段落替换为换行
            content = content.replaceAll("<p .*?>", "\r\n");
            // <br><br/>替换为换行
            content = content.replaceAll("<br\\s*/?>", "\r\n");
            // 去掉其它的<>之间的东西
            content = content.replaceAll("\\<.*?>", "");
            return content;
        } else {
            return "";
        }

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TUserResourceVo vo = (TUserResourceVo) o;
        String resValueSame = vo.getResValue();
        String resValueTypeSame = vo.getResValueType();
        String fullValueSame = vo.getFullValue();
        String suitServiceTypeSame = vo.getSuitServiceType();
        String expDateSame = vo.getExpDate();
        String effDateSame = vo.getEffDate();
        String uStatusSame = vo.getuStatus();
        String transPageSame=vo.getTransPage();
            if (this.getResValue().equals(resValueSame)
                    && this.getResValueType().equals(resValueTypeSame)
                    && this.getFullValue().equals(fullValueSame)
                    && this.getSuitServiceType().equals(suitServiceTypeSame)
                    && this.getExpDate().equals(expDateSame)
                    && this.getEffDate().equals(effDateSame)
                    && this.getuStatus().equals(uStatusSame)
                    && this.getTransPage().equals(transPageSame)
                    ) {
                return true;

            }


        return false;
    }

    @Override
    public String toString() {
        return "TUserResourceVo{" +
                "uStatus='" + uStatus + '\'' +
                ", expDate='" + expDate + '\'' +
                ", effDate='" + effDate + '\'' +
                ", resValue='" + resValue + '\'' +
                ", transPage='" + transPage + '\'' +
                ", resValueType='" + resValueType + '\'' +
                ", fullValue='" + fullValue + '\'' +
                ", suitServiceType='" + suitServiceType + '\'' +
                '}';
    }

    public static void main(String[] args) {
//        String a=null;
////        String b=null;
////        if(a.equals(b)){
////            System.out.printf("1");
////        }
        TUserResourceVo vo = new TUserResourceVo();
        vo.setFullValue("1");
        TUserResourceVo vo1 = new TUserResourceVo();
        vo1.setFullValue("1");
        boolean flag = vo.equals(vo1);
        if (flag) {
            System.out.println("1");
        }

    }


}
