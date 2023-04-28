package com.codeshu.service.impl;

import com.codeshu.entity.UserPostEntity;
import com.codeshu.mapper.UserPostMapper;
import com.codeshu.service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/1/10 19:24
 */
@Service
public class UserPostServiceImpl implements UserPostService {
	@Autowired
	private UserPostMapper userPostMapper;

	@Override
	public List<UserPostEntity> getByUserIds(List<Long> userIds) {
		return userPostMapper.selectByUserIds(userIds);
	}
}
