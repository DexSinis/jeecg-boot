package org.jeecg.modules.statistics.mapper;

import org.jeecg.modules.statistics.entity.WeixinFansRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-10
 */
public interface WeixinFansRecordMapper extends BaseMapper<WeixinFansRecord> {

    List<Map> findWeixinRecordByCreateTime(@Param("start")long start, @Param("end")long end);

}
