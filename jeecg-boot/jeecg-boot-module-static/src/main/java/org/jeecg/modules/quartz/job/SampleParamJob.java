package org.jeecg.modules.quartz.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.constants.CommonConstant;
import org.jeecg.modules.quartz.entity.QuartzJob;
import org.jeecg.modules.quartz.entity.QuartzTaskLog;
import org.jeecg.modules.quartz.service.IQuartzJobService;
import org.jeecg.modules.quartz.service.QuartzTaskLogService;
import org.jeecg.util.date.ZxDateUtils;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 示例带参定时任务
 * 
 * @author Scott
 */
@Component("sampleParamJob")
@Slf4j
public class SampleParamJob  {

	/**
	 * 若参数变量名修改 QuartzJobController中也需对应修改
	 */
	private String parameter;

	@Resource
	QuartzTaskLogService quartzTaskLogService;

	@Resource
	IQuartzJobService quartzJobService;

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public void execute() throws JobExecutionException {

		QuartzTaskLog quartzTaskLog = new QuartzTaskLog();
		quartzTaskLog.setSuccessStatus(CommonConstant.SuccessStatus.Error);
		QuartzJob quartzJob = new QuartzJob();
		quartzJob.setJobClassName(this.getClass().getName());
		QueryWrapper<QuartzJob> queryWrapper = new QueryWrapper<QuartzJob>(quartzJob);
		QuartzJob quartzJobRecord  = quartzJobService.getOne(queryWrapper);
		if(quartzJobRecord!=null){
			quartzTaskLog.setJobId(quartzJobRecord.getId());
		}
		quartzTaskLog.setCreateTime(new Date());
		quartzTaskLog.setStartTime(new Date());
		quartzTaskLog.setUpdateTime(new Date());
		quartzTaskLog.setJobId(this.getClass().getName());
		quartzTaskLogService.save(quartzTaskLog);

		log.info(String.format("welcome %s! DexSinis 带参数定时任务 SampleParamJob !   时间:" + ZxDateUtils.now(), this.parameter));

		quartzTaskLog.setEndTime(new Date());
		quartzTaskLog.setSuccessStatus(CommonConstant.SuccessStatus.SUCCESS);
		quartzTaskLogService.updateById(quartzTaskLog);
	}
}
