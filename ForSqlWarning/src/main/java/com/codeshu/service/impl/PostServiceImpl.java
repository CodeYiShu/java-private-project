package com.codeshu.service.impl;

import com.codeshu.entity.PostEntity;
import com.codeshu.mapper.PostMapper;
import com.codeshu.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author CodeShu
 * @date 2023/1/10 19:24
 */
@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostMapper postMapper;

	@Override
	public List<PostEntity> getByIds(Collection<Long> ids) {
		return postMapper.selectByIds(ids);
	}
}
