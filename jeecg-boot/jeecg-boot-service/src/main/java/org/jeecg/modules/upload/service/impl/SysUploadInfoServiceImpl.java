package org.jeecg.modules.upload.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.sims.entity.SimsFile;
import org.jeecg.modules.upload.entity.SysUploadInfo;
import org.jeecg.modules.upload.mapper.SysUploadInfoMapper;
import org.jeecg.modules.upload.service.SysUploadInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 文件上传配置,1,switch-qiniuTestAccess-YES-true-qiniu_test_access,switch-ossTestAccess-YES-true-oss_test_access 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@Service
public class SysUploadInfoServiceImpl extends ServiceImpl<SysUploadInfoMapper, SysUploadInfo> implements SysUploadInfoService {

    @Resource
    private SysUploadInfoMapper sysUploadInfoMapper;


    public SysUploadInfo getOneInfo() {
        QueryWrapper<SysUploadInfo> wrapper = new QueryWrapper<SysUploadInfo>();
        return sysUploadInfoMapper.selectOne(wrapper);
    }
}
