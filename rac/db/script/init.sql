-- 领域
INSERT INTO RAC_DOMAIN(ID, NAME, REMARK) VALUES ('default', '默认领域', '系统和账户的默认领域');
INSERT INTO RAC_DOMAIN(ID, NAME, REMARK) VALUES ('platform', '平台领域', '平台管理领域');
INSERT INTO RAC_DOMAIN(ID, NAME, REMARK) VALUES ('ops', '运营领域', '运营管理领域');
-- 系统
INSERT INTO RAC_SYS (ID, NAME, DOMAIN_ID,MENU,REMARK) VALUES 
('platform-admin-web', '平台后台管理', 'platform',
'[{"key":"/base","title":"基础","children":[{"key":"/base/rac-domain","title":"领域"},{"key":"/base/rac-sys","title":"系统"},{"key":"/base/rac-perm","title":"权限"},{"key":"/base/rac-role","title":"角色"},{"key":"/base/rac-dic","title":"字典"}]},{"key":"/account","title":"账户","children":[{"key":"/account/rac-org","title":"组织"},{"key":"/account/rac-account","title":"账户"}]},{"key":"/log","title":"日志","children":[{"key":"/log/lock-log","title":"锁定日志"},{"key":"/log/op-log","title":"操作日志"}]}]'
,'对平台的后台管理提供最基本的功能');
INSERT INTO RAC_SYS (ID, NAME, DOMAIN_ID,REMARK) VALUES 
('ops-admin-web', '运营后台管理', 'ops','对运营的后台管理提供最基本的功能');

-- 空账号
INSERT INTO RAC_ACCOUNT (ID,DOMAIN_ID,SIGN_IN_NAME,SIGN_IN_PSWD,SIGN_IN_PSWD_SALT,SIGN_IN_NICKNAME,CREATE_TIMESTAMP,UPDATE_TIMESTAMP)VALUES(0,'platform','null','','','空',UNIX_TIMESTAMP(SYSDATE()) * 1000,UNIX_TIMESTAMP(SYSDATE()) * 1000);
-- 系统账号
INSERT INTO RAC_ACCOUNT (ID,DOMAIN_ID,SIGN_IN_NAME,SIGN_IN_PSWD,SIGN_IN_PSWD_SALT,SIGN_IN_NICKNAME,CREATE_TIMESTAMP,UPDATE_TIMESTAMP)VALUES(1,'platform','system','','','系统',UNIX_TIMESTAMP(SYSDATE()) * 1000,UNIX_TIMESTAMP(SYSDATE()) * 1000);
-- 平台管理员
INSERT INTO RAC_ACCOUNT (ID,DOMAIN_ID,SIGN_IN_NAME,SIGN_IN_PSWD,SIGN_IN_PSWD_SALT,SIGN_IN_NICKNAME,SIGN_IN_AVATAR,CREATE_TIMESTAMP,UPDATE_TIMESTAMP)VALUES(10,'platform','super','05eeac84965236b8a479c3ebef3a2dc4','dX3jbg','平台管理员','/img/account/tiger.jpg',UNIX_TIMESTAMP(SYSDATE()) * 1000,UNIX_TIMESTAMP(SYSDATE()) * 1000);
-- 运营管理员
INSERT INTO RAC_ACCOUNT (ID,DOMAIN_ID,SIGN_IN_NAME,SIGN_IN_PSWD,SIGN_IN_PSWD_SALT,SIGN_IN_NICKNAME,SIGN_IN_AVATAR,CREATE_TIMESTAMP,UPDATE_TIMESTAMP)VALUES(20,'ops','admin','28bac2dc3862a24b376314014d8ef920','zcSeWA','运营管理员','/img/account/9527.jpg',UNIX_TIMESTAMP(SYSDATE()) * 1000,UNIX_TIMESTAMP(SYSDATE()) * 1000);

