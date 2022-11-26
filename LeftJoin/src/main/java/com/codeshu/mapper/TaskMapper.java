package com.codeshu.mapper;

import com.codeshu.entity.TaskEntity;

import java.util.List;
import java.util.Map;

/**
 * @author CodeShu
 * @date 2022/11/25 21:23
 */

public interface TaskMapper {
	List<TaskEntity> getAll();

	List<TaskEntity> getAllByPlanType(Integer planType);

	List<TaskEntity> getByMap(Map<String,Object> params);
}
