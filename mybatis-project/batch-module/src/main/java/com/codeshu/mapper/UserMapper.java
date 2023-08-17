package com.codeshu.mapper;

import com.codeshu.entity.UserEntity;
import com.codeshu.response.SelectBatchResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/7/22 16:52
 */
public interface UserMapper {
	/**
	 * 批量查询
	 */
	List<SelectBatchResponse> selectBatch(@Param("ids") List<Long> ids);

	/**
	 * 批量插入
	 */
	void insertBatch(@Param("userEntityList") List<UserEntity> userEntityList);

	/**
	 * 批量更新
	 */
	void updateBatch(@Param("userEntityList") List<UserEntity> userEntityList);

	/**
	 * 批量删除
	 */
	void deleteBatch(@Param("ids") List<Long> ids);
}
