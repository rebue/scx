/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/12/10 11:53:19                          */
/*==============================================================*/


drop table if exists RAC_DOMAIN;


alter table RAC_DOMAIN_USER 
   drop foreign key FK_DOMAIN_USER_AND_DOMAIN;

alter table RAC_DOMAIN_USER 
   drop foreign key FK_DOMAIN_USER_AND_USER;

drop table if exists RAC_DOMAIN_USER;


alter table RAC_LOCK_LOG 
   drop foreign key FK_LOCK_LOG_AND_SYS;

alter table RAC_LOCK_LOG 
   drop foreign key FK_LOCK_LOG_AND_LOCK_USER;

alter table RAC_LOCK_LOG 
   drop foreign key FK_LOCK_LOG_AND_LOCK_OP;

alter table RAC_LOCK_LOG 
   drop foreign key FK_LOCK_LOG_AND_UNLOCK_OP;

drop table if exists RAC_LOCK_LOG;


alter table RAC_MENU 
   drop foreign key FK_MENU_AND_SYS;

drop table if exists RAC_MENU;


alter table RAC_OP_LOG 
   drop foreign key FK_OP_LOG_AND_USER;

alter table RAC_OP_LOG 
   drop foreign key FK_OP_LOG_AND_SYS;

drop table if exists RAC_OP_LOG;


alter table RAC_ORG 
   drop foreign key FK_ORG_AND_ORG;

alter table RAC_ORG 
   drop foreign key FK_ORG_AND_DOMAIN;

drop table if exists RAC_ORG;


alter table RAC_ORG_USER 
   drop foreign key FK_ORG_USER_AND_ORG;

alter table RAC_ORG_USER 
   drop foreign key FK_ORG_USER_AND_USER;

drop table if exists RAC_ORG_USER;


alter table RAC_PERM 
   drop foreign key FK_PERM_AND_DOMAIN;

alter table RAC_PERM 
   drop foreign key FK_PERM_AND_PERM_GROUP;

drop table if exists RAC_PERM;


alter table RAC_PERM_GROUP 
   drop foreign key FK_PERM_GROUP_AND_DOMAIN;

drop table if exists RAC_PERM_GROUP;


alter table RAC_PERM_MENU 
   drop foreign key FK_PERM_MENU_AND_PERM;

alter table RAC_PERM_MENU 
   drop foreign key FK_PERM_MENU_AND_MENU;

drop table if exists RAC_PERM_MENU;


alter table RAC_PERM_URN 
   drop foreign key FK_PERM_URN_AND_PERM;

drop table if exists RAC_PERM_URN;

drop table if exists RAC_PERSON;


alter table RAC_ROLE 
   drop foreign key FK_ROLE_AND_DOMAIN;

drop table if exists RAC_ROLE;


alter table RAC_ROLE_PERM 
   drop foreign key FK_ROLE_PERM_AND_ROLE;

alter table RAC_ROLE_PERM 
   drop foreign key FK_ROLE_PERM_AND_PERM;

drop table if exists RAC_ROLE_PERM;


alter table RAC_SYS 
   drop foreign key FK_SYS_AND_DOMAIN;

drop table if exists RAC_SYS;


alter table RAC_USER 
   drop foreign key FK_USER_AND_PERSON;

drop table if exists RAC_USER;


alter table RAC_USER_ROLE 
   drop foreign key FK_USER_ROLE_AND_ROLE;

alter table RAC_USER_ROLE 
   drop foreign key FK_USER_ROLE_AND_USER;

drop table if exists RAC_USER_ROLE;

/*==============================================================*/
/* Table: RAC_DOMAIN                                            */
/*==============================================================*/
create table RAC_DOMAIN
(
   ID                   varchar(32) not null  comment '领域ID',
   NAME                 varchar(20) not null  comment '领域名称',
   REMARK               varchar(50)  comment '领域备注',
   primary key (ID),
   unique key AK_DOMAIN_NAME (NAME)
);

