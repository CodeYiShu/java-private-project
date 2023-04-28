package com.codeshu.optional;

import cn.hutool.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * @author ShuCode
 * @date 2022/7/12 12:25
 * @Email 13828965090@163.com
 */
public class OptionalUtils {

	/**
	 * 从容器获取对象的属性值
	 * @param t 对象
	 * @param elseValue 当属性值为null时，赋予的默认值
	 * @param fieldName 属性名
	 * @return 当属性值不为null返回原先属性值，为null返回默认值
	 */
	public static<T,R> R getOptionalField(T t,R elseValue,String fieldName) throws NoSuchFieldException, IllegalAccessException {
		//得到对象t的指定属性名的属性对象，设置为可见
		Field field = t.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		//得到对象t的指定属性的属性值
		R fieldValue = (R) field.get(t);
		//将属性值放入容器
		Optional<R> optionalO = Optional.ofNullable(fieldValue);
		//当属性值为null时则返回elseValue，不为null时返回原有属性值
		R optionalFieldValue = optionalO.orElse(elseValue);
		//设置对象的属性值为optionalFieldValue
		field.set(t,optionalFieldValue);
		return optionalFieldValue;
	}

	/**
	 * 从容器获取对象的属性值
	 * @param jsonObject JSON对象
	 * @param elseValue 当属性值为null时，赋予的默认值
	 * @param fieldName 属性名
	 * @return 当属性值不为null返回原先属性值，为null返回默认值
	 */
	public static<R> R getOptionalField(JSONObject jsonObject, R elseValue, String fieldName) throws NoSuchFieldException, IllegalAccessException {
		R fieldValue = (R) jsonObject.getObj(fieldName, elseValue.getClass());
		Optional<R> optionalO = Optional.ofNullable(fieldValue);
		R optionalFieldValue = optionalO.orElse(elseValue);
		//将字段设置到jsonObject对象中
		jsonObject.put(fieldName, optionalFieldValue);
		return optionalFieldValue;
	}
}
