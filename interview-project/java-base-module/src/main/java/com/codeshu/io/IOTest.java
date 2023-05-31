package com.codeshu.io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * @author CodeShu
 * @date 2023/5/25 15:02
 */
@SuppressWarnings("all")
public class IOTest {
	@Test
	public void test1() throws IOException {
		File file = new File("file.txt");
		InputStream inputStream = new FileInputStream(file);
		byte[] bytes = new byte[1024];
		inputStream.read(bytes);
		System.out.println(bytes);
		System.out.println(new String(bytes));  //手动转为字符
	}

	@Test
	public void test2() throws IOException {
		File file = new File("file.txt");
		Reader reader = new FileReader(file);
		char[] chars = new char[1024];
		reader.read(chars);  //直接将二进制数据转为字符，存储到char数组中
		System.out.println(chars);
	}
}