alter table RAC_DOMAIN comment '领域';

/*==============================================================*/
/* Table: RAC_DOMAIN_USER                                       */
/*==============================================================*/
create table RAC_DOMAIN_USER
(
   ID                   bigint unsigned not null  comment '领域用户ID',
   DOMAIN_ID            varchar(32) not null  comment '领域ID',
   USER_ID              bigint unsigned not null  comment '用户ID',
   IS_ENABLED           bool not null default true  comment '是否启用',
   primary key (ID),
   unique key AK_DOMAIN_AND_USER (DOMAIN_ID, USER_ID)
);

alter table RAC_DOMAIN_USER comment '领域用户';

/*==============================================================*/
/* Table: RAC_LOCK_LOG                                          */
/*==============================================================*/
create table RAC_LOCK_LOG
(
   ID                   bigint unsigned not null  comment '锁定日志ID',
   SYS_ID               varchar(32) not null  comment '系统ID',
   LOCK_USER_ID         bigint unsigned not null  comment '锁定用户的用户ID',
   LOCK_OP_ID           bigint unsigned not null  comment '锁定操作员的用户ID',
   LOCK_REASON          varchar(100) not null  comment '锁定原因',
   LOCK_DATETIME        datetime not null  comment '锁定时间',
   UNLOCK_REASON        varchar(100) not null  comment '解锁原因',
   UNLOCK_DATETIME      datetime  comment '解锁时间',
   UNLOCK_OP_ID         bigint unsigned  comment '解锁操作员的用户ID',
   primary key (ID),
   key AK_USER_AND_LOCK_DATETIME (LOCK_USER_ID, LOCK_DATETIME),
   key AK_USER_AND_UNLOCK_DATETIME (LOCK_USER_ID, UNLOCK_DATETIME)
);

alter table RAC_LOCK_LOG comment '锁定日志';

/*==============================================================*/
/* Table: RAC_MENU                                              */
/*==============================================================*/
create table RAC_MENU
(
   ID                   bigint unsigned not null  comment '菜单ID',
   SYS_ID               varchar(32) not null  comment '系统ID',
   CODE                 varchar(20) not null  comment '菜单编码',
   NAME                 varchar(20) not null  comment '菜单名称',
   TITLE                varchar(30)  comment '标题(点击菜单后显示在内容页面的标题)',
   PATH                 varchar(20) not null  comment '路径',
   IS_ENABLED           bool not null default true  comment '是否启用',
   ICON                 varchar(20)  comment '菜单图标',
   REMARK               varchar(50)  comment '菜单备注',
   primary key (ID)
);

alter table RAC_MENU comment '菜单';

/*==============================================================*/
/* Table: RAC_OP_LOG                                            */
/*==============================================================*/
create table RAC_OP_LOG
(
   ID                   bigint unsigned not null  comment '操作日志ID',
   SYS_ID               varchar(32) not null  comment '系统ID',
   USER_ID              bigint unsigned not null  comment '用户ID',
   OP_TYPE              varchar(20) not null  comment '操作类型',
   OP_TITLE             varchar(32) not null  comment '操作标题',
   OP_DETAIL            varchar(300) not null  comment '操作详情',
   OP_DATETIME          datetime not null  comment '操作时间',
   primary key (ID),
   key AK_USER_AND_OP_TYPE_AND_DATETIME (USER_ID, OP_TYPE, OP_DATETIME)
);

alter table RAC_OP_LOG comment '操作日志';

