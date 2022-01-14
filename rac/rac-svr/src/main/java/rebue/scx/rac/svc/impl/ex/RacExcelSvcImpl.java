package rebue.scx.rac.svc.impl.ex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.http.MediaType;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.nacos.shaded.com.google.common.net.HttpHeaders;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mapper.RacAccountRoleMapper;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.mo.RacAccountRoleMo;
import rebue.scx.rac.mo.RacOrgMo;
import rebue.scx.rac.mo.RacRoleMo;
import rebue.scx.rac.mo.RacUserMo;
import rebue.scx.rac.svc.RacAccountSvc;
import rebue.scx.rac.svc.RacOrgSvc;
import rebue.scx.rac.svc.RacRoleSvc;
import rebue.scx.rac.svc.RacUserSvc;
import rebue.scx.rac.svc.ex.RacExcelSvc;
import rebue.scx.rac.to.RacAccountAddTo;
import rebue.scx.rac.to.RacAccountOneTo;
import rebue.scx.rac.to.RacAccountRoleAddTo;
import rebue.scx.rac.to.RacOrgAddTo;
import rebue.scx.rac.to.RacOrgOneTo;
import rebue.scx.rac.to.RacRoleOneTo;
import rebue.scx.rac.to.RacUserAddTo;
import rebue.scx.rac.to.RacUserOneTo;
import rebue.scx.rac.util.FieldCollection;
import rebue.scx.rac.util.ImporExcelUtil;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.core.RandomEx;
import rebue.wheel.core.util.OrikaUtils;
import rebue.wheel.core.util.RegexUtils;

