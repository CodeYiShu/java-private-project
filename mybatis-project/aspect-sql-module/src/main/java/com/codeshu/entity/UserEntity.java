package com.codeshu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author CodeShu
 * @date 2023/7/22 16:49
 */
@Data
@TableName("mybatis_aspect_user")
public class UserEntity {
	private Long id;

	private String username;
}
