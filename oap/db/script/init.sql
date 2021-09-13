-- rac 库 rac_app
INSERT INTO `rac`.`RAC_APP`(`ID`, `NAME`, `REALM_ID`, `URL`, `MENU`, `REMARK`, `IS_ENABLED`, `IMG_URL`, `SEQ_NO`, `IS_CERTIFIED`)
VALUES ('unified-auth', '统一认证平台', 'default', 'http://127.0.0.1/admin-web/#/app/index', NULL, '', 1, '', 0, 0);

-- oap 库 oap_app
-- app_id 要和上面新增rac_app的记录的id相同
INSERT INTO `oap`.`OAP_APP`(`ID`, `APP_ID`, `IS_ENABLED`, `CLIENT_ID`, `SECRET`, `OBJ_ID`, `CREATE_TIMESTAMP`, `UPDATE_TIMESTAMP`)
VALUES (1, 'unified-auth', 1, 'unified-auth', '123456', NULL, 1, 1);

-- rac 库 rac_account
-- 统一身份认证一个账户
INSERT INTO `rac`.`RAC_ACCOUNT`(`ID`, `USER_ID`, `REMARK`, `ORG_ID`, `REALM_ID`, `IS_ENABLED`, `SIGN_IN_NAME`, `SIGN_IN_MOBILE`, `SIGN_IN_EMAIL`, `SIGN_IN_PSWD`, `SIGN_IN_PSWD_SALT`, `PAY_PSWD`, `PAY_PSWD_SALT`, `SIGN_IN_NICKNAME`, `SIGN_IN_AVATAR`, `WX_OPEN_ID`, `WX_UNION_ID`, `WX_NICKNAME`, `WX_AVATAR`, `QQ_OPEN_ID`, `QQ_UNION_ID`, `QQ_NICKNAME`, `QQ_AVATAR`, `IS_TESTER`, `CREATE_TIMESTAMP`, `UPDATE_TIMESTAMP`) VALUES (909945462243459072, NULL, NULL, NULL, 'default', 1, 'adminer', NULL, NULL, '53682613730bd2a22336535b987360c6', 'Q5gGYE', NULL, NULL, 'adminer', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1630890031987, 1630890031987);

-- 第三方示例应用
INSERT INTO `rac`.`RAC_APP`(`ID`, `NAME`, `REALM_ID`, `URL`, `MENU`, `REMARK`, `IS_ENABLED`, `IMG_URL`, `SEQ_NO`, `IS_CERTIFIED`)
VALUES ('third-party-demo', '第三方实例应用', 'default', 'http://the-localhost:30010', NULL, NULL, 1, NULL, 0, 0);

-- 第三方示例应用
INSERT INTO `oap`.`OAP_APP`(`ID`, `APP_ID`, `IS_ENABLED`, `CLIENT_ID`, `SECRET`, `OBJ_ID`, `CREATE_TIMESTAMP`, `UPDATE_TIMESTAMP`)
VALUES (2, 'third-party-demo', 1, 'third-party-demo', '123456', NULL, 1, 1);
