package com.codeshu.service;

import com.codeshu.entity.DeptEntity;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/1/7 17:43
 */
public interface DeptService {
	List<DeptEntity> getAll();

	/**
	 * 根据ID获取
	 */
	DeptEntity getById(Long id);

	/**
	 * 获取有被用户关联的部门
	 */
	List<DeptEntity> getInUserId();

	/**
	 * 获取被指定用户关联的部门
	 */
	List<DeptEntity> getInUserId2(List<Long> userIds);

	/**
	 * 根据部门ID集合批量查询
	 */
	List<DeptEntity> getByIds(List<Long> ids);
}
