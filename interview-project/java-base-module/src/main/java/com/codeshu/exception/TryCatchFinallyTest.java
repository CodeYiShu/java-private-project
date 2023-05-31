package com.codeshu.exception;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author CodeShu
 * @date 2023/5/25 9:39
 */
@SuppressWarnings("all")
public class TryCatchFinallyTest {
	@Test
	public void test1() {
		try {
			System.out.println("Try to do something  1");
			int i = 1 / 0;
			System.out.println("Try to do something  2");
		} catch (Exception e) {
			System.out.println("Catch Exception -> " + e.getMessage());
		} finally {
			System.out.println("Finally");
		}
	}

	@Test
	public void test2() {
		System.out.println(fun(2));
	}

	public static int fun(int value) {
		try {
			//try具备return，应当返回4
			return value * value;
		} finally {
			if (value == 2) {
				//finally的return
				return 0;
			}
		}
	}

	@Test
	public void test3() {
		//读取文本文件的内容
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("D://Download//PanDownload//libcurl.dll"));
			while (scanner.hasNext()) {
				System.out.println(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			//关闭资源
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	@Test
	public void test4() {
		try (Scanner scanner = new Scanner(new File("D://Download//PanDownload//libcurl.dll"))) {
			while (scanner.hasNext()) {
				System.out.println(scanner.nextLine());
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}
}
