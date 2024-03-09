package com.codeshu;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.codeshu.bean.BasGyqyNew;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author CodeShu
 * @date 2023/9/5 14:19
 */
public class GenerateCodeTest {
	String PATH = "E:\\java\\program\\java-private-project\\file-import-export-project";

	/**
	 * 生成表
	 */
	@Test
	public void generateCreateTable() throws IOException {
		//1、获取文件流
		FileInputStream fileInputStream = new FileInputStream(PATH + "\\字段注释.xlsx");

		//2、将输入流封装成一个工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

		//3、获取底部第一个工作表
		XSSFSheet sheet = workbook.getSheet("Sheet0");

		//4、获取行数
		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();

		//5、获取第一行（定义列名）和列数
		XSSFRow row = sheet.getRow(0);
		int physicalNumberOfCells = row.getPhysicalNumberOfCells();
		List<String> fieldList = new ArrayList<>();
		List<String> commendList = new ArrayList<>();
		for (int i = 0; i < physicalNumberOfCells; i++) {
			//6、获取此行的列值
			fieldList.add(PinyinUtil.getFirstLetter(row.getCell(i).toString(), ""));
			commendList.add(row.getCell(i).toString());
		}

		//7、处理其他行
		for (int i = 1; i < physicalNumberOfRows; i++) {
			XSSFRow otherRow = sheet.getRow(i);
			for (int j = 0; j < physicalNumberOfCells; j++) {
				if (Objects.nonNull(otherRow)) {
					//获取此行的列值
					XSSFCell cell = otherRow.getCell(j);
					if (Objects.nonNull(cell) && StringUtils.isNotBlank(cell.toString())) {
						fieldList.add(PinyinUtil.getFirstLetter(cell.toString(), "").toUpperCase());
						commendList.add(cell.toString());
					}
				}
			}
		}
		fileInputStream.close();

		// 生成表
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("CREATE TABLE BYDBC_ORIGIN.HT135_GYQY2(\n");
		for (String field : fieldList) {
			stringBuilder.append("\"").append(field).append("\" VARCHAR2(2000),\n");
		}
		stringBuilder.append(") STORAGE(ON \"BYDBC\", CLUSTERBTR) ;");
		System.out.println(stringBuilder);

		// 生成注释
		StringBuilder stringBuilder2 = new StringBuilder();
		for (int i = 0; i < fieldList.size(); i++) {
			String filed = fieldList.get(i);
			String commend = commendList.get(i);
			stringBuilder2.append("COMMENT ON COLUMN \"BYDBC_ORIGIN\".\"HT135_GYQY2\".\"").append(filed).append("\" ").append("IS '").append(commend).append("';\n");
		}
		System.out.println(stringBuilder2);
	}

