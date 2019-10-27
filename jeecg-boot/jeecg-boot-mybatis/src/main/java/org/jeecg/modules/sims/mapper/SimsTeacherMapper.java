package org.jeecg.modules.sims.mapper;

import org.jeecg.modules.sims.entity.SimsTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.sims.vo.SimsTeacherVo;

import java.util.List;

/**
 * <p>
 * 教师  Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-03
 */
public interface SimsTeacherMapper extends BaseMapper<SimsTeacher> {

    List<SimsTeacherVo> querySimsTeacherBySort3();

}
