package com.codeshu.service.Impl;

import com.codeshu.entity.PlanTaskEntity;
import com.codeshu.mapper.PlanTaskMapper;
import com.codeshu.service.PlanTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CodeShu
 * @date 2022/11/26 9:19
 */
@Service
public class PlanTaskServiceImpl implements PlanTaskService {
	@Autowired
	private PlanTaskMapper planTaskMapper;
	@Override
	public List<PlanTaskEntity> getAll() {
		return planTaskMapper.getAll();
	}
}
