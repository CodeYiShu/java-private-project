package com.codeshu.dao;

import com.codeshu.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-13 18:03:30
 */
@Mapper
public interface UserDao {
	List<User> list();

	void save(User user);
}

