package org.jeecg.modules.sims.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecg.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 课程列表
 * @Author: jeecg-boot
 * @Date:   2019-08-21
 * @Version: V1.0
 */
@Data
@TableName("sims_lesson")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="sims_lesson对象", description="课程列表")
public class SimsLesson {

    /**课程号*/
    @TableId(type = IdType.UUID)
    @ApiModelProperty(value = "课程号")
    private java.lang.String id;
    /**课程名*/
    @Excel(name = "课程名", width = 15)
    @ApiModelProperty(value = "课程名")
    private java.lang.String name;
    /**教师ID*/
    @Excel(name = "教师ID", width = 15)
    @ApiModelProperty(value = "教师ID")
    private java.lang.String teacherId;
    /**学生ID*/
    @Excel(name = "学生ID", width = 15)
    @ApiModelProperty(value = "学生ID")
    private java.lang.String studentId;
    /**课室ID*/
    @Excel(name = "课室ID", width = 15)
    @ApiModelProperty(value = "课室ID")
    private java.lang.String roomId;
    /**课程开始时间*/
    @Excel(name = "课程开始时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "课程开始时间")
    private java.util.Date lessonStart;
    /**课程结束时间*/
    @Excel(name = "课程结束时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "课程结束时间")
    private java.util.Date lessonEnd;
    /**课程说明*/
    @Excel(name = "课程说明", width = 15)
    @ApiModelProperty(value = "课程说明")
    private java.lang.String intro;
    /**创建人*/
    @Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
    /**创建时间*/
    @Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
    /**更新人*/
    @Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
    /**更新时间*/
    @Excel(name = "更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private java.util.Date updateTime;
    /**课程状态 0 未开始 1教师进入课室 2 学生进入教室 3已完成 4 缺课 */
    @Excel(name = "课程状态 0 未开始 1教师进入课室 2 学生进入教室 3已完成 4 缺课 5 未预定超时", width = 15)
    @ApiModelProperty(value = "课程状态 0 未开始 1教师进入课室 2 学生进入教室 3已完成 4 缺课 5 未预定超时")
    @Dict(dicCode = "lesson_status")
    private java.lang.Integer lessonStatus;


    /**预约状态 0 未预定 1已经预定 */
    @Excel(name = "预定状态 0 未预定 1已经预定 ", width = 15)
    @ApiModelProperty(value = "预定状态 0 未预定 1已经预定 ")
    @Dict(dicCode = "reserve")
    private java.lang.Integer reserve;
}
