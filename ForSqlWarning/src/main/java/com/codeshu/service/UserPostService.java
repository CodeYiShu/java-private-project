package com.codeshu.service;

import com.codeshu.entity.UserPostEntity;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/1/10 19:24
 */
public interface UserPostService {
	/**
	 * 根据用户ID集合，批量获取其关联关系
	 */
	List<UserPostEntity> getByUserIds(List<Long> userIds);
}
