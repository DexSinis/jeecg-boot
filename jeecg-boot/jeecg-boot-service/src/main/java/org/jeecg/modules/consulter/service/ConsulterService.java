package org.jeecg.modules.consulter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.consulter.entity.Consulter;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 咨询者、病人、粉丝 服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-05-14
 */
public interface ConsulterService extends IService<Consulter> {

    Map findConsulterIdsByLoginTime(Integer lastTime);

    Map findMaxConsulterId();

    List<Map> findPersUserDList(String before,String after);

    List<Map> findPersUserDListByConsulterId(String beforeConsulterId,String afterConsulterId);

    List<Map> findOrderCountList(String before,String after);

    List<Map> findOrderCountListByConsulterId(String beforeConsulterId,String afterConsulterId);
}
