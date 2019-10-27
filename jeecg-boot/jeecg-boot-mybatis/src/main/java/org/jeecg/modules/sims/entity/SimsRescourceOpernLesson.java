package org.jeecg.modules.sims.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资料课程关系
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SimsRescourceOpernLesson", description="课程曲谱关系信息")
@TableName("sims_rescource_opern_lesson")
public class SimsRescourceOpernLesson implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 乐观锁
     */
    private Integer revision;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 房间号
     */
    private String roomId;

    /**
     * 资源号
     */
    private String opernId;

    /**
     * 学生号
     */
    private String studentId;

    /**
     * 课程资料关联号
     */
    private String id;


}
