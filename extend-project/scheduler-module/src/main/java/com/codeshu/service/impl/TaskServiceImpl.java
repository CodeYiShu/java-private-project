package com.codeshu.service.impl;

import com.codeshu.service.TaskService;
import com.codeshu.task.MyTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ScheduledFuture;

/**
 * @author CodeShu
 * @date 2023/8/22 11:33
 */
@Slf4j
@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private ThreadPoolTaskScheduler scheduler;

	/**
	 * 存放任务的执行情况
	 */
	private ScheduledFuture future;

	@Override
	public void startTask(String cron) {
		//每次调用前,可执行一次关闭之前的
		stopTask();
		//构造 cron 触发器
		CronTrigger cronTrigger = new CronTrigger(cron);
		//调度定时任务，将情况放入 future
		future = scheduler.schedule(new MyTask(), cronTrigger);
	}

	@Override
	public void stopTask() {
		if (Objects.nonNull(future)) {
			future.cancel(true);
			log.info("任务停止成功");
		}
	}
}
