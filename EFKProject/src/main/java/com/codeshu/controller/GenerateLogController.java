package com.codeshu.controller;

import com.codeshu.utils.LogFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 产生日志
 *
 * @author CodeShu
 * @date 2023/4/3 9:01
 */
@RestController
public class GenerateLogController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateLogController.class);

	@GetMapping("log")
	public String log() {
		LOGGER.debug("GenerateLogController:debug");
		LOGGER.info("GenerateLogController:info");
		LOGGER.warn("GenerateLogController:warn");
		LOGGER.error("GenerateLogController:error");
		int i = 1 / 0;
		return "success";
	}

	@GetMapping("logUtils")
	public String logUtils() {
		Map<String, Object> params = new HashMap<>();
		params.put("key1", "value1");
		params.put("key2", "value2");
		params.put("key3", "value3");
		LogFormatUtils.printError(Thread.currentThread().getStackTrace()[1], params);

		return "success";
	}
}