/**
 * Excel的实现类
 *
 * <pre>
 *  
 * 注意：
 * 1. 查询数据库操作的方法，不用设置默认 @Transactional
 *    在类上方已经设置默认为 readOnly=true, propagation=Propagation.SUPPORTS
 *    而涉及到 增删改 数据库操作的方法时，要设置 readOnly=false, propagation=Propagation.REQUIRED
 * 2. 事务不会针对受控异常（checked exception）回滚
 *    要想回滚事务，须抛出运行时异常(RuntimeException)
 * 3. 如果类上方不带任何参数的 @Transactional 注解时，如同下面的设置
 *    propagation(传播模式)=REQUIRED，readOnly=false，isolation(事务隔离级别)=READ_COMMITTED
 * </pre>
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Service
@Slf4j
public class RacExcelSvcImpl implements RacExcelSvc {

    @Resource
    private RacAccountSvc        racAccountSvc;
    @Resource
    private RacOrgSvc            racOrgSvc;
    @Resource
    private RacUserSvc           racUserSvc;
    @Resource
    private RacRoleSvc           racRoleSvc;
    @Resource
    private RacAccountRoleMapper racAccountRoleMapper;

    private static String        Realm_ID = "default";

    /**
     * excel模板文件下载
     */
    @Override
    public Mono<Void> getExcelTemplate(final String type, final ServerHttpResponse response) {
        ZeroCopyHttpOutputMessage zeroCopyResponse = (ZeroCopyHttpOutputMessage) response;
        String                    fileName         = "账户信息收集表.xlsx";
        if ("org".equals(type)) {
            fileName = "组织信息收集表.xlsx";
        }
        File targetFile = null;
        try {
            targetFile = File.createTempFile("cateringConsole", "xlsx");
            Workbook workbook = new SXSSFWorkbook();
            setSheet(workbook, type, 0);
            FileOutputStream stream = new FileOutputStream(targetFile);
            workbook.write(stream);
            workbook.close();
            stream.flush();
            stream.close();
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {

        }
        response.getHeaders().set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName);
        response.getHeaders().setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return zeroCopyResponse.writeWith(targetFile, 0, targetFile.length());
    }

    @Override
    public Mono<?> getExcelContent(Flux<FilePart> filePartFlux) {

        return Mono.create(callback -> callback.success("ss"));
    }

    @Override
    public Ro<?> getExcelContent(InputStream inputStream, String type, String fileName) {
        // 获取字段数组
        String[]                  cols      = FieldCollection.getAccountInformationCol();
        String[]                  orgCols   = FieldCollection.getOrgInformationCol();
        List<Map<String, Object>> readExcel = new ArrayList<>();
        try {
            int i = 0;
            switch (type) {
            case "account":
                readExcel = ImporExcelUtil.readExcel(inputStream, fileName, 1, cols.length, cols);
                recursionAdd(readExcel, i);
                break;
            case "org":
                readExcel = ImporExcelUtil.readExcel(inputStream, fileName, 1, orgCols.length, orgCols);
                orgAdd(readExcel, i);
                break;
            default:
                throw new RuntimeExceptionX("不支持该类型: " + type);
            }
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Ro.success("导入成功");
    }

    /**
     * 添加组织
     * 
     * @param readExcel
     * @param i
     */
    private void orgAdd(List<Map<String, Object>> readExcel, int i) {
        String[] cols = FieldCollection.getOrgInformationCol();
        Collections.sort(readExcel, new Comparator<Map<String, Object>>() {

            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                String value1     = (String) o1.get(cols[1]);
                String value2     = (String) o2.get(cols[1]);
                long   parseLong2 = Long.valueOf(value2);
                long   parseLong  = Long.valueOf(value1);

                if (parseLong == parseLong2) {
                    if (value1.length() == value2.length()) {
                        return 0;
                    }
                    if (value1.length() > value2.length()) {
                        return 1;
                    }
                    return -1;
                }
                boolean b = parseLong > parseLong2;
                return b ? 1 : -1;
            };
        });
        int size = readExcel.size();
        log.info(StringUtils.rightPad("*** 剩余记录size ： " + size + " 条", 100));
        Iterator<Map<String, Object>> iterator = readExcel.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> next = iterator.next();
            log.info(StringUtils.rightPad("*** 当前记录 " + (i += 1) + " : " + next.toString(), 100));
            RacOrgMo orgMo = insertOrgRecord(next);
            iterator.remove();
        }

    }

    /**
     * 遍历添加账户信息
     */
    private void recursionAdd(List<Map<String, Object>> readExcel, int i) {
        int size = readExcel.size();
        log.info(StringUtils.rightPad("*** 剩余记录size ： " + size + " 条", 100));
        Iterator<Map<String, Object>> iterator = readExcel.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> next = iterator.next();
            log.info(StringUtils.rightPad("*** 当前记录 " + (i += 1) + " : " + next.toString(), 100));
            RacUserMo    userMo    = insertUserRecord(next);
            RacOrgMo     orgMo     = seleteOrgRecord(next);
            RacAccountMo accountMo = insertAccountRecord(next, userMo, orgMo);
            RacRoleMo    roleMo    = seleteRoleRecord(next);
            insertAccountRoleRecord(roleMo, accountMo);
            iterator.remove();
        }
    }

    /**
     * 查询角色
     * 
     * @param map
     * 
     */
    private RacRoleMo seleteRoleRecord(Map<String, Object> map) {
        // 获取字段数组
        String[] cols     = FieldCollection.getAccountInformationCol();
        String   roleId   = (String) map.get(cols[8]);
        String   roleName = (String) map.get(cols[9]);
        if (!StringUtils.isEmpty(roleId)) {
            long valueOf = Integer.parseInt(roleId);
            return racRoleSvc.getById(valueOf);
        }
        if (!StringUtils.isEmpty(roleName)) {
            RacRoleOneTo oneTo = new RacRoleOneTo();
            oneTo.setRealmId(Realm_ID);
            oneTo.setName(roleName);
            return racRoleSvc.getOne(oneTo);
        }
        return null;
    }

    /**
     * 插入帐号角色关系
     * 
     * @param roleMo
     * @param accountMo
     */
    // @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    private void insertAccountRoleRecord(RacRoleMo roleMo, RacAccountMo accountMo) {
        if (roleMo == null || accountMo == null) {
            return;
        }
        // 是否存在关联角色
        RacAccountRoleMo rolenoe = new RacAccountRoleMo();
        rolenoe.setAccountId(accountMo.getId());
        rolenoe.setRoleId(roleMo.getId());
        RacAccountRoleMo selectOne = racAccountRoleMapper.selectOne(rolenoe).orElse(null);
        if (selectOne != null) {
            return;
        }
        RacAccountRoleAddTo roleAddTo = new RacAccountRoleAddTo();
        List<Long>          list      = new ArrayList<Long>();
        roleAddTo.setAccountId(accountMo.getId());
        list.add(roleMo.getId());
        roleAddTo.setRoleIds(list);
        racRoleSvc.addAccountRole(roleAddTo);
    }

    /**
     * 查询组织
     * 
     * @param map
     * 
     * @return RacOrgMo
     */
    private RacOrgMo seleteOrgRecord(Map<String, Object> map) {
        // 获取字段数组
        String[] cols    = FieldCollection.getAccountInformationCol();
        // 对应组织中的code
        String   orgId   = (String) map.get(cols[6]);
        String   orgName = (String) map.get(cols[7]);
        // 过滤都为空
        if (StringUtils.isEmpty(orgId) && StringUtils.isEmpty(orgName)) {
            return null;
        }
        if (!StringUtils.isEmpty(orgId)) {
            final RacOrgOneTo oneTo = new RacOrgOneTo();
            oneTo.setRealmId(Realm_ID);
            oneTo.setName(orgName);
            oneTo.setCode(orgId);
            RacOrgMo one = racOrgSvc.getOne(oneTo);
            if (one != null) {
                return one;
            }
        }
        if (!StringUtils.isEmpty(orgName)) {
            final RacOrgOneTo oneName = new RacOrgOneTo();
            oneName.setRealmId(Realm_ID);
            oneName.setName(orgName);
            RacOrgMo orgMo = racOrgSvc.getOne(oneName);
            if (orgMo != null) {
                return orgMo;
            }
        }

        RacOrgAddTo addTo = new RacOrgAddTo();
        if (!StringUtils.isEmpty(orgName)) {
            addTo.setName(orgName);
        }
        if (!StringUtils.isEmpty(orgId)) {
            addTo.setCode(orgId);
        }
        addTo.setRealmId(Realm_ID);
        addTo.setOrgType((byte) 1);
        addTo.setFullName(addTo.getName());
        RacOrgMo add = racOrgSvc.add(addTo);
        return add;
    }

    /**
     * 插入用户信息（新建用户,已存在则不用插入）
     * 
     * @param map
     * 
     * @return RacUserMo
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    private RacUserMo insertUserRecord(Map<String, Object> map) {
        // 获取字段数组
        String[] cols           = FieldCollection.getAccountInformationCol();
        String   signInNickname = (String) map.get(cols[2]);
        String   idCard         = (String) map.get(cols[3]);
        String   signInMobile   = (String) map.get(cols[4]);
        String   signInEmail    = (String) map.get(cols[5]);
        // 过滤姓名或身份证为空
        if (StringUtils.isEmpty(signInNickname) || StringUtils.isEmpty(idCard)) {
            return null;
        }
        // 校验身份证
        boolean matchIdCard = RegexUtils.matchIdCard(idCard);
        if (!matchIdCard) {
            return null;
        }
        if (idCard.length() != 18) {
            return null;
        }
        RacUserAddTo userAddTo = new RacUserAddTo();
        userAddTo.setRealName(signInNickname);
        userAddTo.setIdCard(idCard);
        // 用户是否存在，存在则返回，不存在则添加并返回
        RacUserMo    userMoOne = null;
        RacUserOneTo user      = new RacUserOneTo();
        user.setIdCard(idCard);
        userMoOne = racUserSvc.getUserMoOne(user);
        if (userMoOne != null) {
            return userMoOne;
        }
        if (!StringUtils.isEmpty(signInMobile)) {
            RacUserOneTo userMobile = new RacUserOneTo();
            userMobile.setMobile(signInMobile);
            userMoOne = racUserSvc.getUserMoOne(userMobile);
            if (userMoOne != null) {
                return userMoOne;
            }
            else {
                userAddTo.setMobile(signInMobile);
            }
        }
        if (!StringUtils.isEmpty(signInEmail)) {
            RacUserOneTo userEmail = new RacUserOneTo();
            userEmail.setEmail(signInEmail);
            userMoOne = racUserSvc.getUserMoOne(userEmail);
            if (userMoOne != null) {
                return userMoOne;
            }
            else {
                userAddTo.setMobile(signInEmail);
            }
        }
        RacUserMo userMo = racUserSvc.add(userAddTo);
        return userMo;

    }

    /**
     * 插入组织信息（新建组织 关联上级组织）
     * 
     * @return
     */
    @SuppressWarnings("unused")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    private RacOrgMo insertOrgRecord(Map<String, Object> map) {
        // 获取字段数组
        String[] cols       = FieldCollection.getOrgInformationCol();
        // 添加组织信息
        String   index      = (String) map.get(cols[0]);
        String   orgId      = (String) map.get(cols[1]);
        String   orgName    = (String) map.get(cols[2]);
        String   parentId   = (String) map.get(cols[3]);
        String   parentName = (String) map.get(cols[4]);
        String   orgType    = (String) map.get(cols[5]);
        // 过滤都为空
        if (StringUtils.isEmpty(orgId) && StringUtils.isEmpty(orgName)) {
            return null;
        }
        // 过滤已存在
        RacOrgAddTo addTo = new RacOrgAddTo();
        if (!StringUtils.isEmpty(orgName)) {
            final RacOrgOneTo oneTo = new RacOrgOneTo();
            oneTo.setRealmId(Realm_ID);
            oneTo.setName(orgName);
            oneTo.setCode(orgId);
            RacOrgMo one = racOrgSvc.getOne(oneTo);
            if (one != null) {
                return one;
            }
            addTo.setName(orgName);
        }
        if (!StringUtils.isEmpty(orgId)) {
            final RacOrgOneTo oneTo = new RacOrgOneTo();
            oneTo.setRealmId(Realm_ID);
            oneTo.setCode(orgId);
            RacOrgMo one = racOrgSvc.getOne(oneTo);
            if (one != null) {
                return one;
            }
            addTo.setCode(orgId);
        }
        // 未存在
        // 1.无组织 无上级组织
        // 2.无组织 有上级组织
        // 2.有组织 无上级组织
        // 4.有组织 有上级组织
        addTo.setRealmId(Realm_ID);
        if (orgType.trim().matches("\\d{1,3}")) {
            Byte valueOf = Byte.valueOf(orgType);
            addTo.setOrgType(valueOf);
        }
        else {
            addTo.setOrgType((byte) 1);
        }
        if (parentId.isEmpty()) {
            addTo.setFullName(addTo.getName());
            RacOrgMo add = racOrgSvc.add(addTo);
            return add;
        }
        else {
            RacOrgOneTo oneTo = new RacOrgOneTo();
            oneTo.setRealmId(Realm_ID);
            oneTo.setCode(parentId);
            RacOrgMo mo = racOrgSvc.getOne(oneTo);
            if (mo != null) {
                addTo.setParentId(mo.getId());
                addTo.setFullName(mo.getFullName() + "/" + addTo.getName());
                final RacOrgOneTo oneMo = OrikaUtils.map(addTo, RacOrgOneTo.class);
                RacOrgMo          one   = racOrgSvc.getOne(oneMo);
                if (one != null) {
                    return one;
                }
                RacOrgMo add = racOrgSvc.add(addTo);
                return add;
            }
            // 按道理而言不会走到这里
            else {
                addTo.setFullName(addTo.getName());
                RacOrgMo add = racOrgSvc.add(addTo);
                return add;
            }
        }

    }

    /**
     * 插入帐号信息（新建帐号 ，更新帐号， 关联用户 ，关联组织）
     * 
     * @param orgMo2
     * @param userMo
     * 
     * @return RacAccountMo
     */
    // @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    private RacAccountMo insertAccountRecord(Map<String, Object> map, RacUserMo userMo, RacOrgMo orgMo) {
        // 获取字段数组
        String[] cols           = FieldCollection.getAccountInformationCol();
        String   signInName     = (String) map.get(cols[1]);
        String   signInNickname = (String) map.get(cols[2]);
        String   signInMobile   = (String) map.get(cols[4]);
        String   signInEmail    = (String) map.get(cols[5]);
        // 过滤登录名为空
        if (StringUtils.isEmpty(signInName) || StringUtils.isEmpty(signInNickname)) {
            return null;
        }
        RacAccountAddTo accountAddTo = new RacAccountAddTo();
        accountAddTo.setRealmId(Realm_ID);
        // 查询是否存在该帐号
        RacAccountOneTo accountSignInNameTo = new RacAccountOneTo();
        accountSignInNameTo.setSignInName(signInName);
        accountSignInNameTo.setRealmId(accountAddTo.getRealmId());
        RacAccountMo accountMo = racAccountSvc.getAccountMoOne(accountSignInNameTo);
        // 帐号是否存在
        if (accountMo != null) {
            return updateAccount(accountMo, userMo, orgMo);
        }
        if (!StringUtils.isEmpty(signInMobile)) {
            RacAccountOneTo accountMobileTo = new RacAccountOneTo();
            accountMobileTo.setSignInMobile(signInMobile);
            accountMobileTo.setRealmId(accountAddTo.getRealmId());
            RacAccountMo accountMobileMo = racAccountSvc.getAccountMoOne(accountMobileTo);
            if (accountMobileMo != null) {
                return updateAccount(accountMobileMo, userMo, orgMo);
            }
            accountAddTo.setSignInMobile(signInMobile);
        }
        if (!StringUtils.isEmpty(signInEmail)) {
            RacAccountOneTo accountEmailTo = new RacAccountOneTo();
            accountEmailTo.setSignInEmail(signInEmail);
            accountEmailTo.setRealmId(accountAddTo.getRealmId());
            RacAccountMo accountEmailMo = racAccountSvc.getAccountMoOne(accountEmailTo);
            if (accountEmailMo != null) {
                return updateAccount(accountEmailMo, userMo, orgMo);
            }
            accountAddTo.setSignInEmail(signInEmail);
        }

        accountAddTo.setSignInName(signInName);
        accountAddTo.setIsTester(false);
        accountAddTo.setIsEnabled(true);
        // // 设置默认密码12345678
        String signInPswdSalt = "zGxxxC";
        String signInPswd     = "25d55ad283aa400af464c76d713c07ad";
        accountAddTo.setSignInPswd(signInPswd);
        accountAddTo.setSignInPswdSalt(signInPswdSalt);
        if (!StringUtils.isEmpty(signInNickname)) {
            accountAddTo.setSignInNickname(signInNickname);
        }
        // 判断是否关联用户组织
        if (userMo != null) {
            accountAddTo.setUserId(userMo.getId());
        }
        if (orgMo != null) {
            accountAddTo.setOrgId(orgMo.getId());
        }
        RacAccountMo mo = racAccountSvc.add(accountAddTo);

        return mo;
    }

    /**
     * 更新帐号关联关系
     * 
     * @param mo
     * @param userMo
     * @param orgMo
     * 
     * @return RacAccountMo
     */
    private RacAccountMo updateAccount(RacAccountMo mo, RacUserMo userMo, RacOrgMo orgMo) {
        if (userMo != null) {
            mo.setUserId(userMo.getId());
        }
        if (orgMo != null) {
            mo.setOrgId(orgMo.getId());
        }
        RacAccountMo modifyMoById = racAccountSvc.modifyMoById(mo);
        return modifyMoById;
    }

    /**
     * 给excel表设置账户信息
     * 
     * @param workbook    Workbook workbook = new SXSSFWorkbook();
     * @param type
     * @param sheetNumber excel表的sheet位置
     */
    private void setSheet(Workbook workbook, String type, int sheetNumber) {
        // 表头样式
        CellStyle headerStyle = workbook.createCellStyle();
        // 字体
        Font      hssfFont    = workbook.createFont();
        hssfFont.setFontName("黑体");
        hssfFont.setFontHeightInPoints((short) 12);
        hssfFont.setBold(true);
        headerStyle.setFont(hssfFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // headerStyle.setFillBackgroundColor((short) 40);
        headerStyle.setFillPattern(FillPatternType.FINE_DOTS);
        headerStyle.setFillForegroundColor(HSSFColorPredefined.LIGHT_YELLOW.getIndex());
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        // 表格样式
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setAlignment(HorizontalAlignment.CENTER); // 水平布局：居中
        cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
        // cellStyle1.setBorderBottom(BorderStyle.THIN);
        cellStyle1.setBorderLeft(BorderStyle.THIN);
        cellStyle1.setBorderRight(BorderStyle.THIN);
        // cellStyle1.setBorderTop(BorderStyle.THIN);
        cellStyle1.setFillPattern(FillPatternType.FINE_DOTS);
        cellStyle1.setFillForegroundColor(HSSFColorPredefined.WHITE.getIndex());
        cellStyle1.setWrapText(true);
        CellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setAlignment(HorizontalAlignment.CENTER); // 水平布局：居中
        cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle2.setFillPattern(FillPatternType.FINE_DOTS);
        // cellStyle2.setBorderBottom(BorderStyle.THIN);
        cellStyle2.setBorderLeft(BorderStyle.THIN);
        cellStyle2.setBorderRight(BorderStyle.THIN);
        // cellStyle2.setBorderTop(BorderStyle.THIN);
        cellStyle2.setWrapText(true);
        cellStyle2.setFillForegroundColor(HSSFColorPredefined.GREY_50_PERCENT.getIndex());

        SXSSFSheet sheet;
        DataFormat dataFormat = workbook.createDataFormat();
        // setBorder.setDataFormat(dataFormat.getFormat("@"));

        switch (type) {
        case "account":
            sheet = (SXSSFSheet) workbook.createSheet("账户信息");
            sheet.setColumnWidth(0, 12 * 256);
            Row row1 = sheet.createRow(0);
            row1.setHeight((short) (30 * 20));
            String[] accountNameInformationCol = FieldCollection.getAccountNameInformationCol();
            for (int i = 0; i < accountNameInformationCol.length; i++) {
                setCell(row1, i, accountNameInformationCol[i], headerStyle);
            }
            break;
        case "org":
            sheet = (SXSSFSheet) workbook.createSheet("组织信息");
            sheet.setColumnWidth(0, 12 * 256);
            Row row2 = sheet.createRow(0);
            row2.setHeight((short) (30 * 20));
            String[] orgNameInformationCol = FieldCollection.getOrgNameInformationCol();
            for (int i = 0; i < orgNameInformationCol.length; i++) {
                setCell(row2, i, orgNameInformationCol[i], headerStyle);
            }
            break;
        default:
            throw new RuntimeExceptionX("不支持该类型: " + type);
        }
        sheet.trackAllColumnsForAutoSizing();
        for (int i = 1; i < 10; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    /**
     * 插入随机数据
     * 
     * @param sheet
     * @param startRowNum 开始行
     * @param endRowNum   结束行
     * @param cellStyle   表格样式
     * @param cellStyle2
     */
    @SuppressWarnings("unused")
    private void setRandomdata(Sheet sheet, int startRowNum, int endRowNum, CellStyle cellStyle1, CellStyle cellStyle2) {
        for (int i = startRowNum; i < endRowNum; i++) {
            CellStyle style = null;
            if (i % 2 == 0) {
                style = cellStyle1;
            }
            else {
                style = cellStyle2;
            }
            Row createRow = sheet.createRow(i);
            createRow.setHeight((short) (18 * 20));
            Random random = new Random();
            setCell(createRow, 0, (i - 1) + "", style);
            setCell(createRow, 1, StringUtils.leftPad(1000000 + i - 2 + "", 8, "1"), style);
            if (random.nextInt(2) % 2 == 0) {
                setCell(createRow, 2, getRandomWords(5), style);
            }
            else {
                setCell(createRow, 2, "", style);
            }
            setCell(createRow, 3, genCodes(6, 1).get(0), style);
            setCell(createRow, 4, RandomEx.random1(8), style);
            if (random.nextInt(2) % 2 == 0) {
                setCell(createRow, 5, getRandomWords(15), style);
            }
            else {
                setCell(createRow, 5, "", style);
            }
            setCell(createRow, 6, getRandomWords(3), style);
            setCell(createRow, 7, RandomEx.random2(18), style);
            if (random.nextInt(2) % 2 == 0) {
                setCell(createRow, 8, "1" + RandomEx.random2(10), style);
            }
            else {
                setCell(createRow, 8, "", style);
            }
            if (random.nextInt(2) % 2 == 0) {
                setCell(createRow, 9, RandomEx.random2(10) + "@qq.com", style);
            }
            else {
                setCell(createRow, 9, "", style);
            }
        }
    }

    /**
     * 给指Cell 设置指定的样式和值
     * 
     * @param row   当前前行
     * @param num   第几列 0开始
     * @param style 样式
     * 
     * @return Cell
     */
    private Cell setCell(Row row, int num, String str, CellStyle style) {
        Cell cell = row.createCell(num);
        cell.setCellValue(str);
        cell.setCellStyle(style);
        return cell;
    }

    /**
     * 生成随机汉字字符串
     * 
     * @return string
     */
    private String getRandomWords(int index) {
        char[] words = new char[index];

        for (int i = 0; i < words.length; i++) {
            words[i] = getRandomChar();
        }
        return String.valueOf(words);
    }

    /**
     * 生成随机一个汉字字符
     * 
     * @return char
     */
    public char getRandomChar() {

        String str    = "";
        int    hightPos;
        int    lowPos;

        Random random = new Random();

        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos   = (161 + Math.abs(random.nextInt(93)));

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();

        try {
            str = new String(b, "GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return str.charAt(0);
    }

    /**
     * 随机数
     * 
     * @param length
     * @param num
     */
    private List<String> genCodes(int length, long num) {
        List<String> results = new ArrayList<String>();
        for (int j = 0; j < num; j++) {
            String val    = "";
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

                if ("char".equalsIgnoreCase(charOrNum)) // 字符串
                {
                    int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
                    val += (char) (choice + random.nextInt(26));
                }
                else if ("num".equalsIgnoreCase(charOrNum)) // 数字
                {
                    val += String.valueOf(random.nextInt(10));
                }
            }
            val = val.toLowerCase();
            if (results.contains(val)) {
                continue;
            }
            else {
                results.add(val);
            }
        }
        return results;

    }
}
