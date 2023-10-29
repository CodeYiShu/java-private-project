package com.codeshu.websocket;

import com.alibaba.fastjson.JSON;
import com.codeshu.entity.AiRoleContent;
import com.codeshu.service.AiRoleContentService;
import com.codeshu.utils.SpringApplicationContextHolder;
import com.codeshu.xfbean.JsonParse;
import com.codeshu.xfbean.Text;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import java.io.IOException;
import java.util.List;

/**
 * WebSocket 监听器
 *
 * @author CodeShu
 * @date 2023/10/27 11:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class XFWebSocketListener extends WebSocketListener {
	/**
	 * 用户 ID
	 */
	private String uid;

	/**
	 * 大模型的回答内容
	 */
	private StringBuilder answer = new StringBuilder();

	/**
	 * 大模型请求是否已经回答
	 * <p>
	 * true-回复 false-尚未回复
	 */
	private Boolean isWsCloseFlag = false;

	public XFWebSocketListener() {
	}

	public XFWebSocketListener(String uid) {
		this.uid = uid;
	}

	/**
	 * 建立连接时触发
	 */
	@Override
	@SuppressWarnings("all")
	public void onOpen(WebSocket webSocket, Response response) {
		super.onOpen(webSocket, response);
		log.info("大模型服务器连接成功！");
	}

	/**
	 * 收到回答消息的回调
	 */
	@Override
	@SuppressWarnings("all")
	public void onMessage(WebSocket webSocket, String text) {
		super.onMessage(webSocket, text);
		// 获取大模型的返回结果
		JsonParse myJsonParse = JSON.parseObject(text, JsonParse.class);
		log.info("myJsonParse:{}", JSON.toJSONString(myJsonParse));
		if (myJsonParse.getHeader().getCode() != 0) {
			log.error("发生错误，错误信息为:{}", JSON.toJSONString(myJsonParse.getHeader()));
			this.answer.append("大模型响应异常");
			// 关闭连接标识
			isWsCloseFlag = true;
			return;
		}
		//获取出回答，可能回答长，所以可能会放到多个 Text，拼接起来
		List<Text> answerList = myJsonParse.getPayload().getChoices().getText();
		for (Text splitAnswer : answerList) {
			log.info("返回的回答（分片）：【{}】", JSON.toJSONString(splitAnswer));
			this.answer.append(splitAnswer.getContent());
		}

		//为 2 表示回答完毕，可以关闭掉 WebSocket 请求
		if (myJsonParse.getHeader().getStatus() == 2) {
			isWsCloseFlag = true;
			//todo 将问答信息入库进行记录，可自行实现，这里模拟存储到缓存
			log.info("返回的回答（完整）：【{}】", this.answer.toString());
			//存入新回答
			AiRoleContent aiRoleContent = new AiRoleContent();
			aiRoleContent.setUserId(Long.valueOf(uid));
			aiRoleContent.setContent(answer.toString());
			aiRoleContent.setRole("assistant");
			SpringApplicationContextHolder.getBeanByClass(AiRoleContentService.class).insert(aiRoleContent);
		}
	}

	@Override
	@SuppressWarnings("all")
	public void onFailure(WebSocket webSocket, Throwable t, Response response) {
		super.onFailure(webSocket, t, response);
		try {
			if (null != response) {
				int code = response.code();
				log.error("onFailure body:{}", response.body().string());
				if (101 != code) {
					log.error("讯飞星火大模型连接异常");
				}
			}
		} catch (IOException e) {
			log.error("IO异常：{}", e);
		}
	}
}
