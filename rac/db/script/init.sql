-- 领域
INSERT INTO RAC_DOMAIN(ID, NAME, REMARK) VALUES ('default', '默认领域', '系统和账户的默认领域');
INSERT INTO RAC_DOMAIN(ID, NAME, REMARK) VALUES ('platform', '平台领域', '平台管理领域');
INSERT INTO RAC_DOMAIN(ID, NAME, REMARK) VALUES ('ops', '运营领域', '运营管理领域');
-- 系统
INSERT INTO RAC_SYS (ID, NAME, DOMAIN_ID,MENU_URN,REMARK) VALUES ('platform-admin-web', '平台后台管理', 'platform','/','对平台的后台管理提供最基本的功能');
INSERT INTO RAC_SYS (ID, NAME, DOMAIN_ID,MENU_URN,REMARK) VALUES ('ops-admin-web', '运营后台管理', 'ops','/','对运营的后台管理提供最基本的功能');
-- 平台管理员
INSERT INTO RAC_ACCOUNT (ID, SIGN_IN_NAME,SIGN_IN_PSWD,SIGN_IN_PSWD_SALT, SIGN_IN_NICKNAME,CREATE_TIMESTAMP,UPDATE_TIMESTAMP)VALUES(1,'super','28bac2dc3862a24b376314014d8ef920','zcSeWA','平台管理员',UNIX_TIMESTAMP(SYSDATE()) * 1000,UNIX_TIMESTAMP(SYSDATE()) * 1000);
INSERT INTO RAC_DOMAIN_ACCOUNT(ID,DOMAIN_ID,ACCOUNT_ID)VALUES(1,'platform',1);
-- 运营管理员
INSERT INTO RAC_ACCOUNT (ID, SIGN_IN_NAME,SIGN_IN_PSWD,SIGN_IN_PSWD_SALT, SIGN_IN_NICKNAME,CREATE_TIMESTAMP,UPDATE_TIMESTAMP)VALUES(2,'admin','28bac2dc3862a24b376314014d8ef920','zcSeWA','运营管理员',UNIX_TIMESTAMP(SYSDATE()) * 1000,UNIX_TIMESTAMP(SYSDATE()) * 1000);
INSERT INTO RAC_DOMAIN_ACCOUNT(ID,DOMAIN_ID,ACCOUNT_ID)VALUES(2,'ops',2);
-- 角色
INSERT INTO RAC_ROLE(ID, NAME, DOMAIN_ID, IS_ENABLED, SEQ_NO, REMARK)VALUES(1,'平台管理员','platform',true,0,'平台管理员');
INSERT INTO RAC_ACCOUNT_ROLE(ID, ROLE_ID, ACCOUNT_ID)VALUES(1,1,1);
INSERT INTO RAC_ROLE(ID, NAME, DOMAIN_ID, IS_ENABLED, SEQ_NO, REMARK)VALUES(2,'运营管理员','ops',true,0,'运营管理员');
INSERT INTO RAC_ACCOUNT_ROLE(ID, ROLE_ID, ACCOUNT_ID)VALUES(2,2,2);
-- 权限
INSERT INTO RAC_PERM_GROUP(ID, DOMAIN_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1,'platform','领域',true,0,'领域');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1,'platform',1,'管理领域',true,0,'拥有管理领域的权限');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(2,'platform',1,'查看领域',true,1,'拥有查看领域的权限');
INSERT INTO RAC_PERM_GROUP(ID, DOMAIN_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(2,'platform','系统',true,1,'系统');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(3,'platform',1,'管理系统',true,0,'拥有管理系统的权限');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(4,'platform',1,'查看系统',true,1,'拥有查看系统的权限');
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(1,1,1);
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(2,1,2);
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(3,1,3);
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(4,1,4);
-- 菜单
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(1,'platform-admin-web',1,'/base/rac-domain');
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(2,'platform-admin-web',2,'/base/rac-domain');
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(3,'platform-admin-web',3,'/base/rac-sys');
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(4,'platform-admin-web',4,'/base/rac-sys');