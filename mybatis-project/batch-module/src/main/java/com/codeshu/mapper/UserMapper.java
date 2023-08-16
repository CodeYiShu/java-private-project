package com.codeshu.mapper;

import com.codeshu.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/7/22 16:52
 */
public interface UserMapper {
	/**
	 * 批量插入
	 */
	void insertBatch(@Param("userEntityList") List<UserEntity> userEntityList);
}
