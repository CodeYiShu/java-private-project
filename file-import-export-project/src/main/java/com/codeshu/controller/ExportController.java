package com.codeshu.controller;

import cn.hutool.core.date.DateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author CodeShu
 * @date 2023/9/5 15:59
 */
@RestController
public class ExportController {
	@GetMapping("exportData")
	public void exportData(HttpServletResponse response) {
		// 列名称
		List<String> columnNameList = Arrays.asList("ID", "姓名", "年龄");
		// 每一行单元格的数量（列数）
		int cellNum = columnNameList.size();

		// 1、创建一个工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();

		// 2、创建一个底部的工作表
		XSSFSheet sheet = workbook.createSheet("sheet");

		// 3、设置每个单元格宽度
		for (int i = 0; i < cellNum; i++) {
			sheet.setColumnWidth(i, 5000);
		}

		// 4、创建第一行，第一行是列名称
		Row firstRow = sheet.createRow(0);
		// 5、设置第一行的高度
		firstRow.setHeight((short) 600);
		// 6、设置第一行的列名称
		for (int i = 0; i < cellNum; i++) {
			Cell firstCell = firstRow.createCell(i);
			firstCell.setCellValue(columnNameList.get(i));
		}

		// 7、插入其他行数据（从第 2 行开始）
		List<List<Object>> rowDataList = Arrays.asList(Arrays.asList(1, "张三", 12), Arrays.asList(2, "李四", 13));
		int rowIndex = 1;
		for (List<Object> rowData : rowDataList) {
			// 创建当前行
			Row row = sheet.createRow(rowIndex);
			// 生成当前行的多个单元格
			for (int i = 0; i < cellNum; i++) {
				// 创建单元格
				Cell cell = row.createCell(i);
				// 单元格的值
				Object cellValue = rowData.get(i);
				if (Objects.isNull(cellValue)) {
					cell.setCellValue("");
				} else if (cellValue instanceof String) {
					cell.setCellValue(StringUtils.hasText((String) cellValue) ? (String) cellValue : "");
				} else if (cellValue instanceof Date) {
					cell.setCellValue(DateUtil.format((Date) cellValue, "yyyy-MM-dd"));
				} else {
					cell.setCellValue(cellValue.toString());
				}
			}
			rowIndex++;
		}

		try {
			response.reset();
			response.setHeader("Access-Control-Allow-Origin", "*");
			// 设置 response 的 Header
			String fileName = UUID.randomUUID().toString();
			response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xlsx");
			response.setContentType("multipart/form-data");
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
