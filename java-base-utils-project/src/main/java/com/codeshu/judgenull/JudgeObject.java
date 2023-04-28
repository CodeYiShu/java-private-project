package com.codeshu.judgenull;

import cn.hutool.core.util.ObjectUtil;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Objects;

/**
 * 判断对象是否为NULL的各种方式
 *
 * @author CodeShu
 * @date 2023/1/17 11:21
 */
public class JudgeObject {
	public static void main(String[] args) {
		System.out.println("-------------------------------------------------");
		generate();
		System.out.println("-------------------------------------------------");
		useObjects();
		System.out.println("-------------------------------------------------");
		useObjectsUtils();
		System.out.println("-------------------------------------------------");
		useHutool();
		System.out.println("-------------------------------------------------");
	}

	/**
	 * 常规方式
	 */
	public static void generate() {
		Person person = new Person();
		System.out.println(person == null);
		System.out.println(person != null);
	}

	/**
	 * 使用Objects来进行判断
	 * 无需引入其他包
	 */
	public static void useObjects() {
		Person person = new Person();
		//false
		System.out.println("Objects.isNull(person) = " + Objects.isNull(person));
		//true
		System.out.println("Objects.nonNull(person) = " + Objects.nonNull(person));
	}

	/**
	 * 使用ObjectsUtils判断
	 * 需要引入commons-lang3依赖
	 */
	public static void useObjectsUtils() {
		Person person1 = new Person();
		Person person2 = new Person();
		Person person3 = null;
		Person person4 = null;

		//allNotNull()：全部参数都不为NULL，才返回true
		System.out.println("ObjectUtils.allNotNull(person1,person2) = " + ObjectUtils.allNotNull(person1, person2));
		System.out.println("ObjectUtils.allNotNull(person1,person3) = " + ObjectUtils.allNotNull(person1, person3));

		//allNull()：全部参数都为NULL，才返回true
		System.out.println("ObjectUtils.allNull(person3,person4) = " + ObjectUtils.allNull(person3, person4));
		System.out.println("ObjectUtils.allNull(person1,person3) = " + ObjectUtils.allNull(person1, person3));
	}

	/**
	 * 使用Hutool工具
	 * 需要引入hutools依赖
	 */
	public static void useHutool() {
		Person person1 = new Person();
		Person person2 = new Person();
		Person person3 = null;
		System.out.println("ObjectUtil.hasNull(person1,person2) = " + ObjectUtil.hasNull(person1, person2));
		System.out.println("ObjectUtil.hasNull(person1,person3) = " + ObjectUtil.hasNull(person1, person3));
	}
}
