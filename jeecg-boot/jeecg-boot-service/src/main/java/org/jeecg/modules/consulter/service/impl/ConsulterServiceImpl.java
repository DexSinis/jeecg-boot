package org.jeecg.modules.consulter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.consulter.entity.Consulter;
import org.jeecg.modules.consulter.mapper.ConsulterMapper;
import org.jeecg.modules.consulter.service.ConsulterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 咨询者、病人、粉丝 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-05-14
 */
@Service
public class ConsulterServiceImpl extends ServiceImpl<ConsulterMapper, Consulter> implements ConsulterService {

    @Resource
    private ConsulterMapper consulterMapper;

    @Override
    public Map findConsulterIdsByLoginTime(Integer lastTime) {

        return consulterMapper.getConsulterIdsByLoginTime(lastTime);
    }

    @Override
    public Map findMaxConsulterId() {

        return consulterMapper.getMaxConsulterId();
    }

    @Override
    public List<Map> findPersUserDList(String before,String after) {
        return consulterMapper.getPersUserDList(before,after);
    }

    @Override
    public List<Map> findPersUserDListByConsulterId(String beforeConsulterId,String afterConsulterId) {
        return consulterMapper.getPersUserDListByConsulterId(beforeConsulterId,afterConsulterId);
    }

    @Override
    public List<Map> findOrderCountList(String before,String after) {
        return consulterMapper.getOrderCountList(before,after);
    }

    @Override
    public List<Map> findOrderCountListByConsulterId(String beforeConsulterId,String afterConsulterId) {
        return consulterMapper.getOrderCountListByConsulterId(beforeConsulterId,afterConsulterId);
    }
}
