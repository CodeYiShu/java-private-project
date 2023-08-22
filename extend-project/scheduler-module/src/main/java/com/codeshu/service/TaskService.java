package com.codeshu.service;

import com.codeshu.task.MyTask;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/8/22 11:33
 */
public interface TaskService {

	/**
	 * 获取所有定时任务
	 *
	 * @return 定时任务
	 */
	List<MyTask> list();

	/**
	 * 启动任务
	 *
	 * @param taskName 任务名称
	 */
	String startTask(String taskName);

	/**
	 * 停止任务
	 */
	String stopTask(String taskName);

	/**
	 * 获取任务状态
	 *
	 * @param taskName 任务名称
	 * @return 状态
	 */
	String getStatus(String taskName);

	/**
	 * 删除定时任务
	 *
	 * @param taskName 任务名称
	 */
	String deleteTask(String taskName);
}
