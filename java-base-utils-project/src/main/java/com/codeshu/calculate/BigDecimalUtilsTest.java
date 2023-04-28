package com.codeshu.calculate;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 测试BigDecimalUtils
 *
 * @author CodeShu
 * @date 2023/3/31 11:50
 */
public class BigDecimalUtilsTest {
	/**
	 * 被除数
	 */
	private final BigDecimal dividend = BigDecimal.valueOf(11.3d);

	/**
	 * 除数
	 */
	private final BigDecimal divisor = BigDecimal.valueOf(12.3d);

	/**
	 * 测试device()
	 * <p>
	 * 注：11.3/12.3 = 0.9186991
	 */
	@Test
	public void testDivide() {
		//0.9186991保留四位小数且四舍五入则为0.9187
		System.out.println(BigDecimalUtils.divide(dividend, divisor, 4, RoundingMode.HALF_UP));

		//0.9186991保留三位小数且四舍五入则为0.919
		System.out.println(BigDecimalUtils.divide(dividend, divisor, 3, RoundingMode.HALF_UP));

		//0.9186991保留两位小数且四舍五入则为0.92
		System.out.println(BigDecimalUtils.divide(dividend, divisor, 2, RoundingMode.HALF_UP));
	}

	/**
	 * 测试device()，结果需乘以倍数
	 * <p>
	 * 注：11.3/12.3 = 0.9186991
	 */
	@Test
	public void testDivideMultiple() {
		//0.9186991保留四位小数且四舍五入则为0.9187，乘以倍数100则为91.87
		System.out.println(BigDecimalUtils.divide(dividend, divisor, 4, RoundingMode.HALF_UP, 100));

		//0.9186991保留三位小数且四舍五入则为0.919，乘以倍数100则为91.9
		System.out.println(BigDecimalUtils.divide(dividend, divisor, 3, RoundingMode.HALF_UP, 100));

		//0.9186991保留两位小数且四舍五入则为0.92，乘以倍数100则为92
		System.out.println(BigDecimalUtils.divide(dividend, divisor, 2, RoundingMode.HALF_UP, 100));
	}
}
