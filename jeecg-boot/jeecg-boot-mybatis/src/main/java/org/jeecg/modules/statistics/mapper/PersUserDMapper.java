package org.jeecg.modules.statistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.statistics.entity.PersUserD;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户日统计表，保存最新用户数据。 Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-05
 */
public interface PersUserDMapper extends BaseMapper<PersUserD> {

    int deletePersUserDByLastTime(@Param("before")String before,@Param("after")String after);

    int deleteAllPersUserD();

    int deleteOnePersUserD(@Param("consulterId")String consulterId);

    int insertPersUserD(@Param("beforeConsulterId")String beforeConsulterId,@Param("afterConsulterId")String afterConsulterId);
}
