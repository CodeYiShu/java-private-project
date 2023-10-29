package com.codeshu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.codeshu.dao.AiRoleContentDao;
import com.codeshu.entity.AiRoleContent;
import com.codeshu.request.GetAllQuestionAnswerRequest;
import com.codeshu.service.AiRoleContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AiRoleContent)表服务实现类
 *
 * @author makejava
 * @since 2023-10-28 13:36:45
 */
@Service("aiRoleContentService")
public class AiRoleContentServiceImpl implements AiRoleContentService {
	@Resource
	private AiRoleContentDao dao;

	@Override
	public PageInfo<AiRoleContent> getAllQuestionAnswer(GetAllQuestionAnswerRequest request) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		LambdaQueryWrapper<AiRoleContent> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(AiRoleContent::getUserId, request.getUid());
		List<AiRoleContent> contentList = dao.selectList(queryWrapper);
		return new PageInfo<>(contentList);
	}

	@Override
	public List<AiRoleContent> getByUserId(Long userId) {
		LambdaQueryWrapper<AiRoleContent> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(AiRoleContent::getUserId, userId);
		return dao.selectList(queryWrapper);
	}

	@Override
	public void insert(AiRoleContent aiRoleContent) {
		dao.insert(aiRoleContent);
	}
}
