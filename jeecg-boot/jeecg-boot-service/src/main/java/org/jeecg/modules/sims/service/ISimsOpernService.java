package org.jeecg.modules.sims.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.common.system.base.service.JeecgService;
import org.jeecg.modules.sims.entity.SimsLesson;
import org.jeecg.modules.sims.entity.SimsOpern;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.sims.entity.SimsStudent;
import org.jeecg.modules.sims.vo.SimsOpernVo;

/**
 * <p>
 * 曲谱  服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-12
 */
public interface ISimsOpernService extends IService<SimsOpern> {

    IPage<SimsOpernVo> simsRescourceOpernStudentList(Page<SimsOpernVo> page, SimsStudent simsStudent);


    IPage<SimsOpernVo> simsRescourceOpernLessonList(Page<SimsOpernVo> page, SimsLesson simsLesson);

    SimsOpernVo simsRescourceOpern(SimsOpern simsOpern);
}
