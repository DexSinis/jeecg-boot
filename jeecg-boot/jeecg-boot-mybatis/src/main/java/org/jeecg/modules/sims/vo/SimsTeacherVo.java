package org.jeecg.modules.sims.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.modules.sims.entity.SimsLesson;
import org.jeecg.modules.sims.entity.SimsOpern;
import org.jeecg.modules.sims.entity.SimsRescource;
import org.jeecg.modules.sims.entity.SimsTeacher;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SimsTeacherVo", description="教师对象信息视图")
public class SimsTeacherVo extends SimsTeacher {

    /**
     * 资料列表
     */
    @ApiModelProperty(value = "课程列表")
    private List<SimsLesson> simsLessonList;
}
