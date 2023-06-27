package com.codeshu.lottery;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 双色球（六选一）
 * <p>
 * 红色：1-33
 * 蓝色：1-16
 *
 * @author CodeShu
 * @date 2023/6/27 13:36
 */
public class ShuangSeQiu {
	/**
	 * 组数
	 */
	private static final Integer count = 5;

	public static void main(String[] args) {
		for (int i = 0; i < count; i++) {
			Set<String> red = red(1, 33);
			Set<String> blue = blue(1, 16);
			System.out.println(red + "------" + blue + "\n");
		}
	}

	/**
	 * 六个红色
	 *
	 * @param num1 起始范围参数（包含）
	 * @param num2 终止范围参数（包含）
	 * @return 随机整数
	 */
	public static Set<String> red(int num1, int num2) {
		LinkedHashSet<String> set = new LinkedHashSet<>();
		do {
			int num = (int) (num1 + Math.random() * (num2 - num1 + 1));
			String strNum = String.valueOf(num);
			if (num < 10) {
				strNum = "0" + strNum;
			}
			set.add(strNum);
		} while (set.size() != 6);
		return set;
	}

	/**
	 * 一个蓝色
	 *
	 * @param num1 起始范围参数（包含）
	 * @param num2 终止范围参数（包含）
	 * @return 随机整数
	 */
	public static Set<String> blue(int num1, int num2) {
		LinkedHashSet<String> set = new LinkedHashSet<>();
		int num = (int) (num1 + Math.random() * (num2 - num1 + 1));
		String strNum = String.valueOf(num);
		if (num < 10) {
			strNum = "0" + strNum;
		}
		set.add(strNum);
		return set;
	}
}
