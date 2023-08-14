package com.codeshu.service.impl;

import com.codeshu.dao.UserDao;
import com.codeshu.entity.User;
import com.codeshu.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2023-08-13 18:03:32
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	@Override
	public List<User> list() {
		return userDao.list();
	}

	@Override
	public void save(User user) {
		userDao.save(user);
	}
}
