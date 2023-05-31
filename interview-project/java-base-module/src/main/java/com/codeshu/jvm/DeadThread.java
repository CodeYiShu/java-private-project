package com.codeshu.jvm;

/**
 * @author CodeShu
 * @date 2023/5/31 16:54
 */
@SuppressWarnings("all")
public class DeadThread {
	static {
		if (true){
			System.out.println(Thread.currentThread().getName() + "正在初始化当前DeadThread类");
			while (true){

			}
		}
	}
}
