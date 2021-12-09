package rebue.scx.rac.svc.impl.ex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import com.google.common.io.Files;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.dic.OrgTypeDic;
import rebue.scx.rac.mapper.RacAccountRoleMapper;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.mo.RacAccountRoleMo;
import rebue.scx.rac.mo.RacOrgMo;
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
import rebue.scx.rac.to.RacUserAddTo;
import rebue.scx.rac.to.RacUserOneTo;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.core.RandomEx;
import rebue.wheel.core.util.OrikaUtils;

/**
 * Excel的实现类
 *
 * <pre>
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
    private RacAccountSvc        accountSvc;
    @Resource
    private RacOrgSvc            racOrgSvc;
    @Resource
    private RacUserSvc           racUserSvc;
    @Resource
    private RacRoleSvc           racRoleSvc;
    @Resource
    private RacAccountRoleMapper racAccountRoleMapper;

    /**
     * excel模板文件下载
     */
    @Override
    public Mono<Void> getExcelTemplate(final String type, final ServerHttpResponse response) {
        ZeroCopyHttpOutputMessage zeroCopyResponse = (ZeroCopyHttpOutputMessage) response;
        String                    fileName         = "账户信息收集表.xlsx";
        File                      targetFile       = null;
        try {
            targetFile = File.createTempFile("cateringConsole", "xlsx");
            Workbook workbook = new SXSSFWorkbook();
            setAccountSheet(workbook, 0);
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
    public Ro<?> getExcelContent(InputStream inputStream, String fileName) {
        final String fileExt         = Files.getFileExtension(fileName);
        Workbook     workbook;
        String       workbookContent = null;
        try {
            workbook        = WorkbookFactory.create(inputStream);
            workbookContent = getWorkbookfcAccountContent(workbook, 0);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeExceptionX("错误");
            // e.printStackTrace();
        }
        return Ro.success(workbookContent);
    }

    /**
     * 获取excel表fc模板帐号信息
     * 
     * @param workbook    Workbook workbook = new SXSSFWorkbook();
     * @param sheetNumber excel表的sheet位置
     */
    private String getWorkbookfcAccountContent(Workbook workbook, int sheetNumber) {
        Sheet                              sheetAt    = workbook.getSheetAt(0);
        Row                                row        = sheetAt.getRow(0);
        // ID
        Cell                               user_id1   = row.getCell(0);
        // 登录名
        Cell                               username1  = row.getCell(1);
        // 手机号
        Cell                               phone1     = row.getCell(4);
        // 帐号昵称
        Cell                               name1      = row.getCell(7);
        // 组织Id
        Cell                               dept_id1   = row.getCell(8);
        // 启用锁定
        Cell                               lock_flag1 = row.getCell(11);
        // 遍历行row
        Map<RacUserAddTo, RacAccountAddTo> map        = new HashMap<RacUserAddTo, RacAccountAddTo>();
        int                                n          = 0;
        for (int rownum = 1; rownum <= sheetAt.getLastRowNum(); rownum++) {
            Row sheetRow = sheetAt.getRow(rownum);
            if (sheetRow == null) {
                continue;
            }
            // ID
            Cell            user_id      = sheetRow.getCell(0);
            // 登录名
            Cell            username     = sheetRow.getCell(1);
            // 手机号
            Cell            phone        = sheetRow.getCell(4);
            // 帐号昵称
            Cell            name         = sheetRow.getCell(7);
            // 组织Id
            Cell            dept_id      = sheetRow.getCell(8);
            // 启用锁定
            Cell            lock_flag    = sheetRow.getCell(11);
            // 姓名
            Cell            stu_name     = sheetRow.getCell(30);
            // 身份证号
            Cell            id_no        = sheetRow.getCell(31);
            // int value = Integer.parseInt(getValue(sheetRow.getCell(12))) + 1;
            RacAccountAddTo accountAddTo = new RacAccountAddTo();
            accountAddTo.setRealmId("default");
            // String value2 = getValue(sheetRow.getCell(0));
            // orgAddTo.setTreeCode(value2);
            String id = getValue(sheetRow.getCell(0));
            accountAddTo.setSignInNickname(getValue(name));
            accountAddTo.setSignInMobile(getValue(phone));
            accountAddTo.setCode(getValue(user_id));
            accountAddTo.setSignInName(getValue(username));
            accountAddTo.setIsTester(false);
            accountAddTo.setOrgId((long) Integer.parseInt(getValue(dept_id)));
            accountAddTo.setIsEnabled(getValue(lock_flag).equals("0") ? true : false);
            RacUserAddTo userAddTo = new RacUserAddTo();
            userAddTo.setIdCard(getValue(id_no));
            userAddTo.setRealName(getValue(stu_name));

            map.put(userAddTo, accountAddTo);
        }
        addfcAccount(map, 3);
        return "添加完成";
    }

    /**
     * 添加fc帐号
     */
    private int addfcAccount(Map<RacUserAddTo, RacAccountAddTo> map, int leng) {
        Iterator<Map.Entry<RacUserAddTo, RacAccountAddTo>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<RacUserAddTo, RacAccountAddTo> entry     = it.next();
            RacUserAddTo                             usermo    = entry.getKey();
            RacAccountAddTo                          accountmo = entry.getValue();
            final RacUserOneTo                       user      = OrikaUtils.map(usermo, RacUserOneTo.class);
            RacUserMo                                userMoOne = null;
            if (user.getIdCard() != null || user.getRealName() != null) {
                userMoOne = racUserSvc.getUserMoOne(user);
            }
            if (userMoOne != null) {
                RacAccountOneTo oneTo = new RacAccountOneTo();
                oneTo.setCode(accountmo.getCode());
                oneTo.setRealmId(accountmo.getRealmId());
                RacAccountMo accountMoOne = accountSvc.getAccountMoOne(oneTo);
                if (accountMoOne != null) {
                    it.remove();
                    continue;
                }
                else {
                    String signInPswdSalt = "zGxxxC";
                    String signInPswd     = "25d55ad283aa400af464c76d713c07ad";
                    accountmo.setSignInPswd(signInPswd);
                    accountmo.setSignInPswdSalt(signInPswdSalt);
                    accountmo.setUserId(userMoOne.getId());
                    RacAccountMo     add     = accountSvc.add(accountmo);
                    RacAccountRoleMo rolenoe = new RacAccountRoleMo();
                    rolenoe.setAccountId(add.getId());
                    rolenoe.setRoleId(943048242716737536L);
                    RacAccountRoleMo selectOne = racAccountRoleMapper.selectOne(rolenoe).orElse(null);
                    if (selectOne != null) {
                        it.remove();
                        continue;
                    }
                    RacAccountRoleAddTo roleAddTo = new RacAccountRoleAddTo();
                    roleAddTo.setAccountId(add.getId());
                    List<Long> list = new ArrayList<Long>();
                    list.add(943048242716737536L);
                    roleAddTo.setRoleIds(list);
                    racRoleSvc.addAccountRole(roleAddTo);
                    it.remove();
                    continue;
                }
            }
            else {
                if (usermo.getIdCard() != null && usermo.getIdCard().length() == 18) {
                    RacUserMo adds = racUserSvc.add(usermo);
                    accountmo.setUserId(adds.getId());
                }
                RacAccountOneTo oneTo = new RacAccountOneTo();
                oneTo.setCode(accountmo.getCode());
                oneTo.setRealmId(accountmo.getRealmId());
                RacAccountMo accountMoOne = accountSvc.getAccountMoOne(oneTo);
                if (accountMoOne != null) {
                    it.remove();
                }
                else {
                    String signInPswdSalt = "zGxxxC";
                    String signInPswd     = "25d55ad283aa400af464c76d713c07ad";
                    accountmo.setSignInPswd(signInPswd);
                    accountmo.setSignInPswdSalt(signInPswdSalt);
                    RacAccountMo     add2    = accountSvc.add(accountmo);
                    RacAccountRoleMo rolenoe = new RacAccountRoleMo();
                    rolenoe.setAccountId(add2.getId());
                    rolenoe.setRoleId(943048242716737536L);
                    RacAccountRoleMo selectOne = racAccountRoleMapper.selectOne(rolenoe).orElse(null);
                    if (selectOne != null) {
                        it.remove();
                        continue;
                    }
                    RacAccountRoleAddTo roleAddTo = new RacAccountRoleAddTo();
                    roleAddTo.setAccountId(add2.getId());
                    List<Long> list = new ArrayList<Long>();
                    list.add(943048242716737536L);
                    roleAddTo.setRoleIds(list);
                    racRoleSvc.addAccountRole(roleAddTo);
                    it.remove();
                }
            }
        }
        if (map.isEmpty() || leng > 30) {
            return 1;
        }
        return addfcAccount(map, leng + 3);
    }

    /**
     * 获取excel表fc模板组织信息
     * 
     * @param workbook    Workbook workbook = new SXSSFWorkbook();
     * @param sheetNumber excel表的sheet位置
     */
    private String getWorkbookfcOrgContent(Workbook workbook, int sheetNumber) {
        Sheet                    sheetAt   = workbook.getSheetAt(0);
        Row                      row       = sheetAt.getRow(0);
        // ID
        Cell                     dept_id   = row.getCell(0);
        // 部门名称
        Cell                     name      = row.getCell(1);
        // 上级部门
        Cell                     parent_id = row.getCell(6);
        // 部门编码
        Cell                     dept_code = row.getCell(9);
        // 部门类型
        Cell                     cell4     = row.getCell(12);
        // 遍历行row
        Map<String, RacOrgAddTo> map       = new HashMap<String, RacOrgAddTo>();
        for (int rownum = 1; rownum <= sheetAt.getLastRowNum(); rownum++) {
            Row sheetRow = sheetAt.getRow(rownum);
            if (sheetRow == null) {
                continue;
            }

            int         value    = Integer.parseInt(getValue(sheetRow.getCell(12))) + 1;
            RacOrgAddTo orgAddTo = new RacOrgAddTo();
            orgAddTo.setRealmId("default");
            // String value2 = getValue(sheetRow.getCell(0));
            // orgAddTo.setTreeCode(value2);
            String id = getValue(sheetRow.getCell(0));
            orgAddTo.setId((long) Integer.parseInt(getValue(sheetRow.getCell(0))));
            orgAddTo.setCode(getValue(sheetRow.getCell(9)));
            orgAddTo.setName(getValue(sheetRow.getCell(1)));
            String value2 = getValue(sheetRow.getCell(6));
            if (value2 != null) {
                orgAddTo.setParentId((long) Integer.parseInt(value2));
            }
            // orgAddTo.setFullName(getValue(sheetRow.getCell(2)));
            if (value <= 5) {
                String desc    = OrgTypeDic.getItem((byte) value).getDesc();
                int    orgType = Integer.parseInt(desc);
                orgAddTo.setOrgType((byte) orgType);
            }
            else {
                orgAddTo.setOrgType((byte) 90);
            }
            map.put(id, orgAddTo);
        }
        addfcOrg(map, 3);
        return "添加完成";
    }

    /**
     * 添加fc组织
     */
    private int addfcOrg(Map<String, RacOrgAddTo> map, int leng) {
        Iterator<Map.Entry<String, RacOrgAddTo>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, RacOrgAddTo> entry = it.next();
            RacOrgAddTo                    mo    = entry.getValue();
            // int length = mo.getTreeCode().length();
            if (mo.getParentId() != null) {
                RacOrgMo one = racOrgSvc.getById(mo.getParentId());
                if (one == null) {
                    continue;
                }
                RacOrgMo one1 = racOrgSvc.getById(mo.getId());
                if (one1 != null) {
                    racOrgSvc.modifyMoById(one1);
                    it.remove();
                    continue;
                }
                mo.setParentId(one.getId());
                racOrgSvc.add(mo);
                it.remove();
            }
            else {
                RacOrgMo one = racOrgSvc.getById(mo.getId());
                if (one != null) {
                    racOrgSvc.modifyMoById(one);
                    it.remove();
                    continue;
                }
                racOrgSvc.add(mo);
                it.remove();
            }
        }
        if (map.isEmpty() || leng > 30) {
            return 1;
        }
        return addfcOrg(map, leng + 3);
    }

    /**
     * 获取excel表nnxy模板组织信息
     * 
     * @param workbook    Workbook workbook = new SXSSFWorkbook();
     * @param sheetNumber excel表的sheet位置
     */
    private String getWorkbooknnxyOrgContent(Workbook workbook, int sheetNumber) {
        Sheet                 sheetAt = workbook.getSheetAt(0);
        Row                   row     = sheetAt.getRow(0);
        // 树编码ID
        Cell                  cell    = row.getCell(0);
        // 部门名称
        Cell                  cell1   = row.getCell(1);
        // 部门全称
        Cell                  cell2   = row.getCell(2);
        // 部门级别
        Cell                  cell3   = row.getCell(3);
        Cell                  cell4   = row.getCell(4);
        // 遍历行row
        Map<String, RacOrgMo> map     = new HashMap<String, RacOrgMo>();
        int                   n       = 0;
        for (int rownum = 1; rownum <= sheetAt.getLastRowNum(); rownum++) {
            Row sheetRow = sheetAt.getRow(rownum);
            if (sheetRow == null) {
                continue;
            }

            int      value    = Integer.parseInt(getValue(sheetRow.getCell(3)));
            RacOrgMo orgAddTo = new RacOrgMo();
            orgAddTo.setRealmId("default");
            String value2 = getValue(sheetRow.getCell(0));
            orgAddTo.setTreeCode(value2);
            orgAddTo.setName(getValue(sheetRow.getCell(1)));
            orgAddTo.setFullName(getValue(sheetRow.getCell(2)));
            if (value <= 5) {
                String desc    = OrgTypeDic.getItem((byte) value).getDesc();
                int    orgType = Integer.parseInt(desc);
                orgAddTo.setOrgType((byte) orgType);
            }
            else {
                orgAddTo.setOrgType((byte) 90);
            }
            map.put(orgAddTo.getTreeCode(), orgAddTo);
            // 遍历列cell
            for (int cellnum = 0; cellnum <= sheetRow.getLastCellNum(); cellnum++) {
                if (cellnum == 3) {
                    String valueLevel = getValue(sheetRow.getCell(cellnum));
                }
                Cell cell11 = sheetRow.getCell(cellnum);
                if (cell11 == null) {
                    continue;
                }
                System.out.print("  " + getValue(cell11));
            }
            System.out.println();

        }
        addnnxyOrg(map, 3);
        return "添加完成";
    }

    /**
     * 添加nnxy组织
     */
    private int addnnxyOrg(Map<String, RacOrgMo> map, int leng) {
        Iterator<Map.Entry<String, RacOrgMo>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, RacOrgMo> entry  = it.next();
            RacOrgMo                    mo     = entry.getValue();
            int                         length = mo.getTreeCode().length();
            if (length == 3) {
                racOrgSvc.addMo(mo);
                it.remove();
            }
            else
                if (leng == length) {
                    RacOrgOneTo to = new RacOrgOneTo();
                    to.setRealmId(mo.getRealmId());
                    to.setTreeCode(mo.getTreeCode().substring(0, length - 3));
                    RacOrgMo one = racOrgSvc.getOne(to);
                    mo.setParentId(one.getId());
                    racOrgSvc.addMo(mo);
                    it.remove();
                }
        }
        if (map.isEmpty()) {
            return 1;
        }
        return addnnxyOrg(map, leng + 3);
    }

    /**
     * 静态
     * 
     * @param hssfCell
     * 
     * @return
     */
    private static String getValue(Cell hssfCell) {
        if (hssfCell == null) {
            return null;
        }
        if (hssfCell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        }
        else if (hssfCell.getCellType() == CellType.FORMULA) {
            return String.valueOf(hssfCell.getDateCellValue());
        }
        else if (hssfCell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(hssfCell)) {
                LocalDateTime localDateTimeCellValue = hssfCell.getLocalDateTimeCellValue();
                return localDateTimeCellValue.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
            }
            return String.valueOf((int) hssfCell.getNumericCellValue());
        }
        else if (hssfCell.getCellType() == CellType.BLANK) {
            return null;
        }
        else if (hssfCell.getCellType() == CellType.STRING) {
            return String.valueOf(hssfCell.getStringCellValue());
        }
        else {
            return String.valueOf("");
        }
    }

    /**
     * 给excel表设置账户信息
     * 
     * @param workbook    Workbook workbook = new SXSSFWorkbook();
     * @param sheetNumber excel表的sheet位置
     */
    private void setAccountSheet(Workbook workbook, int sheetNumber) {
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
        SXSSFSheet sheet      = (SXSSFSheet) workbook.createSheet("账户信息");

        DataFormat dataFormat = workbook.createDataFormat();
        // setBorder.setDataFormat(dataFormat.getFormat("@"));
        sheet.setColumnWidth(0, 12 * 256);
        Row row = sheet.createRow(0);
        row.setHeight((short) (25 * 20));
        setCell(row, 0, "是否必填", headerStyle);
        setCell(row, 1, "否", headerStyle);
        setCell(row, 2, "是", headerStyle);
        setCell(row, 3, "是", headerStyle);
        setCell(row, 4, "是", headerStyle);
        setCell(row, 5, "否", headerStyle);
        setCell(row, 6, "否", headerStyle);
        setCell(row, 7, "否", headerStyle);
        setCell(row, 8, "否", headerStyle);
        setCell(row, 9, "否", headerStyle);
        setCell(row, 10, "是", headerStyle);
        setCell(row, 11, "是", headerStyle);
        setCell(row, 12, "否", headerStyle);
        setCell(row, 13, "否", headerStyle);
        Row row2 = sheet.createRow(1);
        row2.setHeight((short) (30 * 20));
        setCell(row2, 0, "序号", headerStyle);
        setCell(row2, 1, "帐号编码", headerStyle);
        setCell(row2, 2, "登录昵称", headerStyle);
        setCell(row2, 3, "登录帐号", headerStyle);
        setCell(row2, 4, "登录密码", headerStyle);
        setCell(row2, 5, "微信openId", headerStyle);
        setCell(row2, 6, "微信unionId", headerStyle);
        setCell(row2, 7, "钉钉openId", headerStyle);
        setCell(row2, 8, "钉钉unionId", headerStyle);
        setCell(row2, 9, "备注", headerStyle);
        setCell(row2, 10, "姓名", headerStyle);
        setCell(row2, 11, "身份证号", headerStyle);
        setCell(row2, 12, "手机号码", headerStyle);
        setCell(row2, 13, "电子邮箱", headerStyle);
        // setRandomdata(sheet, 2, 1000, cellStyle1, cellStyle2);
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
