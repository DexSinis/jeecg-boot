package org.jeecg.modules.statistics.service.impl;

import org.jeecg.modules.statistics.entity.PersUserOrderD;
import org.jeecg.modules.statistics.mapper.PersUserOrderDMapper;
import org.jeecg.modules.statistics.service.PersUserOrderDService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户工单日全量表，保存累计到统计当天用户工单量等信息。 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-04
 */
@Service
public class PersUserOrderDServiceImpl extends ServiceImpl<PersUserOrderDMapper, PersUserOrderD> implements PersUserOrderDService {

}
