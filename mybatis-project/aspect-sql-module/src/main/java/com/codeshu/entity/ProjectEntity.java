package com.codeshu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author CodeShu
 * @date 2023/7/22 16:50
 */
@Data
@TableName("mybatis_aspect_project")
public class ProjectEntity {
	private Long id;

	private String projectName;
}
