package com.codeshu.calculate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * BigDecimal计算
 *
 * @author CodeShu
 * @date 2023/3/31 11:50
 */
public class BigDecimalUtils {
	/**
	 * 格式化（小数末尾0会被去掉）
	 */
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###################.###########");

	/**
	 * 除法
	 *
	 * @param dividend     被除数
	 * @param divisor      除数
	 * @param scale        保留小数位
	 * @param roundingMode {@link RoundingMode}
	 */
	public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, int scale, RoundingMode roundingMode) {
		if (BigDecimal.ZERO.compareTo(divisor) == 0) {
			return null;
		}
		BigDecimal divide = dividend.divide(divisor, scale, roundingMode);
		return new BigDecimal(DECIMAL_FORMAT.format(divide));
	}

	/**
	 * 除法（结果乘以multiple）
	 *
	 * @param dividend     被除数
	 * @param divisor      除数
	 * @param scale        保留小数位
	 * @param roundingMode {@link RoundingMode}
	 * @param multiple     倍数
	 */
	public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, int scale, RoundingMode roundingMode, int multiple) {
		if (BigDecimal.ZERO.compareTo(divisor) == 0) {
			return null;
		}
		BigDecimal divide = dividend.divide(divisor, scale, roundingMode);
		return new BigDecimal(DECIMAL_FORMAT.format(divide.multiply(BigDecimal.valueOf(multiple))));
	}
}
