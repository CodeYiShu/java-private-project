package com.codeshu.lottery;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 大乐透（五选二）
 * <p>
 * 前区：1-35
 * 后区：1-12
 *
 * @author CodeShu
 * @date 2023/6/27 13:36
 */
public class DaLeTou {
	/**
	 * 组数
	 */
	private static final Integer count = 5;

	public static void main(String[] args) {
		for (int i = 0; i < count; i++) {
			Set<String> before = before(1, 35);
			Set<String> after = after(1, 12);
			System.out.println(before + "------" + after + "\n");
		}
	}

	/**
	 * 五个前区
	 *
	 * @param num1 起始范围参数（包含）
	 * @param num2 终止范围参数（包含）
	 * @return 随机整数
	 */
	public static Set<String> before(int num1, int num2) {
		LinkedHashSet<String> set = new LinkedHashSet<>();
		do {
			int num = (int) (num1 + Math.random() * (num2 - num1 + 1));
			String strNum = String.valueOf(num);
			if (num < 10) {
				strNum = "0" + strNum;
			}
			set.add(strNum);
		} while (set.size() != 5);
		return set;
	}

	/**
	 * 两个后区
	 *
	 * @param num1 起始范围参数（包含）
	 * @param num2 终止范围参数（包含）
	 * @return 随机整数
	 */
	public static Set<String> after(int num1, int num2) {
		LinkedHashSet<String> set = new LinkedHashSet<>();
		do {
			int num = (int) (num1 + Math.random() * (num2 - num1 + 1));
			String strNum = String.valueOf(num);
			if (num < 10) {
				strNum = "0" + strNum;
			}
			set.add(strNum);
		} while (set.size() != 2);
		return set;
	}
}
