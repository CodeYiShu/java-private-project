package com.codeshu.service;

import com.codeshu.entity.User;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2023-08-13 18:03:31
 */
public interface UserService {
	List<User> list();

	void save(User user);
}
