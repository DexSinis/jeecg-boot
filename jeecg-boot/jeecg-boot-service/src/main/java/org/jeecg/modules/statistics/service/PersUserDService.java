package org.jeecg.modules.statistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.statistics.entity.PersUserD;

/**
 * <p>
 * 用户日统计表，保存最新用户数据。 服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-05
 */
public interface PersUserDService extends IService<PersUserD> {

    boolean consulterDAnalysisForJob(String beforeConsulterId,String afterConsulterId);

    boolean insertconsulterDForJob(String beforeConsulterId,String afterConsulterId);

    boolean clearAllConsulterAnalysis();

    boolean clearOneConsulterAnalysis(String consulterId);

    boolean clearConsulterDAnalysis(String before,String after);

    boolean consulterDAnalysis(String before,String after);

}
