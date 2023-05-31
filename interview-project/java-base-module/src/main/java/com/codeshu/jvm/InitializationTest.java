package com.codeshu.jvm;

/**
 * @author CodeShu
 * @date 2023/5/31 13:56
 */
@SuppressWarnings("all")
public class InitializationTest {
	static class Father{ //父类
		public static int A = 1; //将A初始化为1
		static {
			A = 2; //再将A赋值为2
		}
	}

	static class Son extends Father{ //子类
		public static int B = A; //将父类的A赋值给B
	}

	public static void main(String[] args) {
		System.out.println(Son.B); //访问子类对象的B属性
	}
}
