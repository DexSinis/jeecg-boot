package org.jeecg.modules.baby.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-06
 */
public class Baby extends Model<Baby> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    /**
     * 1-备孕，2-怀孕，3-宝宝 4-女性
     */
    private Date childbirth;

    /**
     * 宝宝性别 0未知 1男 2女
     */
    private Integer sex;

    private Integer consulterid;

    private Date createTime;

    private Integer isdefault;

    private Integer birthstatus;

    private Integer gestationalWeek;

    private Double babyWeight;

    private Double babyHeight;

    private String babyUrl;

    /**
     * 状态  0-删除，  1-使用
     */
    private String status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getChildbirth() {
        return childbirth;
    }

    public void setChildbirth(Date childbirth) {
        this.childbirth = childbirth;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getConsulterid() {
        return consulterid;
    }

    public void setConsulterid(Integer consulterid) {
        this.consulterid = consulterid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    public Integer getBirthstatus() {
        return birthstatus;
    }

    public void setBirthstatus(Integer birthstatus) {
        this.birthstatus = birthstatus;
    }

    public Integer getGestationalWeek() {
        return gestationalWeek;
    }

    public void setGestationalWeek(Integer gestationalWeek) {
        this.gestationalWeek = gestationalWeek;
    }

    public Double getBabyWeight() {
        return babyWeight;
    }

    public void setBabyWeight(Double babyWeight) {
        this.babyWeight = babyWeight;
    }

    public Double getBabyHeight() {
        return babyHeight;
    }

    public void setBabyHeight(Double babyHeight) {
        this.babyHeight = babyHeight;
    }

    public String getBabyUrl() {
        return babyUrl;
    }

    public void setBabyUrl(String babyUrl) {
        this.babyUrl = babyUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Baby{" +
        "id=" + id +
        ", name=" + name +
        ", childbirth=" + childbirth +
        ", sex=" + sex +
        ", consulterid=" + consulterid +
        ", createTime=" + createTime +
        ", isdefault=" + isdefault +
        ", birthstatus=" + birthstatus +
        ", gestationalWeek=" + gestationalWeek +
        ", babyWeight=" + babyWeight +
        ", babyHeight=" + babyHeight +
        ", babyUrl=" + babyUrl +
        ", status=" + status +
        "}";
    }
}
