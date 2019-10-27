package org.jeecg.modules.notice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.notice.entity.ServiceDynamicInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-05
 */
public interface ServiceDynamicInfoService extends IService<ServiceDynamicInfo> {

    IPage<Map> serviceDynamicInfoList(Page<Map> page, Map params);

    List<Map> serviceDynamicInfoAnalysis(long startTime, long endTime);
}
