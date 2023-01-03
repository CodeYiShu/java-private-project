package com.codeshu.mapper;

import com.codeshu.controller.GetByObjRequest;
import com.codeshu.entity.TaskEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author CodeShu
 * @date 2022/11/25 21:23
 */

public interface TaskMapper {
	List<TaskEntity> getAll();

	List<TaskEntity> getAllByPlanType(Integer planType);

	List<TaskEntity> getByMap(Map<String, Object> params);

	List<TaskEntity> getByObj(GetByObjRequest request);

	/**
	 * 根据对象绑定参数获取，同时可以给参数起名字
	 */
	List<TaskEntity> getByObjWithName(@Param("request") GetByObjRequest request);
}
