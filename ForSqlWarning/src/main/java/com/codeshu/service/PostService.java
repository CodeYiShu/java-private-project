package com.codeshu.service;

import com.codeshu.entity.PostEntity;

import java.util.Collection;
import java.util.List;

/**
 * @author CodeShu
 * @date 2023/1/10 19:24
 */
public interface PostService {
	/**
	 * 根据ID批量获取岗位信息
	 */
	List<PostEntity> getByIds(Collection<Long> ids);
}
