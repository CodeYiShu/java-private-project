package com.codeshu.service.impl;

import com.codeshu.config.ThreadPoolTaskSchedulerConfig;
import com.codeshu.enums.TaskStatusEnum;
import com.codeshu.service.TaskService;
import com.codeshu.task.MyTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

	@Override
	public List<MyTask> list() {
		List<MyTask> myTaskList = new ArrayList<>();
		myTaskList.add(new MyTask("task1", "0/1 * * * * ?"));
		myTaskList.add(new MyTask("task2", "0/2 * * * * ?"));
		return myTaskList;
	}

	@Override
	public String startTask(String taskName) {
		//获取定时任务对象
		List<MyTask> myTaskList = list();
		MyTask myTask = null;
		for (MyTask task : myTaskList) {
			if (task.getTaskName().equals(taskName)) {
				myTask = task;
			}
		}
		if (Objects.nonNull(myTask)) {
			String status = getStatus(taskName);
			if (TaskStatusEnum.RUNNING.getDesc().equals(status)) {
				return "定时任务已经启动";
			}

			//构造 cron 触发器
			CronTrigger cronTrigger = new CronTrigger(myTask.getCron());
			//调度定时任务，将情况放入 future
			ScheduledFuture future = scheduler.schedule(myTask, cronTrigger);

			//将定时任务保存下来
			ThreadPoolTaskSchedulerConfig.FUTURE_LIST.put(myTask.getTaskName(), future);
			return "开启定时任务成功：" + taskName;
		} else {
			return "定时任务找不到";
		}
	}

	@Override
	public String stopTask(String taskName) {
		//从队列获取出定时任务
		ScheduledFuture future = ThreadPoolTaskSchedulerConfig.FUTURE_LIST.get(taskName);
		//如果非关闭状态则进行关闭
		if (Objects.nonNull(future) && !future.isCancelled()) {
			future.cancel(true);
			return "关闭定时任务成功：" + taskName;
		}
		return "定时任务不存在或定时任务已关闭";
	}

	@Override
	public String getStatus(String taskName) {
		ScheduledFuture future = ThreadPoolTaskSchedulerConfig.FUTURE_LIST.get(taskName);
		if (Objects.isNull(future)) {
			return "定时任务不存在";
		}

		if (future.isCancelled()) {
			return TaskStatusEnum.STOP.getDesc();
		} else {
			return TaskStatusEnum.RUNNING.getDesc();
		}
	}

	@Override
	public String deleteTask(String taskName) {
		ScheduledFuture future = ThreadPoolTaskSchedulerConfig.FUTURE_LIST.get(taskName);
		if (Objects.isNull(future)) {
			return "定时任务不存在";
		}

		// 还未关闭则先进行关闭
		if (!future.isCancelled()) {
			stopTask(taskName);
		}

		//从队列移除
		ThreadPoolTaskSchedulerConfig.FUTURE_LIST.remove(taskName);
		return "删除定时任务成功";
	}
}
