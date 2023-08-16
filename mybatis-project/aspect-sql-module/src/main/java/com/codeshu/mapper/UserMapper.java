package com.codeshu.mapper;

import com.codeshu.response.GetUserInfoResponse;
import org.apache.ibatis.annotations.Param;

/**
 * @author CodeShu
 * @date 2023/7/22 16:52
 */
public interface UserMapper {
	GetUserInfoResponse getUserInfo(@Param("id") Long id);
}
