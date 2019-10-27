package org.jeecg.modules.sims.service.impl;

import org.jeecg.modules.sims.entity.SimsTeacher;
import org.jeecg.modules.sims.mapper.SimsOpernMapper;
import org.jeecg.modules.sims.mapper.SimsTeacherMapper;
import org.jeecg.modules.sims.service.ISimsTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.sims.vo.SimsTeacherVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 教师  服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-03
 */
@Service
public class SimsTeacherServiceImpl extends ServiceImpl<SimsTeacherMapper, SimsTeacher> implements ISimsTeacherService {


    @Resource
    SimsTeacherMapper simsTeacherMapper;

    @Override
    public List<SimsTeacherVo> querySimsTeacherBySort3() {
        return simsTeacherMapper.querySimsTeacherBySort3();
    }
}
