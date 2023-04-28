package com.codeshu.springgetbean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试工具类
 * @author CodeShu
 * @date 2022/11/25 19:32
 */
@Controller
public class TestHolder {
	private static final int CORE_POOL_SIZE = 5; //核心线程数
	private static final int MAX_POOL_SIZE = 10; //最大线程数
	private static final int QUEUE_CAPACITY = 100; //任务队列大小
	private static final Long KEEP_ALIVE_TIME = 1L; //等待时间
	ThreadPoolExecutor executorService = new ThreadPoolExecutor(
			CORE_POOL_SIZE,
			MAX_POOL_SIZE,
			KEEP_ALIVE_TIME,
			TimeUnit.SECONDS,
			new ArrayBlockingQueue<>(QUEUE_CAPACITY),
			new ThreadPoolExecutor.CallerRunsPolicy());

	@GetMapping("/testHolder")
	@ResponseBody
	public String testHolder(){
		executorService.submit(new MyRunnable());
		return "";
	}
}
