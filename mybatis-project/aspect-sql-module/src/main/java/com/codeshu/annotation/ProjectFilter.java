package com.codeshu.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目过滤注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProjectFilter {
	/**
	 * project_id 字段所在的表别名，单表则不需要填写
	 */
	String tableAlias() default "";

	/**
	 * 项目字段名称，默认是 project_id
	 */
	String projectId() default "project_id";
}
