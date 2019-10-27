package org.jeecg.modules.statistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.statistics.entity.PersUserOrderD;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户工单日全量表，保存累计到统计当天用户工单量等信息。 Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-04
 */
public interface PersUserOrderDMapper extends BaseMapper<PersUserOrderD> {

    int deletePersUserOrderDByLastTime(@Param("before")String before,@Param("after")String after);

    int deleteAllPersUserOrderD();

    int deleteOnePersUserOrderD(@Param("consulterId")String consulterId);

    int insertPersUserOrderD(@Param("beforeConsulterId")String beforeConsulterId,@Param("afterConsulterId")String afterConsulterId);
}
