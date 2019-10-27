package org.jeecg.modules.baby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.baby.entity.Baby;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-06
 */
@Component
public interface BabyMapper extends BaseMapper<Baby> {

    List<Map<String,Object>> queryConsulterBabyMessage(@Param("consulterId") String consulterId, @Param("type") String type);



   List<Map<String,Object>> queryDefaultMessage(@Param("type") String type,@Param("consulterId") String consulterId,
                                                @Param("tempOrderId") String tempOrderId,@Param("babyId") String babyId);


   void updateChildBirth(String babyId);

}
