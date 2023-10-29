package com.codeshu.bak.cache;

import com.codeshu.xfbean.RoleContent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户所提出的所有问题和 AI 的所有回答（可用 MySQL 替换）
 *
 * @author CodeShu
 * @date 2023/10/27 13:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QuestionAndAnswerCache {
	/**
	 * Key：用户 ID
	 * Value：用户所提出的所有问题和 AI 的所有回答
	 */
	private static final Map<String, List<RoleContent>> uidQuestionListMap = new HashMap<>();

	/**
	 * 添加用户的问题或 AI 的回答
	 *
	 * @param uid  用户 ID
	 * @param text 问题或者回答
	 * @param type user-提问 assistant-回答
	 */
	public static void addQuestionOrAnswer(String uid, String text, String type) {
		List<RoleContent> requestList = uidQuestionListMap.get(uid);
		if (CollectionUtils.isEmpty(requestList)) {
			uidQuestionListMap.put(uid, new ArrayList<>());
		}
		//存入最新问题或最新回答
		RoleContent roleContent = new RoleContent();
		roleContent.setRole(type);
		roleContent.setContent(text);
		uidQuestionListMap.get(uid).add(roleContent);
	}

	/**
	 * 获取用户所提出的所有问题和 AI 的所有回答
	 */
	public static List<RoleContent> getAllQuestionAnswer(String uid) {
		List<RoleContent> requestList = uidQuestionListMap.get(uid);
		if (CollectionUtils.isEmpty(requestList)) {
			return new ArrayList<>();
		}
		return requestList;
	}
}
