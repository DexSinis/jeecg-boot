package org.jeecg.modules.upload.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文件上传配置,1,switch-qiniuTestAccess-YES-true-qiniu_test_access,switch-ossTestAccess-YES-true-oss_test_access
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUploadInfo extends Model<SysUploadInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 本地window系统上传路径,input,YES,false,true,false
     */
    private String localWindowUrl;

    /**
     * 本地LINUX系统上传路径,input,YES,false,true,false
     */
    private String localLinuxUrl;

    /**
     * 七牛前缀路径,input,YES,false,true,false
     */
    private String qiniuBasePath;

    /**
     * 七牛bucket的目录名称,input,YES,false,true,false
     */
    private String qiniuBucketName;

    /**
     * 七牛文件存储目录,input,YES,false,true,false
     */
    private String qiniuDir;

    /**
     * 七牛qiniuAccess值,input,YES,false,true,false
     */
    private String qiniuAccessKey;

    /**
     * 七牛qiniuKey的值,input,YES,false,true,false
     */
    private String qiniuSecretKey;

    /**
     * 七牛上传测试,switch,YES,true,true,false
     */
    private Boolean qiniuTestAccess;

    /**
     * 阿里云前缀路径,input,YES,false,true,false
     */
    private String ossBasePath;

    /**
     * 阿里云bucket的目录名称,input,YES,false,true,false
     */
    private String ossBucketName;

    /**
     * 阿里云文件上传目录,input,YES,false,true,false
     */
    private String ossDir;

    /**
     * 阿里云ACCESS_KEY_ID值,input,YES,false,true,false
     */
    private String ossKeyId;

    /**
     * 阿里云ACCESS_KEY_SECRET,input,YES,false,true,false
     */
    private String ossKeySecret;

    /**
     * 阿里云ENDPOINT值,input,YES,false,true,false
     */
    private String ossEndpoint;

    /**
     * 阿里云上传测试,switch,YES,true,true,false
     */
    private Boolean ossTestAccess;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 修改人
     */
    private String updateBy;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
