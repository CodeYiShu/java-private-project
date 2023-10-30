package com.codeshu.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CodeShu
 * @date 2023/10/30 13:47
 */
@Data
@TableName(value = "mybatis_plus_user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
	/**
	 * 雪花算法
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;

	private String name;

	private Integer age;

	private String description;
}
