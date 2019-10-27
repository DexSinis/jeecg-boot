package org.jeecg.modules.sims.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.system.base.entity.JeecgEntity;
import org.jeecg.modules.sims.entity.SimsLesson;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程视图
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-07
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SimsLessonVo", description="课程对象信息视图")
public class SimsLessonVo extends SimsLesson {

    private static final long serialVersionUID = 1L;


    /**
     * 学生ID
     */
    @ApiModelProperty(value = "学生ID")
    private String studentId;

    /**
     * 学生头像
     */
    @ApiModelProperty(value = "学生头像")
    private String studentAvatar;

    /**
     * 学生名字
     */
    @ApiModelProperty(value = "学生名字")
    private String studentName;


    /**
     * 教师ID
     */
    @ApiModelProperty(value = "教师ID")
    private String teacherId;

    /**
     * 教师头像
     */
    @ApiModelProperty(value = "教师头像")
    private String teacherAvatar;


    /**
     * 教师名字
     */
    @ApiModelProperty(value = "教师名字")
    private String teacherName;








}
