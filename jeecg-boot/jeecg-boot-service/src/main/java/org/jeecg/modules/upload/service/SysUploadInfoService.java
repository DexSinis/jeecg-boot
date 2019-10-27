package org.jeecg.modules.upload.service;

import org.jeecg.modules.upload.entity.SysUploadInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文件上传配置,1,switch-qiniuTestAccess-YES-true-qiniu_test_access,switch-ossTestAccess-YES-true-oss_test_access 服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
public interface SysUploadInfoService extends IService<SysUploadInfo> {

    SysUploadInfo getOneInfo();
}
