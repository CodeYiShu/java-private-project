package com.codeshu.service.Impl;

import com.codeshu.entity.PlanEntity;
import com.codeshu.mapper.PlanMapper;
import com.codeshu.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CodeShu
 * @date 2022/11/26 9:19
 */
@Service
public class PlanServiceImpl implements PlanService {
	@Autowired
	private PlanMapper planMapper;
	@Override
	public List<PlanEntity> getAll() {
		return planMapper.getAll();
	}
}
