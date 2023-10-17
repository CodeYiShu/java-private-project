package com.codeshu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Mybatis的映射技巧(Student)实体类
 *
 * @author makejava
 * @since 2023-08-16 15:40:43
 */
@Data
public class StudentInfo implements Serializable {
	private static final long serialVersionUID = 751172260466109106L;

	private Long id;

	private Long studentId;

	private Integer age;

	private String address;
}

