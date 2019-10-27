package org.jeecg.modules.consulter.mapper;

import org.jeecg.modules.consulter.entity.ConsulterOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 工单表 Mapper 接口
 * </p>
 *
 * @author DexSinis
 * @since 2019-05-14
 */
@Repository
public interface ConsulterOrderMapper extends BaseMapper<ConsulterOrder> {
    ConsulterOrder getOrderStatusById(Integer id);

    List<ConsulterOrder> queryConsulterList();
}
