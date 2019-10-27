package org.jeecg.modules.sims.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.system.base.entity.JeecgEntity;
import org.jeecg.modules.sims.entity.SimsOpern;
import org.jeecg.modules.sims.entity.SimsRescource;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
@ApiModel(value="SimsOpernVo", description="课程对象信息视图")
public class SimsOpernVo extends SimsOpern {

    private static final long serialVersionUID = 1L;

    /**
     * 资料列表
     */
    @ApiModelProperty(value = "资料列表")
    private List<SimsRescource> simsRescourceList;




}
