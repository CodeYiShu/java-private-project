package com.codeshu.excel.common;

import cn.hutool.extra.pinyin.PinyinUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Excel 操作的工具类
 *
 * @author CodeShu
 * @date 2024/5/12 13:34
 */
public class ExcelCommonUtils {

	/**
	 * 根据 Excel 表中的中文字段，获取其中文和拼音
	 */
	public static Map<String, List<String>> getFieldCommendFromExcel(String filePath) throws IOException {
		// 获取文件流
		FileInputStream fileInputStream = new FileInputStream(filePath);
		// 将输入流封装成一个工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		// 获取底部第一个工作表
		XSSFSheet sheet = workbook.getSheet("Sheet0");

		// 获取行数
		int rowNumber = sheet.getPhysicalNumberOfRows();

		// 获取第一行，以及根据第一行获取列数（每一行的单元格个数）
		XSSFRow firstRow = sheet.getRow(0);
		int columnNumber = firstRow.getPhysicalNumberOfCells();

		// 表字段名称和注释
		List<String> fieldList = new ArrayList<>();
		List<String> commendList = new ArrayList<>();

		// 处理第一行
		for (int i = 0; i < columnNumber; i++) {
			// 获取第一行的每个单元格
			String cellValue = firstRow.getCell(i).toString();
			// 单元格的值就是注释名称
			commendList.add(cellValue);
			// 将单元格的值转为拼音作为表字段名称
			fieldList.add(PinyinUtil.getFirstLetter(cellValue, "").toUpperCase());
		}

		// 处理其他行
		for (int i = 1; i < rowNumber; i++) {
			XSSFRow otherRow = sheet.getRow(i);
			// 处理当前行的每个单元格
			for (int j = 0; j < columnNumber; j++) {
				if (Objects.nonNull(otherRow)) {
					// 获取此行的每个单元格
					XSSFCell cellValue = otherRow.getCell(j);
					if (Objects.nonNull(cellValue) && StringUtils.hasText(cellValue.toString())) {
						// 单元格的值就是注释名称
						commendList.add(cellValue.toString());
						// 将单元格的值转为拼音作为表字段名称
						fieldList.add(PinyinUtil.getFirstLetter(cellValue.toString(), "").toUpperCase());
					}
				}
			}
		}
		fileInputStream.close();
		Map<String, List<String>> result = new HashMap<>();
		result.put("fieldList", fieldList);
		result.put("commendList", commendList);
		return result;
	}
}
