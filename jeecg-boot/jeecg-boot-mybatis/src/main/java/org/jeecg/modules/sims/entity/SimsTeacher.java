package org.jeecg.modules.sims.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.system.base.entity.JeecgEntity;
import org.jeecg.common.util.oConvertUtils;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 教师 
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SimsTeacher", description="教师对象信息")
@TableName("sims_teacher")
public class SimsTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 所在学院
     */
    @ApiModelProperty(value = "所在学院")
    private String collegeId;

    /**
     * 教师编号
     */
    @ApiModelProperty(value = "教师编号")
    private String id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobilePhone;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 对接平台账号ID(如腾讯)
     */
    @ApiModelProperty(value = "对接平台账号ID(如腾讯)")
    private String simsid;

    /**
     * 对接平台密码(如腾讯urlSig)
     */
    @ApiModelProperty(value = "对接平台密码(如腾讯urlSig)")
    private String simsPassword;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String gender;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    private Date birth;

    /**
     * 介绍
     */
    @ApiModelProperty(value = "介绍")
    private String intro;

    /**
     * 是否在线
     */
    @ApiModelProperty(value = "是否在线")
    private Integer online;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 应用内标示
     */
    @ApiModelProperty(value = "应用内标示")
    private String token;


    /**创建人*/
    @Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**创建时间*/
    @Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**更新人*/
    @Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    /**更新时间*/
    @Excel(name = "更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


    public SimsTeacher() {
    }

    public SimsTeacher(Map map) {
        this.setMobilePhone(oConvertUtils.getString(map.get("mobilePhone"),""));
        this.setPassword(oConvertUtils.getString(map.get("password"),""));
    }
}
