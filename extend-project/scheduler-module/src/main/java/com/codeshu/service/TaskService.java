package com.codeshu.service;

/**
 * @author CodeShu
 * @date 2023/8/22 11:33
 */
public interface TaskService {
	/**
	 * 启动任务
	 *
	 * @param cron cron表达式
	 */
	void startTask(String cron);

	/**
	 * 停止任务
	 */
	void stopTask();
}
