use healthytest;

DROP TABLE
IF EXISTS quartz_task;

CREATE TABLE
IF NOT EXISTS quartz_task (
  id INT (11) AUTO_INCREMENT NOT NULL COMMENT '主键',
  job_name VARCHAR (50) NOT NULL COMMENT '任务名称',
  job_group VARCHAR (50) NOT NULL COMMENT '任务分组',
  bean_name VARCHAR (50) NOT NULL COMMENT '定时任务在spring容器的beanName',
  cron VARCHAR (50) NOT NULL COMMENT '定时表达式',
  pause_status INT (3) NOT NULL COMMENT '是否暂停 同trueOrFalse 字典',
  create_uid INT (11) NULL COMMENT '创建管理员id',
  update_uid INT (11) NULL COMMENT '修改管理员id',
  create_time DATETIME NOT NULL COMMENT '创建时间',
  update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY (job_name,job_group)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT '定时任务表';

DROP TABLE
IF EXISTS quartz_task_log;

CREATE TABLE
IF NOT EXISTS quartz_task_log (
  id INT (11) AUTO_INCREMENT NOT NULL COMMENT '主键',
  job_name VARCHAR (50) NOT NULL COMMENT '任务名称',
  job_group VARCHAR (50) NOT NULL COMMENT '任务分组',
  start_time  DATETIME NOT NULL COMMENT '执行开始时间',
  end_time  DATETIME  NULL COMMENT '执行结束时间',
success_status INT (3) NOT NULL COMMENT '是否成功 同trueOrFalse 字典',
  create_uid INT (11) NULL COMMENT '创建管理员id',
  update_uid INT (11) NULL COMMENT '修改管理员id',
  create_time DATETIME NOT NULL COMMENT '创建时间',
  update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT '定时任务执行日志表';




