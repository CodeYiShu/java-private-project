package com.codeshu.condition.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CodeShu
 * @date 2023/3/30 9:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Long id;

	private String name;

	private Integer age;
}
