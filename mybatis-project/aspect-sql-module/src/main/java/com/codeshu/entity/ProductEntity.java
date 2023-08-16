package com.codeshu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author CodeShu
 * @date 2023/7/22 16:50
 */
@Data
@TableName("mybatis_aspect_product")
public class ProductEntity {
	private Long id;

	private String name;

	private Long projectId;
}
