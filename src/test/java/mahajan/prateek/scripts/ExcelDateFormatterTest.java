package mahajan.prateek.scripts;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by: pramahajan on 7/16/19 5:24 PM GMT+05:30
 */

@RunWith(JUnit4.class)
public class ExcelDateFormatterTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelDateFormatterTest.class);


    @Test
    public void formatDateTime() throws IOException {
        String fileName = "/Users/pramahajan/Downloads/Prime_Short_LTD_analysis.xlsx";
        File xlfile = new File(fileName);
        FileInputStream inputStream = new FileInputStream(xlfile);

        //Create Workbook instance holding reference to .xls file
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        System.out.println("**** STARTING RUNNING SCRIPT ****");

        formatDatesInLocalFormat(xlfile, inputStream, workbook, sheet);

        System.out.println("**** DONE RUNNING SCRIPT ****");
    }

    private void formatDatesInLocalFormat(File xlfile, FileInputStream inputStream, XSSFWorkbook workbook, XSSFSheet sheet) {
        try
        {
            for (int i=1;i<sheet.getPhysicalNumberOfRows(); i++) { // run till sheet.getPhysicalNumberOfRows()
                try {
                    Row row = sheet.getRow(i);
                    //For each row, iterate through all the columns
                    Cell cell = row.getCell(6);

                    String bookingDateTime = cell.getStringCellValue();
                    ZonedDateTime parsedDateTime = ZonedDateTime.parse(bookingDateTime);
                    row.createCell(7).setCellValue(parsedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                } catch (Exception e) {
                    LOGGER.error("Error in row {}", i , e);
                }
            }
            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream(xlfile);

            workbook.write(outputStream);


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