/*==============================================================*/
/* Table: RAC_ORG                                               */
/*==============================================================*/
create table RAC_ORG
(
   ID                   bigint unsigned not null  comment '组织ID(组织ID=账户ID，与账户一一对应)',
   NAME                 varchar(30) not null  comment '组织名称(简称)',
   PARENT_ID            bigint unsigned  comment '上级组织ID(根组织填0)',
   DOMAIN_ID            varchar(32) not null  comment '领域ID',
   ORG_TYPE             tinyint unsigned not null  comment '组织类型(1.集团;2.公司;99.部门)',
   LEFT_VALUE           int unsigned not null  comment '左值',
   RIGHT_VALUE          int unsigned not null  comment '右值',
   FULL_NAME            varchar(80)  comment '组织全名',
   INTRODUCTION         varchar(200)  comment '组织简介',
   REMARK               varchar(100)  comment '组织备注',
   primary key (ID),
   unique key AK_NAME (NAME),
   unique key AK_FULL_NAME (FULL_NAME)
);

alter table RAC_ORG comment '组织';

/*==============================================================*/
/* Table: RAC_ORG_USER                                          */
/*==============================================================*/
create table RAC_ORG_USER
(
   ID                   bigint unsigned not null  comment '组织用户ID',
   ORG_ID               bigint unsigned not null  comment '组织ID',
   USER_ID              bigint unsigned not null  comment '用户ID',
   primary key (ID),
   unique key AK_ORG_AND_USER (ORG_ID, USER_ID)
);

alter table RAC_ORG_USER comment '组织用户';

/*==============================================================*/
/* Table: RAC_PERM                                              */
/*==============================================================*/
create table RAC_PERM
(
   ID                   bigint unsigned not null  comment '权限ID',
   DOMAIN_ID            varchar(32) not null  comment '领域ID',
   GROUP_ID             bigint unsigned not null  comment '权限分组ID',
   NAME                 varchar(20) not null  comment '权限名称',
   IS_AUTHORIZE         bool not null default false  comment '是否鉴权(不鉴权意味着放开访问权限)',
   IS_ENABLED           bool not null default true  comment '是否启用',
   ORDER_NO             tinyint unsigned not null  comment '顺序号',
   REMARK               varchar(50)  comment '权限备注',
   primary key (ID),
   unique key AK_PERM_GROUP_AND_PERM_NAME (NAME, GROUP_ID)
);

alter table RAC_PERM comment '权限';

/*==============================================================*/
/* Table: RAC_PERM_GROUP                                        */
/*==============================================================*/
create table RAC_PERM_GROUP
(
   ID                   bigint unsigned not null  comment '权限分组ID',
   DOMAIN_ID            varchar(32) not null  comment '领域ID',
   NAME                 varchar(20) not null  comment '权限分组名称',
   IS_ENABLED           bool not null default true  comment '是否启用(如果分组没有启用，其下所有权限都要被设置为不启用；只要有一个权限启用，其分组就必须启用)',
   ORDER_NO             tinyint unsigned not null  comment '顺序号',
   REMARK               varchar(50)  comment '权限分组备注',
   primary key (ID),
   unique key AK_DOMAIN_AND_GROUP_NAME (NAME, DOMAIN_ID)
);

alter table RAC_PERM_GROUP comment '权限分组';

/*==============================================================*/
/* Table: RAC_PERM_MENU                                         */
/*==============================================================*/
create table RAC_PERM_MENU
(
   ID                   bigint unsigned not null  comment '权限菜单ID',
   PERM_ID              bigint unsigned not null  comment '权限ID',
   MENU_ID              bigint unsigned not null  comment '菜单ID',
   primary key (ID),
   unique key AK_PERM_AND_MENU (PERM_ID, MENU_ID)
);

alter table RAC_PERM_MENU comment '权限菜单';

/*==============================================================*/
/* Table: RAC_PERM_URN                                          */
/*==============================================================*/
create table RAC_PERM_URN
(
   ID                   bigint unsigned not null  comment '权限URN的ID',
   PERM_ID              bigint unsigned not null  comment '权限ID',
   URN                  varchar(100) not null  comment 'URN',
   primary key (ID),
   unique key AK_PERM_AND_URN (PERM_ID, URN)
);

