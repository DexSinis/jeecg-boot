package org.jeecg.modules.sims.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.system.base.entity.JeecgEntity;

/**
 * <p>
 * 曲谱 
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-12
 */



@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SimsOpern", description="曲谱对象信息")
@TableName("sims_opern")
public class SimsOpern extends JeecgEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 乐观锁
     */
    @ApiModelProperty(value = "乐观锁")
    private Integer revision;

    /**
     * 曲谱号
     */
    @ApiModelProperty(value = "曲谱号")
    private String id;


    /**
     * 曲谱名字
     */
    @ApiModelProperty(value = "曲谱名")
    private String opernName;

    /**
     * 学生号
     */
    @ApiModelProperty(value = "学生号")
    private String studentId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }



    public String getOpernName() {
        return opernName;
    }

    public void setOpernName(String opernName) {
        this.opernName = opernName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
