package com.codeshu.controller;

import com.codeshu.entity.AiRoleContent;
import com.codeshu.request.GetAllQuestionAnswerRequest;
import com.codeshu.response.CommonResult;
import com.codeshu.service.AiRoleContentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CodeShu
 * @date 2023/10/29 12:56
 */
@RestController
@RequestMapping("/ai/role/content")
public class AiRoleContentController {
	@Autowired
	private AiRoleContentService service;

	@GetMapping("/getAllQuestionAnswer")
	public CommonResult getAllQuestionAnswer(GetAllQuestionAnswerRequest request) {
		PageInfo<AiRoleContent> pageInfo = service.getAllQuestionAnswer(request);
		return CommonResult.success(pageInfo);
	}
}
