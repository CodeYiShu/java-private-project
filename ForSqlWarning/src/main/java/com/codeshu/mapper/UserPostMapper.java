package com.codeshu.mapper;

import com.codeshu.entity.UserPostEntity;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/1/10 19:22
 */
public interface UserPostMapper {
	/**
	 * 根据用户ID集合，批量获取与这些用户关联关系
	 */
	List<UserPostEntity> selectByUserIds(List<Long> userIds);
}
