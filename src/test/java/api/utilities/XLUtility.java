package api.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class XLUtility {

    public FileInputStream file;
    public XSSFWorkbook  workbook;

    public XSSFSheet sheet;

    public XSSFRow row;

    public XSSFCell cell;



    String path;


    public XLUtility(String path)
    {
        this.path=path;
    }
    public static void main(String[] args) throws IOException {

        XLUtility obj = new XLUtility("/Users/shibahar/Downloads/Udemydetails.xlsx");

        int ans=obj.getCellCount("Google Drive",3);
        System.out.println(ans);

        String ans2=obj.getCellData("Google Drive",5,0);
        System.out.println(ans2);

    }


    public int getRowCount(String sheetName) throws IOException {

        file = new FileInputStream(path);
        workbook = new XSSFWorkbook(file);
        sheet= workbook.getSheet(sheetName);
        int rowCount=sheet.getLastRowNum();
        workbook.close();
        file.close();
        return rowCount;
    }


    public int getCellCount(String sheetName,int rowNum) throws IOException {

        file = new FileInputStream(path);
        workbook = new XSSFWorkbook(file);
        sheet= workbook.getSheet(sheetName);
        row=sheet.getRow(rowNum);
        int cellCount= row.getLastCellNum();
        workbook.close();
        file.close();
        return cellCount;
    }




    public String getCellData(String sheetName,int rowNum,int colNum) throws IOException {

        file = new FileInputStream(path);
        workbook = new XSSFWorkbook(file);
        sheet= workbook.getSheet(sheetName);
        row=sheet.getRow(rowNum);
        cell=row.getCell(colNum);

        DataFormatter formatter = new DataFormatter();

        String data;

        try
        {
            data= formatter.formatCellValue(cell);// Returns the formatted value of the cell as string regardless of its type
        }
        catch (Exception e)
        {
            data="";
        }

        workbook.close();
        file.close();
        return data;

    }




}
