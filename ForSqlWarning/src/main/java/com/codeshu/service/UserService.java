package com.codeshu.service;

import com.codeshu.entity.UserEntity;
import com.codeshu.response.UserWithDeptNameResponse;
import com.codeshu.response.UserWithPostResponse;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/1/7 17:42
 */
public interface UserService {
	List<UserEntity> getAll();

	/**
	 * 获取用户信息，且其所在部门名称
	 */
	List<UserWithDeptNameResponse> getUserWithDeptName();

	/**
	 * 获取用户信息，且其所在部门名称
	 * 优化写法1：先查询出所有关联数据
	 */
	List<UserWithDeptNameResponse> getUserWithDeptNameOptimize1();

	/**
	 * 获取用户信息，且其所在部门名称
	 * 优化写法2：先查询出所有关联数据，但是使用IN减少关联数据的查询
	 */
	List<UserWithDeptNameResponse> getUserWithDeptNameOptimize2();

	/**
	 * 获取用户信息，且其所在部门名称
	 * 优化写法3：先根据当前主查询数据，再得到关联数据的ID，使用IN关键字查询关联数据
	 */
	List<UserWithDeptNameResponse> getUserWithDeptNameOptimize3();

	/**
	 * 获取用户信息，且其所在部门名称
	 * 优化写法4：根据当前主查询数据，得到关联数据的ID，使用IN关键字查询关联数据
	 */
	List<UserWithDeptNameResponse> getUserWithDeptNameOptimize4();

	/**
	 * 获取用户信息，且其所有岗位信息
	 */
	List<UserWithPostResponse> getUserWithPost();
}
