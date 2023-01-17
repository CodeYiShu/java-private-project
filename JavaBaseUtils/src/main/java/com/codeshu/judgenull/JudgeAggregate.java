package com.codeshu.judgenull;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author CodeShu
 * @date 2023/1/17 13:56
 */
public class JudgeAggregate {
	public static void main(String[] args) {
		System.out.println("------------------------------");
		generate();
		System.out.println("------------------------------");
		useCollectionUtil();
		System.out.println("------------------------------");
		useCollectionUtilForMap();
		System.out.println("------------------------------");
		useCollectionUtils();
	}

	public static void generate() {
		List<String> list = new ArrayList<>();
		System.out.println(list != null && !list.isEmpty());
		System.out.println(Objects.nonNull(list) && !list.isEmpty());
		System.out.println(ObjectUtil.isNotNull(list) && !list.isEmpty());
		System.out.println(ObjectUtils.allNotNull(list) && !list.isEmpty());
	}

	/**
	 * 使用CollectionUtil判断
	 * 需要引入Hutool依赖
	 */
	public static void useCollectionUtil(){
		List<String> list1 = new ArrayList<>();
		List<String> list2 = null;
		List<String> list3 = Arrays.asList("a","b","c");

		System.out.println("CollectionUtil.isEmpty(list1) = " + CollectionUtil.isEmpty(list1));
		System.out.println("CollectionUtil.isEmpty(list2) = " + CollectionUtil.isEmpty(list2));
		System.out.println("CollectionUtil.isEmpty(list3) = " + CollectionUtil.isEmpty(list3));

		System.out.println("CollectionUtil.isNotEmpty(list1) = " + CollectionUtil.isNotEmpty(list1));
		System.out.println("CollectionUtil.isNotEmpty(list2) = " + CollectionUtil.isNotEmpty(list2));
		System.out.println("CollectionUtil.isNotEmpty(list3) = " + CollectionUtil.isNotEmpty(list3));
	}

	/**
	 * 使用CollectionUtil判断
	 * 需要引入Hutool依赖
	 */
	public static void useCollectionUtilForMap(){
		Map<String,Object> map1 = new HashMap<>();
		Map<String,Object> map2 = null;
		Map<String,Object> map3 = new HashMap<>();
		map3.put("a",1);

		System.out.println("CollectionUtil.isEmpty(map1) = " + CollectionUtil.isEmpty(map1));
		System.out.println("CollectionUtil.isEmpty(map2) = " + CollectionUtil.isEmpty(map2));
		System.out.println("CollectionUtil.isEmpty(map3) = " + CollectionUtil.isEmpty(map3));

		System.out.println("CollectionUtil.isNotEmpty(map1) = " + CollectionUtil.isNotEmpty(map1));
		System.out.println("CollectionUtil.isNotEmpty(map2) = " + CollectionUtil.isNotEmpty(map2));
		System.out.println("CollectionUtil.isNotEmpty(map3) = " + CollectionUtil.isNotEmpty(map3));
	}

	/**
	 * 使用CollecitonUtils判断
	 * 需要引入spring-core包
	 */
	public static void useCollectionUtils(){
		List<String> list1 = new ArrayList<>();
		List<String> list2 = null;
		List<String> list3 = Arrays.asList("a","b","c");

		System.out.println("CollectionUtils.isEmpty(list1) = " + CollectionUtils.isEmpty(list1));
		System.out.println("CollectionUtils.isEmpty(list2) = " + CollectionUtils.isEmpty(list2));
		System.out.println("CollectionUtils.isEmpty(list3) = " + CollectionUtils.isEmpty(list3));

		System.out.println("!CollectionUtils.isEmpty(list1) = " + !CollectionUtils.isEmpty(list1));
		System.out.println("!CollectionUtils.isEmpty(list2) = " + !CollectionUtils.isEmpty(list2));
		System.out.println("!CollectionUtils.isEmpty(list3) = " + !CollectionUtils.isEmpty(list3));
	}


}
