package com.codeshu.excel;

import com.codeshu.excel.common.ExcelCommonUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author CodeShu
 * @date 2024/5/12 14:04
 */
public class ExcelGenerateImport {
	// Excel 表路径
	private final static String PATH = "E:\\java\\program\\java-private-project\\generate-code-project\\src\\main\\resources\\excel\\字段.xlsx";

	// Excel 表路径
	private final static String PATH2 = "E:\\java\\program\\java-private-project\\generate-code-project\\src\\main\\resources\\excel\\实战导入模板.xlsx";

	public static void main(String[] args) throws IOException {
		// generateImport(PATH);
		generateImport(PATH2);
	}

	/**
	 * 生成导入代码
	 */
	public static void generateImport(String path) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();

		// 表字段名称和注释
		Map<String, List<String>> resultMap = ExcelCommonUtils.getFieldCommendFromExcel2(path, false);
		List<String> fieldList = resultMap.get("fieldList");
		List<String> commendList = resultMap.get("commendList");

		for (int i = 0; i < fieldList.size(); i++) {
			String fieldName = fieldList.get(i);
			stringBuilder.append("// ").append(commendList.get(i)).append("\n");
			stringBuilder.append("entity.set").append(fieldName.substring(0, 1).toUpperCase())
					.append(fieldName.substring(1))
					.append("(").append("item[").append(i).append("]").append(");").append("\n");
		}
		System.out.println(stringBuilder);
	}
}
