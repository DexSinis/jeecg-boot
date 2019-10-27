package org.jeecg.modules.notice.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.notice.entity.ServiceDynamicInfo;
import org.jeecg.modules.notice.mapper.ServiceDynamicInfoMapper;
import org.jeecg.modules.notice.service.ServiceDynamicInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-05
 */
@Service
public class ServiceDynamicInfoServiceImpl extends ServiceImpl<ServiceDynamicInfoMapper, ServiceDynamicInfo> implements ServiceDynamicInfoService {

    @Autowired
    ServiceDynamicInfoMapper serviceDynamicInfoMapper;

    @Override
    public IPage<Map> serviceDynamicInfoList(Page<Map> page, Map params) {
//        IPage<Map> result = serviceDynamicInfoMapper.serviceDynamicInfoList(page,params);
        return serviceDynamicInfoMapper.serviceDynamicInfoList(page,params);
    }

    @Override
    public List<Map> serviceDynamicInfoAnalysis(long startTime, long endTime) {
        return serviceDynamicInfoMapper.serviceDynamicInfoAnalysis(startTime, endTime);
    }
}
