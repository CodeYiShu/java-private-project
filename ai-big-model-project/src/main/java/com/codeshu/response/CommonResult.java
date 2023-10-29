package com.codeshu.response;

import lombok.Data;

/**
 * @author CodeShu
 * @date 2023/10/29 12:40
 */
@Data
public class CommonResult {
	/**
	 * 用来表示本次请求是否成功，200成功，非200失败
	 */
	private int code;
	/**
	 * 结果消息，无论接口因为什么原因调用失败，如果后端希望告知前端，都可以将必要信息存入这个字段
	 */
	private String message;
	/**
	 * 结果数据，用来存储实际的返回数据
	 */
	private Object data;

	public CommonResult() {
	}

	//响应正常数据时调用
	public static CommonResult success(Object data) {
		//响应状态码200，message为操作成功，data可能是用户对象等数据
		return success(200, "操作成功", data);
	}

	public static CommonResult success(int code, String message, Object data) {
		//自定义响应状态码、message和data
		CommonResult result = new CommonResult();
		result.setCode(code);
		result.setMessage(message);
		result.setData(data);
		return result;
	}

	//响应异常数据时调用
	public static CommonResult fail(String message, Object data) {
		//响应状态码400，自定义message和data
		return fail(400, message, data);
	}

	public static CommonResult fail(String message) {
		//响应状态码400，自定义message，没有data
		return fail(400, message, null);
	}

	public static CommonResult fail(int code, String message, Object data) {
		//自定义状态码、message和data
		CommonResult result = new CommonResult();
		result.setCode(code);
		result.setMessage(message);
		result.setData(data);
		return result;
	}
}
