package com.codeshu.test03.mapper;

import com.codeshu.test03.entity.Test03User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/5/6 17:15
 */
@Mapper
public interface Test03UserMapper {
	/**
	 * 查询所有用户
	 */
	List<Test03User> selectList();
}
