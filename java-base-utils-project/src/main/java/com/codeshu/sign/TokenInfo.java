package com.codeshu.sign;

/**
 * @author CodeShu
 * @date 2023/2/5 14:10
 */
public class TokenInfo {

	private boolean isAuth;
	private Integer code;
	private String msg;

	public boolean isAuth() {
		return isAuth;
	}

	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
