import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
    ArrayList<Point2D> stockPrice;

    public void makeStockPriceList() {
        try {
            FileInputStream file = new FileInputStream("E:/github-repositories/inu_algorithm/com.week15.final/changes_of_closed_price(20.06.07~21.06.06).xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            int rowIndex = 0;
            int columnIndex = 0;
            XSSFSheet sheet = workbook.getSheetAt(0);

            int rows = sheet.getPhysicalNumberOfRows();
            for (rowIndex = 0; rowIndex < rows; rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                if (row != null) {
                    int cells = row.getPhysicalNumberOfCells();
                    for (columnIndex = 0; columnIndex <= cells; columnIndex++) {
                        XSSFCell cell = row.getCell(columnIndex);
                        String value = "";
                        if (cell == null) {
                            continue;
                        } else {
                            switch (cell.getCellType()) {
                                case XSSFCell.CELL_TYPE_FORMULA:
                                    value = cell.getCellFormula();
                                    break;
                                case XSSFCell.CELL_TYPE_NUMERIC:
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        Date date = cell.getDateCellValue();
                                        value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                    } else
                                        value = cell.getNumericCellValue() + "";
                                    break;
                                case XSSFCell.CELL_TYPE_STRING:
                                    value = cell.getStringCellValue() + "";
                                    break;
                                case XSSFCell.CELL_TYPE_BLANK:
                                    value = cell.getBooleanCellValue() + "";
                                    break;
                                case XSSFCell.CELL_TYPE_ERROR:
                                    value = cell.getErrorCellValue() + "";
                                    break;
                            }
                        }
                        System.out.println("Row: " + rowIndex + ", Col: " + columnIndex + " => Value = " + value);
                        if (columnIndex == 3)
                            this.stockPrice.add(makePoint(rowIndex - 2, Double.parseDouble(value)));
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Point2D makePoint(double x, double y) {
        return new Point2D.Double(x, y);
    }
}