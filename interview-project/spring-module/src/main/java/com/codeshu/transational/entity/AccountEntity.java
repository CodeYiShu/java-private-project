package com.codeshu.transational.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author CodeShu
 * @date 2023/6/8 15:00
 */
@Data
public class AccountEntity {
	private Long id;

	private String name;

	private BigDecimal money;
}
