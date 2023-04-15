package com.codeshu.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 定义websocket服务器端，它的功能主要是将目前的类定义成一个websocket服务器端。注解的值将被用于监听用户连接的终端访问URL地址
 *
 * <p>
 * 地址1：<a href="https://blog.csdn.net/qq_48721706/article/details/124995148">...</a>
 * 地址2：<a href="https://blog.csdn.net/weixin_56995925/article/details/120543965?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-120543965-blog-124995148.235%5Ev29%5Epc_relevant_default_base3&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-120543965-blog-124995148.235%5Ev29%5Epc_relevant_default_base3&utm_relevant_index=1">...</a>
 *
 * @author CodeShu
 * @date 2023/4/15 21:08
 */
@Component
@ServerEndpoint("/websocket/{userId}")
public class WebSocket {
	private final static Logger logger = LoggerFactory.getLogger(WebSocket.class);
	/**
	 * 实例一个session，这个session是当前websocket对象的session，一个对象一个Session
	 */
	private Session session;

	/**
	 * 存放websocket的集合（本次demo不会用到，聊天室的demo会用到）
	 * <p>
	 * 注：虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，所以可以用一个静态set保存起来
	 */
	private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

	/**
	 * 用来存在线连接用户信息，一个用户ID对应一个Session对象
	 * <p>
	 * 注：
	 * 一个请求对应一个Session，这个请求携带userId过来让用户ID和Session绑定，所以一个用户ID对应一个Session
	 * 一个WebSocket对象操作一个Session
	 */
	private static ConcurrentHashMap<String, Session> sessionPool = new ConcurrentHashMap<>();

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 前端请求时创建一个websocket对象
	 *
	 * @param session session对象
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam(value = "userId") Long userId) {
		this.session = session;
		this.userId = userId;
		webSocketSet.add(this);
		sessionPool.put(String.valueOf(userId), session);
		logger.info("【websocket消息】有新的连接, 总数:{}", webSocketSet.size());
	}

	/**
	 * 前端关闭时一个websocket时
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);
		sessionPool.remove(String.valueOf(this.userId));
		logger.info("【websocket消息】连接断开, 总数:{}", webSocketSet.size());
	}

	/**
	 * 前端向后端发送消息
	 *
	 * @param message 消息
	 */
	@OnMessage
	public void onMessage(String message) {
		logger.info("【websocket消息】收到客户端发来的消息:{}", message);
	}

	/**
	 * 后端主动向每个客户端发送消息
	 *
	 * @param message 消息
	 */
	public void sendMessage(String message) {
		//向每个客户端进行发送
		for (WebSocket webSocket : webSocketSet) {
			logger.info("【websocket消息】广播消息, message={}", message);
			try {
				webSocket.session.getBasicRemote().sendText(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 后端主动向单个客户端发送消息
	 *
	 * @param userId  用户ID
	 * @param message 消息
	 */
	public void sendMessage(Long userId, String message) {
		//根据用户ID获取其对应的Session
		Session session = sessionPool.get(String.valueOf(userId));
		if (session != null && session.isOpen()) {
			try {
				logger.info("【websocket消息】 单点消息:" + message);
				session.getAsyncRemote().sendText(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
