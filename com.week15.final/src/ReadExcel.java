import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

    public static void main(String[] args) {

        try {  // E:\github-repositories\inu_algorithm\com.week15.final\changes_of_closed_price(20.06.07~21.06.06).xlsx
            FileInputStream file = new FileInputStream("E:/github-repositories/inu_algorithm/com.week15.final/changes_of_closed_price(20.06.07~21.06.06).xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            int rowIndex = 0;
            int columnIndex = 0;
            //시트 수 (첫번째에만 존재하므로 0을 준다)
            //만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
            XSSFSheet sheet = workbook.getSheetAt(0);
            //행의 수
            int rows = sheet.getPhysicalNumberOfRows();
            for (rowIndex = 0; rowIndex < rows; rowIndex++) {
                //행을읽는다
                XSSFRow row = sheet.getRow(rowIndex);
                if (row != null) {
                    //셀의 수
                    int cells = row.getPhysicalNumberOfCells();
                    for (columnIndex = 0; columnIndex <= cells; columnIndex++) {
                        //셀값을 읽는다
                        XSSFCell cell = row.getCell(columnIndex);
                        String value = "";
                        //셀이 빈값일경우를 위한 널체크
                        if (cell == null) {
                            continue;
                        } else {
                            //타입별로 내용 읽기
                            switch (cell.getCellType()) {
                                case XSSFCell.CELL_TYPE_FORMULA:
                                    value = cell.getCellFormula();
                                    break;
                                case XSSFCell.CELL_TYPE_NUMERIC:
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        Date date = cell.getDateCellValue();
                                        value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                    } else
                                        //cellString = String.valueOf(cell.getNumericCellValue());
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
                        System.out.println(rowIndex + "번 행 : " + columnIndex + "번 열 값은: " + value);
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}