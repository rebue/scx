/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/6/8 10:29:28                            */
/*==============================================================*/


/*==============================================================*/
/* Table: RAC_LOGIN_LOG                                         */
/*==============================================================*/
create table RAC_LOGIN_LOG
(
   ID                   bigint unsigned not null  comment '用户登录日志ID',
   USER_ID              bigint unsigned not null  comment '用户ID(如为1则是散客)',
   SYS_ID               varchar(32) not null  comment '系统ID',
   LOGIN_WAY            varchar(32) not null  comment '登录类型(与注册类型一致)
             LOGIN_NAME: 登录名与密码
             EMAIL_PASSWORD: 电子邮箱与密码
             MOBILE_PASSWORD: 手机号与密码
             MOBILE_SMS. 手机短信验证
             WECHAT_OFFICIAL_ACCOUNTS: 微信公众号',
   LOGIN_TIME           datetime not null  comment '登录时间',
   primary key (ID)
);

alter table RAC_LOGIN_LOG comment '用户登录日志';

/*==============================================================*/
/* Table: RAC_MENU                                              */
/*==============================================================*/
create table RAC_MENU
(
   ID                   varchar(32) not null  comment '菜单ID',
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

alter table RAC_MENU comment '菜单信息';

/*==============================================================*/
/* Table: RAC_OP_LOG                                            */
/*==============================================================*/
create table RAC_OP_LOG
(
   ID                   bigint unsigned not null  comment '用户操作日志ID',
   USER_ID              bigint unsigned not null  comment '用户ID(如为1则是散客)',
   SYS_ID               varchar(32) not null  comment '系统ID',
   OP_TITLE             varchar(32) not null  comment '操作标题',
   OP_DETAIL            varchar(300) not null  comment '操作详情',
   OP_TIME              datetime not null  comment '操作时间',
   primary key (ID)
);

alter table RAC_OP_LOG comment '用户操作日志';

/*==============================================================*/
/* Table: RAC_ORG                                               */
/*==============================================================*/
create table RAC_ORG
(
   ID                   bigint unsigned not null  comment '组织ID(组织ID=账户ID，与账户一一对应)',
   NAME                 varchar(30) not null  comment '组织名称(简称)',
   PARENT_ID            bigint unsigned  comment '上级组织ID',
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

alter table RAC_ORG comment '组织信息';

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
   ID                   varchar(32) not null  comment '权限ID',
   GROUP_ID             varchar(32) not null  comment '权限分组ID',
   SYS_ID               varchar(32) not null  comment '系统ID',
   NAME                 varchar(20) not null  comment '权限名称',
   IS_AUTHORIZE         bool not null default false  comment '是否鉴权(不鉴权意味着放开访问权限)',
   IS_ENABLED           bool not null default true  comment '是否启用',
   ORDER_NO             tinyint not null  comment '顺序号',
   REMARK               varchar(50)  comment '权限备注',
   primary key (ID),
   unique key AK_PERM_GROUP_AND_PERM_NAME (GROUP_ID, NAME)
);

alter table RAC_PERM comment '权限信息';

/*==============================================================*/
/* Table: RAC_PERM_GROUP                                        */
/*==============================================================*/
create table RAC_PERM_GROUP
(
   ID                   varchar(32) not null  comment '权限分组ID',
   SYS_ID               varchar(32) not null  comment '系统ID',
   NAME                 varchar(20) not null  comment '权限分组名称',
   IS_ENABLED           bool not null default true  comment '是否启用(如果分组没有启用，其下所有权限都要被设置为不启用；只要有一个权限启用，其分组就必须启用)',
   ORDER_NO             tinyint unsigned not null  comment '顺序号',
   REMARK               varchar(50)  comment '权限分组备注',
   primary key (ID),
   unique key AK_SYS_AND_GROUP_NAME (SYS_ID, NAME)
);

alter table RAC_PERM_GROUP comment '权限分组';

/*==============================================================*/
/* Table: RAC_PERM_MENU                                         */
/*==============================================================*/
create table RAC_PERM_MENU
(
   ID                   bigint unsigned not null  comment '权限菜单ID',
   PERM_ID              varchar(32) not null  comment '权限ID',
   MENU_ID              varchar(32) not null  comment '菜单ID',
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
   PERM_ID              varchar(32) not null  comment '权限ID',
   URN                  varchar(100) not null  comment 'URN',
   primary key (ID),
   unique key AK_PERM_AND_URN (PERM_ID, URN)
);

alter table RAC_PERM_URN comment '权限URN';

/*==============================================================*/
/* Table: RAC_ROLE                                              */
/*==============================================================*/
create table RAC_ROLE
(
   ID                   varchar(32) not null  comment '角色ID',
   SYS_ID               varchar(32) not null  comment '系统ID',
   NAME                 varchar(20) not null  comment '角色名称',
   HOME_PATH            varchar(70)  comment '首页路径',
   IS_ENABLED           bool not null default true  comment '是否启用',
   ORDER_NO             tinyint unsigned not null  comment '顺序号',
   REMARK               varchar(50)  comment '角色备注',
   primary key (ID),
   unique key AK_SYS_AND_ROLE_NAME (SYS_ID, NAME)
);

alter table RAC_ROLE comment '角色信息';

/*==============================================================*/
/* Table: RAC_ROLE_PERM                                         */
/*==============================================================*/
create table RAC_ROLE_PERM
(
   ID                   bigint unsigned not null  comment '角色权限ID',
   ROLE_ID              varchar(32) not null  comment '角色ID',
   PERM_ID              varchar(32) not null  comment '权限ID',
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
   REMARK               varchar(50)  comment '系统备注',
   primary key (ID),
   unique key AK_SYS_NAME (NAME)
);

alter table RAC_SYS comment '系统信息';

/*==============================================================*/
/* Table: RAC_SYS_USER                                          */
/*==============================================================*/
create table RAC_SYS_USER
(
   ID                   bigint not null  comment '系统用户ID',
   SYS_ID               varchar(32) not null  comment '系统ID',
   USER_ID              bigint unsigned not null  comment '用户ID(如为1则是散客)',
   primary key (ID),
   unique key AK_SYS_AND_USER (SYS_ID, USER_ID)
);

alter table RAC_SYS_USER comment '系统用户';

/*==============================================================*/
/* Table: RAC_USER                                              */
/*==============================================================*/
create table RAC_USER
(
   ID                   bigint unsigned not null  comment '用户ID(如为1则是散客)',
   NICKNAME             varchar(20)  comment '用户昵称',
   AVATAR               varchar(300)  comment '用户头像',
   LOGIN_NAME           varchar(20)  comment '登录名称',
   LOGIN_PSWD           varchar(32)  comment '登录密码',
   PAY_PSWD             varchar(32)  comment '支付密码
             用户的支付密码默认和登录密码一致
             保存在字段的计算方法如下：
             MD5(数据库存储的已加密的登陆密码)',
   SALT                 char(6)  comment '密码组合码
             与密码组合加密用
             登录密码=小写(MD5(小写(MD5(密码明文))+小写(密码组合码)))',
   MOBILE               varchar(11)  comment '手机',
   IS_VERIFIED_MOBILE   bool default false  comment '是否已验证手机号码',
   EMAIL                varchar(50)  comment '电子邮箱',
   IS_VERIFIED_EMAIL    bool default false  comment '是否已验证电子邮箱',
   WX_OPEN_ID           varchar(64)  comment '微信的OpenId',
   WX_UNION_ID          varchar(64)  comment '微信的UnionId',
   WX_NICKNAME          varchar(100)  comment '微信昵称',
   WX_AVATAR            varchar(300)  comment '微信头像',
   QQ_OPEN_ID           varchar(64)  comment 'QQ的OpenId',
   QQ_UNION_ID          varchar(64)  comment 'QQ的UnionId',
   QQ_NICKNAME          varchar(100)  comment 'QQ昵称',
   QQ_AVATAR            varchar(300)  comment 'QQ头像',
   REAL_NAME            varchar(100)  comment '用户实名',
   IS_VERIFIED_REALNAME bool default false  comment '是否已验证实名',
   ID_CARD              char(18)  comment '身份证号',
   IS_VERIFIED_IDCARD   bool default false  comment '是否已验证身份证号',
   SEX                  tinyint unsigned  comment '性别',
   AGE                  tinyint unsigned  comment '年龄',
   IS_TESTER            bool not null default false  comment '是否测试者',
   IS_ENABLED           bool not null default true  comment '是否启用',
   MODIFIED_TIMESTAMP   bigint unsigned not null  comment '修改时间戳',
   primary key (ID),
   unique key AK_NICKNAME (NICKNAME),
   unique key AK_LOGIN_NAME (LOGIN_NAME),
   unique key AK_MOBILE (MOBILE),
   unique key AK_EMAIL (EMAIL),
   unique key AK_WX_OPEN_ID (WX_OPEN_ID),
   unique key AK_WX_UNION_ID (WX_UNION_ID),
   unique key AK_QQ_OPEN_ID (QQ_OPEN_ID),
   unique key AK_QQ_UNION_ID (QQ_UNION_ID),
   unique key AK_ID_CARD (ID_CARD)
);

alter table RAC_USER comment '用户信息';

/*==============================================================*/
/* Table: RAC_USER_ROLE                                         */
/*==============================================================*/
create table RAC_USER_ROLE
(
   ID                   bigint unsigned not null  comment '用户角色ID',
   SYS_ID               varchar(20) not null  comment '系统ID',
   ROLE_ID              varchar(32) not null  comment '角色ID',
   USER_ID              bigint unsigned not null  comment '用户ID(如为1则是散客)',
   primary key (ID),
   unique key AK_USER_AND_ROLE (ROLE_ID, USER_ID)
);

alter table RAC_USER_ROLE comment '用户角色';

alter table RAC_LOGIN_LOG add constraint FK_LOGIN_LOG_AND_USER foreign key (USER_ID)
      references RAC_USER (ID) on delete restrict on update restrict;

alter table RAC_LOGIN_LOG add constraint FK_LOGIN_LOG_AND_SYS foreign key (SYS_ID)
      references RAC_SYS (ID) on delete restrict on update restrict;

alter table RAC_MENU add constraint FK_MENU_AND_SYS foreign key (SYS_ID)
      references RAC_SYS (ID) on delete restrict on update restrict;

alter table RAC_OP_LOG add constraint FK_OP_LOG_AND_USER foreign key (USER_ID)
      references RAC_USER (ID) on delete restrict on update restrict;

alter table RAC_OP_LOG add constraint FK_OP_LOG_AND_SYS foreign key (SYS_ID)
      references RAC_SYS (ID) on delete restrict on update restrict;

alter table RAC_ORG add constraint FK_ORG_AND_ORG foreign key (PARENT_ID)
      references RAC_ORG (ID) on delete restrict on update restrict;

alter table RAC_ORG_USER add constraint FK_ORG_USER_AND_ORG foreign key (ORG_ID)
      references RAC_ORG (ID) on delete restrict on update restrict;

alter table RAC_ORG_USER add constraint FK_ORG_USER_AND_USER foreign key (USER_ID)
      references RAC_USER (ID) on delete restrict on update restrict;

alter table RAC_PERM add constraint FK_PERM_AND_PERM_GROUP foreign key (GROUP_ID)
      references RAC_PERM_GROUP (ID) on delete restrict on update restrict;

alter table RAC_PERM add constraint FK_PERM_AND_SYS foreign key (SYS_ID)
      references RAC_SYS (ID) on delete restrict on update restrict;

alter table RAC_PERM_GROUP add constraint FK_PERM_GROUP_AND_SYS foreign key (SYS_ID)
      references RAC_SYS (ID) on delete restrict on update restrict;

alter table RAC_PERM_MENU add constraint FK_PERM_MENU_AND_PERM foreign key (PERM_ID)
      references RAC_PERM (ID) on delete restrict on update restrict;

alter table RAC_PERM_MENU add constraint FK_PERM_MENU_AND_MENU foreign key (MENU_ID)
      references RAC_MENU (ID) on delete restrict on update restrict;

alter table RAC_PERM_URN add constraint FK_PERM_URN_AND_PERM foreign key (PERM_ID)
      references RAC_PERM (ID) on delete restrict on update restrict;

alter table RAC_ROLE add constraint FK_ROLE_AND_SYS foreign key (SYS_ID)
      references RAC_SYS (ID) on delete restrict on update restrict;

alter table RAC_ROLE_PERM add constraint FK_ROLE_PERM_AND_PERM foreign key (PERM_ID)
      references RAC_PERM (ID) on delete restrict on update restrict;

alter table RAC_ROLE_PERM add constraint FK_ROLE_PERM_AND_ROLE foreign key (ROLE_ID)
      references RAC_ROLE (ID) on delete restrict on update restrict;

alter table RAC_SYS_USER add constraint FK_SYS_USER_AND_SYS foreign key (SYS_ID)
      references RAC_SYS (ID) on delete restrict on update restrict;

alter table RAC_SYS_USER add constraint FK_SYS_USER_AND_USER foreign key (USER_ID)
      references RAC_USER (ID) on delete restrict on update restrict;

alter table RAC_USER_ROLE add constraint FK_USER_ROLE_AND_ROLE foreign key (ROLE_ID)
      references RAC_ROLE (ID) on delete restrict on update restrict;

alter table RAC_USER_ROLE add constraint FK_USER_ROLE_AND_USER foreign key (USER_ID)
      references RAC_USER (ID) on delete restrict on update restrict;

