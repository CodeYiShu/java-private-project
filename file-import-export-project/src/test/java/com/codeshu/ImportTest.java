package com.codeshu;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author CodeShu
 * @date 2023/9/5 14:19
 */
public class ImportTest {
	String PATH = "E:\\java\\program\\java-private-project\\file-import-export-project";

	/**
	 * .xls 导入
	 */
	@Test
	public void import03() throws IOException {
		//1、获取文件流
		FileInputStream fileInputStream = new FileInputStream(PATH + "\\user01.xls");

		//2、将输入流封装成一个工作簿
		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);

		//3、获取底部第一个工作表
		HSSFSheet sheet = workbook.getSheet("sheet");

		//4、获取行数
		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();

		//5、获取第一行（定义列名）和列数
		HSSFRow row = sheet.getRow(0);
		int physicalNumberOfCells = row.getPhysicalNumberOfCells();
		for (int i = 0; i < physicalNumberOfCells; i++) {
			//6、获取此行的列值
			System.out.print(row.getCell(i) + "\t");
		}

		//6、处理其他行
		for (int i = 1; i < physicalNumberOfRows; i++) {
			HSSFRow otherRow = sheet.getRow(i);
			System.out.println();
			for (int j = 0; j < physicalNumberOfCells; j++) {
				//获取此行的列值
				System.out.print(otherRow.getCell(j) + "\t");
			}
		}
		fileInputStream.close();
	}

	/**
	 * .xlsx 导入
	 */
	@Test
	public void import07() throws IOException {
		//1、获取文件流
		FileInputStream fileInputStream = new FileInputStream(PATH + "\\user02.xlsx");

		//2、将输入流封装成一个工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

		//3、获取底部第一个工作表
		XSSFSheet sheet = workbook.getSheet("sheet");

		//4、获取行数
		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();

		//5、获取第一行（定义列名）和列数
		XSSFRow row = sheet.getRow(0);
		int physicalNumberOfCells = row.getPhysicalNumberOfCells();
		for (int i = 0; i < physicalNumberOfCells; i++) {
			//6、获取此行的列值
			System.out.print(row.getCell(i) + "\t");
		}

		//6、处理其他行
		for (int i = 1; i < physicalNumberOfRows; i++) {
			XSSFRow otherRow = sheet.getRow(i);
			System.out.println();
			for (int j = 0; j < physicalNumberOfCells; j++) {
				//获取此行的列值
				System.out.print(otherRow.getCell(j) + "\t");
			}
		}
		fileInputStream.close();
	}

	/**
	 * 根据类型来导入
	 */
	@Test
	public void CellType() throws Exception {
		//获取文件流
		FileInputStream fis = new FileInputStream(PATH + "/user02.xlsx");
		//获取一个工作簿
		Workbook workbook = new XSSFWorkbook(fis);
		//获取一个工作表
		Sheet sheet = workbook.getSheetAt(0);
		//获取第一行内容
		Row row = sheet.getRow(0);
		if (row != null) {
			//获取所有的列
			int Cells = row.getPhysicalNumberOfCells();
			for (int col = 0; col < Cells; col++) {
				//获取当前列
				Cell cell = row.getCell(col);
				if (cell != null) {
					//获取当前行的第 col 列的值
					String cellValue = cell.getStringCellValue();
					System.out.print(cellValue + " | ");
				}
			}
		}
		//获取标准的内容
		//获取有多少行
		int rowCount = sheet.getPhysicalNumberOfRows();
		//从1开始，第一行是标题
		for (int rowNum = 1; rowNum < rowCount; rowNum++) {
			Row rowData = sheet.getRow(rowNum);
			if (rowData != null) {
				//获取当前行的列数
				int cellCount = rowData.getPhysicalNumberOfCells();
				System.out.println();
				for (int col = 0; col < cellCount; col++) {
					//获取当前列的值
					Cell cellData = rowData.getCell(col);
					//打印当前行当前列的值
					System.out.print("[" + (rowNum + 1) + "-" + (col + 1) + "]");
					//匹配列的类型
					if (cellData != null) {
						//获取列的类型
						int cellType = cellData.getCellType();
						String cellValue = "";
						switch (cellType) {
							case Cell.CELL_TYPE_STRING://字符串
								System.out.print("[string]");
								cellValue = cellData.getStringCellValue();
								break;
							case Cell.CELL_TYPE_BOOLEAN://布尔
								System.out.print("[boolean]");
								cellValue = String.valueOf(cellData.getBooleanCellValue());
								break;
							case Cell.CELL_TYPE_BLANK://空
								System.out.print("[blank]");
								break;
							case Cell.CELL_TYPE_NUMERIC://数字（日期、普通数字）
								System.out.print("[numeric]");
								if (HSSFDateUtil.isCellDateFormatted(cellData)) {
									//如果是日期
									System.out.print("[日期] ");
									Date date = cellData.getDateCellValue();
									cellValue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
								} else {
									//不是日期格式，防止数字过长
									System.out.print("[转换字符串输出] ");
									//转为字符串
									cellData.setCellType(HSSFCell.CELL_TYPE_STRING);
									cellValue = cellData.toString();

								}
								break;
							case Cell.CELL_TYPE_ERROR://错误
								System.out.print("[error]");
								break;
						}
						System.out.print("[" + cellValue + "]\n");
					}
				}
			}
		}

		System.out.println();
		System.out.println("over");
		fis.close();
	}

}
