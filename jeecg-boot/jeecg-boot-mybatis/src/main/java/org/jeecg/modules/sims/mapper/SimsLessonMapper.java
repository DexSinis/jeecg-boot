package org.jeecg.modules.sims.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.sims.entity.SimsLesson;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.sims.vo.SimsLessonVo;

import java.util.Map;

/**
 * <p>
 * 课程  Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-07
 */
public interface SimsLessonMapper extends BaseMapper<SimsLesson> {

    IPage<SimsLessonVo> querySimsLessonByParamsAdmin(Page<SimsLessonVo> page,@Param("simsLesson") SimsLesson simsLesson);

    IPage<SimsLessonVo> querySimsLessonByParams(Page<SimsLessonVo> page,@Param("simsLesson") SimsLesson simsLesson);
}
