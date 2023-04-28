package com.codeshu.mapper;

import com.codeshu.entity.PostEntity;

import java.util.Collection;
import java.util.List;

/**
 * @author CodeShu
 * @date 2023/1/10 19:22
 */
public interface PostMapper {
	List<PostEntity> selectByIds(Collection<Long> ids);
}
