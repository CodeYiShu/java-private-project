package com.codeshu.excel;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.codeshu.excel.bean.TestBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据对象生成导出代码（不涉及 Excel 文件）
 */
public class ExcelGenerateExport {
	public static void main(String[] args) {
		generateExport(new TestBean());
	}

	/**
	 * 生成导出
	 */
	public static <T> void generateExport(T bean) {
		StringBuilder stringBuilder = new StringBuilder();
		// 字段名称
		List<String> fieldNameList = getFieldAndColumnNames(bean).get("fieldNames");
		for (int i = 0; i < fieldNameList.size(); i++) {
			String filedName = fieldNameList.get(i);
			stringBuilder.append("entity.set").append(filedName.substring(0, 1).toUpperCase()).append(filedName.substring(1))
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
