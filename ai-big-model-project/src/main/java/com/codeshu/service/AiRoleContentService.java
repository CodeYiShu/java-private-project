package com.codeshu.service;

import com.codeshu.entity.AiRoleContent;
import com.codeshu.request.GetAllQuestionAnswerRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (AiRoleContent)表服务接口
 *
 * @author makejava
 * @since 2023-10-28 13:36:44
 */
public interface AiRoleContentService {
	/**
	 * 获取用户的所有提问和回答-分页
	 */
	PageInfo<AiRoleContent> getAllQuestionAnswer(GetAllQuestionAnswerRequest request);

	/**
	 * 获取用户的所有提问和回答
	 */
	List<AiRoleContent> getByUid(String uid);

	/**
	 * 插入提问和回答
	 */
	void insert(AiRoleContent aiRoleContent);
}
