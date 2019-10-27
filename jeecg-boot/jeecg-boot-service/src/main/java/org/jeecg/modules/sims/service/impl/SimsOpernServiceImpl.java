package org.jeecg.modules.sims.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.sims.entity.SimsLesson;
import org.jeecg.modules.sims.entity.SimsOpern;
import org.jeecg.modules.sims.entity.SimsStudent;
import org.jeecg.modules.sims.mapper.SimsOpernMapper;
import org.jeecg.modules.sims.mapper.SimsOpernMapper;
import org.jeecg.modules.sims.service.ISimsOpernService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.sims.vo.SimsOpernVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 曲谱  服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-12
 */
@Service
public class SimsOpernServiceImpl extends ServiceImpl<SimsOpernMapper, SimsOpern> implements ISimsOpernService {


    @Resource
    SimsOpernMapper simsOpernMapper;
    
    @Override
    public IPage<SimsOpernVo> simsRescourceOpernStudentList(Page<SimsOpernVo> page, SimsStudent simsStudent) {
        return simsOpernMapper.simsRescourceOpernStudentList( page,  simsStudent);
    }

    @Override
    public IPage<SimsOpernVo> simsRescourceOpernLessonList(Page<SimsOpernVo> page, SimsLesson simsLesson) {
        return simsOpernMapper.simsRescourceOpernLessonList( page, simsLesson);
    }


    @Override
    public SimsOpernVo simsRescourceOpern(SimsOpern simsOpern){
        return simsOpernMapper.simsRescourceOpern(simsOpern);
    }

}
