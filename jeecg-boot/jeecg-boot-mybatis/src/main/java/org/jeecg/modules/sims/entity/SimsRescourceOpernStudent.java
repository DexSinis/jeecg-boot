package org.jeecg.modules.sims.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.system.base.entity.JeecgEntity;
import org.jeecg.common.util.upload.UUIDUtils;

/**
 * <p>
 * 资料学生关系
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SimsRescourceOpernStudent", description="学生曲谱关系信息")
@TableName("sims_rescource_opern_student")
public class SimsRescourceOpernStudent extends JeecgEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 乐观锁
     */
    @ApiModelProperty(value = "乐观锁")
    private Integer revision;


    /**
     * 学生号
     */
    @ApiModelProperty(value = "学生号")
    private String studentId;

    /**
     * 曲谱号
     */
    @ApiModelProperty(value = "曲谱号")
    private String opernId;

    /**
     * 学生资料关联号
     */
    @ApiModelProperty(value = "学生资料关联号")
    private String id;

    public SimsRescourceOpernStudent() {

    }
    public SimsRescourceOpernStudent(Map params) {
        this.id = UUIDUtils.getUUID();
        this.opernId=params.get("opernId").toString();
    }
}
