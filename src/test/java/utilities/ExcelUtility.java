package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook workBook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	String path;

	public ExcelUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workBook.close();
		fi.close();
		return rowCount;
	}

	public int getCellCount(String sheetName, int rownum) throws IOException {
		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		workBook.close();
		fi.close();
		return cellCount;
	}

	public String getCellData(String sheetName, int rownum, int cellnum) throws IOException {
		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(cellnum);
		String data;
		try {
			// data = cell.getStringCellValue();
			// data = cell.toString();
			DataFormatter formatter = new DataFormatter();
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		workBook.close();
		fi.close();
		return data;
	}

	public void setCellData(String sheetName, int rownum, int cellnum, String data) throws IOException {
		File xlfile = new File(path);
		if (!xlfile.exists()) {
			workBook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workBook.write(fo);
		}

		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);

		if (workBook.getSheetIndex(sheetName) == -1)
			workBook.createSheet(sheetName);
		sheet = workBook.getSheet(sheetName);
		if (sheet.getRow(rownum) == null)
			sheet.createRow(rownum);
		row = sheet.getRow(rownum);
		cell = row.createCell(cellnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		workBook.write(fo);
		workBook.close();
		fi.close();
		fo.close();
	}

	public void fillGreenColor(String sheetName, int rownum, int cellnum) throws IOException {
		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(cellnum);

		style = workBook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);

		fi.close();

		fo = new FileOutputStream(path);
		workBook.write(fo);
		workBook.close();
		fo.close();
	}

	public void fillRedColor(String sheetName, int rownum, int cellnum) throws IOException {
		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(cellnum);

		style = workBook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);

		fi.close();

		fo = new FileOutputStream(path);
		workBook.write(fo);
		workBook.close();
		fo.close();
	}
}
