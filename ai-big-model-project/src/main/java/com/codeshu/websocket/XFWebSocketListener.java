package com.codeshu.websocket;

import com.alibaba.fastjson.JSON;
import com.codeshu.cache.QuestionAndAnswerCache;
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
	 * 大模型的回答
	 */
	private StringBuilder answer = new StringBuilder();

	/**
	 * 大模型请求是否已经回答 true-回复 否-尚未回复
	 */
	private Boolean isWsCloseFlag = false;

	public XFWebSocketListener() {
	}

	public XFWebSocketListener(String uid) {
		this.uid = uid;
	}

	/**
	 * 建立连接事触发
	 */
	@Override
	@SuppressWarnings("all")
	public void onOpen(WebSocket webSocket, Response response) {
		super.onOpen(webSocket, response);
		log.info("大模型服务器连接成功！");
	}

	/**
	 * 收到消息回调
	 */
	@Override
	@SuppressWarnings("all")
	public void onMessage(WebSocket webSocket, String text) {
		super.onMessage(webSocket, text);
		//获取大模型的返回结果
		JsonParse myJsonParse = JSON.parseObject(text, JsonParse.class);
		log.info("myJsonParse:{}", JSON.toJSONString(myJsonParse));
		if (myJsonParse.getHeader().getCode() != 0) {
			log.error("发生错误，错误信息为:{}", JSON.toJSONString(myJsonParse.getHeader()));
			this.answer.append("大模型响应异常");
			// 关闭连接标识
			isWsCloseFlag = true;
			return;
		}
		//获取出回答，可能回答长，所以可能会放到多个Text，拼接起来
		List<Text> textList = myJsonParse.getPayload().getChoices().getText();
		for (Text temp : textList) {
			log.info("返回结果信息为：【{}】", JSON.toJSONString(temp));
			this.answer.append(temp.getContent());
		}

		//为 2 表示回答完毕，可以关闭掉 WebSocket 请求
		if (myJsonParse.getHeader().getStatus() == 2) {
			isWsCloseFlag = true;
			//todo 将问答信息入库进行记录，可自行实现，这里模拟存储到缓存
			//存入新回答
			log.info("result:{}", this.answer.toString());
			QuestionAndAnswerCache.addQuestionOrAnswer(uid, answer.toString(), "assistant");
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
