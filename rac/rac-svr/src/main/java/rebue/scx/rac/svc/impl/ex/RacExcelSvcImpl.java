package rebue.scx.rac.svc.impl.ex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
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
import rebue.scx.rac.svc.RacAccountSvc;
import rebue.scx.rac.svc.ex.RacExcelSvc;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.core.RandomEx;

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
    private RacAccountSvc accountSvc;

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
        final String fileExt = Files.getFileExtension(fileName);
        Workbook     workbook;
        try {
            workbook = WorkbookFactory.create(inputStream);
            String workbookContent = getWorkbookAccountContent(workbook, 0);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeExceptionX("错误");
            // e.printStackTrace();
        }
        return Ro.success(null);
    }

    /**
     * 获取excel表模板账户信息
     * 
     * @param workbook    Workbook workbook = new SXSSFWorkbook();
     * @param sheetNumber excel表的sheet位置
     */
    private String getWorkbookAccountContent(Workbook workbook, int sheetNumber) {
        Sheet sheetAt = workbook.getSheetAt(0);
        Row   row     = sheetAt.getRow(0);
        Cell  cell    = row.getCell(0);
        Cell  cell1   = row.getCell(1);
        Cell  cell2   = row.getCell(2);
        Cell  cell3   = row.getCell(3);
        Cell  cell4   = row.getCell(4);
        // 遍历行row
        for (int rownum = 0; rownum <= sheetAt.getLastRowNum(); rownum++) {
            Row sheetRow = sheetAt.getRow(rownum);
            if (sheetRow == null) {
                continue;
            }
            // 遍历列cell
            for (int cellnum = 0; cellnum <= sheetRow.getLastCellNum(); cellnum++) {
                Cell cell11 = sheetRow.getCell(cellnum);
                if (cell11 == null) {
                    continue;
                }
                System.out.print("  " + getValue(cell11));
            }
            System.out.println();

        }
        System.out.println(cell1);
        System.out.println(cell2);
        System.out.println(cell3);
        System.out.println(cell4);
        return "账户";
    }

    /**
     * 静态
     * 
     * @param hssfCell
     * 
     * @return
     */
    private static String getValue(Cell hssfCell) {
        if (hssfCell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        }
        else if (hssfCell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        }
        else if (hssfCell.getCellType() == CellType.BLANK) {
            return "null";
        }
        else if (hssfCell.getCellType() == CellType.STRING) {
            return String.valueOf(hssfCell.getStringCellValue());
        }
        else {
            return String.valueOf("未知数据");
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
        setCell(row, 6, "是", headerStyle);
        setCell(row, 7, "是", headerStyle);
        setCell(row, 8, "否", headerStyle);
        setCell(row, 9, "否", headerStyle);
        Row row2 = sheet.createRow(1);
        row2.setHeight((short) (30 * 20));
        setCell(row2, 0, "序号", headerStyle);
        setCell(row2, 1, "帐号编码", headerStyle);
        setCell(row2, 2, "登录昵称", headerStyle);
        setCell(row2, 3, "登录帐号", headerStyle);
        setCell(row2, 4, "登录密码", headerStyle);
        setCell(row2, 5, "备注", headerStyle);
        setCell(row2, 6, "姓名", headerStyle);
        setCell(row2, 7, "身份证号", headerStyle);
        setCell(row2, 8, "手机号码", headerStyle);
        setCell(row2, 9, "电子邮箱", headerStyle);
        setRandomdata(sheet, 2, 1000, cellStyle1, cellStyle2);
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