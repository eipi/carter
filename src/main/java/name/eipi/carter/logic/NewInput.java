package name.eipi.carter.logic;

import name.eipi.carter.entity.LineItem;
import name.eipi.carter.entity.Report;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Damien on 07/09/2016.
 */
public class NewInput<T extends Report> {

    public void processExcel(T report, String filePath) throws Exception {

        try {
            File file = new File(filePath);
            FileInputStream inputStream = new FileInputStream(file);

            processExcel(report, inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void processExcel(T report, InputStream inputStream) throws Exception {

        try {

            //Get the workbook instance for XLS file
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

            //Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);
            readSheet(sheet, report);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readSheet(HSSFSheet hssfSheet, T report) throws Exception {
        // First rebaseline the existing entries
        Row column1 = hssfSheet.getRow(1);
        Cell dateCell = column1.getCell(0);
        String date = null;
        if (dateCell != null) {
            String dateCellValue = dateCell.getStringCellValue();
            if (dateCellValue.startsWith("Report Date:")) {
                date = dateCellValue.replaceFirst("Report Date:", "");
            }
        }
        report.rebaseline(date);
        //Iterate through each rows from first sheet
        if (hssfSheet.getLastRowNum() > 2) {

            Row columns = hssfSheet.getRow(2);
            Map<String, Integer> columnIndex = indexColumns(columns);

            for (int rowNum = 3; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                parseRow(hssfSheet.getRow(rowNum), columnIndex, report);
            }
        }
    }

    private Map<String, Integer> indexColumns(Row row) {
        Map<String, Integer> map = new HashMap<>();
        for (Cell cell : row) {
            map.put(cell.getStringCellValue(), cell.getColumnIndex());
        }
        System.out.println(map);
        return map;
    }

    private void parseRow(Row row, Map<String, Integer> map, T report) throws Exception {
        //For each row, iterate through each columns
        Map<String, String> rowData = new HashMap<>();
        for (String column : map.keySet()) {
            Cell cell = row.getCell(map.get(column));
            if (cell != null) {
                if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        rowData.put(column, cell.getDateCellValue().toString());
                    } else {
                        rowData.put(column, "" + cell.getNumericCellValue());
                    }
                } else if (HSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
                    rowData.put(column, "" + cell.getStringCellValue());
                }
            }
        }
        if (!rowData.isEmpty()) {
            report.add(rowData);
        }
    }

}
