package com.codeshu.excel;

import com.codeshu.excel.common.ExcelCommonUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 根据 Excel 字段生成前端表单页面
 *
 * @author CodeShu
 * @date 2024/5/12 13:49
 */
public class ExcelGenerateQianDuan {
	// Excel 表路径
	private final static String PATH = "E:\\java\\program\\java-private-project\\generate-code-project\\src\\main\\resources\\excel\\字段.xlsx";

	public static void main(String[] args) throws IOException {
		generateQianDuan();
	}

	/**
	 * 生成前端页面
	 */
	public static void generateQianDuan() throws IOException {
		// 表字段名称和注释
		Map<String, List<String>> resultMap = ExcelCommonUtils.getFieldCommendFromExcel(PATH);
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
}
