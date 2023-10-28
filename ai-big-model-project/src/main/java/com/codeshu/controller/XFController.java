package com.codeshu.controller;

import com.codeshu.cache.QuestionAndAnswerCache;
import com.codeshu.config.XFConfigProperties;
import com.codeshu.config.XFWebSocketClient;
import com.codeshu.dao.AiUserDao;
import com.codeshu.entity.AiUser;
import com.codeshu.request.AskQuestionRequest;
import com.codeshu.websocket.XFWebSocketListener;
import com.codeshu.xfbean.RoleContent;
import lombok.extern.slf4j.Slf4j;
import okhttp3.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CodeShu
 * @date 2023/10/27 11:32
 */
@RestController
@RequestMapping("xf")
@Slf4j
public class XFController {
	@Autowired
	private XFWebSocketClient xfWebSocketClient;
	@Autowired
	private XFConfigProperties xfConfigProperties;
	@Resource
	private AiUserDao userDao;

	@GetMapping("askQuestion")
	public String askQuestion(AskQuestionRequest request) {
		// 存入新问题
		QuestionAndAnswerCache.addQuestionOrAnswer(request.getUid(), request.getQuestion(), "user");
		// 获取出此用户的所有历史提问和回答 + 最新一条提问，让 AI 可以根据以往的提问和回答进行回答
		List<RoleContent> questionAndAnswerList = QuestionAndAnswerCache.getAllQuestionAnswer(request.getUid());

		// 创建监听器
		XFWebSocketListener listener = new XFWebSocketListener(request.getUid());
		// 建立与大模型的 WebSocket 连接发起提问，下一步走指定的监听器
		WebSocket webSocket = xfWebSocketClient.sendMsg(request.getUid(), questionAndAnswerList, listener);
		if (webSocket == null) {
			log.error("webSocket连接异常");
			return "webSocket连接异常";
		}
		try {
			// 循环等待大模型的返回，若指定时间内未返回，则返回请求失败至前端，这里设置成 200 * 5 * 30= 30000 ms（30 s）
			int count = 0;
			int maxCount = xfConfigProperties.getMaxResponseTime() * 5;
			while (count <= maxCount) {
				Thread.sleep(200);
				// 在监听器中被设置为 true 则表示已经收到回答结果
				if (listener.getIsWsCloseFlag()) {
					break;
				}
				count++;
			}
			if (count > maxCount) {
				log.error("响应超时");
			}
		} catch (Exception e) {
			log.error("请求异常：{}", e);
		} finally {
			// 关闭 WebSocket 连接
			webSocket.close(1000, "");
		}
		// 从监听器中获取出回答
		return listener.getAnswer().toString();
	}

	/**
	 * 获取用户的所有提问和回答
	 */
	@GetMapping("/getAllQuestionAnswer/{uid}")
	public List<RoleContent> getAllQuestionAnswer(@PathVariable("uid") String uid) {
		return QuestionAndAnswerCache.getAllQuestionAnswer(uid);
	}

	@GetMapping("/getUser")
	public List<AiUser> getUser() {
		return userDao.selectList(null);
	}
}
