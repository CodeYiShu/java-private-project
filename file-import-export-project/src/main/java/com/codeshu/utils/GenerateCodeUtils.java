package com.codeshu.utils;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import lombok.Data;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

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
public class GenerateCodeUtils {
	private final static String PATH = "E:\\java\\program\\java-private-project\\file-import-export-project";

	private final static String SCHEMA_NAME = "BYDBC_ORIGIN";

	private final static String TABLE_NAME = "MY_TABLE";

	public static void main(String[] args) throws IOException {
		// 生成创建表的 SQL
		generateCreateTable();
		System.out.println("-------------------------------------------------------------");

		// 生成前端表单字段
		generateQianDuan();
		System.out.println("-------------------------------------------------------------");

		// 得到表之后，可以去使用生成代码的项目生成得到实体类，然后在这里去生成导出代码
		generateExport();
	}

	/**
	 * 生成表
	 */
	public static void generateCreateTable() throws IOException {
		// 表字段名称和注释
		Map<String, List<String>> resultMap = getFieldCommendFromExcel();
		List<String> fieldList = resultMap.get("fieldList");
		List<String> commendList = resultMap.get("commendList");

		// 生成表
		StringBuilder stringBuilder1 = new StringBuilder();
		stringBuilder1.append("CREATE TABLE ").append(SCHEMA_NAME).append(".").append(TABLE_NAME).append("(\n");
		for (String field : fieldList) {
			stringBuilder1.append("\"").append(field).append("\" VARCHAR2(2000),\n");
		}
		stringBuilder1.append("\"").append("CREATE_USER").append("\" VARCHAR2(2000),\n");
		stringBuilder1.append("\"").append("CREATE_TIME").append("\" DATETIME(6),\n");
		stringBuilder1.append("\"").append("UPDATE_USER").append("\" VARCHAR2(2000),\n");
		stringBuilder1.append("\"").append("UPDATE_TIME").append("\" DATETIME(6),\n");
		stringBuilder1.append(") STORAGE(ON \"BYDBC\", CLUSTERBTR) ;");
		System.out.println(stringBuilder1);

		// 生成注释
		StringBuilder stringBuilder2 = new StringBuilder();
		for (int i = 0; i < fieldList.size(); i++) {
			String filed = fieldList.get(i);
			String commend = commendList.get(i);
			stringBuilder2.append("COMMENT ON COLUMN \"").append(SCHEMA_NAME).append("\".\"").append(TABLE_NAME).append("\".\"")
					.append(filed).append("\" ")
					.append("IS '").append(commend)
					.append("';\n");
		}
		stringBuilder2.append("COMMENT ON COLUMN \"").append(SCHEMA_NAME).append("\".\"").append(TABLE_NAME).append("\".\"").append("CREATE_USER").append("\" ").append("IS '").append("创建人").append("';\n");
		stringBuilder2.append("COMMENT ON COLUMN \"").append(SCHEMA_NAME).append("\".\"").append(TABLE_NAME).append("\".\"").append("CREATE_TIME").append("\" ").append("IS '").append("创建时间").append("';\n");
		stringBuilder2.append("COMMENT ON COLUMN \"").append(SCHEMA_NAME).append("\".\"").append(TABLE_NAME).append("\".\"").append("UPDATE_USER").append("\" ").append("IS '").append("修改人").append("';\n");
		stringBuilder2.append("COMMENT ON COLUMN \"").append(SCHEMA_NAME).append("\".\"").append(TABLE_NAME).append("\".\"").append("UPDATE_TIME").append("\" ").append("IS '").append("修改时间").append("';\n");
		System.out.println(stringBuilder2);
	}

	/**
	 * 生成前端页面
	 */
	public static void generateQianDuan() throws IOException {
		// 表字段名称和注释
		Map<String, List<String>> resultMap = getFieldCommendFromExcel();
		List<String> fieldList = resultMap.get("fieldList");
		List<String> commendList = resultMap.get("commendList");

		// 前端页面
		StringBuilder stringBuilder = new StringBuilder();

		// 可以在这里过滤掉不要展示的字段
		fieldList = fieldList.stream().filter(f -> !f.equals("id")).collect(Collectors.toList());
		commendList = commendList.stream().filter(f -> !f.equals("id")).collect(Collectors.toList());

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
		System.out.println(stringBuilder);
	}

	public static Map<String, List<String>> getFieldCommendFromExcel() throws IOException {
		// 获取文件流
		FileInputStream fileInputStream = new FileInputStream(PATH + "\\字段注释.xlsx");
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
			fieldList.add(PinyinUtil.getFirstLetter(cellValue, ""));
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

	/**
	 * 生成导出
	 */
	public static void generateExport() throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		// 字段名称
		List<String> fieldNameList = getFieldAndColumnNames(new TestBean()).get("fieldNames");
		for (int i = 0; i < fieldNameList.size(); i++) {
			String filedName = fieldNameList.get(i);
			stringBuilder.append("entity.set").append(filedName.substring(0, 1).toUpperCase()).append(filedName.substring(1))
					.append("(").append("item[").append(i).append("]").append(");").append("\n");
		}
		System.out.println(stringBuilder);
	}

	@Data
	public static class TestBean {
		private String id;

		private String czmc;

		private String czsj;

		private String czry;
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
