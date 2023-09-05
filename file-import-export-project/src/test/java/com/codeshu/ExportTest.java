package com.codeshu;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author CodeShu
 * @date 2023/9/5 13:46
 */
@Slf4j
public class ExportTest {
	String PATH = "E:\\java\\program\\java-private-project\\file-import-export-project";

	/**
	 * .xls 的导出
	 */
	@Test
	public void export03() throws IOException {
		//1、创建一个工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();

		//2、创建一个底部的工作表
		HSSFSheet sheet = workbook.createSheet("sheet");

		//3、设置列宽度
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 9000);
		sheet.setColumnWidth(2, 7000);

		//4、创建第一行
		Row row1 = sheet.createRow(0);
		//5、为第一行添加列值，作为列名
		row1.createCell(0).setCellValue("ID");
		row1.createCell(1).setCellValue("姓名");
		row1.createCell(2).setCellValue("年龄");

		//6、循环插入6行
		for (int i = 1; i < 6; i++) {
			Row row = sheet.createRow(i);
			row.createCell(0).setCellValue(i);
			row.createCell(1).setCellValue("姓名" + i);
			row.createCell(2).setCellValue("年龄" + i);
		}

		//生成一张表(IO流)，03版本就是使用xls结尾
		FileOutputStream fos = new FileOutputStream(PATH + "\\user01.xls");
		//输出
		workbook.write(fos);
		//关闭流
		fos.close();
		log.info("导出完毕");
	}

	/**
	 * .xlsx 的导出
	 */
	@Test
	public void export07() throws IOException {
		//1、创建一个工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();

		//2、创建一个底部的工作表
		XSSFSheet sheet = workbook.createSheet("sheet");

		//3、设置列宽度
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 9000);
		sheet.setColumnWidth(2, 7000);

		//4、创建第一行
		Row row1 = sheet.createRow(0);
		//5、为第一行添加列值，作为列名
		row1.createCell(0).setCellValue("ID");
		row1.createCell(1).setCellValue("姓名");
		row1.createCell(2).setCellValue("年龄");

		//6、循环插入6行
		for (int i = 1; i < 6; i++) {
			Row row = sheet.createRow(i);
			row.createCell(0).setCellValue(i);
			row.createCell(1).setCellValue("姓名" + i);
			row.createCell(2).setCellValue("年龄" + i);
		}

		//生成一张表(IO流)，03版本就是使用xls结尾
		FileOutputStream fos = new FileOutputStream(PATH + "\\user02.xlsx");
		//输出
		workbook.write(fos);
		//关闭流
		fos.close();
		log.info("导出完毕");
	}
}
