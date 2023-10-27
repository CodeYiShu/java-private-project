package com.codeshu.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codeshu.xfbean.RoleContent;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

/**
 * @author CodeShu
 * @date 2023/10/27 10:32
 */
@Slf4j
@Component
public class XFWebSocketClient {
	@Autowired
	private XFConfigProperties xfConfigProperties;

	/**
	 * 发送请求至大模型方法
	 *
	 * @param uid                   用户ID
	 * @param questionAndAnswerList 历史的问题和回答 + 最后一条新问题
	 * @param listener              指定 Websocket 监听器
	 **/
	public WebSocket sendMsg(String uid, List<RoleContent> questionAndAnswerList, WebSocketListener listener) {
		String authUrl;
		try {
			// 获取鉴权url
			authUrl = getAuthUrl(xfConfigProperties.getHostUrl(), xfConfigProperties.getApiKey(), xfConfigProperties.getApiSecret());
		} catch (Exception e) {
			// 鉴权方法生成失败，直接返回 null
			log.error("鉴权失败：{}", e);
			return null;
		}
		OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
		// 将 https/http 连接替换为 ws/wss 连接
		String url = authUrl.replace("http://", "ws://").replace("https://", "wss://");
		Request request = new Request.Builder().url(url).build();
		// 指定请求对象和监听器，建立 wss 连接
		WebSocket webSocket = okHttpClient.newWebSocket(request, listener);
		// 组装请求参数
		JSONObject requestDTO = createRequestParams(uid, questionAndAnswerList);
		// 指定请求参数，发送请求
		webSocket.send(JSONObject.toJSONString(requestDTO));
		return webSocket;
	}

	/**
	 * 鉴权方法
	 *
	 * @param hostUrl   API 地址
	 * @param apiKey    API Key
	 * @param apiSecret API Secret
	 * @return 鉴权url
	 */
	public static String getAuthUrl(String hostUrl, String apiKey, String apiSecret) throws Exception {
		URL url = new URL(hostUrl);
		// 时间
		SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		String date = format.format(new Date());
		// 拼接
		String preStr = "host: " + url.getHost() + "\n" +
				"date: " + date + "\n" +
				"GET " + url.getPath() + " HTTP/1.1";
		// System.err.println(preStr);
		// SHA256加密
		Mac mac = Mac.getInstance("hmacsha256");
		SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "hmacsha256");
		mac.init(spec);

		byte[] hexDigits = mac.doFinal(preStr.getBytes(StandardCharsets.UTF_8));
		// Base64加密
		String sha = Base64.getEncoder().encodeToString(hexDigits);
		// System.err.println(sha);
		// 拼接
		String authorization = String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", apiKey, "hmac-sha256", "host date request-line", sha);
		// 拼接地址
		HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse("https://" + url.getHost() + url.getPath())).newBuilder().//
				addQueryParameter("authorization", Base64.getEncoder().encodeToString(authorization.getBytes(StandardCharsets.UTF_8))).//
				addQueryParameter("date", date).//
				addQueryParameter("host", url.getHost()).//
				build();

		// System.err.println(httpUrl.toString());
		return httpUrl.toString();
	}

	/**
	 * 构建参数
	 */
	public JSONObject createRequestParams(String uid, List<RoleContent> questionAndAnswerList) {
		JSONObject requestJson = new JSONObject();
		// header参数
		JSONObject header = new JSONObject();
		header.put("app_id", xfConfigProperties.getAppid());
		header.put("uid", uid);
		// parameter参数
		JSONObject parameter = new JSONObject();
		JSONObject chat = new JSONObject();
		chat.put("domain", "generalv3");
		chat.put("temperature", 0.5);
		chat.put("max_tokens", 4096);
		parameter.put("chat", chat);
		// payload参数
		JSONObject payload = new JSONObject();
		JSONObject message = new JSONObject();
		JSONArray text = new JSONArray();
		text.addAll(questionAndAnswerList);

		message.put("text", text);
		payload.put("message", message);
		requestJson.put("header", header);
		requestJson.put("parameter", parameter);
		requestJson.put("payload", payload);
		return requestJson;
	}
}
