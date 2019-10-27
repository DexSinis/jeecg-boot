package org.jeecg.modules.sims.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.sims.entity.SimsLesson;
import org.jeecg.modules.sims.mapper.SimsLessonMapper;
import org.jeecg.modules.sims.service.ISimsLessonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.sims.vo.SimsLessonVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 课程  服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-07
 */
@Slf4j
@Service
public class SimsLessonServiceImpl extends ServiceImpl<SimsLessonMapper, SimsLesson> implements ISimsLessonService {

    @Resource
    SimsLessonMapper simsLessonMapper;

    @Override
    public IPage<SimsLessonVo> querySimsLessonByParams(Page<SimsLessonVo> page, SimsLesson simsLesson) {
        log.error(JSONObject.toJSONString(simsLesson));
        return simsLessonMapper.querySimsLessonByParams( page, simsLesson);
    }

    @Override
    public IPage<SimsLessonVo> querySimsLessonByParamsAdmin(Page<SimsLessonVo> page, SimsLesson simsLesson) {
        return simsLessonMapper.querySimsLessonByParamsAdmin( page, simsLesson);
    }
}
