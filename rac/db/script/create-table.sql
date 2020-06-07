/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/6/7 21:01:51                            */
/*==============================================================*/


/*==============================================================*/
/* Table: rac_login_log                                         */
/*==============================================================*/
create table rac_login_log
(
   id                   bigint unsigned not null  comment '用户登录日志ID',
   user_id              bigint unsigned not null  comment '用户ID(如为1则是散客)',
   sys_id               varchar(32) not null  comment '系统ID',
   login_way            varchar(32) not null  comment '登录类型(与注册类型一致)
             LOGIN_NAME: 登录名与密码
             EMAIL_PASSWORD: 电子邮箱与密码
             MOBILE_PASSWORD: 手机号与密码
             MOBILE_SMS. 手机短信验证
             WECHAT_OFFICIAL_ACCOUNTS: 微信公众号',
   login_time           datetime not null  comment '登录时间',
   primary key (id)
);

alter table rac_login_log comment '用户登录日志';

/*==============================================================*/
/* Table: rac_menu                                              */
/*==============================================================*/
create table rac_menu
(
   id                   varchar(32) not null  comment '菜单ID',
   sys_id               varchar(32) not null  comment '系统ID',
   code                 varchar(20) not null  comment '菜单编码',
   name                 varchar(20) not null  comment '菜单名称',
   title                varchar(30)  comment '标题(点击菜单后显示在内容页面的标题)',
   path                 varchar(20) not null  comment '路径',
   is_enabled           bool not null default true  comment '是否启用',
   icon                 varchar(20)  comment '菜单图标',
   remark               varchar(50)  comment '菜单备注',
   primary key (id)
);

alter table rac_menu comment '菜单信息';

/*==============================================================*/
/* Table: rac_op_log                                            */
/*==============================================================*/
create table rac_op_log
(
   id                   bigint unsigned not null  comment '用户操作日志ID',
   user_id              bigint unsigned not null  comment '用户ID(如为1则是散客)',
   sys_id               varchar(32) not null  comment '系统ID',
   op_title             varchar(32) not null  comment '操作标题',
   op_detail            varchar(300) not null  comment '操作详情',
   op_time              datetime not null  comment '操作时间',
   primary key (id)
);

alter table rac_op_log comment '用户操作日志';

/*==============================================================*/
/* Table: rac_org                                               */
/*==============================================================*/
create table rac_org
(
   id                   bigint unsigned not null  comment '组织ID(组织ID=账户ID，与账户一一对应)',
   name                 varchar(30) not null  comment '组织名称(简称)',
   parent_id            bigint unsigned  comment '上级组织ID',
   org_type             tinyint unsigned not null  comment '组织类型(1.集团;2.公司;99.部门)',
   left_value           int unsigned not null  comment '左值',
   right_value          int unsigned not null  comment '右值',
   full_name            varchar(80)  comment '组织全名',
   introduction         varchar(200)  comment '组织简介',
   remark               varchar(100)  comment '组织备注',
   primary key (id),
   unique key ak_name (name),
   unique key ak_full_name (full_name)
);

alter table rac_org comment '组织信息';

/*==============================================================*/
/* Table: rac_org_user                                          */
/*==============================================================*/
create table rac_org_user
(
   id                   bigint unsigned not null  comment '组织用户ID',
   org_id               bigint unsigned not null  comment '组织ID',
   user_id              bigint unsigned not null  comment '用户ID',
   primary key (id),
   unique key ak_org_and_user (org_id, user_id)
);

alter table rac_org_user comment '组织用户';

/*==============================================================*/
/* Table: rac_perm                                              */
/*==============================================================*/
create table rac_perm
(
   id                   varchar(32) not null  comment '权限ID',
   group_id             varchar(32) not null  comment '权限分组ID',
   sys_id               varchar(32) not null  comment '系统ID',
   name                 varchar(20) not null  comment '权限名称',
   is_authorize         bool not null default false  comment '是否鉴权(不鉴权意味着放开访问权限)',
   is_enabled           bool not null default true  comment '是否启用',
   order_no             tinyint not null  comment '顺序号',
   remark               varchar(50)  comment '权限备注',
   primary key (id),
   unique key ak_perm_group_and_perm_name (group_id, name)
);

alter table rac_perm comment '权限信息';

/*==============================================================*/
/* Table: rac_perm_group                                        */
/*==============================================================*/
create table rac_perm_group
(
   id                   varchar(32) not null  comment '权限分组ID',
   sys_id               varchar(32) not null  comment '系统ID',
   name                 varchar(20) not null  comment '权限分组名称',
   is_enabled           bool not null default true  comment '是否启用(如果分组没有启用，其下所有权限都要被设置为不启用；只要有一个权限启用，其分组就必须启用)',
   order_no             tinyint unsigned not null  comment '顺序号',
   remark               varchar(50)  comment '权限分组备注',
   primary key (id),
   unique key ak_sys_and_group_name (sys_id, name)
);

