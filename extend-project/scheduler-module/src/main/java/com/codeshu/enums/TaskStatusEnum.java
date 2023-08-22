package com.codeshu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author CodeShu
 * @date 2023/8/22 13:44
 */
@Getter
@AllArgsConstructor
public enum TaskStatusEnum {
	RUNNING(1, "启动中"),

	STOP(2, "已停止");

	private final Integer code;
	private final String desc;

	public static String getNameByCode(Integer code) {
		List<String> collect = Stream.of(TaskStatusEnum.values())
				.filter(statusEnum -> statusEnum.getCode().equals(code))
				.map(TaskStatusEnum::getDesc)
				.collect(Collectors.toList());
		if (CollectionUtils.isEmpty(collect)) {
			return null;
		}
		return collect.get(0);
	}
}
