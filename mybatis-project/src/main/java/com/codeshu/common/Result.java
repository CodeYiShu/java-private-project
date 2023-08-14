package com.codeshu.common;

import java.io.Serializable;

/**
 * @author CodeShu
 * @date 2023/8/14 10:45
 */
public class Result<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 编码：0表示成功，其他值表示失败
	 */
	private int code = 0;

	/**
	 * 消息内容
	 */
	private String msg = "success";
	/**
	 * 响应数据
	 */
	private T data;

	public Result<T> ok(T data) {
		this.setData(data);
		return this;
	}

	public Result<T> okMsg(String msg) {
		this.code = 0;
		this.setMsg(msg);
		return this;
	}

	public boolean success() {
		return code == 0 ? true : false;
	}

	public Result<T> error() {
		this.code = 500;
		this.msg = "服务器异常";
		return this;
	}

	public Result<T> error(int code, String msg) {
		this.code = code;
		this.msg = msg;
		return this;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public Result<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "{"
				+ "\"code\":"
				+ code
				+ ",\"msg\":\""
				+ msg + '\"'
				+ ",\"data\":"
				+ data
				+ "}";

	}
}
