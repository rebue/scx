package rebue.scx.rac.util;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import rebue.wheel.api.exception.RuntimeExceptionX;

public class ImporExcelUtil {

    public static boolean isXls(String fileName) {
        // (?i)忽略大小写
        if (fileName.matches("^.+\\.(?i)(xls)$")) {
            return true;
        }
        else if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return false;
        }
        else {
            throw new RuntimeExceptionX("格式不对");
        }
    }

    /**
     * 读取execl文件数据
     * 
     * @param startIndex 从第几行开始读
     * @param colWide    有多少列数据
     * @param cols       字段数组
     */
    public static List<Map<String, Object>> readExcel(InputStream inputStream, String fileName, int startIndex, int colWide, String[] cols) throws IOException {
        Workbook workbook = null;
        // 根据后缀创建不同的对象
        if (isXls(fileName)) {
            workbook = new HSSFWorkbook(inputStream);
        }
        else {
            workbook = new XSSFWorkbook(inputStream);
        }
        Sheet                     sheet      = workbook.getSheetAt(0);
        int                       lastRowNum = sheet.getLastRowNum();
        List<Map<String, Object>> list       = new ArrayList<>();
        for (int i = startIndex; i <= lastRowNum; i++) {
            Map<String, Object> map  = new HashMap<>();
            Row                 row  = sheet.getRow(i);
            boolean             flag = false;
            for (int j = 0; j < colWide; j++) {
                String key  = cols[j];
                Cell   cell = row.getCell(j);
                // 防止空指针异常
                if (cell == null) {
                    map.put(key, "");
                    continue;
                }
                // 主键
                // map.put("id", UUID.randomUUID().toString());
                if (j == 0) {
                    // 如果读取某一行为空值时，跳出循环
                    if (StringUtils.isBlank(getValue(cell))) {
                        flag = true;
                        break;
                    }
                }
                map.put(key, getValue(cell));
            }
            if (flag) {
                break;
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 静态
     * 
     * @param hssfCell
     */
    private static String getValue(Cell hssfCell) {
        if (hssfCell == null) {
            return String.valueOf("");
        }
        if (hssfCell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        }
        else if (hssfCell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(hssfCell)) {
                LocalDateTime localDateTimeCellValue = hssfCell.getLocalDateTimeCellValue();
                return localDateTimeCellValue.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
            }
            return String.valueOf((int) hssfCell.getNumericCellValue());
        }
        else if (hssfCell.getCellType() == CellType.BLANK) {
            return String.valueOf("");
        }
        else if (hssfCell.getCellType() == CellType.STRING) {
            return String.valueOf(hssfCell.getStringCellValue());
        }
        else {
            return String.valueOf("");
        }
    }
}
