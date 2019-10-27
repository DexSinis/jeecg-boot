package org.jeecg.modules.statistics.service;

import org.jeecg.modules.statistics.entity.GratuityDtlD;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 心意日统计表，保存用户的送出的心意明细数据。 服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-06
 */
public interface GratuityDtlDService extends IService<GratuityDtlD> {

    public boolean refreshGratuityDtlDByTime(String before, String after);
}
