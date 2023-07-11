package com.codeshu.threadlocal;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CodeShu
 * @date 2023/7/11 10:56
 */
@Service
public class TestService {
	public void find(){
		//获取当前线程对象的请求对象（本地变量）
		HttpServletRequest httpServletRequest = HttpServletRequestHolder.get();
	}
}
