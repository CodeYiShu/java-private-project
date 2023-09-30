package com.codeshu.file;

import org.junit.Test;

import java.io.File;

/**
 * 创建 File 对象（所对应文件可不存在）
 *
 * @author CodeShu
 * @date 2023/9/25 15:26
 */
public class CreateFileObjectTest {
	@Test
	public void test01() {
		//创建一个 File 对象（相对路径）
		File file1 = new File("Hello1.txt");
		//创建一个 File 对象（绝对路径）
		File file2 = new File("E:\\java\\program\\java-private-project" +
				"\\interview-project\\io-module" +
				"\\src\\main\\java\\com\\codeshu\\file\\Hello2.txt");
		System.out.println(file1);
		System.out.println(file2);
	}

	@Test
	public void test02() {
		//父子路径的形式
		File file = new File("E:\\java\\program\\java-private-project" +
				"\\interview-project\\io-module" +
				"\\src\\main\\java\\com\\codeshu\\file", "Hello.txt");
		System.out.println(file);
	}

	@Test
	public void test03() {
		//父子路径的形式
		File file1 = new File("E:\\java\\program\\java-private-project" +
				"\\interview-project\\io-module" +
				"\\src\\main\\java\\com\\codeshu\\file");
		File file2 = new File(file1, "Hello.txt");
		System.out.println(file2);
	}
}
