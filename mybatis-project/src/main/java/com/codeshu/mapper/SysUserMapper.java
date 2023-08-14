package com.codeshu.mapper;

import com.codeshu.entity.SysUserEntity;
import com.codeshu.response.GetUserInfoResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/7/22 16:52
 */
public interface SysUserMapper {
	GetUserInfoResponse getUserInfo(@Param("id") Long id);

	/**
	 * 批量插入
	 */
	void insertBatch(@Param("userEntityList") List<SysUserEntity> userEntityList);
}
