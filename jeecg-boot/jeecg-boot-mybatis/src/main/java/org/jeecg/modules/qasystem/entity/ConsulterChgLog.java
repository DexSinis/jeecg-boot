package org.jeecg.modules.qasystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息修改日志表
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-24
 */
@TableName("ts_e_consulter_chg_log")
public class ConsulterChgLog extends Model<ConsulterChgLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    /**
     * 变更时间
     */
    private Date chgTime;

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 登录用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * openid
     */
    private String openid;

    private String sceneid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String headurl;

    private String templocation;

    /**
     * 地区
     */
    private String location;

    private String address;

    /**
     * 镇
     */
    private String town;

    /**
     * 村
     */
    private String village;

    /**
     * 宝宝性别 0未知 1男 2女
     */
    private Integer childsex;

    /**
     * 宝宝姓名
     */
    private String childname;

    /**
     * 宝宝生日
     */
    private Long childbirth;

    /**
     * 电话
     */
    private String phone;

    /**
     * 生育状态:1: 备孕 2:怀孕 3:有宝宝 4:女性
     */
    private Integer birthStatus;

    /**
     * 宝宝头像
     */
    private String childhead;

    /**
     * 标签
     */
    private String tag;

    /**
     * 商户标识
     */
    private String bMark;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 上次经期
     */
    private Long lastPeriod;

    /**
     * 经期时长
     */
    private Integer remainPeriod;

    /**
     * 平均周期长度
     */
    private Integer avgPeriod;

    /**
     * 预产期
     */
    private Long edd;

    /**
     * 咨询者性别，0:宝妈，1:奶爸，3:未知
     */
    private Integer sex;

    /**
     * 注册时间
     */
    private Long createTime;

    /**
     * 是否冻结 0未冻结 1冻结
     */
    private Integer isfrozen;

    /**
     * 上次进入微信公众号会话时间
     */
    private Long lastSessionTime;

    /**
     * 是否被投诉，0，否 1，是
     */
    private Integer iscomplaint;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 应用来源
     */
    private String application;

    private Integer level;

    private Double longitude;

    private Double latitude;

    /**
     * 应用ID
     */
    private Integer appid;

    private Integer customerid;

    /**
     * 签约时间
     */
    private Date contractTime;

    /**
     * 多宝标识 0：无 1，有
     */
    private Integer ismany;

    private Integer isneed;

    private String realName;

    private Integer mc;

    private Integer mcDates;

    private Date birthdate;

    /**
     * 二维码id
     */
    private Integer mediumId;


    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Date getChgTime() {
        return chgTime;
    }

    public void setChgTime(Date chgTime) {
        this.chgTime = chgTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSceneid() {
        return sceneid;
    }

    public void setSceneid(String sceneid) {
        this.sceneid = sceneid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getTemplocation() {
        return templocation;
    }

    public void setTemplocation(String templocation) {
        this.templocation = templocation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public Integer getChildsex() {
        return childsex;
    }

    public void setChildsex(Integer childsex) {
        this.childsex = childsex;
    }

    public String getChildname() {
        return childname;
    }

    public void setChildname(String childname) {
        this.childname = childname;
    }

    public Long getChildbirth() {
        return childbirth;
    }

    public void setChildbirth(Long childbirth) {
        this.childbirth = childbirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getBirthStatus() {
        return birthStatus;
    }

    public void setBirthStatus(Integer birthStatus) {
        this.birthStatus = birthStatus;
    }

    public String getChildhead() {
        return childhead;
    }

    public void setChildhead(String childhead) {
        this.childhead = childhead;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getbMark() {
        return bMark;
    }

    public void setbMark(String bMark) {
        this.bMark = bMark;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Long getLastPeriod() {
        return lastPeriod;
    }

    public void setLastPeriod(Long lastPeriod) {
        this.lastPeriod = lastPeriod;
    }

    public Integer getRemainPeriod() {
        return remainPeriod;
    }

    public void setRemainPeriod(Integer remainPeriod) {
        this.remainPeriod = remainPeriod;
    }

    public Integer getAvgPeriod() {
        return avgPeriod;
    }

    public void setAvgPeriod(Integer avgPeriod) {
        this.avgPeriod = avgPeriod;
    }

    public Long getEdd() {
        return edd;
    }

    public void setEdd(Long edd) {
        this.edd = edd;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getIsfrozen() {
        return isfrozen;
    }

    public void setIsfrozen(Integer isfrozen) {
        this.isfrozen = isfrozen;
    }

    public Long getLastSessionTime() {
        return lastSessionTime;
    }

    public void setLastSessionTime(Long lastSessionTime) {
        this.lastSessionTime = lastSessionTime;
    }

    public Integer getIscomplaint() {
        return iscomplaint;
    }

    public void setIscomplaint(Integer iscomplaint) {
        this.iscomplaint = iscomplaint;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public Date getContractTime() {
        return contractTime;
    }

    public void setContractTime(Date contractTime) {
        this.contractTime = contractTime;
    }

    public Integer getIsmany() {
        return ismany;
    }

    public void setIsmany(Integer ismany) {
        this.ismany = ismany;
    }

    public Integer getIsneed() {
        return isneed;
    }

    public void setIsneed(Integer isneed) {
        this.isneed = isneed;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getMc() {
        return mc;
    }

    public void setMc(Integer mc) {
        this.mc = mc;
    }

    public Integer getMcDates() {
        return mcDates;
    }

    public void setMcDates(Integer mcDates) {
        this.mcDates = mcDates;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getMediumId() {
        return mediumId;
    }

    public void setMediumId(Integer mediumId) {
        this.mediumId = mediumId;
    }

    @Override
    protected Serializable pkVal() {
        return this.logId;
    }

    @Override
    public String toString() {
        return "ConsulterChgLog{" +
        "logId=" + logId +
        ", chgTime=" + chgTime +
        ", id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", openid=" + openid +
        ", sceneid=" + sceneid +
        ", nickname=" + nickname +
        ", headurl=" + headurl +
        ", templocation=" + templocation +
        ", location=" + location +
        ", address=" + address +
        ", town=" + town +
        ", village=" + village +
        ", childsex=" + childsex +
        ", childname=" + childname +
        ", childbirth=" + childbirth +
        ", phone=" + phone +
        ", birthStatus=" + birthStatus +
        ", childhead=" + childhead +
        ", tag=" + tag +
        ", bMark=" + bMark +
        ", inviteCode=" + inviteCode +
        ", lastPeriod=" + lastPeriod +
        ", remainPeriod=" + remainPeriod +
        ", avgPeriod=" + avgPeriod +
        ", edd=" + edd +
        ", sex=" + sex +
        ", createTime=" + createTime +
        ", isfrozen=" + isfrozen +
        ", lastSessionTime=" + lastSessionTime +
        ", iscomplaint=" + iscomplaint +
        ", email=" + email +
        ", os=" + os +
        ", application=" + application +
        ", level=" + level +
        ", longitude=" + longitude +
        ", latitude=" + latitude +
        ", appid=" + appid +
        ", customerid=" + customerid +
        ", contractTime=" + contractTime +
        ", ismany=" + ismany +
        ", isneed=" + isneed +
        ", realName=" + realName +
        ", mc=" + mc +
        ", mcDates=" + mcDates +
        ", birthdate=" + birthdate +
        ", mediumId=" + mediumId +
        "}";
    }
}
