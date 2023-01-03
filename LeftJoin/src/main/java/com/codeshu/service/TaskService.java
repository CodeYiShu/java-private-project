package com.codeshu.service;

import com.codeshu.controller.GetByObjRequest;
import com.codeshu.entity.TaskEntity;

import java.util.List;
import java.util.Map;

/**
 * @author CodeShu
 * @date 2022/11/25 21:24
 */
public interface TaskService {
	List<TaskEntity> getAll();

	List<TaskEntity> getAllByPlanType(Integer planType);

	List<TaskEntity> getByMap(Map<String, Object> params);

	List<TaskEntity> getByObj(GetByObjRequest request);

	List<TaskEntity> getByObjWithName(GetByObjRequest request);
}
