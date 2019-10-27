package org.jeecg.modules.statistics.service;

import org.jeecg.modules.statistics.entity.ChnlCustAttentionD;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * <p>
 * 渠道关注日统计表，保存每天各个渠道的医生用户关注数量。 服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-10
 */
public interface ChnlCustAttentionDService extends IService<ChnlCustAttentionD> {

    public boolean refreshChnlCustAttentionDByTime(Date start,Date end);
}