alter table RAC_PERM_URN comment '权限URN';

/*==============================================================*/
/* Table: RAC_PERSON                                            */
/*==============================================================*/
create table RAC_PERSON
(
   ID                   bigint unsigned not null  comment '个人ID',
   IS_ENABLED           bool not null default true  comment '是否启用',
   PAY_PSWD             varchar(32)  comment '支付密码(小写(MD5(小写(MD5(密码明文))+小写(密码组合码))))',
   PAY_PSWD_SALT        char(6)  comment '支付密码组合码(与支付密码组合加密用，详见支付密码备注)',
   MOBILE               varchar(11)  comment '手机',
   IS_VERIFIED_MOBILE   bool default false  comment '是否已验证手机号码',
   EMAIL                varchar(50)  comment '电子邮箱',
   IS_VERIFIED_EMAIL    bool default false  comment '是否已验证电子邮箱',
   REAL_NAME            varchar(100)  comment '用户实名',
   IS_VERIFIED_REALNAME bool default false  comment '是否已验证实名',
   ID_CARD              char(18)  comment '身份证号',
   IS_VERIFIED_IDCARD   bool default false  comment '是否已验证身份证号',
   SEX                  tinyint unsigned  comment '性别',
   CREATER_TIMESTAMP    bigint unsigned not null  comment '建立时间戳',
   UPDATE_TIMESTAMP     bigint unsigned not null  comment '修改时间戳',
   primary key (ID),
   key AK_MOBILE (MOBILE),
   key AK_EMAIL (EMAIL),
   key AK_ID_CARD (ID_CARD)
);

alter table RAC_PERSON comment '个人';

/*==============================================================*/
/* Table: RAC_ROLE                                              */
/*==============================================================*/
create table RAC_ROLE
(
   ID                   bigint unsigned not null  comment '角色ID',
   NAME                 varchar(20) not null  comment '角色名称',
   DOMAIN_ID            varchar(32) not null  comment '领域ID',
   IS_ENABLED           bool not null default true  comment '是否启用',
   ORDER_NO             tinyint unsigned not null  comment '顺序号',
   REMARK               varchar(50)  comment '角色备注',
   primary key (ID),
   unique key AK_SYS_AND_ROLE_NAME (NAME)
);

alter table RAC_ROLE comment '角色';

/*==============================================================*/
/* Table: RAC_ROLE_PERM                                         */
/*==============================================================*/
create table RAC_ROLE_PERM
(
   ID                   bigint unsigned not null  comment '角色权限ID',
   ROLE_ID              bigint unsigned not null  comment '角色ID',
   PERM_ID              bigint unsigned not null  comment '权限ID',
   primary key (ID),
   unique key AK_ROLE_AND_PERM (ROLE_ID, PERM_ID)
);

alter table RAC_ROLE_PERM comment '角色权限';

/*==============================================================*/
/* Table: RAC_SYS                                               */
/*==============================================================*/
create table RAC_SYS
(
   ID                   varchar(32) not null  comment '系统ID',
   NAME                 varchar(20) not null  comment '系统名称',
   DOMAIN_ID            varchar(32) not null  comment '领域ID',
   INDEX_PATH           varchar(70)  comment '索引路径',
   REMARK               varchar(50)  comment '系统备注',
   primary key (ID),
   unique key AK_SYS_NAME (NAME)
);

alter table RAC_SYS comment '系统';

