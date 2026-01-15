package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    String path;
    FileInputStream fi;
    FileOutputStream fo;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFRow row;
    XSSFCell cell;
    XSSFCellStyle style;

    public ExcelUtility(String path) {
        this.path = path;
    }

    // ✅ Returns LAST ROW INDEX (not count)
    public int getrowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        int rowcount = sheet.getLastRowNum();

        workbook.close();
        fi.close();
        return rowcount;
    }

    // ✅ Safe cell count
    public int getCellCount(String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);

        int cellcount = (row == null) ? 0 : row.getLastCellNum();

        workbook.close();
        fi.close();
        return cellcount;
    }

    // ✅ Safe cell data reader
    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);

        if (row == null) {
            workbook.close();
            fi.close();
            return "";
        }

        cell = row.getCell(colnum);
        DataFormatter formatter = new DataFormatter();
        String data = "";

        if (cell != null) {
            data = formatter.formatCellValue(cell);
        }

        workbook.close();
        fi.close();
        return data;
    }

    // ✅ Safe write method
    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {

        File xlfile = new File(path);
        if (!xlfile.exists()) {
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
            fo.close();
        }

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        if (workbook.getSheetIndex(sheetName) == -1)
            workbook.createSheet(sheetName);

        sheet = workbook.getSheet(sheetName);

        if (sheet.getRow(rownum) == null)
            sheet.createRow(rownum);

        row = sheet.getRow(rownum);
        cell = row.createCell(colnum);
        cell.setCellValue(data);

        fi.close();
        fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fo.close();
    }

    // ✅ Green color fill (FIXED)
    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fi.close();
        fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fo.close();
    }

    // ✅ Red color fill (FIXED)
    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fi.close();
        fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fo.close();
    }
}
