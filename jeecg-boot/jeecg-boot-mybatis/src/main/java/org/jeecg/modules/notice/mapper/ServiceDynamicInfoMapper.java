package org.jeecg.modules.notice.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.notice.entity.ServiceDynamicInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-05
 */
public interface ServiceDynamicInfoMapper extends BaseMapper<ServiceDynamicInfo> {

    IPage<Map> serviceDynamicInfoList(Page<Map> page, Map params);

    List<Map> serviceDynamicInfoAnalysis(long startTime, long endTime);
}
