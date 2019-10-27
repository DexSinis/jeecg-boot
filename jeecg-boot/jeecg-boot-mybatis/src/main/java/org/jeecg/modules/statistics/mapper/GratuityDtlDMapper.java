package org.jeecg.modules.statistics.mapper;

import org.jeecg.modules.statistics.entity.GratuityDtlD;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 心意日统计表，保存用户的送出的心意明细数据。 Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-06
 */
public interface GratuityDtlDMapper extends BaseMapper<GratuityDtlD> {

    List<Map> findGratuityDtlDByTime(@Param("before")String before, @Param("after")String after);

}