-- 角色
INSERT INTO RAC_ROLE(ID, NAME, DOMAIN_ID, IS_ENABLED, SEQ_NO, REMARK)VALUES(1,'平台管理员','platform',true,0,'平台管理员');
INSERT INTO RAC_ACCOUNT_ROLE(ID, ROLE_ID, ACCOUNT_ID)VALUES(1,1,10);
INSERT INTO RAC_ROLE(ID, NAME, DOMAIN_ID, IS_ENABLED, SEQ_NO, REMARK)VALUES(2,'运营管理员','ops',true,0,'运营管理员');
INSERT INTO RAC_ACCOUNT_ROLE(ID, ROLE_ID, ACCOUNT_ID)VALUES(2,2,20);
-- 权限
INSERT INTO RAC_PERM_GROUP(ID, DOMAIN_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1,'platform','领域',true,0,'领域');
INSERT INTO RAC_PERM_GROUP(ID, DOMAIN_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(2,'platform','系统',true,1,'系统');
INSERT INTO RAC_PERM_GROUP(ID, DOMAIN_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(3,'platform','权限',true,2,'权限');
INSERT INTO RAC_PERM_GROUP(ID, DOMAIN_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(4,'platform','角色',true,3,'角色');
INSERT INTO RAC_PERM_GROUP(ID, DOMAIN_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(5,'platform','组织',true,4,'组织');
INSERT INTO RAC_PERM_GROUP(ID, DOMAIN_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(6,'platform','账户',true,5,'账户');
INSERT INTO RAC_PERM_GROUP(ID, DOMAIN_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(7,'platform','日志',true,6,'日志');
INSERT INTO RAC_PERM_GROUP(ID, DOMAIN_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(8,'platform','字典',true,7,'日志');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1001,'platform',1,'管理领域',true,0,'拥有管理领域的权限');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1002,'platform',1,'查看领域',true,1,'拥有查看领域的权限');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1003,'platform',2,'管理系统',true,0,'拥有管理系统的权限');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1004,'platform',2,'查看系统',true,1,'拥有查看系统的权限');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1005,'platform',3,'管理权限',true,0,'拥有管理权限的权限');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1006,'platform',3,'查看权限',true,1,'拥有查看权限的权限');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1007,'platform',4,'管理角色',true,0,'拥有管理角色的权限');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1008,'platform',4,'查看角色',true,1,'拥有查看角色的权限');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1009,'platform',5,'管理组织',true,0,'拥有管理组织的权限');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1010,'platform',5,'查看组织',true,1,'拥有查看组织的权限');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1011,'platform',6,'管理账户',true,0,'拥有管理账户的权限');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1012,'platform',6,'查看账户',true,1,'拥有查看账户的权限');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1013,'platform',7,'查看锁定日志',true,0,'拥有查看锁定日志的权限');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1014,'platform',7,'查看操作日志',true,1,'拥有查看操作日志权限');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1015,'platform',8,'管理字典',true,0,'拥有管理字典权限');
INSERT INTO RAC_PERM(ID, DOMAIN_ID, GROUP_ID, NAME, IS_ENABLED, SEQ_NO, REMARK)VALUES(1016,'platform',8,'查看字典',true,1,'拥有查看字典权限');
-- 角色权限
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(1,1,1001);
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(2,1,1002);
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(3,1,1003);
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(4,1,1004);
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(5,1,1005);
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(6,1,1006);
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(7,1,1007);
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(8,1,1008);
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(9,1,1009);
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(10,1,1010);
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(11,1,1011);
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(12,1,1012);
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(13,1,1013);
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(14,1,1014);
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(15,1,1015);
INSERT INTO RAC_ROLE_PERM(ID, ROLE_ID, PERM_ID)VALUES(16,1,1016);
-- 菜单
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(1,'platform-admin-web',1001,'/base/rac-domain');
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(2,'platform-admin-web',1002,'/base/rac-domain');
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(3,'platform-admin-web',1003,'/base/rac-sys');
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(4,'platform-admin-web',1004,'/base/rac-sys');
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(5,'platform-admin-web',1005,'/base/rac-perm');
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(6,'platform-admin-web',1006,'/base/rac-perm');
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(7,'platform-admin-web',1007,'/base/rac-role');
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(8,'platform-admin-web',1008,'/base/rac-role');
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(9,'platform-admin-web',1009,'/account/rac-org');
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(10,'platform-admin-web',1010,'/account/rac-org');
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(11,'platform-admin-web',1011,'/account/rac-account');
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(12,'platform-admin-web',1012,'/account/rac-account');
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(13,'platform-admin-web',1013,'/log/lock-log');
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(14,'platform-admin-web',1014,'/log/op-log');
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(15,'platform-admin-web',1015,'/base/rac-dic');
INSERT INTO RAC_PERM_MENU(ID, SYS_ID, PERM_ID, MENU_URN)VALUES(16,'platform-admin-web',1016,'/base/rac-dic');

