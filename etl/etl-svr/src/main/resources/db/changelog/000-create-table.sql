/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/9/29 11:24:55                           */
/*==============================================================*/


/*==============================================================*/
/* Table: ETL_CONN                                              */
/*==============================================================*/
DROP TABLE IF EXISTS `ETL_SYNC_STRATEGY_DETAIL`;
DROP TABLE IF EXISTS `ETL_SYNC_STRATEGY`;
DROP TABLE IF EXISTS `ETL_CONN`;
create table ETL_CONN
(
   ID                   bigint unsigned not null  comment '连接器ID',
   NAME                 varchar(32) not null  comment '数据库连接器名称',
   DB_TYPE              tinyint unsigned not null  comment '数据库类型',
   DB_NAME              varchar(32) not null  comment '数据库名称',
   HOST                 varchar(32) not null  comment '主机名称',
   PORT                 smallint unsigned not null  comment '端口号',
   USER_NAME            varchar(32) not null  comment '用户名称',
   USER_PSWD            varchar(32) not null  comment '用户密码',
   REMARK               varchar(32)  comment '源备注',
   primary key (ID),
   unique key AK_SOURCE_NAME (NAME)
);

alter table ETL_CONN comment '数据库连接器';

/*==============================================================*/
/* Table: ETL_SYNC_STRATEGY                                     */
/*==============================================================*/
create table ETL_SYNC_STRATEGY
(
   ID                   bigint unsigned not null  comment '策略ID',
   NAME                 varchar(32) not null  comment '策略名称',
   IS_ENABLED           bool not null default true  comment '是否启用',
   SRC_CONN_ID          bigint unsigned not null  comment '来源的连接器ID',
   SRC_NAME             varchar(32) not null  comment '来源名称',
   DST_CONN_ID          bigint unsigned not null  comment '目的的连接器ID',
   DST_NAME             varchar(32) not null  comment '目的名称',
   REMARK               varchar(32)  comment '策略备注',
   primary key (ID),
   unique key AK_STRATEGY_NAME (NAME)
);

alter table ETL_SYNC_STRATEGY comment '同步策略';

/*==============================================================*/
/* Table: ETL_SYNC_STRATEGY_DETAIL                              */
/*==============================================================*/
create table ETL_SYNC_STRATEGY_DETAIL
(
   ID                   bigint unsigned not null  comment '策略详情ID',
   STRATEGY_ID          bigint unsigned  comment '策略ID',
   SRC_TABLE_NAME       varchar(32)  comment '来源表名称',
   SRC_FIELD_NAME       varchar(32) not null  comment '来源字段名称',
   SRC_FIELD_TYPE       varchar(32) not null  comment '来源字段类型',
   SRC_FIELD_LENGTH     tinyint unsigned  comment '来源字段长度',
   SRC_FIELD_PRECISION  tinyint unsigned  comment '来源字段精度',
   DST_TABLE_NAME       varchar(32) not null  comment '目的表名称',
   DST_FIELD_NAME       varchar(32) not null  comment '目的字段名称',
   DST_FIELD_TYPE       varchar(32) not null  comment '目的字段类型',
   DST_FIELD_LENGTH     tinyint unsigned  comment '目的字段长度',
   DST_FIELD_PRECISION  tinyint unsigned  comment '目的字段精度',
   primary key (ID),
   unique key AK_SRC_FIELD_AND_DST_FIELD (STRATEGY_ID, SRC_TABLE_NAME, SRC_FIELD_NAME, DST_TABLE_NAME, DST_FIELD_NAME)
);

alter table ETL_SYNC_STRATEGY_DETAIL comment '同步策略详情';

alter table ETL_SYNC_STRATEGY add constraint FK_SYNC_STRATEGY_AND_SRC_CONN foreign key (SRC_CONN_ID)
      references ETL_CONN (ID) on delete restrict on update restrict;

alter table ETL_SYNC_STRATEGY add constraint FK_SYNC_STRATEGY_AND_DST_CONN foreign key (DST_CONN_ID)
      references ETL_CONN (ID) on delete restrict on update restrict;

alter table ETL_SYNC_STRATEGY_DETAIL add constraint FK_SYNC_STRATEGY_DETAIL_AND_SYNC_STRATEGY foreign key (STRATEGY_ID)
      references ETL_SYNC_STRATEGY (ID) on delete restrict on update restrict;

