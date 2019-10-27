insert into `sys_permission` ( `id`, `parent_id`, `name`, `url`, `component`, `component_name`, `redirect`, `menu_type`, `perms`, `perms_type`, `sort_no`, `always_show`, `icon`, `is_route`, `is_leaf`, `keep_alive`, `hidden`, `description`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`, `rule_flag`, `status`) values ( '64fd7b20249ec0d5ccc270e8d02939dc', 'e6f7e08521831945053bd48b737146ed', '课程列表', '/modules/sims/simsLessonList', 'modules/sims/SimsLessonList', null, null, '1', null, '1', '2', '0', null, '1', '1', '0', '0', null, 'admin', '2019-08-21 11:44:51', 'admin', '2019-08-21 15:28:26', '0', '0', '1');
insert into `sys_permission` ( `id`, `parent_id`, `name`, `url`, `component`, `component_name`, `redirect`, `menu_type`, `perms`, `perms_type`, `sort_no`, `always_show`, `icon`, `is_route`, `is_leaf`, `keep_alive`, `hidden`, `description`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`, `rule_flag`, `status`) values ( 'aa766be7a05ee9734e0fe04b8e1c2f54', 'e6f7e08521831945053bd48b737146ed', '教师管理', '/modules/sims/simsTeacherList', 'modules/sims/SimsTeacherList', null, null, '1', null, '1', '1', '0', null, '1', '1', '0', '0', null, 'admin', '2019-08-21 15:28:08', null, null, '0', '0', '1');
insert into `sys_permission` ( `id`, `parent_id`, `name`, `url`, `component`, `component_name`, `redirect`, `menu_type`, `perms`, `perms_type`, `sort_no`, `always_show`, `icon`, `is_route`, `is_leaf`, `keep_alive`, `hidden`, `description`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`, `rule_flag`, `status`) values ( 'b52820f081aeb4b3988690f8ef027e1e', 'e6f7e08521831945053bd48b737146ed', '学生管理', '/modules/sims/simsStudentList', 'modules/sims/SimsStudentList', null, null, '1', null, '1', '0', '0', '', '1', '1', '0', '0', null, 'admin', '2019-08-21 15:26:50', 'admin', '2019-08-21 15:28:17', '0', '0', '1');
insert into `sys_permission` ( `id`, `parent_id`, `name`, `url`, `component`, `component_name`, `redirect`, `menu_type`, `perms`, `perms_type`, `sort_no`, `always_show`, `icon`, `is_route`, `is_leaf`, `keep_alive`, `hidden`, `description`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`, `rule_flag`, `status`) values ( 'e6f7e08521831945053bd48b737146ed', '', '课程管理', '/sims', 'layouts/RouteView', null, null, '0', null, '1', '11', '0', 'copy', '1', '0', '0', '0', null, 'admin', '2019-08-21 11:43:38', 'admin', '2019-08-21 11:47:35', '0', '0', '1');

insert into `sys_user` ( `id`, `username`, `realname`, `password`, `salt`, `avatar`, `birthday`, `sex`, `email`, `phone`, `org_code`, `status`, `del_flag`, `activiti_sync`, `create_by`, `create_time`, `update_by`, `update_time`) values ( 'e2b1e496ed004636bbd5ff80f04a2993', '13510291023', '陈本葵', '6714fe6319cd58d76d73ed228ec891c2', 'oDDqR3on', 'files/20190815/WX20190726-145538@2x_1565840412079.png', null, null, null, null, 'A03A01', '1', '0', '1', 'admin', '2019-08-15 11:40:53', 'admin', '2019-08-21 15:45:52');
insert into `sys_role` ( `id`, `role_name`, `role_code`, `description`, `create_by`, `create_time`, `update_by`, `update_time`) values ( '9f1bd8aa84e4b0be50e82d79028b604a', '课程管理角色', 'lesson', '课程编辑者', 'admin', '2019-08-15 11:38:58', 'admin', '2019-08-15 11:39:08');
insert into `sys_depart` ( `id`, `parent_id`, `depart_name`, `depart_name_en`, `depart_name_abbr`, `depart_order`, `description`, `org_type`, `org_code`, `mobile`, `fax`, `address`, `memo`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) values ( 'b6511a51035f4034bf28c5ff627d3af4', '', '彼德陪练', null, null, '0', null, '1', 'A03', null, null, null, null, null, '0', 'admin', '2019-08-15 11:36:20', null, null);
insert into `sys_depart` ( `id`, `parent_id`, `depart_name`, `depart_name_en`, `depart_name_abbr`, `depart_order`, `description`, `org_type`, `org_code`, `mobile`, `fax`, `address`, `memo`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) values ( '14be6b84065f40fd9cc9061f9353409f', 'b6511a51035f4034bf28c5ff627d3af4', '音乐部', null, null, '0', null, '2', 'A03A01', null, null, null, null, null, '0', 'admin', '2019-08-15 11:36:49', null, null);