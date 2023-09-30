package com.codeshu.file;

import org.junit.Test;

import java.io.File;

/**
 * 使用File类无法对文件内容进行修改、删除等，但是可以有判断功能。
 * <p>
 * 判断的相关API：
 * public boolean isDirectory()：判断是否是文件目录
 * public boolean isFile()：判断是否是文件
 * public boolean exists()：判断是否存在
 * public boolean canRead()：判断是否可读
 * public boolean canWrite()：判断是否可写
 * public boolean isHidden()：判断是否隐藏
 *
 * @author CodeShu
 * @date 2023/9/25 15:48
 */
public class CommonMethodsTest2 {

	@Test
	public void testMethod() {
		//file1 不存在，file2 存在
		File file1 = new File("src\\main\\java\\com\\codeshu\\file\\noExistHello1.txt");
		System.out.println("判断是否存在" + file1.exists());
		System.out.println("判断是否是文件目录:" + file1.isDirectory());
		System.out.println("判断是否是文件:" + file1.isFile());
		System.out.println("判断是否可读:" + file1.canRead());
		System.out.println("判断是否可写:" + file1.canWrite());
		System.out.println("判断是否隐藏:" + file1.isHidden());
		System.out.println();
		File file2 = new File("src\\main\\java\\com\\codeshu\\file\\Hello1.txt");
		System.out.println("判断是否存在" + file2.exists());
		System.out.println("判断是否是文件目录:" + file2.isDirectory());
		System.out.println("判断是否是文件:" + file2.isFile());
		System.out.println("判断是否可读:" + file2.canRead());
		System.out.println("判断是否可写:" + file2.canWrite());
		System.out.println("判断是否隐藏:" + file2.isHidden());
	}
}
