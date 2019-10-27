package org.jeecg.modules.sims.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.sims.entity.SimsRescource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程资料表 Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-10
 */
public interface SimsRescourceMapper extends BaseMapper<SimsRescource> {

    IPage<SimsRescource> querySimsRescourceByParams(Page<SimsRescource> page, SimsRescource simsRescource);
}
