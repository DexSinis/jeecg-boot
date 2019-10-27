package org.jeecg.modules.quartz.service.impl;

import org.jeecg.modules.quartz.entity.QuartzTaskLog;
import org.jeecg.modules.quartz.mapper.QuartzTaskLogMapper;
import org.jeecg.modules.quartz.service.QuartzTaskLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务执行日志表 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-08
 */
@Service
public class QuartzTaskLogServiceImpl extends ServiceImpl<QuartzTaskLogMapper, QuartzTaskLog> implements QuartzTaskLogService {

}
