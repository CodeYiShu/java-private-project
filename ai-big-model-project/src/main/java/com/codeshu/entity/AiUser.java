package com.codeshu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * (AiUser)实体类
 *
 * @author makejava
 * @since 2023-10-28 13:38:27
 */
@Data
public class AiUser implements Serializable {
	private static final long serialVersionUID = -98738441642715845L;
	@TableId(type = IdType.ASSIGN_ID)
	private String id;

	private String username;
}

