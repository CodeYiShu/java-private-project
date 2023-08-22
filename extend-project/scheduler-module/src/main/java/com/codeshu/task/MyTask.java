package com.codeshu.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author CodeShu
 * @date 2023/8/22 11:31
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyTask implements Runnable {
	private String taskName;

	private String cron;

	@Override
	public void run() {
		log.info(taskName + "执行了，时间是：" + new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").format(new Date()));
	}
}
