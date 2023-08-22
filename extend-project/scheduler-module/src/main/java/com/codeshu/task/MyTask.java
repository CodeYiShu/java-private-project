package com.codeshu.task;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author CodeShu
 * @date 2023/8/22 11:31
 */
@Slf4j
public class MyTask implements Runnable {
	@Override
	public void run() {
		log.info("我执行了，时间是：" + new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").format(new Date()));
	}
}
