package com.codeshu.service.Impl;

import com.codeshu.controller.GetByObjRequest;
import com.codeshu.entity.TaskEntity;
import com.codeshu.mapper.TaskMapper;
import com.codeshu.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CodeShu
 * @date 2022/11/25 21:24
 */
@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskMapper taskMapper;

	@Override
	public List<TaskEntity> getAll() {
		return taskMapper.getAll();
	}

	@Override
	public List<TaskEntity> getAllByPlanType(Integer planType) {
		return taskMapper.getAllByPlanType(planType);
	}

	@Override
	public List<TaskEntity> getByMap(Map<String, Object> params) {
		//params.put("taskName","任务1");
		//Mybatis可以接收Map参数
		return taskMapper.getByMap(params);
	}

	@Override
	public List<TaskEntity> getByObj(GetByObjRequest request) {
		return taskMapper.getByObj(request);
	}

	@Override
	public List<TaskEntity> getByObjWithName(GetByObjRequest request) {
		return taskMapper.getByObjWithName(request);
	}
}
