CREATE TABLE `sys_quartz_job` (
  `id` varchar(255) NOT NULL,
  `create_by` varchar(255) default NULL COMMENT '创建人',
  `create_time` datetime default NULL COMMENT '创建时间',
  `del_flag` int(11) default NULL COMMENT '删除状态',
  `update_by` varchar(255) default NULL COMMENT '修改人',
  `update_time` datetime default NULL COMMENT '修改时间',
  `job_class_name` varchar(255) default NULL COMMENT '任务类名',
  `cron_expression` varchar(255) default NULL COMMENT 'cron表达式',
  `parameter` varchar(255) default NULL COMMENT '参数',
  `description` varchar(255) default NULL COMMENT '描述',
  `status` int(11) default NULL COMMENT '状态 0正常 -1停止',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
INSERT INTO `sys_quartz_job` VALUES ('df26ecacf0f75d219d746750fe84bbee', null, null, '0', 'admin', '2019-01-19 15:09:41', 'org.jeecg.module.quartz.job.SampleParamJob', '0/1 * * * * ?', 'scott', '带参测试 后台将每隔1秒执行输出日志', '-1');
INSERT INTO `sys_quartz_job` VALUES ('58180f2a7c8cd36a121fd0fff3f02a36', null, null, '0', 'admin', '2019-01-19 15:09:44', 'org.jeecg.module.quartz.job.SampleJob', '0/1 * * * * ?', null, null, '-1');
INSERT INTO `sys_quartz_job` VALUES ('58180f2a7c8cd36a121fd0fff3f02a00', null, null, '0', 'admin', '2019-01-19 15:09:44', 'org.jeecg.module.quartz.job.ToEOrderEvaluationDStatisticsJob', '0/10 * * * * ?', null, null, '-1');