-- URL
INSERT INTO `RAC_PERM_URN` VALUES 
(875980015911698436,1001,'*:/rac/domain/**'),
(875986330897743872,1002,'GET:/rac/domain/**'),
(875980164188733445,1003,'*:/rac/sys/**'),
(875986401345273857,1004,'GET:/rac/sys/**'),
(875978616129847296,1005,'*:/rac/perm-group/**'),
(875978616280842241,1005,'*:/rac/perm-menu/**'),
(875978616339562498,1005,'*:/rac/perm-urn/**'),
(875978616398282755,1005,'*:/rac/perm/**'),
(875986559676055554,1006,'GET:/rac/perm-group/**'),
(875986559768330243,1006,'GET:/rac/perm-menu/**'),
(875986559818661892,1006,'GET:/rac/perm-urn/**'),
(875986559860604933,1006,'GET:/rac/perm/**'),
(875980626589777926,1007,'*:/rac/role/**'),
(875986649526435846,1008,'GET:/rac/role/**'),
(875982137797509127,1009,'*:/rac/org/**'),
(875986920314896391,1010,'GET:/rac/org/**'),
(875982622923292680,1011,'*:/rac/account/**'),
(875986997913714696,1012,'GET:/rac/account/**'),
(875987052754239497,1013,'GET:/rac/lock-log/**'),
(875987086598078474,1014,'GET:/rac/op-log/**'),
(878551998646059009,1015,'*:/rac/dic-item/**'),
(878551998574755840,1015,'*:/rac/dic/**'),
(878551998692196354,1015,'GET:/rac/domain/list-all'),
(878551998717362179,1015,'GET:/rac/sys/list'),
(878552197858721796,1016,'GET:/rac/dic-item/**'),
(878552197917442053,1016,'GET:/rac/dic/**'),
(878552197976162310,1016,'GET:/rac/domain/list-all'),
(878552198039076871,1016,'GET:/rac/sys/list');
 -- 添加字典  
 INSERT INTO `RAC_DIC` VALUES 
 (883242188006359041,'考核状态','考核状态',NULL,NULL,'个人---考核状态'),
 (883243775281987586,'工作任务状态','工作任务状态',NULL,NULL,'个人---工作任务状态'),
 (883244623965847555,'计划完成状态','计划完成状态',NULL,NULL,'个人---计划完成状态'),
 (883245584088170500,'考核类型','考核类型',NULL,NULL,'考核类型'),
 (883246993894408197,'评语模板类型','评语模板类型',NULL,NULL,'评语模板类型'),
 (883247593726017542,'考核上报表状态','考核上报表状态',NULL,NULL,'考核上报表状态'),
 (883248418087108615,'考核上报表锁定','考核上报表锁定',NULL,NULL,'考核上报表锁定'),
 (883249355207868424,'单位归口','单位归口',NULL,NULL,'单位归口'),
 (884603623693221888,'职级设定','职级设定',NULL,NULL,'职级设定');
 -- 添加字典项
