package org.jeecg.modules.sims.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.sims.entity.SimsRescource;
import org.jeecg.modules.sims.mapper.SimsRescourceMapper;
import org.jeecg.modules.sims.mapper.SimsRescourceMapper;
import org.jeecg.modules.sims.service.ISimsRescourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 课程资料表 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-10
 */
@Service
public class SimsRescourceServiceImpl extends ServiceImpl<SimsRescourceMapper, SimsRescource> implements ISimsRescourceService {

    @Resource
    SimsRescourceMapper simsRescourceMapper;

    @Override
    public IPage<SimsRescource> querySimsRescourceByParams(Page<SimsRescource> page, SimsRescource simsRescource) {
        return simsRescourceMapper.querySimsRescourceByParams( page, simsRescource);
    }
}
