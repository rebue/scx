
INSERT INTO `RAC_REALM` VALUES ('default','默认领域','应用和账户的默认领域')
,('ops','运营领域','运营管理领域')
,('platform','平台领域','平台管理领域');



INSERT INTO `RAC_APP` VALUES ('baidu','百度','default','https://www.baidu.com',NULL,'',0,'/oss-obj/933906776471240716.png?a=1636602854504',2,0,0)
,('charge-manage','收费管理系统','default','',NULL,'收费管理系统',0,'/oss-obj/933904014954070026.png?a=1636602196104',5,0,0)
,('data-sharing','数据共享平台','default','',NULL,'数据共享平台',0,'/oss-obj/933904169187016715.png?a=1636602232874',6,0,0)
,('integrated-platform','内诊平台','default','http://nz.maiyuesoft.com',NULL,'内诊平台',1,'/oss-obj/933903814344704009.png?a=1636602148272',4,0,0)
,('ops-admin-web','运营后台管理','ops','https://auth.maiyuesoft.com/admin-web/ops-admin-web/account/rac-account','[{\"key\":\"/ops-admin-web/account\",\"title\":\"账户\",\"children\":[{\"key\":\"/ops-admin-web/account/rac-org\",\"title\":\"组织\"},{\"key\":\"/ops-admin-web/account/rac-account\",\"title\":\"账户\"},{\"key\":\"/ops-admin-web/account/rac-user\",\"title\":\"用户\"},{\"key\":\"/ops-admin-web/account/rac-account-unlock\",\"title\":\"解锁\"}]},{\"key\":\"/ops-admin-web/log\",\"title\":\"日志\",\"children\":[{\"key\":\"/ops-admin-web/log/lock-log\",\"title\":\"账户解锁日志\"},{\"key\":\"/ops-admin-web/log/disable-log\",\"title\":\"账户启/禁用日志\"},{\"key\":\"/ops-admin-web/log/op-log\",\"title\":\"操作日志\"}]},{\"key\":\"/ops-admin-web/system-manage\",\"title\":\"系统管理\",\"children\":[{\"key\":\"/ops-admin-web/user-system-manage/weChat-config\",\"title\":\"公众号配置\"},{\"key\":\"/ops-admin-web/user-system-manage/dingTalk-config\",\"title\":\"钉钉配置\"},{\"key\":\"/ops-admin-web/user-system-manage/level-protect\",\"title\":\"账户密码安全等级配置\"}]},{\"key\":\"/ops-admin-web/system-survey\",\"title\":\"系统概况\",\"children\":[{\"key\":\"/ops-admin-web/user-system-survey/today-survey\",\"title\":\"今日账号概况\"}]}]','对运营的后台管理提供最基本的功能',1,'/oss-obj/933946597872893970.png?a=1636612348658',0,1,1)
,('personnel-manage','人事管理系统','default','',NULL,'人事管理系统',0,'/oss-obj/933903776637911048.png?a=1636602139295',3,0,0)
,('platform-admin-web','平台后台管理','platform','https://auth.maiyuesoft.com/admin-web/platform-admin-web/base/rac-realm','[{\"key\":\"/platform-admin-web/base\",\"title\":\"基础\",\"children\":[{\"key\":\"/platform-admin-web/base/rac-realm\",\"title\":\"领域\"},{\"key\":\"/platform-admin-web/base/rac-app\",\"title\":\"应用\"},{\"key\":\"/platform-admin-web/base/rac-perm\",\"title\":\"权限\"},{\"key\":\"/platform-admin-web/base/rac-role\",\"title\":\"角色\"},{\"key\":\"/platform-admin-web/base/rac-dic\",\"title\":\"字典\"}]},{\"key\":\"/platform-admin-web/account\",\"title\":\"账户\",\"children\":[{\"key\":\"/platform-admin-web/account/rac-org\",\"title\":\"组织\"},{\"key\":\"/platform-admin-web/account/rac-account\",\"title\":\"账户\"},{\"key\":\"/platform-admin-web/account/rac-user\",\"title\":\"用户\"},{\"key\":\"/platform-admin-web/account/rac-account-unlock\",\"title\":\"解锁\"}]},{\"key\":\"/platform-admin-web/log\",\"title\":\"日志\",\"children\":[{\"key\":\"/platform-admin-web/log/lock-statistics\",\"title\":\"日志统计\"},{\"key\":\"/platform-admin-web/log/lock-log\",\"title\":\"账户解锁日志\"},{\"key\":\"/platform-admin-web/log/disable-log\",\"title\":\"账户启/禁用日志\"},{\"key\":\"/platform-admin-web/log/op-log\",\"title\":\"操作日志\"}]},{\"key\":\"/platform-admin-web/user-synchro\",\"title\":\"数据管理\",\"children\":[{\"key\":\"/platform-admin-web/user-synchro/account-sy\",\"title\":\"同步策略\"}]},{\"key\":\"/platform-admin-web/system-manage\",\"title\":\"系统管理\",\"children\":[{\"key\":\"/platform-admin-web/user-system-manage/weChat-config\",\"title\":\"公众号配置\"},{\"key\":\"/platform-admin-web/user-system-manage/dingTalk-config\",\"title\":\"钉钉配置\"},{\"key\":\"/platform-admin-web/user-system-manage/level-protect\",\"title\":\"账户密码安全等级配置\"}]},{\"key\":\"/platform-admin-web/system-survey\",\"title\":\"系统概况\",\"children\":[{\"key\":\"/platform-admin-web/user-system-survey/today-survey\",\"title\":\"今日账号概况\"},{\"key\":\"/platform-admin-web/user-system-survey/authentication\",\"title\":\"认证概况\"}]}]','对平台的后台管理提供最基本的功能',1,'/oss-obj/933946226748293137.png?a=1636612260175',0,1,1)
,('smart-campus','智慧校园一卡通','default','',NULL,'智慧校园一卡通',0,'/oss-obj/933944365878214669.png?a=1636611816522',7,0,0)
,('third-party-demo','第三方实例应用','default','http://122.9.109.134:30010',NULL,NULL,1,'/oss-obj/933946134058369040.png?a=1636612238078',1,1,2)
,('unified-auth','统一认证平台','default','https://auth.maiyuesoft.com/admin-web/unified-auth/app/index',NULL,'',1,'/oss-obj/933946674217615379.png?a=1636612366864',0,1,2);