INSERT INTO `RAC_DIC_ITEM` VALUES 
(883242296731107330,883242188006359041,NULL,'0','未完成','000','考核状态---未完成'),
(883242576625401859,883242188006359041,NULL,'1','已阅读','001','考核状态---已阅读'),
(883242832696049668,883242188006359041,NULL,'2','已自评','002','考核状态---已自评'),
(883243202000322565,883242188006359041,NULL,'3','评鉴中','003','考核状态---评鉴中'),
(883243324574662662,883242188006359041,NULL,'4','评鉴完成','004','考核状态---评鉴完成'),
(883243895012589575,883243775281987586,NULL,'0','未读','000','工作任务状态---未读'),
(883244084649656328,883243775281987586,NULL,'1','已读','001','工作任务状态---已读'),
(883244150101770249,883243775281987586,NULL,'2','完成','002','工作任务状态---完成'),
(883244943743778826,883244623965847555,NULL,'0','未完成','000','计划完成状态---计划完成状态'),
(883245265459478539,883244623965847555,NULL,'1','完成','001','计划完成状态---完成'),
(883245694356422668,883245584088170500,NULL,'0','平时考核','000','考核类型---平时考核'),
(883245809041276941,883245584088170500,NULL,'1','年度考核','001','考核类型---年度考核'),
(883246016910983182,883245584088170500,NULL,'2','专项考核','002','考核类型---专项考核'),
(883247097170755599,883246993894408197,NULL,'0','积极评价','000','评语模板类型---积极评价'),
(883247252150288400,883246993894408197,NULL,'1','一般评价','001','评语模板类型---一般评价'),
(883247868863971346,883247593726017542,NULL,'0','不上报，组织部不可见','000','考核上报表状态---不上报，组织部不可见'),
(883248021826043923,883247593726017542,NULL,'1','上报，组织部可见','001','考核上报表状态---上报组织部可见'),
(883248597506850836,883248418087108615,NULL,'0','未锁定','000','考核上报表锁定---未锁定'),
(883248681392930837,883248418087108615,NULL,'1','锁定','001','考核上报表锁定---锁定'),
(883249601287684118,883249355207868424,NULL,'0','党群口','000','单位归口---党群口'),
(883249678991360023,883249355207868424,NULL,'1','政府口','001','单位归口---政府口'),
(884605119285559296,884603623693221888,NULL,'书记员','书记员','000','职级设定---书记员'),
(884605542302089217,884603623693221888,NULL,'高级书记员','高级书记员','000000','高级书记员'),
(884606117202755587,884603623693221888,NULL,'普通书记员','普通书记员','000001','普通书记员'),
(884606184378728452,884603623693221888,NULL,'一级书记员','一级书记员','000001000','一级书记员'),
(884606316260229125,884603623693221888,NULL,'二级书记员','二级书记员','000001001','二级书记员'),
(884606388003799046,884603623693221888,NULL,'三级书记员','三级书记员','000001002','三级书记员'),
(884606483608764423,884603623693221888,NULL,'四级书记员','四级书记员','000001003','四级书记员'),
(884606547852918792,884603623693221888,NULL,'五级书记员','五级书记员','000001004','五级书记员'),
(884607261794762761,884603623693221888,NULL,'六级书记员','六级书记员','000001005','六级书记员'),
(884607456297222154,884603623693221888,NULL,'一级高级书记员','一级高级书记员','000000000','一级高级书记员'),
(884607526086246411,884603623693221888,NULL,'二级高级书记员','二级高级书记员','000000001','二级高级书记员'),
(884607599792750604,884603623693221888,NULL,'三级高级书记员','三级高级书记员','000000002','三级高级书记员'),
(884607753685958669,884603623693221888,NULL,'四级高级书记员','四级高级书记员','000000003','四级高级书记员'),
(884607812116807694,884603623693221888,NULL,'五级高级书记员','五级高级书记员','000000004','五级高级书记员'),
(884610300400238607,884603623693221888,NULL,'检察官、法官助理','检察官、法官助理','001','检察官、法官助理'),
(884610749618585616,884603623693221888,NULL,'普通检察官、法官助理','普通检察官、法官助理','001000','普通检察官、法官助理'),
(884610987557257233,884603623693221888,NULL,'一级检察官、法官助理','一级检察官、法官助理','001000000','一级检察官、法官助理'),
(884611041579892754,884603623693221888,NULL,'二级检察官、法官助理','二级检察官、法官助理','001000001','二级检察官、法官助理'),
(884611094906273811,884603623693221888,NULL,'三级检察官、法官助理','三级检察官、法官助理','001000002','三级检察官、法官助理'),
(884611163546058772,884603623693221888,NULL,'四级检察官、法官助理','四级检察官、法官助理','001000003','四级检察官、法官助理'),
(884611326339579925,884603623693221888,NULL,'五级检察官、法官助理','五级检察官、法官助理','001000004','五级检察官、法官助理'),
(884611498452844566,884603623693221888,NULL,'高级检察官、法官助理','高级检察官、法官助理','001006','高级检察官、法官助理'),
(884611556741087255,884603623693221888,NULL,'一级高级检察官、法官助理','一级高级检察官、法官助理','001006000','一级高级检察官、法官助理'),
(884611683295821848,884603623693221888,NULL,'二级高级检察官、法官助理','二级高级检察官、法官助理','001006001','二级高级检察官、法官助理'),
(884611751977549849,884603623693221888,NULL,'三级高级检察官、法官助理','三级高级检察官、法官助理','001006002','三级高级检察官、法官助理'),
(884611859406258202,884603623693221888,NULL,'四级高级检察官、法官助理','四级高级检察官、法官助理','001006003','四级高级检察官、法官助理'),
(884612521938518043,884603623693221888,NULL,'特级检察官、法官助理','特级检察官、法官助理','001011','特级检察官、法官助理'),
(884612875728060445,884603623693221888,NULL,'检察官、法官','检察官、法官','002','检察官、法官'),
(884612931046735902,884603623693221888,NULL,'普通检察官、法官','普通检察官、法官','002000','普通检察官、法官'),
(884613011602538527,884603623693221888,NULL,'一级检察官、法官','一级检察官、法官','002000000','一级检察官、法官'),
(884613078807871520,884603623693221888,NULL,'二级检察官、法官','二级检察官、法官','002000001','二级检察官、法官'),
(884613132230721569,884603623693221888,NULL,'三级检察官、法官','三级检察官、法官','002000002','三级检察官、法官'),
(884613407783911458,884603623693221888,NULL,'四级检察官、法官','四级检察官、法官','002000003','四级检察官、法官'),
(884613485470810147,884603623693221888,NULL,'五级检察官、法官','五级检察官、法官','002000004','五级检察官、法官'),
(884613764064870436,884603623693221888,NULL,'高级检察官、法官','高级检察官、法官','002006','高级检察官、法官'),
(884614940504227877,884603623693221888,NULL,'一级高级检察官、法官','一级高级检察官、法官','002006000','一级高级检察官、法官'),
(884615015469023270,884603623693221888,NULL,'二级高级检察官、法官','二级高级检察官、法官','002006001','二级高级检察官、法官'),
(884615069139337255,884603623693221888,NULL,'三级高级检察官、法官','三级高级检察官、法官','002006002','三级高级检察官、法官'),
(884615133412851752,884603623693221888,NULL,'四级高级检察官、法官','四级高级检察官、法官','002006003','四级高级检察官、法官'),
(884615395833675817,884603623693221888,NULL,'特级检察官、法官','特级检察官、法官','002011','特级检察官、法官'),
(884615992733466666,884603623693221888,NULL,'执法勤务警员','执法勤务警员','003','执法勤务警员'),
(884616065290731563,884603623693221888,NULL,'警员','警员','003000','警员'),
(884616135335608364,884603623693221888,NULL,'一级警员','一级警员','003000000','一级警员'),
(884616188829761581,884603623693221888,NULL,'二级警员','二级警员','003000001','二级警员'),
(884616294530416686,884603623693221888,NULL,'警长','警长','003003','警长'),
(884616353074511919,884603623693221888,NULL,'一级警长','一级警长','003003000','一级警长'),
(884616412696543280,884603623693221888,NULL,'二级警长','二级警长','003003001','二级警长'),
(884616488374370353,884603623693221888,NULL,'三级警长','三级警长','003003002','三级警长'),
(884616539456798770,884603623693221888,NULL,'四级警长','四级警长','003003003','四级警长'),
(884616708403363891,884603623693221888,NULL,'高级警长','高级警长','003008','高级警长'),
(884616770298708020,884603623693221888,NULL,'一级高级警长','一级高级警长','003008000','一级高级警长'),
(884616818063441973,884603623693221888,NULL,'二级高级警长','二级高级警长','003008001','二级高级警长'),
(884616897046380598,884603623693221888,NULL,'三级高级警长','三级高级警长','003008002','三级高级警长'),
(884616951836573751,884603623693221888,NULL,'四级高级警长','四级高级警长','003008003','四级高级警长'),
(884617083579662392,884603623693221888,NULL,'警务专员','警务专员','003013','警务专员'),
(884617144397070393,884603623693221888,NULL,'一级警务专员','一级警务专员','003013000','一级警务专员'),
(884617191012565050,884603623693221888,NULL,'二级警务专员','二级警务专员','003013001','二级警务专员'),
(884617730026766395,884603623693221888,NULL,'警务技术','警务技术','004','警务技术'),
(884618864380477500,884603623693221888,NULL,'技术员','技术员','004000','技术员'),
(884619977083191358,884603623693221888,NULL,'主管','主管','004001','主管'),
(884620097086423104,884603623693221888,NULL,'一级主管','一级主管','004001000','一级主管'),
(884620156645539905,884603623693221888,NULL,'二级主管','二级主管','004001001','二级主管'),
(884620203911151682,884603623693221888,NULL,'三级主管','三级主管','004001002','三级主管'),
(884620264980217923,884603623693221888,NULL,'四级主管','四级主管','004001003','四级主管'),
(884620654769471557,884603623693221888,NULL,'主任','主任','004006','主任'),
(884620713837854790,884603623693221888,NULL,'一级主任','一级主任','004006000','一级主任'),
(884620807475691591,884603623693221888,NULL,'二级主任','二级主任','004006001','二级主任'),
(884620858528759880,884603623693221888,NULL,'三级主任','三级主任','004006002','三级主任'),
(884620909300809801,884603623693221888,NULL,'四级主任','四级主任','004006003','四级主任'),
(884620968432107594,884603623693221888,NULL,'总监','总监','004011','总监'),
(884621021603299403,884603623693221888,NULL,'一级总监','一级总监','004011000','一级总监'),
(884621109775958092,884603623693221888,NULL,'二级总监','二级总监','004011001','二级总监'),
(884666304693403648,884603623693221888,NULL,'专业技术类公务员','专业技术类公务员','005','专业技术类公务员'),
(884666831506374657,884603623693221888,NULL,'专业技术员','专业技术员','005000','专业技术员');

