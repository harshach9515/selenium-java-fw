package com.data;
import org.apache.poi.ss.usermodel.*;

import com.utils.ConfigReader;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.*;

public class ExcelUtils {

    public static TestData getTestData(String testName) {
        TestData testData = new TestData();
        try {
        	FileInputStream fis = new FileInputStream(ConfigReader.get("testData"));
            Workbook workbook = WorkbookFactory.create(fis);

            Sheet sheet = workbook.getSheet("testdata");
            Iterator<Row> rows = sheet.iterator();

            // Read headers
            Row headerRow = rows.next();
            List<String> headers = new ArrayList<String>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue().trim());
            }

            // Find matching TestName row
            while (rows.hasNext()) {
                Row row = rows.next();
                Cell testNameCell = row.getCell(headers.indexOf("TestName"));

                if (testNameCell != null && testName.equalsIgnoreCase(testNameCell.getStringCellValue())) {
                    for (int i = 0; i < headers.size(); i++) {
                        String columnName = headers.get(i).trim().toLowerCase().replace(" ", "");
                        Cell cell = row.getCell(i);
                        String value = (cell == null) ? "" : cell.toString();

                        try {
                            // match column to POJO field name
                            Field field = TestData.class.getDeclaredField(columnName);
                            field.setAccessible(true);
                            field.set(testData, value);
                        } catch (NoSuchFieldException ignored) {
                            // if POJO doesn’t have a field, just skip
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testData;
    }
}
