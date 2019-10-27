package org.jeecg.modules.sims.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.sims.entity.SimsLesson;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.sims.vo.SimsLessonVo;

import java.util.Map;

/**
 * <p>
 * 课程  服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-07
 */
public interface ISimsLessonService extends IService<SimsLesson> {

    IPage<SimsLessonVo> querySimsLessonByParamsAdmin(Page<SimsLessonVo> page, SimsLesson simsLesson);

    IPage<SimsLessonVo> querySimsLessonByParams(Page<SimsLessonVo> page, SimsLesson simsLesson);
}
