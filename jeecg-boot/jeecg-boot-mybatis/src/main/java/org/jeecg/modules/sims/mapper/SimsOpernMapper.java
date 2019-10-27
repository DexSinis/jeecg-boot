package org.jeecg.modules.sims.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.sims.entity.SimsLesson;
import org.jeecg.modules.sims.entity.SimsOpern;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.sims.entity.SimsStudent;
import org.jeecg.modules.sims.vo.SimsOpernVo;
import org.jeecg.modules.sims.vo.SimsTeacherVo;

import java.util.List;

/**
 * <p>
 * 曲谱  Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-12
 */
public interface SimsOpernMapper extends BaseMapper<SimsOpern> {

    IPage<SimsOpernVo> simsRescourceOpernStudentList(Page<SimsOpernVo> page, SimsStudent simsStudent);

    IPage<SimsOpernVo> simsRescourceOpernLessonList(Page<SimsOpernVo> page, SimsLesson simsLesson);

    SimsOpernVo simsRescourceOpern(SimsOpern simsOpern);
}
