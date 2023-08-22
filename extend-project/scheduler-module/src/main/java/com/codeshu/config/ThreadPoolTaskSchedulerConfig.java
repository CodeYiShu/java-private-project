package com.codeshu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 配置调度器
 *
 * @author CodeShu
 * @date 2023/8/22 11:24
 */
@Configuration
public class ThreadPoolTaskSchedulerConfig {

	//配置定时任务线程池-自定义名称避免冲突
	@Bean(name = "myThreadPoolTaskScheduler")
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
		//线程池的线程数量限制
		executor.setPoolSize(2);
		//定时任务线程的名称前缀
		executor.setThreadNamePrefix("codeshu-task-");
		//关闭时不中断正在运行的任务并执行队列中的所有任务
		executor.setWaitForTasksToCompleteOnShutdown(true);
		//关闭时最多等待 60 秒
		executor.setAwaitTerminationSeconds(60);
		return executor;
	}
}
