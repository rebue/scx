INSERT INTO `RAC_REALM` VALUES ('default','默认领域','应用和账户的默认领域'),('ops','运营领域','运营管理领域')
,('platform','平台领域','平台管理领域');
INSERT INTO `RAC_APP` VALUES ('login-manage','登录','ops','http://www.abc.com/admin-web/#/app/index',NULL,'',1,'',1,0,0)

,('ops-admin-web','运营后台管理','ops',NULL,
'[{\"key\":\"/user\",\"title\":\"用户中心\",\"children\":[{\"key\":\"/user/ops-org\",\"title\":\"组织\"},
{\"key\":\"/user/ops-account\",\"title\":\"账户\"},{\"key\":\"/user/ops-user\",\"title\":\"用户\"},
{\"key\":\"/user/rac-account-unlock\",\"title\":\"解锁\"}]},{\"key\":\"/user-log\",\"title\":\"日志\",\"children\":
[{\"key\":\"/user-log/lock-log\",\"title\":\"账户解锁日志\"},{\"key\":\"/user-log/disable-log\",\"title\":
\"账户启/禁用日志\"},{\"key\":\"/user-log/op-log\",\"title\":\"操作日志\"}]}]','对运营的后台管理提供最基本的功能',1,NULL,0,0,0)

,('platform-admin-web','平台后台管理','platform',NULL,'[{\"key\":\"/base\",\"title\":\"基础\",\"children\":
[{\"key\":\"/base/rac-realm\",\"title\":\"领域\"},{\"key\":\"/base/rac-app\",\"title\":\"应用\"},{\"key\":
\"/base/rac-perm\",\"title\":\"权限\"},{\"key\":\"/base/rac-role\",\"title\":\"角色\"},{\"key\":
\"/base/rac-dic\",\"title\":\"字典\"}]},{\"key\":\"/account\",\"title\":\"账户\",\"children\":
[{\"key\":\"/account/rac-org\",\"title\":\"组织\"},{\"key\":\"/account/rac-account\",\"title\":\"账户\"},
{\"key\":\"/account/rac-user\",\"title\":\"用户\"},{\"key\":\"/account/rac-account-unlock\",\"title\":\"解锁\"}]},
{\"key\":\"/log\",\"title\":\"日志\",\"children\":[{\"key\":\"/log/lock-log\",\"title\":\"账户解锁日志\"},
{\"key\":\"/log/disable-log\",\"title\":\"账户启/禁用日志\"},
{\"key\":\"/log/op-log\",\"title\":\"操作日志\"}]}]','对平台的后台管理提供最基本的功能',1,NULL,0,0,0)

,('third-party-demo','第三方实例应用','default','http://the-localhost:30010',NULL,NULL,1,NULL,0,1,0),

('unified-auth','统一认证平台','default','http://127.0.0.1/admin-web/#/app/index',NULL,'',1,'',0,1,0);

INSERT INTO `RAC_PERM_GROUP` VALUES (1,'platform','领域',1,0,'领域')
,(2,'platform','应用',1,1,'应用')
,(3,'platform','权限',1,2,'权限')
,(4,'platform','角色',1,3,'角色')
,(5,'platform','组织',1,4,'组织')
,(6,'platform','账户',1,5,'账户')
,(7,'platform','日志',1,6,'日志')
,(8,'platform','字典',1,7,'日志')
,(886042488652038144,'ops','用户中心',1,0,'用户中心')
,(915768980965163008,'ops','日志中心',1,1,'日志中心');

INSERT INTO `RAC_PERM` VALUES (1001,'platform',1,'管理领域',1,0,'拥有管理领域的权限')
,(1002,'platform',1,'查看领域',1,1,'拥有查看领域的权限')
,(1003,'platform',2,'管理应用',1,0,'拥有管理应用的权限')
,(1004,'platform',2,'查看应用',1,1,'拥有查看应用的权限')
,(1005,'platform',3,'管理权限',1,0,'拥有管理权限的权限')
,(1006,'platform',3,'查看权限',1,1,'拥有查看权限的权限')
,(1007,'platform',4,'管理角色',1,0,'拥有管理角色的权限')
,(1008,'platform',4,'查看角色',1,1,'拥有查看角色的权限')
,(1009,'platform',5,'管理组织',1,0,'拥有管理组织的权限')
,(1010,'platform',5,'查看组织',1,1,'拥有查看组织的权限')
,(1011,'platform',6,'管理账户',1,0,'拥有管理账户的权限')
,(1012,'platform',6,'查看账户',1,1,'拥有查看账户的权限')
,(1013,'platform',7,'查看管理解锁日志',1,0,'拥有查看管理解锁日志的权限')
,(1014,'platform',7,'查看操作日志',1,1,'拥有查看操作日志权限')
,(1015,'platform',8,'管理字典',1,0,'拥有管理字典权限')
,(1016,'platform',8,'查看字典',1,1,'拥有查看字典权限')
,(886042515130679296,'ops',886042488652038144,'查看组织',1,0,'查看组织')
,(886046060617793537,'ops',886042488652038144,'管理组织',1,1,'管理组织')
,(886046205266755586,'ops',886042488652038144,'查看账户',1,3,'查看账户')
,(886047198507302915,'ops',886042488652038144,'管理账户',1,2,'管理账户')
,(915759029353971712,'platform',7,'查看账户禁用日志',1,2,'拥有查看账户禁用日志权限')
,(915769145721618433,'ops',915768980965163008,'查看管理解锁日志',1,0,'查看管理解锁日志')
,(915769255989870594,'ops',915768980965163008,'查看操作日志',1,1,'查看操作日志')
,(915769317612584963,'ops',915768980965163008,'查看账户禁用日志',1,2,'查看账户禁用日志');

INSERT INTO `RAC_PERM_MENU` VALUES (1,'platform-admin-web',1001,'/base/rac-realm')
,(2,'platform-admin-web',1002,'/base/rac-realm')
,(3,'platform-admin-web',1003,'/base/rac-app')
,(4,'platform-admin-web',1004,'/base/rac-app')
,(5,'platform-admin-web',1005,'/base/rac-perm')
,(6,'platform-admin-web',1006,'/base/rac-perm')
,(7,'platform-admin-web',1007,'/base/rac-role')
,(8,'platform-admin-web',1008,'/base/rac-role')
,(9,'platform-admin-web',1009,'/account/rac-org')
,(10,'platform-admin-web',1010,'/account/rac-org')
,(15,'platform-admin-web',1015,'/base/rac-dic')
,(16,'platform-admin-web',1016,'/base/rac-dic')
,(886047276856901640,'ops-admin-web',886046060617793537,'/user/ops-org')
,(886050584556339210,'ops-admin-web',886042515130679296,'/user/ops-org')
,(915759642049511425,'platform-admin-web',1013,'/log/lock-log')
,(915762236067151882,'platform-admin-web',915759029353971712,'/log/disable-log')
,(915762310289555467,'platform-admin-web',1014,'/log/op-log')
,(915764476337192974,'platform-admin-web',1011,'/account/rac-account')
,(915764476416884751,'platform-admin-web',1011,'/account/rac-user')
,(915764476437856272,'platform-admin-web',1011,'/account/rac-account-unlock')
,(915764515977560081,'platform-admin-web',1012,'/account/rac-account')
,(915764515994337298,'platform-admin-web',1012,'/account/rac-user')
,(915764516015308819,'platform-admin-web',1012,'/account/rac-account-unlock')
,(915770310995738647,'ops-admin-web',886046205266755586,'/user/ops-account')
,(915770311012515864,'ops-admin-web',886046205266755586,'/user/ops-user')
,(915770311083819033,'ops-admin-web',886046205266755586,'/user/rac-account-unlock')
,(915770360601772058,'ops-admin-web',886047198507302915,'/user/ops-account')
,(915770360618549275,'ops-admin-web',886047198507302915,'/user/ops-user')
,(915770360685658140,'ops-admin-web',886047198507302915,'/user/rac-account-unlock')
,(915775720272166941,'ops-admin-web',915769145721618433,'/user-log/lock-log')
,(915775740744564766,'ops-admin-web',915769255989870594,'/user-log/op-log')
,(915775765260271647,'ops-admin-web',915769317612584963,'/user-log/disable-log');

INSERT INTO `RAC_PERM_URN` VALUES (875980015911698436,1001,'*:/rac/realm/**')
,(875986330897743872,1002,'GET:/rac/realm/**')
,(875980164188733445,1003,'*:/rac/app/**')
,(875986401345273857,1004,'GET:/rac/app/**')
,(915758522178732036,1005,'*:/rac/perm-command/**')
,(915758522015154176,1005,'*:/rac/perm-group/**')
,(915758522082263041,1005,'*:/rac/perm-menu/**')
,(915758522111623170,1005,'*:/rac/perm-urn/**')
,(915758522145177603,1005,'*:/rac/perm/**')
,(915758584422203397,1006,'GET:/rac/perm-group/**')
,(915758584489312262,1006,'GET:/rac/perm-menu/**')
,(915758584514478087,1006,'GET:/rac/perm-urn/**')
,(915758584552226824,1006,'GET:/rac/perm/**')
,(915758584577392649,1006,'GET:rac/perm-command/**')
,(875980626589777926,1007,'*:/rac/role/**')
,(875986649526435846,1008,'GET:/rac/role/**')
,(875982137797509127,1009,'*:/rac/org/**')
,(875986920314896391,1010,'GET:/rac/org/**')
,(915764792495439888,1011,'*:/rac/account/**')
,(915764792549965842,1011,'*:/rac/sign-in/**')
,(915764792524800017,1011,'*:/rac/user/**')
,(915764856634736659,1012,'GET:/rac/account/**')
,(915764856689262613,1012,'GET:/rac/sign-in/**')
,(915764856664096788,1012,'GET:/rac/user/**')
,(915762629589336075,1013,'*:/rac/lock-log/**')
,(875987086598078474,1014,'GET:/rac/op-log/**')
,(878551998646059009,1015,'*:/rac/dic-item/**')
,(878551998574755840,1015,'*:/rac/dic/**')
,(878551998717362179,1015,'GET:/rac/app/list')
,(878551998692196354,1015,'GET:/rac/realm/list-all')
,(878552198039076871,1016,'GET:/rac/app/list')
,(878552197858721796,1016,'GET:/rac/dic-item/**')
,(878552197917442053,1016,'GET:/rac/dic/**')
,(878552197976162310,1016,'GET:/rac/realm/list-all')
,(886045981131538434,886042515130679296,'GET:/rac/org/*')
,(886045981064429569,886042515130679296,'GET:/rac/realm/list-all')
,(886046130557812740,886046060617793537,'*:/rac/org/*')
,(886046130494898179,886046060617793537,'GET:/rac/realm/list-all')
,(915770685157015582,886046205266755586,'GET:/rac/account/**')
,(915770685245095967,886046205266755586,'GET:/rac/org/list')
,(915770685274456096,886046205266755586,'GET:/rac/realm/list-all')
,(915770685299621921,886046205266755586,'GET:/rac/sign-in/**')
,(915770685324787746,886046205266755586,'GET:/rac/user/**')
,(915770584778932249,886047198507302915,'*:/rac/account/**')
,(915770584904761372,886047198507302915,'*:/rac/sign-in/**')
,(915770584934121501,886047198507302915,'*:/rac/user/**')
,(915770584850235418,886047198507302915,'GET:/rac/org/list')
,(915770584883789851,886047198507302915,'GET:/rac/realm/list-all')
,(915762127447261194,915759029353971712,'GET:/rac/disable-log/**')
,(915769453117964310,915769145721618433,'*:/rac/lock-log/**')
,(915769520184885271,915769255989870594,'GET:/rac/op-log/**')
,(915769583518875672,915769317612584963,'GET:/rac/disable-log/**');

INSERT INTO `RAC_ROLE` VALUES (1,'平台管理员','platform',null,1,0,'平台管理员'),(2,'运营管理员','ops',null,1,0,'运营管理员');

INSERT INTO `RAC_ROLE_PERM` VALUES (915762427449049088,1,1001)
,(915762427495186433,1,1002),(915762427503575042,1,1003)
,(915762427516157955,1,1004),(915762427524546564,1,1005)
,(915762427532935173,1,1006),(915762427545518086,1,1007)
,(915762427558100999,1,1008),(915762427566489608,1,1009)
,(915762427579072521,1,1010),(915762427587461130,1,1011)
,(915762427595849739,1,1012),(915762427604238348,1,1013)
,(915762427612626957,1,1014),(915762427621015566,1,1015)
,(915762427633598479,1,1016),(915762427654570000,1,915759029353971712)
,(915769642151051281,2,886042515130679296)
,(915769642213965842,2,886046060617793537)
,(915769642226548755,2,886046205266755586)
,(915769642239131668,2,886047198507302915)
,(915769642247520277,2,915769145721618433)
,(915769642255908886,2,915769255989870594)
,(915769642264297495,2,915769317612584963);

INSERT INTO `RAC_ACCOUNT` VALUES (0,NULL,NULL,NULL,NULL,NULL,'platform',1,'null',NULL,NULL,'','',NULL,NULL,'空',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,1632272751000,1632272751000)
,(1,NULL,NULL,NULL,NULL,NULL,'platform',1,'system',NULL,NULL,'','',NULL,NULL,'应用',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,1632272751000,1632272751000)
,(10,99999,NULL,NULL,NULL,NULL,'platform',1,'super',NULL,NULL,'ca01d98164025658cabbde3e17e72d8e','WwPs7F',NULL,NULL,'平台管理员','http://172.20.14.125:9000/rac-avatar/10.png?a=1632275049216',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,1632272751000,1632275049229)
,(20,NULL,NULL,NULL,NULL,NULL,'ops',1,'admin',NULL,NULL,'28bac2dc3862a24b376314014d8ef920','zcSeWA',NULL,NULL,'运营管理员','/img/account/9527.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,1632272751000,1632279918883)
,(100,99999,NULL,NULL,NULL,NULL,'default',1,'super',NULL,NULL,'ca01d98164025658cabbde3e17e72d8e','WwPs7F',NULL,NULL,'(默认)平台管理员','http://172.20.14.125:9000/rac-avatar/10.png?a=1632275049216',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,1632272751000,1632275049229)
,(909945462243459072,NULL,NULL,NULL,NULL,NULL,'default',1,'adminer',NULL,NULL,'53682613730bd2a22336535b987360c6','Q5gGYE',NULL,NULL,'adminer',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,1630890031987,1630890031987);

INSERT INTO `RAC_ACCOUNT_ROLE` VALUES (1,1,10),(2,2,20);


