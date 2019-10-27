package org.jeecg.modules.sims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.util.oConvertUtils;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Map;

/**
 * @Description: 学生后台管理
 * @Author: jeecg-boot
 * @Date:   2019-08-21
 * @Version: V1.0
 */
@Data
@TableName("sims_student")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="sims_student对象", description="学生信息对象")
public class SimsStudent {
    
	/**所在学院*/
	@Excel(name = "所在学院", width = 15)
    @ApiModelProperty(value = "所在学院")
	private String collegeId;
	/**所在班级*/
	@Excel(name = "所在班级", width = 15)
    @ApiModelProperty(value = "所在班级")
	private String classId;
	/**学号*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "学号")
	private String id;
	/**对接平台账号ID(如腾讯)*/
	@Excel(name = "对接平台账号ID(如腾讯)", width = 15)
    @ApiModelProperty(value = "对接平台账号ID(如腾讯)")
	private String simsid;
	/**对接平台密码(如腾讯)*/
	@Excel(name = "对接平台密码(如腾讯)", width = 15)
    @ApiModelProperty(value = "对接平台密码(如腾讯)")
	private String simsPassword;
	/**学生姓名*/
	@Excel(name = "学生姓名", width = 15)
    @ApiModelProperty(value = "学生姓名")
	private String name;
	/**身份证号*/
	@Excel(name = "身份证号", width = 15)
    @ApiModelProperty(value = "身份证号")
	private String idCardNo;
	/**英文名*/
	@Excel(name = "英文名", width = 15)
    @ApiModelProperty(value = "英文名")
	private String engName;
	/**手机号*/
	@Excel(name = "手机号", width = 15)
    @ApiModelProperty(value = "手机号")
	private String mobilePhone;
	/**密码*/
	@Excel(name = "密码", width = 15)
    @ApiModelProperty(value = "密码")
	private String password;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	private String gender;
	/**出生日期*/
	@Excel(name = "出生日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出生日期")
	private Date birth;
	/**头像*/
	@Excel(name = "头像", width = 15)
    @ApiModelProperty(value = "头像")
	private String avatar;
	/**身高*/
	@Excel(name = "身高", width = 15)
    @ApiModelProperty(value = "身高")
	private java.math.BigDecimal height;
	/**体重*/
	@Excel(name = "体重", width = 15)
    @ApiModelProperty(value = "体重")
	private java.math.BigDecimal weight;
	/**名族*/
	@Excel(name = "名族", width = 15)
    @ApiModelProperty(value = "名族")
	private String nation;
	/**政治面貌*/
	@Excel(name = "政治面貌", width = 15)
    @ApiModelProperty(value = "政治面貌")
	private String political;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
    @ApiModelProperty(value = "婚姻状况")
	private String marital;
	/**籍贯（省） 国标行政区域代码-省级*/
	@Excel(name = "籍贯（省） 国标行政区域代码-省级", width = 15)
    @ApiModelProperty(value = "籍贯（省） 国标行政区域代码-省级")
	private String domicilePlaceProvince;
	/**籍贯（市） 国标行政区域代码-市级*/
	@Excel(name = "籍贯（市） 国标行政区域代码-市级", width = 15)
    @ApiModelProperty(value = "籍贯（市） 国标行政区域代码-市级")
	private String domicilePlaceCity;
	/**户籍地址*/
	@Excel(name = "户籍地址", width = 15)
    @ApiModelProperty(value = "户籍地址")
	private String domicilePlaceAddress;
	/**爱好*/
	@Excel(name = "爱好", width = 15)
    @ApiModelProperty(value = "爱好")
	private String hobby;
	/**简要介绍*/
	@Excel(name = "简要介绍", width = 15)
    @ApiModelProperty(value = "简要介绍")
	private String intro;
	/**居住地址*/
	@Excel(name = "居住地址", width = 15)
    @ApiModelProperty(value = "居住地址")
	private String presentAddress;
	/**电子邮件*/
	@Excel(name = "电子邮件", width = 15)
    @ApiModelProperty(value = "电子邮件")
	private String email;
	/**入学日期*/
	@Excel(name = "入学日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "入学日期")
	private Date entryDate;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
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
	/**online*/
	@Excel(name = "online", width = 15)
    @ApiModelProperty(value = "online")
	private Integer online;
	/**token*/
	@Excel(name = "token", width = 15)
    @ApiModelProperty(value = "token")
	private String token;

	public SimsStudent() {
	}

	public SimsStudent(Map map) {
		this.setMobilePhone(oConvertUtils.getString(map.get("mobilePhone"),""));
		this.setPassword(oConvertUtils.getString(map.get("password"),""));
	}
}
