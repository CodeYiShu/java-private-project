package com.codeshu.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 导入工具类
 * <p>
 * 使用查看 ImportUtilsTest
 *
 * @author CodeShu
 * @date 2023/9/5 15:14
 */
@Slf4j
public class ImportUtils {
	private static final String PATH = "E:\\java\\program\\java-private-project\\file-import-export-project";

	public static void main(String[] args) throws IOException {
		// 获取文件流
		FileInputStream fis = new FileInputStream(PATH + "/user02.xlsx");
		// 获取一个工作簿
		Workbook workbook = new XSSFWorkbook(fis);

		List<String[]> dataList = ImportUtils.readExcelData(workbook, 0, 1);

		assert dataList != null;
		for (String[] data : dataList) {
			System.out.println(Arrays.toString(data));
		}
	}

	/**
	 * 将 Excel 表每条记录读取为一个 List 元素（可处理合并单元格）
	 *
	 * @param workbook      工作簿
	 * @param sheetIndex    底部第几个工作表
	 * @param startReadLine 开始读取的行数（如果第一行是列名，不想读取出来第一行则写 2 表示从第二行开始读取）
	 * @return list[ [列值1,列值2,列值3] ,[列值1,列值2,列值3] ]
	 */
	public static List<String[]> readExcelData(Workbook workbook, int sheetIndex, int startReadLine) {
		// 构造返回的数据结构（一行就是 List 的一个 String[] 元素，此行的一个单元格值就是 String[] 的一个 String 元素）
		List<String[]> result = new ArrayList<String[]>();
		// 获取工作表
		Sheet sheet = workbook.getSheetAt(sheetIndex);

		Row row;
		// 总行数
		int lastRowNum = sheet.getLastRowNum();
		// 获取第一行，以及根据第一行获取每一行的单元格个数（列数）
		Row firstRow = sheet.getRow(0);
		int cellNumber = firstRow.getPhysicalNumberOfCells();
		// 也可以使用以下方式获取单元格个数
		// int cellNumber = sheet.getRow(0).getLastCellNum();

		try {
			// 读取指定要开始读取的行数 ~ 最后一行
			for (int i = startReadLine - 1; i <= lastRowNum; i++) {
				// 获取当前行
				row = sheet.getRow(i);
				// 存放当前行的所有单元格值
				String[] values = new String[cellNumber];
				boolean isMerge;
				// 处理当前行的每一个单元格值
				for (int j = 0; j < cellNumber; j++) {
					// 获取当前单元格
					Cell cell = row.getCell(j);
					if (cell != null) {
						// 当前单元格的类型
						int cellType = cell.getCellType();
						// 将单元格的值，都转为 String 类型，放入数组中
						if (cellType == Cell.CELL_TYPE_NUMERIC && HSSFDateUtil.isCellDateFormatted(cell)) {
							DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							values[j] = format.format(cell.getDateCellValue());
						} else {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							// 判断是否具有合并单元格
							isMerge = isMergedRegion(sheet, i, cell.getColumnIndex());
							if (isMerge) {
								// 获取合并单元格的值
								String rs = getMergedRegionValue(sheet, row.getRowNum(), cell.getColumnIndex());
								values[j] = rs;
								log.debug("{}  ", values[j]);
							} else {
								values[j] = cell.getRichStringCellValue().toString();
							}
						}
					} else {
						// 当前单元格为空
						values[j] = null;
					}
				}
				result.add(values);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 判断指定的单元格是否是合并单元格
	 *
	 * @param sheet  工作表
	 * @param row    行下标
	 * @param column 列下标
	 */
	private static boolean isMergedRegion(Sheet sheet, int row, int column) {

		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {

			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 获取合并单元格的值
	 *
	 * @param sheet  工作表
	 * @param row    行下标
	 * @param column 列下标
	 */
	public static String getMergedRegionValue(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {

				if (column >= firstColumn && column <= lastColumn) {
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return getCellValue(fCell);
				}
			}
		}

		return null;
	}

	/**
	 * 获取单元格的值（都转为 String 类型）
	 *
	 * @param cell 列
	 */
	public static String getCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			return cell.getCellFormula();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		}
		return "";
	}
}