/*==============================================================*/
/* Table: RAC_USER                                              */
/*==============================================================*/
create table RAC_USER
(
   ID                   bigint unsigned not null  comment '用户ID',
   PERSON_ID            bigint unsigned  comment '个人ID',
   IS_ENABLED           bool not null default true  comment '是否启用',
   SIGN_IN_NAME         varchar(20)  comment '登录名称',
   SIGN_IN_MOBILE       varchar(11)  comment '登录手机',
   SIGN_IN_EMAIL        varchar(50)  comment '登录邮箱',
   SIGN_IN_PSWD         varchar(32)  comment '登录密码(小写(MD5(小写(MD5(密码明文))+小写(密码组合码))))
             注意：
             1. 计算方法中的密码在前端传过来时推荐先进行md5序列化，以避免在密码传递过程中使用明码被截获
             2. 密码组合码在生成密码时随机生成并保存下来，和密码组合起来使用，增加破解的难度',
   SIGN_IN_PSWD_SALT    char(6)  comment '登录密码组合码(与密码组合加密用，详见登录密码备注)',
   SIGN_IN_NICKNAME     varchar(20)  comment '登录用户昵称',
   SIGN_IN_AVATAR       varchar(300)  comment '登录用户头像',
   WX_OPEN_ID           varchar(64)  comment '微信的OpenId',
   WX_UNION_ID          varchar(64)  comment '微信的UnionId',
   WX_NICKNAME          varchar(100)  comment '微信昵称',
   WX_AVATAR            varchar(300)  comment '微信头像',
   QQ_OPEN_ID           varchar(64)  comment 'QQ的OpenId',
   QQ_UNION_ID          varchar(64)  comment 'QQ的UnionId',
   QQ_NICKNAME          varchar(100)  comment 'QQ昵称',
   QQ_AVATAR            varchar(300)  comment 'QQ头像',
   IS_TESTER            bool not null default false  comment '是否测试者',
   CREATE_TIMESTAMP     bigint unsigned not null  comment '建立时间戳',
   UPDATE_TIMESTAMP     bigint unsigned not null  comment '修改时间戳',
   primary key (ID),
   unique key AK_LOGIN_NICKNAME (SIGN_IN_NICKNAME),
   unique key AK_LOGIN_NAME (SIGN_IN_NAME),
   unique key AK_LOGIN_MOBILE (SIGN_IN_MOBILE),
   unique key AK_LOGIN_EMAIL (SIGN_IN_EMAIL),
   unique key AK_WX_OPEN_ID (WX_OPEN_ID),
   unique key AK_WX_UNION_ID (WX_UNION_ID),
   unique key AK_QQ_OPEN_ID (QQ_OPEN_ID),
   unique key AK_QQ_UNION_ID (QQ_UNION_ID)
);

alter table RAC_USER comment '用户';

/*==============================================================*/
/* Table: RAC_USER_ROLE                                         */
/*==============================================================*/
create table RAC_USER_ROLE
(
   ID                   bigint unsigned not null  comment '用户角色ID',
   ROLE_ID              bigint unsigned not null  comment '角色ID',
   USER_ID              bigint unsigned not null  comment '用户ID',
   primary key (ID),
   unique key AK_USER_AND_ROLE (ROLE_ID, USER_ID)
);

alter table RAC_USER_ROLE comment '用户角色';

alter table RAC_DOMAIN_USER add constraint FK_DOMAIN_USER_AND_DOMAIN foreign key (DOMAIN_ID)
      references RAC_DOMAIN (ID) on delete restrict on update restrict;

alter table RAC_DOMAIN_USER add constraint FK_DOMAIN_USER_AND_USER foreign key (USER_ID)
      references RAC_USER (ID) on delete restrict on update restrict;

alter table RAC_LOCK_LOG add constraint FK_LOCK_LOG_AND_SYS foreign key (SYS_ID)
      references RAC_SYS (ID) on delete restrict on update restrict;

alter table RAC_LOCK_LOG add constraint FK_LOCK_LOG_AND_LOCK_USER foreign key (LOCK_USER_ID)
      references RAC_USER (ID) on delete restrict on update restrict;

alter table RAC_LOCK_LOG add constraint FK_LOCK_LOG_AND_LOCK_OP foreign key (LOCK_OP_ID)
      references RAC_USER (ID) on delete restrict on update restrict;

