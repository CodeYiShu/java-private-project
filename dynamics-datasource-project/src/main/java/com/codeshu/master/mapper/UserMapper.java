package com.codeshu.master.mapper;

import com.codeshu.master.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/5/6 17:15
 */
@Mapper
public interface UserMapper {
	/**
	 * 查询所有用户
	 */
	List<User> selectList();
}
