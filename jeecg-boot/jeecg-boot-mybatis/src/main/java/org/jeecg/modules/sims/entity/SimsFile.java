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
 * 文件信息
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SimsFile", description="文件信息")
@TableName("sims_file")
public class SimsFile extends JeecgEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 乐观锁
     */
    @ApiModelProperty(value = "乐观锁")
    private Integer revision;


    /**
     * 资料号
     */
    @ApiModelProperty(value = "资料号")
    private String id;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String fileName;

    /**
     * 来源
     */
    @ApiModelProperty(value = "来源")
    private String source;

    /**
     * 资源网络地址
     */
    @ApiModelProperty(value = "乐观锁")
    private String webUrl;

    /**
     * 文件标识
     */
    @ApiModelProperty(value = "文件标识")
    private String hash;

    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小")
    private String fileSize;

    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型")
    private String fileType;



    public SimsFile() {
    }

    public SimsFile(Map params) {
        this.id = UUIDUtils.getUUID();
        this.fileName = params.get("fileName").toString();
        this.source=params.get("source").toString();
        this.webUrl = params.get("webUrl").toString();
        this.fileSize = params.get("fileSize").toString();
        this.fileType = params.get("fileType").toString();
    }
}
