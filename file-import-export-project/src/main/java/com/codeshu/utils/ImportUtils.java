package com.codeshu.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	/**
	 * 将 Excel 表每条记录读取为一个 List 元素（可处理合并单元格）
	 *
	 * @param workbook      工作簿
	 * @param sheetIndex    底部第几个工作表
	 * @param startReadLine 开始读取的行数（如果第一列是列名，不想读取出来则写 2 表示从第二行开始读取）
	 * @return list[ [列值1,列值2,列值3] ,[列值1,列值2,列值3] ]
	 */
	public static List<String[]> readExcelData(Workbook workbook, int sheetIndex, int startReadLine) {
		//构造返回的数据结构
		List<String[]> result = new ArrayList<String[]>();
		Sheet sheet = workbook.getSheetAt(sheetIndex);
		Row row;
		//总行数
		int lastRowNum = sheet.getLastRowNum();
		try {
			// 读取指定要开始读取的行数 ~ 最后一行
			for (int i = startReadLine - 1; i <= lastRowNum; i++) {
				row = sheet.getRow(i);
				//取第一行表头的列数
				int columnNum = sheet.getRow(0).getLastCellNum();
				//存放当前行的列值（单元格）
				String[] values = new String[columnNum];
				boolean isMerge;
				//处理当前行的每一个列（单元格）
				for (int j = 0; j < columnNum; j++) {
					Cell c = row.getCell(j);
					if (c != null) {
						//根据列（单元格）的类型，都转为 String 类型，放入数组中
						if (c.getCellType() == Cell.CELL_TYPE_NUMERIC && HSSFDateUtil.isCellDateFormatted(c)) {
							DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							values[j] = format.format(c.getDateCellValue());
						} else {
							c.setCellType(Cell.CELL_TYPE_STRING);
							// 判断是否具有合并单元格
							isMerge = isMergedRegion(sheet, i, c.getColumnIndex());
							if (isMerge) {
								// 获取合并单元格的值
								String rs = getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());
								values[j] = rs;
								log.debug("{}  ", values[j]);
							} else {
								values[j] = c.getRichStringCellValue().toString();
							}
						}
					} else {
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
