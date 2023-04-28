package com.codeshu.mapper;

import com.codeshu.entity.DeptEntity;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/1/7 17:37
 */
public interface DeptMapper {
	List<DeptEntity> selectAll();

	DeptEntity selectById(Long id);

	/**
	 * 获取有被用户关联的部门
	 */
	List<DeptEntity> selectInUserId();

	/**
	 * 获取被指定用户关联的部门
	 */
	List<DeptEntity> selectInUserId2(List<Long> userIds);

	/**
	 * 根据部门ID集合批量查询
	 */
	List<DeptEntity> selectByIds(List<Long> ids);
}
