package org.jeecg.modules.vip.service.impl;

import org.jeecg.modules.vip.entity.ConsulterOrderPkgPay;
import org.jeecg.modules.vip.mapper.ConsulterOrderPkgPayMapper;
import org.jeecg.modules.vip.service.ConsulterOrderPkgPayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.vip.vo.VipServiceDevelopmentInfoVo;
import org.jeecg.util.ObjectCheckUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@Service
public class ConsulterOrderPkgPayServiceImpl extends ServiceImpl<ConsulterOrderPkgPayMapper, ConsulterOrderPkgPay> implements ConsulterOrderPkgPayService {


    @Resource
    private ConsulterOrderPkgPayMapper consulterOrderPkgPayMapper;

    @Override
    public  List<VipServiceDevelopmentInfoVo> queryVipServiceDevelopment(String pageSize) {
        List<VipServiceDevelopmentInfoVo> list = consulterOrderPkgPayMapper.queryVipServiceDevelopment(Integer.valueOf(pageSize));
        return list;

    }
}