alter table rac_perm_group comment '权限分组';

/*==============================================================*/
/* Table: rac_perm_menu                                         */
/*==============================================================*/
create table rac_perm_menu
(
   id                   bigint unsigned not null  comment '权限菜单ID',
   perm_id              varchar(32) not null  comment '权限ID',
   menu_id              varchar(32) not null  comment '菜单ID',
   primary key (id),
   unique key ak_perm_and_menu (perm_id, menu_id)
);

alter table rac_perm_menu comment '权限菜单';

/*==============================================================*/
/* Table: rac_perm_urn                                          */
/*==============================================================*/
create table rac_perm_urn
(
   id                   bigint unsigned not null  comment '权限URN的ID',
   perm_id              varchar(32) not null  comment '权限ID',
   urn                  varchar(100) not null  comment 'URN',
   primary key (id),
   unique key ak_perm_and_urn (perm_id, urn)
);

alter table rac_perm_urn comment '权限URN';

/*==============================================================*/
/* Table: rac_role                                              */
/*==============================================================*/
create table rac_role
(
   id                   varchar(32) not null  comment '角色ID',
   sys_id               varchar(32) not null  comment '系统ID',
   name                 varchar(20) not null  comment '角色名称',
   home_path            varchar(70)  comment '首页路径',
   is_enabled           bool not null default true  comment '是否启用',
   order_no             tinyint unsigned not null  comment '顺序号',
   remark               varchar(50)  comment '角色备注',
   primary key (id),
   unique key ak_sys_and_role_name (sys_id, name)
);

alter table rac_role comment '角色信息';

/*==============================================================*/
/* Table: rac_role_perm                                         */
/*==============================================================*/
create table rac_role_perm
(
   id                   bigint unsigned not null  comment '角色权限ID',
   role_id              varchar(32) not null  comment '角色ID',
   perm_id              varchar(32) not null  comment '权限ID',
   primary key (id),
   unique key ak_role_and_perm (role_id, perm_id)
);

alter table rac_role_perm comment '角色权限';

/*==============================================================*/
/* Table: rac_sys                                               */
/*==============================================================*/
create table rac_sys
(
   id                   varchar(32) not null  comment '系统ID',
   name                 varchar(20) not null  comment '系统名称',
   remark               varchar(50)  comment '系统备注',
   primary key (id),
   unique key ak_sys_name (name)
);

alter table rac_sys comment '系统信息';

/*==============================================================*/
/* Table: rac_sys_user                                          */
/*==============================================================*/
create table rac_sys_user
(
   id                   bigint not null  comment '系统用户ID',
   sys_id               varchar(32) not null  comment '系统ID',
   user_id              bigint unsigned not null  comment '用户ID(如为1则是散客)',
   primary key (id),
   unique key ak_sys_and_user (sys_id, user_id)
);

alter table rac_sys_user comment '系统用户';

/*==============================================================*/
/* Table: rac_user                                              */
/*==============================================================*/
create table rac_user
(
   id                   bigint unsigned not null  comment '用户ID(如为1则是散客)',
   nickname             varchar(20)  comment '用户昵称',
   avatar               varchar(300)  comment '用户头像',
   login_name           varchar(20)  comment '登录名称',
   login_pswd           varchar(32)  comment '登录密码',
   pay_pswd             varchar(32)  comment '支付密码
             用户的支付密码默认和登录密码一致
             保存在字段的计算方法如下：
             MD5(数据库存储的已加密的登陆密码)',
   salt                 char(6)  comment '密码组合码
             与密码组合加密用
             登录密码=小写(MD5(小写(MD5(密码明文))+小写(密码组合码)))',
   mobile               varchar(11)  comment '手机',
   is_verified_mobile   bool default false  comment '是否已验证手机号码',
   email                varchar(50)  comment '电子邮箱',
   is_verified_email    bool default false  comment '是否已验证电子邮箱',
   wx_open_id           varchar(64)  comment '微信的OpenId',
   wx_union_id          varchar(64)  comment '微信的UnionId',
   wx_nickname          varchar(100)  comment '微信昵称',
   wx_avatar            varchar(300)  comment '微信头像',
   qq_open_id           varchar(64)  comment 'QQ的OpenId',
   qq_union_id          varchar(64)  comment 'QQ的UnionId',
   qq_nickname          varchar(100)  comment 'QQ昵称',
   qq_avatar            varchar(300)  comment 'QQ头像',
   real_name            varchar(100)  comment '用户实名',
   is_verified_realname bool default false  comment '是否已验证实名',
   id_card              char(18)  comment '身份证号',
   is_verified_idcard   bool default false  comment '是否已验证身份证号',
   sex                  tinyint unsigned  comment '性别',
   age                  tinyint unsigned  comment '年龄',
   is_tester            bool not null default false  comment '是否测试者',
   is_enabled           bool not null default true  comment '是否启用',
   modified_timestamp   bigint unsigned not null  comment '修改时间戳',
   primary key (id),
   unique key ak_nickname (nickname),
   unique key ak_login_name (login_name),
   unique key ak_mobile (mobile),
   unique key ak_email (email),
   unique key ak_wx_open_id (wx_open_id),
   unique key ak_wx_union_id (wx_union_id),
   unique key ak_qq_open_id (qq_open_id),
   unique key ak_qq_union_id (qq_union_id),
   unique key ak_id_card (id_card)
);