INSERT INTO `RAC_DIC` VALUES (923116577378664449,'ApplyLabel','应用标签',NULL,NULL,'应用标签key，约定为ApplyLabel','2021-10-12 17:17:51')
,(928145752221155328,'levelProtect','等保',NULL,NULL,NULL,'2021-10-26 14:21:59');


INSERT INTO `RAC_DIC_ITEM` VALUES (923389909341569026,923116577378664449,NULL,'jiaowu',NULL,'教务','001','2021-10-13 11:23:58',NULL)
,(923390559798427653,923116577378664449,NULL,'xuegong',NULL,'学工','000','2021-10-13 11:26:33',NULL)
,(933894369363099648,923116577378664449,NULL,'platform','platform','平台','002','2021-11-11 11:04:56',NULL)
,(933894419468255233,923116577378664449,NULL,'ops','ops','运营','003','2021-11-11 11:05:08',NULL);


INSERT INTO `RAC_APP_TAG` VALUES (933946232746934288,'platform-admin-web',933894369363099648)
,(933946609542234129,'ops-admin-web',933894419468255233)
,(933946679666802706,'unified-auth',923390559798427653)
,(933968382954438682,'third-party-demo',923390559798427653);



INSERT INTO `RAC_ACCOUNT` VALUES (0,NULL,NULL,NULL,NULL,NULL,'platform',1,'null',NULL,NULL,'','',NULL,NULL,'空',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1632272751000,1632272751000)
,(1,NULL,NULL,NULL,NULL,NULL,'platform',1,'system',NULL,NULL,'','',NULL,NULL,'应用',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1632272751000,1632272751000)
,(10,99999,NULL,NULL,NULL,NULL,'platform',1,'super',NULL,NULL,'ca01d98164025658cabbde3e17e72d8e','WwPs7F',NULL,NULL,'平台管理员','/rac-avatar/10.png?a=1636534127534',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1632272751000,1632275049229)
,(20,NULL,NULL,NULL,NULL,NULL,'ops',1,'admin',NULL,NULL,'28bac2dc3862a24b376314014d8ef920','zcSeWA',NULL,NULL,'运营管理员','/img/account/9527.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1632272751000,1632279918883)
,(100,NULL,NULL,NULL,NULL,NULL,'default',1,'super',NULL,NULL,'3b6c8388550ee9fb424379a3e105c1f7','YSwfwg',NULL,NULL,'(默认)平台管理员','http://172.20.14.125:9000/rac-avatar/10.png?a=1632275049216',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1632272751000,1636598304644)
,(909945462243459072,NULL,NULL,NULL,NULL,NULL,'default',1,'adminer',NULL,NULL,'53682613730bd2a22336535b987360c6','Q5gGYE',NULL,NULL,'adminer','/rac-avatar/909945462243459072.png?a=1636591595782',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1630890031987,1630890031987);


INSERT INTO `RAC_ROLE` VALUES (1,'平台管理员','platform',1,0,'平台管理员')
,(2,'运营管理员','ops',1,0,'运营管理员')
,(933890453275410452,'默认','default',1,0,NULL);

INSERT INTO `RAC_ACCOUNT_ROLE` VALUES (1,1,10),(2,2,20);


INSERT INTO `RAC_PERM_GROUP` VALUES (1,'platform','领域',1,0,'领域')
,(2,'platform','应用',1,1,'应用')
,(3,'platform','权限',1,2,'权限')
,(4,'platform','角色',1,3,'角色')
,(5,'platform','组织',1,4,'组织')
,(6,'platform','账户',1,5,'账户')
,(7,'platform','日志',1,6,'日志')
,(8,'platform','字典',1,7,'日志')
,(886042488652038144,'ops','用户中心',1,0,'用户中心')
,(915768980965163008,'ops','日志中心',1,1,'日志中心')
,(933621033449160704,'platform','管理员',1,8,NULL)
,(933895911889436672,'ops','管理员',1,2,NULL);


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
,(915769317612584963,'ops',915768980965163008,'查看账户禁用日志',1,2,'查看账户禁用日志')
,(933621215167512576,'platform',933621033449160704,'全部权限',1,0,NULL)
,(933895940498915328,'ops',933895911889436672,'管理员',1,0,NULL);


INSERT INTO `RAC_PERM_MENU` VALUES (933864893205053440,'platform-admin-web',933621215167512576,'/platform-admin-web/account/rac-account')
,(933864893259579393,'platform-admin-web',933621215167512576,'/platform-admin-web/account/rac-account-unlock')
,(933864893288939522,'platform-admin-web',933621215167512576,'/platform-admin-web/account/rac-org')
,(933864893314105347,'platform-admin-web',933621215167512576,'/platform-admin-web/account/rac-user')
,(933864893335076868,'platform-admin-web',933621215167512576,'/platform-admin-web/base/rac-app')
,(933864893356048389,'platform-admin-web',933621215167512576,'/platform-admin-web/base/rac-dic')
,(933864893372825606,'platform-admin-web',933621215167512576,'/platform-admin-web/base/rac-perm')
,(933864893393797127,'platform-admin-web',933621215167512576,'/platform-admin-web/base/rac-realm')
,(933864893414768648,'platform-admin-web',933621215167512576,'/platform-admin-web/base/rac-role')
,(933864893435740169,'platform-admin-web',933621215167512576,'/platform-admin-web/log/disable-log')
,(933864893456711690,'platform-admin-web',933621215167512576,'/platform-admin-web/log/lock-log')
,(933864893477683211,'platform-admin-web',933621215167512576,'/platform-admin-web/log/lock-statistics')
,(933864893498654732,'platform-admin-web',933621215167512576,'/platform-admin-web/log/op-log')
,(933864893519626253,'platform-admin-web',933621215167512576,'/platform-admin-web/user-synchro/account-sy')
,(933864893536403470,'platform-admin-web',933621215167512576,'/platform-admin-web/user-system-manage/dingTalk-config')
,(933864893557374991,'platform-admin-web',933621215167512576,'/platform-admin-web/user-system-manage/level-protect')
,(933864893578346512,'platform-admin-web',933621215167512576,'/platform-admin-web/user-system-manage/weChat-config')
,(933864893599318033,'platform-admin-web',933621215167512576,'/platform-admin-web/user-system-survey/authentication')
,(933864893620289554,'platform-admin-web',933621215167512576,'/platform-admin-web/user-system-survey/today-survey')
,(933896773111513107,'ops-admin-web',933895940498915328,'/ops-admin-web/account/rac-org')
,(933896773132484628,'ops-admin-web',933895940498915328,'/ops-admin-web/account/rac-account')
,(933896773149261845,'ops-admin-web',933895940498915328,'/ops-admin-web/account/rac-user')
,(933896773166039062,'ops-admin-web',933895940498915328,'/ops-admin-web/account/rac-account-unlock')
,(933896773182816279,'ops-admin-web',933895940498915328,'/ops-admin-web/log/lock-log')
,(933896773203787800,'ops-admin-web',933895940498915328,'/ops-admin-web/log/disable-log')
,(933896773220565017,'ops-admin-web',933895940498915328,'/ops-admin-web/log/op-log')
,(933896773237342234,'ops-admin-web',933895940498915328,'/ops-admin-web/user-system-manage/weChat-config')
,(933896773254119451,'ops-admin-web',933895940498915328,'/ops-admin-web/user-system-manage/dingTalk-config')
,(933896773270896668,'ops-admin-web',933895940498915328,'/ops-admin-web/user-system-manage/level-protect')
,(933896773287673885,'ops-admin-web',933895940498915328,'/ops-admin-web/user-system-survey/today-survey');


INSERT INTO `RAC_PERM_URN` VALUES (875980015911698436,1001,'*:/rac/realm/**')
,(875986330897743872,1002,'GET:/rac/realm/**'),(875980164188733445,1003,'*:/rac/app/**')
,(875986401345273857,1004,'GET:/rac/app/**'),(915758522178732036,1005,'*:/rac/perm-command/**')
,(915758522015154176,1005,'*:/rac/perm-group/**'),(915758522082263041,1005,'*:/rac/perm-menu/**')
,(915758522111623170,1005,'*:/rac/perm-urn/**'),(915758522145177603,1005,'*:/rac/perm/**')
,(915758584422203397,1006,'GET:/rac/perm-group/**'),(915758584489312262,1006,'GET:/rac/perm-menu/**')
,(915758584514478087,1006,'GET:/rac/perm-urn/**'),(915758584552226824,1006,'GET:/rac/perm/**')
,(915758584577392649,1006,'GET:rac/perm-command/**'),(875980626589777926,1007,'*:/rac/role/**')
,(875986649526435846,1008,'GET:/rac/role/**'),(875982137797509127,1009,'*:/rac/org/**')
,(875986920314896391,1010,'GET:/rac/org/**'),(915764792495439888,1011,'*:/rac/account/**')
,(915764792549965842,1011,'*:/rac/sign-in/**'),(915764792524800017,1011,'*:/rac/user/**')
,(915764856634736659,1012,'GET:/rac/account/**'),(915764856689262613,1012,'GET:/rac/sign-in/**')
,(915764856664096788,1012,'GET:/rac/user/**'),(933621932692602927,1013,'*:/rac/lock-log/**')
,(875987086598078474,1014,'GET:/rac/op-log/**'),(878551998646059009,1015,'*:/rac/dic-item/**')
,(878551998574755840,1015,'*:/rac/dic/**'),(878551998717362179,1015,'GET:/rac/app/list')
,(878551998692196354,1015,'GET:/rac/realm/list-all'),(878552198039076871,1016,'GET:/rac/app/list')
,(878552197858721796,1016,'GET:/rac/dic-item/**'),(878552197917442053,1016,'GET:/rac/dic/**')
,(878552197976162310,1016,'GET:/rac/realm/list-all'),(886045981131538434,886042515130679296,'GET:/rac/org/*')
,(886045981064429569,886042515130679296,'GET:/rac/realm/list-all'),(886046130557812740,886046060617793537,'*:/rac/org/*')
,(886046130494898179,886046060617793537,'GET:/rac/realm/list-all'),(915770685157015582,886046205266755586,'GET:/rac/account/**')
,(915770685245095967,886046205266755586,'GET:/rac/org/list'),(915770685274456096,886046205266755586,'GET:/rac/realm/list-all')
,(915770685299621921,886046205266755586,'GET:/rac/sign-in/**'),(915770685324787746,886046205266755586,'GET:/rac/user/**')
,(915770584778932249,886047198507302915,'*:/rac/account/**'),(915770584904761372,886047198507302915,'*:/rac/sign-in/**')
,(915770584934121501,886047198507302915,'*:/rac/user/**'),(915770584850235418,886047198507302915,'GET:/rac/org/list')
,(915770584883789851,886047198507302915,'GET:/rac/realm/list-all'),(915762127447261194,915759029353971712,'GET:/rac/disable-log/**')
,(915769453117964310,915769145721618433,'*:/rac/lock-log/**'),(915769520184885271,915769255989870594,'GET:/rac/op-log/**')
,(915769583518875672,915769317612584963,'GET:/rac/disable-log/**'),(933621892590862380,933621215167512576,'*:/rac/account/**')
,(933621892347592739,933621215167512576,'*:/rac/app/**'),(933621892393730084,933621215167512576,'*:/rac/org/**')
,(933621892418895909,933621215167512576,'*:/rac/perm-command/**'),(933621892444061734,933621215167512576,'*:/rac/perm-group/**')
,(933621892469227559,933621215167512576,'*:/rac/perm-menu/**'),(933621892494393384,933621215167512576,'*:/rac/perm-urn/**')
,(933621892519559209,933621215167512576,'*:/rac/perm/**'),(933621892544725034,933621215167512576,'*:/rac/realm/**')
,(933621892565696555,933621215167512576,'*:/rac/role/**'),(933621892611833901,933621215167512576,'*:/rac/sign-in/**')
,(933621892636999726,933621215167512576,'*:/rac/user/**'),(933897296053141504,933895940498915328,'*:/rac/org/*')
,(933897296090890241,933895940498915328,'GET:/rac/realm/list-all');


INSERT INTO `RAC_ROLE_PERM` VALUES (933865467131199488,1,1001)
,(933865467168948225,1,1002),(933865467194114050,1,1003)
,(933865467219279875,1,1004),(933865467223474180,1,1005)
,(933865467231862789,1,1006),(933865467236057094,1,1007)
,(933865467269611527,1,1008),(933865467311554568,1,1009)
,(933865467319943177,1,1010),(933865467328331786,1,1011)
,(933865467332526091,1,1012),(933865467366080524,1,1013)
,(933865467374469133,1,1014),(933865467378663438,1,1015)
,(933865467391246351,1,1016),(933865467399634960,1,915759029353971712)
,(933865467408023569,1,933621215167512576),(933897907103072285,2,886042515130679296)
,(933897907111460894,2,886046060617793537),(933897907136626720,2,886046205266755586)
,(933897907115655199,2,886047198507302915),(933897907140821025,2,915769145721618433)
,(933897907145015330,2,915769255989870594),(933897907153403939,2,915769317612584963)
,(933897907094683676,2,933895940498915328);



