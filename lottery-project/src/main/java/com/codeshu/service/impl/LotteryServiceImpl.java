package com.codeshu.service.impl;

import com.codeshu.response.DaLeTouResponse;
import com.codeshu.response.ShuangSeQiuResponse;
import com.codeshu.service.LotteryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author CodeShu
 * @date 2023/7/15 22:15
 */
@Service
public class LotteryServiceImpl implements LotteryService {
	/**
	 * 组数
	 */
	private static final Integer count = 5;

	@Override
	public List<DaLeTouResponse> daLeTou() {
		List<DaLeTouResponse> responseList = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			DaLeTouResponse response = new DaLeTouResponse();
			Set<String> before = daLeTouBefore(1, 35);
			Set<String> after = daLeTouAfter(1, 12);
			response.setBefore(before);
			response.setAfter(after);
			responseList.add(response);
		}
		return responseList;
	}

	/**
	 * 五个前区
	 *
	 * @param num1 起始范围参数（包含）
	 * @param num2 终止范围参数（包含）
	 * @return 随机整数
	 */
	public static Set<String> daLeTouBefore(int num1, int num2) {
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
	public static Set<String> daLeTouAfter(int num1, int num2) {
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

	@Override
	public List<ShuangSeQiuResponse> shuangSeQiu() {
		List<ShuangSeQiuResponse> responseList = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			ShuangSeQiuResponse response = new ShuangSeQiuResponse();
			Set<String> red = red(1, 33);
			Set<String> blue = blue(1, 16);
			response.setRed(red);
			response.setBlue(blue);
			responseList.add(response);
		}
		return responseList;
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