alter table RAC_LOCK_LOG add constraint FK_LOCK_LOG_AND_UNLOCK_OP foreign key (UNLOCK_OP_ID)
      references RAC_USER (ID) on delete restrict on update restrict;

alter table RAC_MENU add constraint FK_MENU_AND_SYS foreign key (SYS_ID)
      references RAC_SYS (ID) on delete restrict on update restrict;

alter table RAC_OP_LOG add constraint FK_OP_LOG_AND_USER foreign key (USER_ID)
      references RAC_USER (ID) on delete restrict on update restrict;

alter table RAC_OP_LOG add constraint FK_OP_LOG_AND_SYS foreign key (SYS_ID)
      references RAC_SYS (ID) on delete restrict on update restrict;

alter table RAC_ORG add constraint FK_ORG_AND_ORG foreign key (PARENT_ID)
      references RAC_ORG (ID) on delete restrict on update restrict;

alter table RAC_ORG add constraint FK_ORG_AND_DOMAIN foreign key (DOMAIN_ID)
      references RAC_DOMAIN (ID) on delete restrict on update restrict;

alter table RAC_ORG_USER add constraint FK_ORG_USER_AND_ORG foreign key (ORG_ID)
      references RAC_ORG (ID) on delete restrict on update restrict;

alter table RAC_ORG_USER add constraint FK_ORG_USER_AND_USER foreign key (USER_ID)
      references RAC_USER (ID) on delete restrict on update restrict;

alter table RAC_PERM add constraint FK_PERM_AND_PERM_GROUP foreign key (GROUP_ID)
      references RAC_PERM_GROUP (ID) on delete restrict on update restrict;

alter table RAC_PERM add constraint FK_PERM_AND_DOMAIN foreign key (DOMAIN_ID)
      references RAC_DOMAIN (ID) on delete restrict on update restrict;

alter table RAC_PERM_GROUP add constraint FK_PERM_GROUP_AND_DOMAIN foreign key (DOMAIN_ID)
      references RAC_DOMAIN (ID) on delete restrict on update restrict;

alter table RAC_PERM_MENU add constraint FK_PERM_MENU_AND_PERM foreign key (PERM_ID)
      references RAC_PERM (ID) on delete restrict on update restrict;

alter table RAC_PERM_MENU add constraint FK_PERM_MENU_AND_MENU foreign key (MENU_ID)
      references RAC_MENU (ID) on delete restrict on update restrict;

alter table RAC_PERM_URN add constraint FK_PERM_URN_AND_PERM foreign key (PERM_ID)
      references RAC_PERM (ID) on delete restrict on update restrict;

alter table RAC_ROLE add constraint FK_ROLE_AND_DOMAIN foreign key (DOMAIN_ID)
      references RAC_DOMAIN (ID) on delete restrict on update restrict;

alter table RAC_ROLE_PERM add constraint FK_ROLE_PERM_AND_PERM foreign key (PERM_ID)
      references RAC_PERM (ID) on delete restrict on update restrict;

alter table RAC_ROLE_PERM add constraint FK_ROLE_PERM_AND_ROLE foreign key (ROLE_ID)
      references RAC_ROLE (ID) on delete restrict on update restrict;

alter table RAC_SYS add constraint FK_SYS_AND_DOMAIN foreign key (DOMAIN_ID)
      references RAC_DOMAIN (ID) on delete restrict on update restrict;

alter table RAC_USER add constraint FK_USER_AND_PERSON foreign key (PERSON_ID)
      references RAC_PERSON (ID) on delete restrict on update restrict;

alter table RAC_USER_ROLE add constraint FK_USER_ROLE_AND_ROLE foreign key (ROLE_ID)
      references RAC_ROLE (ID) on delete restrict on update restrict;

alter table RAC_USER_ROLE add constraint FK_USER_ROLE_AND_USER foreign key (USER_ID)
      references RAC_USER (ID) on delete restrict on update restrict;

