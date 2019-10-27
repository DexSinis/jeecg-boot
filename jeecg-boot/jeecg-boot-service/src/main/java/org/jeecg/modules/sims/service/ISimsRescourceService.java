package org.jeecg.modules.sims.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.sims.entity.SimsRescource;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程资料表 服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-10
 */
public interface ISimsRescourceService extends IService<SimsRescource> {

    IPage<SimsRescource> querySimsRescourceByParams(Page<SimsRescource> page, SimsRescource simsRescource);
}
