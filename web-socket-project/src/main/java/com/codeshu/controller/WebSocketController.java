package com.codeshu.controller;

import com.codeshu.server.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CodeShu
 * @date 2023/4/15 21:36
 */
@RestController
@EnableScheduling
public class WebSocketController {
	@Autowired
	private WebSocket webSocket;

	/**
	 * 前端调用后端，获取实时信息
	 *
	 * @throws InterruptedException
	 */
	@PostMapping("/getData")
	public void getData() throws InterruptedException {
		String msg = "";
		for (int i = 0; i <= 100; i++) {
			msg = String.valueOf(i);
			Thread.sleep(100);
			webSocket.sendMessage(msg);
		}
	}

	/**
	 * 后端主动发送数据给前端
	 *
	 * @throws InterruptedException
	 */
	//@Scheduled(cron = "* * * * * ?")
	public void sendData() throws InterruptedException {
		String msg = "";
		for (int i = 0; i <= 100; i = i + 2) {
			msg = String.valueOf(i);
			Thread.sleep(100);
			webSocket.sendMessage(msg);
		}
	}

	/**
	 * 给单个客户端发
	 *
	 * @throws InterruptedException
	 */
	@GetMapping("/sendDataToOne")
	public void sendDataToOne(Long userId) throws InterruptedException {
		String msg = "";
		for (int i = 0; i <= 100; i = i + 2) {
			msg = String.valueOf(i);
			Thread.sleep(100);
			webSocket.sendMessage(userId, msg);
		}
	}
}
