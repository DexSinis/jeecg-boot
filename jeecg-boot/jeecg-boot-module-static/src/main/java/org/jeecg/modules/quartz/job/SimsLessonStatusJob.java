package org.jeecg.modules.quartz.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.constant.PetrusConstant;
import org.jeecg.constants.CommonConstant;
import org.jeecg.modules.quartz.service.IQuartzJobService;
import org.jeecg.modules.quartz.entity.QuartzJob;
import org.jeecg.modules.quartz.entity.QuartzTaskLog;
import org.jeecg.modules.quartz.service.QuartzTaskLogService;
import org.jeecg.modules.sims.entity.SimsLesson;
import org.jeecg.modules.sims.service.ISimsLessonService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 查询已经过期的课程，根据状态置为 缺课 4   超时 5
 */
@Component("simsLessonStatusJob")
@Slf4j
public class SimsLessonStatusJob {
    @Resource
    QuartzTaskLogService quartzTaskLogService;

    @Resource
    IQuartzJobService quartzJobService;

    @Resource
    ISimsLessonService simsLessonService;

    public void execute() {
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
        System.out.println("-------------------SimsLessonStatusJob任务执行开始-------------------");

        QueryWrapper<SimsLesson> queryWrapperSimsLesson = new QueryWrapper<SimsLesson>();
        queryWrapperSimsLesson.lt("LESSON_END",new Date());
        queryWrapperSimsLesson.orderByAsc("LESSON_START");
        List<SimsLesson> simsLessonList = simsLessonService.list(queryWrapperSimsLesson);

        for (int i = 0; i <simsLessonList.size() ; i++) {
            SimsLesson simsLesson =  simsLessonList.get(i);
            if(simsLesson.getLessonStatus()== PetrusConstant.LESSON_STATUS.NONE
            &&simsLesson.getReserve()==PetrusConstant.RESERVE_TYPE.DONE){
                simsLesson.setLessonStatus(PetrusConstant.LESSON_STATUS.UNSTART);
            }

            if(simsLesson.getLessonStatus()== PetrusConstant.LESSON_STATUS.NONE
                    &&simsLesson.getReserve()==PetrusConstant.RESERVE_TYPE.NONE){
                simsLesson.setLessonStatus(PetrusConstant.LESSON_STATUS.OVER);
            }
            simsLessonService.saveOrUpdate(simsLesson);
        }

        log.error(simsLessonList.size()+"---------simsLessonList");
        System.out.println(new Date());
        System.out.println("-------------------SimsLessonStatusJob任务执行结束-------------------");
        quartzTaskLog.setEndTime(new Date());
        quartzTaskLog.setSuccessStatus(CommonConstant.SuccessStatus.SUCCESS);
        quartzTaskLogService.updateById(quartzTaskLog);
    }
}