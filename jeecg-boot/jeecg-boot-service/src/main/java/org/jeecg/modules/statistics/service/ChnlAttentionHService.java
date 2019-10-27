package org.jeecg.modules.statistics.service;

import org.jeecg.modules.statistics.entity.ChnlAttentionH;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * <p>
 * 渠道关注时统计表，存储每日偶数时点关注数据。每偶数整点增量统计。 服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-11
 */
public interface ChnlAttentionHService extends IService<ChnlAttentionH> {

    boolean refreshChnlAttentionHByTime(Date start, Date end);
}
