package org.jeecg.modules.quartz.job;

import org.jeecg.util.date.ZxDateUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 示例不带参定时任务
 * 
 * @author Scott
 */
@Component("sampleJob")
@Slf4j
public class SampleJob {

	public void execute() throws JobExecutionException {
		log.info(String.format(" DexSinis 普通定时任务 SampleJob !  时间:" + ZxDateUtils.getTimestamp()));

	}
}
