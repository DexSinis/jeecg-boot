package org.jeecg.modules.consulter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.consulter.entity.Consulter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 咨询者、病人、粉丝 Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-05-14
 */
@Repository
public interface ConsulterMapper extends BaseMapper<Consulter> {
    Consulter getByOpenIdAndBmark(@Param("openid")String openid, @Param("bmark")String bmark);

    List<Map<String,Object>> getThreeMonthOrdersByPage(@Param("time")long time, @Param("consulterId")String consulterId, @Param("customerId")String customerId);

    Map getConsulterIdsByLoginTime(Integer lastTime);

    Map getMaxConsulterId();

    List<Map> getPersUserDList(@Param("before")String before,@Param("after")String after);

    List<Map> getPersUserDListByConsulterId(@Param("beforeConsulterId")String beforeConsulterId,@Param("afterConsulterId")String afterConsulterId);

    List<Map> getOrderCountList(@Param("before")String before,@Param("after")String after);

    List<Map> getOrderCountListByConsulterId(@Param("beforeConsulterId")String beforeConsulterId,@Param("afterConsulterId")String afterConsulterId);
}
