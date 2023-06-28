package com.codeshu.network;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author CodeShu
 * @date 2023/6/28 15:56
 */
public class TestServer {
	@Test
	public void testGet() throws IOException {
		Request request = new Request.Builder()
				.url("http://127.0.0.1:8080/test/get?param=xxxx")
				.get()
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.addHeader("Accept", "application/json")
				.build();
		OkHttpClient httpClient = new OkHttpClient().newBuilder().build();
		Response httpResponse = httpClient.newCall(request).execute();
		System.out.println(httpResponse);
	}

	@Test
	public void testPost() throws IOException {
		PostParam param = new PostParam();
		param.setId(1L);
		param.setName("xxx");
		FormBody formBody = new FormBody.Builder().add("id", "1").add("name", "zhangsan").build();
		RequestBody.create(MediaType.parse("application/json"), JSONUtil.toJsonStr(param));
		Request request = new Request.Builder()
				.url("http://127.0.0.1:8080/test/post")
				.method("POST", formBody)
				.addHeader("Content-Type", "application/json")
				.addHeader("Accept", "application/json")
				.build();
		OkHttpClient httpClient = new OkHttpClient().newBuilder().build();
		Response httpResponse = httpClient.newCall(request).execute();
		assert httpResponse.body() != null;
		System.out.println(httpResponse.body().string());
	}

	@Data
	static class PostParam {
		private Long id;

		private String name;
	}
}
