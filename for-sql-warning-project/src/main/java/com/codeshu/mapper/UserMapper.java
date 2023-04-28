package com.codeshu.mapper;

import com.codeshu.entity.UserEntity;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/1/7 17:37
 */
public interface UserMapper {
	List<UserEntity> selectAll();
}
