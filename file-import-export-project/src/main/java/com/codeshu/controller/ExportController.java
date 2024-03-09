package com.codeshu.controller;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @author CodeShu
 * @date 2023/9/5 15:59
 */
@RestController
public class ExportController {
	@GetMapping("exportData")
	public void exportData(HttpServletResponse response) {
		// 1、创建一个工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();

		// 2、创建一个底部的工作表
		XSSFSheet sheet = workbook.createSheet("sheet");

		// 3、设置列宽度
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 9000);
		sheet.setColumnWidth(2, 7000);

		// 4、创建第一行
		Row row1 = sheet.createRow(0);
		// 5、为第一行添加单元格值，作为列名
		row1.createCell(0).setCellValue("ID");
		row1.createCell(1).setCellValue("姓名");
		row1.createCell(2).setCellValue("年龄");

		// 6、循环插入6行
		for (int i = 1; i < 6; i++) {
			Row row = sheet.createRow(i);
			row.createCell(0).setCellValue(i);
			row.createCell(1).setCellValue("姓名" + i);
			row.createCell(2).setCellValue("年龄" + i);
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
