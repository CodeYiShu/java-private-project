package com.codeshu.thread.semaphore;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author CodeShu
 * @date 2023/7/10 15:18
 */
public class HasSemaphoreTest2 {
	private static final int CORE_POOL_SIZE = 10;   //核心线程数
	private static final int MAX_POOL_SIZE = 10;    //运行的最大线程数量
	private static final int QUEUE_CAPACITY = 100;  //任务队列大小
	private static final Long KEEP_ALIVE_TIME = 1L; //等待的时间超过了 keepAliveTime 回收大于 corePoolSize 的线程

	public static void main(String[] args) throws InterruptedException {
		TableController tableController = new TableController();
		Thread netword1 = new Thread(tableController);
		Thread netword2 = new Thread(tableController);
		Thread netword3 = new Thread(tableController);
		netword1.start();
		netword2.start();
		netword3.start();
	}

	//发起操作的父线程（模拟网络请求）
	static class TableController implements Runnable {
		//创建线程池
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue<>(QUEUE_CAPACITY), new ThreadPoolExecutor.CallerRunsPolicy());

		//一个10张表，需要10个工作线程
		int tableCount = 10;
		//创建CountDownLatch类，指定latch值为10，也就是有10个工作线程
		CountDownLatch countDownLatch = new CountDownLatch(tableCount);
		//创建semaphore信号量，指定有5个信号量，也就是控制线程并发数是5个
		Semaphore semaphore = new Semaphore(5);
		//创建semaphore信号量，指定有1个信号量，也就是发起操作的网络请求（父线程）只能有1个
		Semaphore sourceSemaphore = new Semaphore(1);

		@Override
		public void run() { //模拟控制器方法
			try {
				//发起操作的父线程，尝试获取sourceSemaphore信号量，获取不到返回false则走else
				if (sourceSemaphore.tryAcquire()) {
					System.out.println("我是网络请求，我获取到了信号量sourceSemaphore，发起操作表的任务");
					//循环处理每张表，循环一次创建1个线程
					for (int i = 1; i <= tableCount; i++) {
						//让父线程获取一个信号量，获取不到则阻塞，不可往下走去创建工作线程
						try {
							semaphore.acquire();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						//将CountDownLatch传入和操作表号给工作线程，同时还要传递信号量，让其完成任务后去释放
						ThreadTask threadTask = new ThreadTask(countDownLatch, i, semaphore);
						//从线程池分配 1 个线程去完成任务
						threadPoolExecutor.submit(threadTask);
					}
					//父线程等待所有工作线程完成任务，再继续往下执行
					countDownLatch.await();
					//父线程打印完成任务的日志
					System.out.println("所有工作线程已经完成工作");
				} else {
					System.out.println("我是网络请求，已经有1个网络请求发起了操作，我走了~");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	static class ThreadTask implements Runnable {
		//父线程传递的countDownLatch，当工作线程完成任务之后让latch-1
		CountDownLatch countDownLatch;
		//父线程交给工作线程操作的数据
		int table;
		//父线程传递的信号量，当工作线程完成任务之后释放
		Semaphore semaphore;

		public ThreadTask(CountDownLatch countDownLatch, int table, Semaphore semaphore) {
			this.countDownLatch = countDownLatch;
			this.table = table;
			this.semaphore = semaphore;
		}

		@Override
		public void run() {
			try {
				Thread.currentThread().setName("线程" + table);
				//工作线程的任务
				System.out.println(Thread.currentThread().getName() + "：操作表" + table);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//无论完成还是抛出异常，都表示任务结束
				//latch-1，表示任务完成
				countDownLatch.countDown();
				//释放信号量，以便于工作线程接着去分配更多一个工作线程完成任务
				semaphore.release();
				System.out.println(Thread.currentThread().getName() + "已经释放信号量，剩余信号量个数：" + semaphore.availablePermits());
			}
		}
	}
}
