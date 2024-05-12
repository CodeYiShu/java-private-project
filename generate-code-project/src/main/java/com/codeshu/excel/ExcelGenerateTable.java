package com.codeshu.excel;

import com.codeshu.excel.common.ExcelCommonUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 根据 Excel 生成创建表的 SQL
 *
 * @author CodeShu
 * @date 2024/5/12 13:33
 */
public class ExcelGenerateTable {
	// Excel 表路径
	private final static String PATH = "E:\\java\\program\\java-private-project\\generate-code-project\\src\\main\\resources\\excel\\字段.xlsx";

	// 表模式
	private final static String SCHEMA_NAME = "BYDBC_ORIGIN";

	// 表名称
	private final static String TABLE_NAME = "MY_TABLE";

	public static void main(String[] args) throws IOException {
		generateCreateTable();
	}

	/**
	 * 生成表
	 */
	public static void generateCreateTable() throws IOException {
		// 表字段名称和注释
		Map<String, List<String>> resultMap = ExcelCommonUtils.getFieldCommendFromExcel(PATH,true);
		List<String> fieldList = resultMap.get("fieldList");
		List<String> commendList = resultMap.get("commendList");

		// 生成表
		StringBuilder stringBuilder1 = new StringBuilder();
		stringBuilder1.append("CREATE TABLE ").append(SCHEMA_NAME).append(".").append(TABLE_NAME).append("(\n");
		stringBuilder1.append("\"ID\" VARCHAR2(100) NOT NULL,\n");
		for (String field : fieldList) {
			stringBuilder1.append("\"").append(field).append("\" VARCHAR2(2000),\n");
		}
		stringBuilder1.append("\"").append("CREATE_USER").append("\" VARCHAR2(2000),\n");
		stringBuilder1.append("\"").append("CREATE_TIME").append("\" DATETIME(6),\n");
		stringBuilder1.append("\"").append("UPDATE_USER").append("\" VARCHAR2(2000),\n");
		stringBuilder1.append("\"").append("UPDATE_TIME").append("\" DATETIME(6),\n");
		stringBuilder1.append("NOT CLUSTER PRIMARY KEY(\"ID\")");
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
}
