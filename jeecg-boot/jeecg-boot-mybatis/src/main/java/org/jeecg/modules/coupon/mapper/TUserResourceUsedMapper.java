package org.jeecg.modules.coupon.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.coupon.entity.TUserResourceUsed;
import org.jeecg.modules.vip.vo.TUserResourceVo;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 记录用户对营销资源的领用、消费使用情况 Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@Component
public interface TUserResourceUsedMapper extends BaseMapper<TUserResourceUsed> {

    IPage<TUserResourceVo> myCoupons(Page<TUserResourceVo> page,
                                     @Param("consulterId") String consulterId,
                                     @Param("type") String type,
                                     @Param("serviceId") String serviceId);

}
