package org.jeecg.modules.sims.service;

import org.jeecg.modules.sims.entity.SimsTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.sims.vo.SimsTeacherVo;

import java.util.List;

/**
 * <p>
 * 教师  服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-03
 */
public interface ISimsTeacherService extends IService<SimsTeacher> {

    List<SimsTeacherVo> querySimsTeacherBySort3();
}
