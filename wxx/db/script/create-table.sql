/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/7/1 18:29:00                            */
/*==============================================================*/


alter table WXX_APP 
   drop foreign key FK_APP_AND_MCH;


alter table WXX_APP 
   drop foreign key FK_APP_AND_MCH;

drop table if exists WXX_APP;

drop table if exists WXX_MCH;

/*==============================================================*/
/* Table: WXX_APP                                               */
/*==============================================================*/
create table WXX_APP
(
   ID                   varchar(32) not null  comment '公众号的appid',
   NAME                 varchar(30) not null  comment 'APP名称',
   MCH_ID               varchar(32)  comment '商户号',
   APP_SECRET           varchar(50) not null  comment '公众号的app secret',
   TOKEN                varchar(50) not null  comment '公众号的token(微信公众号绑定网站的域名时，会通过此token进行校验)',
   ENCODEING_AES_KEY    varchar(50)  comment '微信加解密消息用的key',
   SUBSCRIBE_AUTO_REPLY varchar(100)  comment '用户关注后自动回复的文本',
   MENU                 varchar(1500)  comment '自定义菜单',
   LOGIN_CALLBACK_URL   varchar(300)  comment '登录回调链接',
   LOGIN_CALLBACK_METHOD_TYPE varchar(6)  comment '登录回调方法类型',
   LOGIN_CALLBACK_SIGNKEY varchar(64)  comment '登录回调签名密钥',
   WXPAY_NOTIFY_URL     varchar(250)  comment '微信支付完成通知的URL',
   primary key (ID)
);

/*==============================================================*/
/* Table: WXX_MCH                                               */
/*==============================================================*/
create table WXX_MCH
(
   ID                   varchar(32) not null  comment '商户号(MCH_ID)',
   NAME                 varchar(30) not null  comment '商户名称',
   API_KEY              varchar(50) not null  comment 'API密钥，签名用的key，在商户平台设置（微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置',
   primary key (ID)
);

alter table WXX_MCH comment '商户信息(微信支付账户信息)';

alter table WXX_APP add constraint FK_APP_AND_MCH foreign key (MCH_ID)
      references WXX_MCH (ID) on delete restrict on update restrict;

