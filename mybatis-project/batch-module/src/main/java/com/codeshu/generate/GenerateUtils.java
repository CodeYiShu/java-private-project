package com.codeshu.generate;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.codeshu.generate.bean.EquipSon;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CodeShu
 * @date 2023/10/30 17:20
 */
public class GenerateUtils {
	@SuppressWarnings("all")
	public static void main(String[] args) {
		System.out.println(generateInsertBatchSQL(new EquipSon(),
						"BYDBC_CONTROL.TFHJ_EQUIP_SON",
						"com.grkj.modules.tfhjsjyj.entity"
				)
		);
	}

	/**
	 * 生成批量插入的 SQL
	 *
	 * @param bean        实体类对象
	 * @param tableName   表名
	 * @param packageName 实体类包名
	 * @return <insert id="insertBatch" parameterType="包名">INSERT INTO ....</insert>
	 * <p>
	 * 注意点：
	 * (1)Mapper接口方法上在集合参数标注上@Param("entityList")，如 void insertBatch(@Param("entityList") List<EquipSon> entityList);
	 * (2)Mapper接口方法名称固定为 insertBatch
	 */
	public static <T> String generateInsertBatchSQL(T bean, String tableName, String packageName) {
		Map<String, List<String>> map = getFieldAndColumnNames(bean);
		String[] packageClassName = bean.getClass().getName().split("\\.");
		String className = packageClassName[packageClassName.length - 1];
		StringBuilder stringBuilder = new StringBuilder("<insert id=\"insertBatch\" parameterType=\"")
				.append(packageName).append(".").append(className).append("\">\n");
		stringBuilder.append("\tINSERT INTO ").append(tableName).append("\n\t(");
		String columnNames = String.join(",", map.get("columnNames"));
		stringBuilder.append(columnNames);
		stringBuilder.append(") \n\tVALUES\n \t<foreach collection=\"entityList\" item=\"entity\" separator=\",\">\n\t\t(");
		for (String fieldName : map.get("fieldNames")) {
			stringBuilder.append("#{entity.").append(fieldName).append("},");
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);

		stringBuilder.append(")\n\t</foreach>\n</insert>");
		return stringBuilder.toString();
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