alter table rac_user comment '用户信息';

/*==============================================================*/
/* Table: rac_user_role                                         */
/*==============================================================*/
create table rac_user_role
(
   id                   bigint unsigned not null  comment '用户角色ID',
   sys_id               varchar(20) not null  comment '系统ID',
   role_id              varchar(32) not null  comment '角色ID',
   user_id              bigint unsigned not null  comment '用户ID(如为1则是散客)',
   primary key (id),
   unique key ak_user_and_role (role_id, user_id)
);

alter table rac_user_role comment '用户角色';

alter table rac_login_log add constraint fk_login_log_and_user foreign key (user_id)
      references rac_user (id) on delete restrict on update restrict;

alter table rac_login_log add constraint fk_login_log_and_sys foreign key (sys_id)
      references rac_sys (id) on delete restrict on update restrict;

alter table rac_menu add constraint fk_menu_and_sys foreign key (sys_id)
      references rac_sys (id) on delete restrict on update restrict;

alter table rac_op_log add constraint fk_op_log_and_user foreign key (user_id)
      references rac_user (id) on delete restrict on update restrict;

alter table rac_op_log add constraint fk_op_log_and_sys foreign key (sys_id)
      references rac_sys (id) on delete restrict on update restrict;

alter table rac_org add constraint fk_org_and_org foreign key (parent_id)
      references rac_org (id) on delete restrict on update restrict;

alter table rac_org_user add constraint fk_org_user_and_org foreign key (org_id)
      references rac_org (id) on delete restrict on update restrict;

alter table rac_org_user add constraint fk_org_user_and_user foreign key (user_id)
      references rac_user (id) on delete restrict on update restrict;

alter table rac_perm add constraint fk_perm_and_perm_group foreign key (group_id)
      references rac_perm_group (id) on delete restrict on update restrict;

alter table rac_perm add constraint fk_perm_and_sys foreign key (sys_id)
      references rac_sys (id) on delete restrict on update restrict;

alter table rac_perm_group add constraint fk_perm_group_and_sys foreign key (sys_id)
      references rac_sys (id) on delete restrict on update restrict;

alter table rac_perm_menu add constraint fk_perm_menu_and_perm foreign key (perm_id)
      references rac_perm (id) on delete restrict on update restrict;

alter table rac_perm_menu add constraint fk_perm_menu_and_menu foreign key (menu_id)
      references rac_menu (id) on delete restrict on update restrict;

alter table rac_perm_urn add constraint fk_perm_urn_and_perm foreign key (perm_id)
      references rac_perm (id) on delete restrict on update restrict;

alter table rac_role add constraint fk_role_and_sys foreign key (sys_id)
      references rac_sys (id) on delete restrict on update restrict;

alter table rac_role_perm add constraint fk_role_perm_and_perm foreign key (perm_id)
      references rac_perm (id) on delete restrict on update restrict;

alter table rac_role_perm add constraint fk_role_perm_and_role foreign key (role_id)
      references rac_role (id) on delete restrict on update restrict;

alter table rac_sys_user add constraint fk_sys_user_and_sys foreign key (sys_id)
      references rac_sys (id) on delete restrict on update restrict;

alter table rac_sys_user add constraint fk_sys_user_and_user foreign key (user_id)
      references rac_user (id) on delete restrict on update restrict;

alter table rac_user_role add constraint fk_user_role_and_role foreign key (role_id)
      references rac_role (id) on delete restrict on update restrict;

alter table rac_user_role add constraint fk_user_role_and_user foreign key (user_id)
      references rac_user (id) on delete restrict on update restrict;

