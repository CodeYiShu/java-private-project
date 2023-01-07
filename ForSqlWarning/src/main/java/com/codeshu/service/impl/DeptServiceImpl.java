package com.codeshu.service.impl;

import com.codeshu.entity.DeptEntity;
import com.codeshu.mapper.DeptMapper;
import com.codeshu.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/1/7 17:44
 */
@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptMapper deptMapper;

	@Override
	public List<DeptEntity> getAll() {
		return deptMapper.selectAll();
	}

	@Override
	public DeptEntity getById(Long id) {
		return deptMapper.selectById(id);
	}

	@Override
	public List<DeptEntity> getInUserId() {
		return deptMapper.selectInUserId();
	}

	@Override
	public List<DeptEntity> getInUserId2(List<Long> userIds) {
		return deptMapper.selectInUserId2(userIds);
	}

	@Override
	public List<DeptEntity> getByIds(List<Long> ids) {
		return deptMapper.selectByIds(ids);
	}
}
