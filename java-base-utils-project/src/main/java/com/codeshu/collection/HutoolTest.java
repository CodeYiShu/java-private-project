package com.codeshu.collection;

import cn.hutool.core.collection.CollUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author CodeShu
 * @date 2023/10/9 16:10
 */
public class HutoolTest {
	/**
	 * 交集
	 * <p>
	 * 有一个集合为空则返回 []
	 */
	@Test
	public void testIntersection() {
		List<String> list1 = Arrays.asList("a", "b", "c", "c", "c");
		List<String> list2 = Arrays.asList("a", "c", "c");

		//[a, c, c]
		System.out.println(CollUtil.intersection(list1, list2));

		//[a, c]  —— 常用
		System.out.println(CollUtil.intersectionDistinct(list1, list2));
	}

	/**
	 * 并集
	 */
	@Test
	public void testUnion() {
		List<String> list1 = Arrays.asList("a", "b", "c", "c", "c");
		List<String> list2 = Arrays.asList("a", "b", "d", "c");

		//[a, b, c, c, c, d]
		System.out.println(CollUtil.union(list1, list2));

		//[a, b, c, d] —— 常用
		System.out.println(CollUtil.unionDistinct(list1, list2));

		//[a, b, c, c, c, a, b, d, c]
		System.out.println(CollUtil.unionAll(list1, list2));
	}

	/**
	 * 单差集
	 * <p>
	 * 只返回【集合1】中有，但是【集合2】中没有的元素，也就是去掉【集合2】的元素
	 */
	@Test
	public void testDisjunction() {
		List<String> list1 = Arrays.asList("a", "b", "c");
		List<String> list2 = Arrays.asList("a", "b");

		//[c]
		System.out.println(CollUtil.subtractToList(list1, list2));
	}
}
