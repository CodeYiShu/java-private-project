package com.codeshu.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;

/**
 * 日志输出格式化封装
 *
 * <p>
 * 获取栈信息，展示类、方法名等信息，可在logback配置文件进行配置替代
 * 由于涉及获取栈信息，效率不高
 */
public class LogFormatUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogFormatUtils.class);

	/**
	 * Debug打印
	 *
	 * @param stackTrace 栈信息
	 * @param params     参数
	 */
	public static void printDebug(StackTraceElement stackTrace, Map<String, Object> params) {
		if (Objects.nonNull(stackTrace.getFileName())) {
			LOGGER.debug("当前方法{}:{}() 打印结果:{}",
					stackTrace.getFileName().replace(".java", ""),
					stackTrace.getMethodName(),
					appendParam(params));
		}
	}

	/**
	 * Info打印
	 *
	 * @param stackTrace 栈信息
	 * @param params     参数
	 */
	public static void printInfo(StackTraceElement stackTrace, Map<String, Object> params) {
		if (Objects.nonNull(stackTrace.getFileName())) {
			LOGGER.info("当前方法{}:{}() 打印结果:{}",
					stackTrace.getFileName().replace(".java", ""),
					stackTrace.getMethodName(),
					appendParam(params));
		}
	}

	/**
	 * Warn打印
	 *
	 * @param stackTrace 栈信息
	 * @param params     参数
	 */
	public static void printWarn(StackTraceElement stackTrace, Map<String, Object> params) {
		if (Objects.nonNull(stackTrace.getFileName())) {
			LOGGER.warn("当前方法{}:{}() 打印结果:{}",
					stackTrace.getFileName().replace(".java", ""),
					stackTrace.getMethodName(),
					appendParam(params));
		}
	}

	/**
	 * Error打印
	 *
	 * @param stackTrace 栈信息
	 * @param params     参数
	 */
	public static void printError(StackTraceElement stackTrace, Map<String, Object> params) {
		if (Objects.nonNull(stackTrace.getFileName())) {
			LOGGER.error("当前方法{}:{}() 打印结果:{}",
					stackTrace.getFileName().replace(".java", ""),
					stackTrace.getMethodName(),
					appendParam(params));
		}
	}

	/**
	 * 拼接参数
	 */
	private static String appendParam(Map<String, Object> params) {
		StringBuilder sb = new StringBuilder();
		sb.append(" ");

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(" ; ");
		}

		sb.deleteCharAt(sb.length() - 1);
		String obj = sb.toString();
		return obj.isEmpty() ? null : obj;
	}
}
