package rebue.scx.rac.test.svc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.svc.RacAccountSvc;
import rebue.scx.rac.to.RacAccountAddTo;
import rebue.scx.rac.to.RacAccountModifyTo;
import rebue.scx.rac.to.RacAccountPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 账户 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacAccountSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RacAccountSvc _svc;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper        dozerMapper;

    /**
     * 测试基本的增删改查
     */
    @Test
    @Disabled
    public void testCrud() {
        RacAccountAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacAccountAddTo) RandomEx.randomPojo(RacAccountAddTo.class);
            addTo.setSignInPswdSalt("aaa");
            addTo.setPayPswdSalt("bbb");
            addTo.setUserId(null);
            addTo.setOrgId(null);
            addTo.setRealmId("ops");
            log.info("添加账户的参数为：" + addTo);
            final RacAccountMo addRo = _svc.add(addTo);
            log.info("添加账户的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<RacAccountMo> pageResult = _svc.page(new RacAccountPageTo());
        log.info("查询账户的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个账户的参数为：" + id);
        final RacAccountMo getByIdResult = _svc.getById(id);
        log.info("获取单个账户的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final RacAccountModifyTo modifyTo = dozerMapper.map(addTo, RacAccountModifyTo.class);
        modifyTo.setId(id);
        log.info("修改账户的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除账户的参数为：" + id);
        _svc.delById(id);
    }

    @Test
    public void read() {
        try {
            FileInputStream inputStream = new FileInputStream("/home/yuanman/Desktop/账户信息收集表.xlsx");
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheetAt = workbook.getSheetAt(0);
            Row row = sheetAt.getRow(0);
            Cell cell = row.getCell(0);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Workbook workbook = new SXSSFWorkbook(100);
        // HSSFWorkbook
        Sheet sheet = workbook.createSheet("账户信息");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("帐号编码");
        row.createCell(1).setCellValue("登录昵称");
        row.createCell(2).setCellValue("登录帐号");
        row.createCell(3).setCellValue("登录密码");
        row.createCell(4).setCellValue("备注");
        row.createCell(5).setCellValue("姓名");
        row.createCell(6).setCellValue("身份证号");
        row.createCell(7).setCellValue("手机号码");
        row.createCell(8).setCellValue("电子邮箱");
        List<RacAccountMo> listAll = _svc.listAll();
        int size = listAll.size();
        for (int i = 0; i < listAll.size(); i++) {
            Row createRow = sheet.createRow(i + 1);
            createRow.createCell(0).setCellValue(listAll.get(i).getCode());
            createRow.createCell(1).setCellValue(listAll.get(i).getSignInNickname());
            createRow.createCell(2).setCellValue(listAll.get(i).getSignInName());
            // createRow.createCell(3).setCellValue("登录密码");
            createRow.createCell(4).setCellValue(listAll.get(i).getRemark());
            // createRow.createCell(5).setCellValue("姓名");
            // createRow.createCell(6).setCellValue("身份证号");
            // createRow.createCell(7).setCellValue("手机号码");
            // createRow.createCell(8).setCellValue("电子邮箱");
        }
        System.out.println(listAll.toString());
        File file = new File("/home/yuanman/Desktop/账户信息收集表.xlsx");
        boolean createFile = createFile(workbook, file);
        if (!createFile) {
            System.out.println("导出文件失败");
        }
    }

    @Test
    @Disabled
    public void write() {
        Workbook workbook = new SXSSFWorkbook(100);
        // HSSFWorkbook
        Sheet sheet = workbook.createSheet("账户信息");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("帐号编码");
        row.createCell(1).setCellValue("登录昵称");
        row.createCell(2).setCellValue("登录帐号");
        row.createCell(3).setCellValue("登录密码");
        row.createCell(4).setCellValue("备注");
        row.createCell(5).setCellValue("姓名");
        row.createCell(6).setCellValue("身份证号");
        row.createCell(7).setCellValue("手机号码");
        row.createCell(8).setCellValue("电子邮箱");
        List<RacAccountMo> listAll = _svc.listAll();
        int size = listAll.size();
        for (int i = 0; i < listAll.size(); i++) {
            Row createRow = sheet.createRow(i + 1);
            createRow.createCell(0).setCellValue(listAll.get(i).getCode());
            createRow.createCell(1).setCellValue(listAll.get(i).getSignInNickname());
            createRow.createCell(2).setCellValue(listAll.get(i).getSignInName());
            // createRow.createCell(3).setCellValue("登录密码");
            createRow.createCell(4).setCellValue(listAll.get(i).getRemark());
            // createRow.createCell(5).setCellValue("姓名");
            // createRow.createCell(6).setCellValue("身份证号");
            // createRow.createCell(7).setCellValue("手机号码");
            // createRow.createCell(8).setCellValue("电子邮箱");
        }
        System.out.println(listAll.toString());
        File file = new File("/home/yuanman/Desktop/账户信息收集表.xlsx");
        boolean createFile = createFile(workbook, file);
        if (!createFile) {
            System.out.println("导出文件失败");
        }
    }

    public static boolean createFile(Workbook workbook, File file) {
        try {
            FileOutputStream stream = new FileOutputStream(file);
            workbook.write(stream);
            stream.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
