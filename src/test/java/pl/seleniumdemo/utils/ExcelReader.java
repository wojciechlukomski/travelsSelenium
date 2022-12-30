package pl.seleniumdemo.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    
    public static void readExcel(String fileName) throws IOException {
        File file = new File("src/test/resources/" + fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        
        Workbook workbook = null;
        String fileExt = fileName.substring(fileName.indexOf("."));
        if (fileExt.equals(".xlsx")) {
            workbook = new XSSFWorkbook(fileInputStream);
        } else if (fileExt.equals(".xls")) {
            workbook = new HSSFWorkbook(fileInputStream);
        }
        
        Sheet sheet = workbook.getSheetAt(0);
        
        int rowCount = sheet.getLastRowNum();
        
        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            
            System.out.println(row.getCell(0).getStringCellValue());
            System.out.println(row.getCell(1).getStringCellValue());
        }
    }
    
    public static void main(String[] args) throws IOException {
        readExcel("CityHotel.xlsx");
    }
}