	/**
	 * 生成前端页面
	 */
	@Test
	public void generateQianDuan() throws IOException {
		//1、获取文件流
		FileInputStream fileInputStream = new FileInputStream(PATH + "\\表字段和字段注释.xlsx");

		//2、将输入流封装成一个工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

		//3、获取底部第一个工作表
		XSSFSheet sheet = workbook.getSheet("Sheet0");

		//4、获取行数
		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();

		//5、获取第一行（定义列名）和列数
		XSSFRow row = sheet.getRow(0);
		int physicalNumberOfCells = row.getPhysicalNumberOfCells();
		List<String> fieldList = new ArrayList<>();
		List<String> commendList = new ArrayList<>();
		for (int i = 0; i < physicalNumberOfCells; i++) {
			//6、获取此行的列值
			fieldList.add(row.getCell(0).toString().toLowerCase());
			commendList.add(row.getCell(1).toString());
		}

		//7、处理其他行
		for (int i = 1; i < physicalNumberOfRows; i++) {
			XSSFRow otherRow = sheet.getRow(i);
			for (int j = 0; j < physicalNumberOfCells; j = j + 2) {
				if (Objects.nonNull(otherRow)) {
					//获取此行的列值
					XSSFCell cell1 = otherRow.getCell(j);
					XSSFCell cell2 = otherRow.getCell(j + 1);
					if (Objects.nonNull(cell1) && Objects.nonNull(cell2)) {
						fieldList.add(cell1.toString().toLowerCase());
						commendList.add(cell2.toString().toLowerCase());
					}
//					if (Objects.nonNull(cell) && StringUtils.isNotBlank(cell.toString())) {
//						fieldList.add(otherRow.getCell(j).toString().toLowerCase());
//						commendList.add(otherRow.getCell(j+1).toString());
//					}
				}
			}
		}
		fileInputStream.close();

		// 前端页面
		StringBuilder stringBuilder = new StringBuilder();
		fieldList = fieldList.stream().filter(f -> !f.equals("xh") && !f.equals("id")).collect(Collectors.toList());
		commendList = commendList.stream().filter(f -> !f.equals("序号") && !f.equals("id")).collect(Collectors.toList());
		for (int i = 0; i < fieldList.size(); i = i + 2) {
			String filed1 = fieldList.get(i).toLowerCase();
			String commend1 = commendList.get(i);

			stringBuilder.append("\t\t\t\t\t\t<tr>\n" +
					"\t\t\t\t\t\t\t<td style=\"width:15%;text-align:right;\"><label class=\"control-label\">" + commend1 + "</label></td>\n" +
					"\t\t\t\t\t\t\t<td style=\"width:35%;\">\n" +
					"\t\t\t\t\t\t\t\t<vm-ele-input name=\"" + filed1 + "\" v-model=\"data." + filed1 + "\" :can-edit=\"canEdit\"></vm-ele-input>\n" +
					"\t\t\t\t\t\t\t</td>\n");
			if (i != fieldList.size() - 1) {
				String filed2 = fieldList.get(i + 1).toLowerCase();
				String commend2 = commendList.get(i + 1);
				stringBuilder.append("\t\t\t\t\t\t\t<td style=\"width:15%;text-align:right;\"><label class=\"control-label\">" + commend2 + "</label></td>\n" +
						"\t\t\t\t\t\t\t<td style=\"width:35%;\">\n" +
						"\t\t\t\t\t\t\t\t<vm-ele-input name=\"" + filed2 + "\" v-model=\"data." + filed2 + "\" :can-edit=\"canEdit\"></vm-ele-input>\n" +
						"\t\t\t\t\t \t\t</td>\n" +
						"\t\t\t\t\t\t</tr>\n");
			}
		}
//		for (int i = 0; i < fieldList.size(); i++) {
//			String filed1 = fieldList.get(i).toLowerCase();
//			String commend1 = commendList.get(i);
//			stringBuilder.append(filed1 + ":''\n");
//		}
		System.out.println(stringBuilder);
	}

	/**
	 * 生成导出
	 */
	@Test
	public void generateExport() throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		// 字段名称
		List<String> fieldNameList = getFieldAndColumnNames(new BasGyqyNew()).get("fieldNames");
		for (int i = 0; i < fieldNameList.size(); i++) {
			String filedName = fieldNameList.get(i);
			stringBuilder.append("entity.set").append(filedName.substring(0,1).toUpperCase()).append(filedName.substring(1))
					.append("(").append("item[").append(i).append("]").append(");").append("\n");
		}
		System.out.println(stringBuilder);
	}

	/**
	 * 将对象的属性驼峰转下划线（大写）
	 */
	public static <T> Map<String, List<String>> getFieldAndColumnNames(T bean) {
		Map<String, List<String>> map = new HashMap<>();

		Field[] fields = ReflectUtil.getFields(bean.getClass());
		List<String> fieldNames = new ArrayList<>();
		List<String> columnNames = new ArrayList<>();
		for (Field field : fields) {
			fieldNames.add(ReflectUtil.getFieldName(field));
		}
		for (String fieldName : fieldNames) {
			//大写，小写可以换成 toLowerCase()
			columnNames.add(StrUtil.toUnderlineCase(fieldName).toUpperCase());
		}
		map.put("fieldNames", fieldNames);
		map.put("columnNames", columnNames);
		return map;
	}
}
