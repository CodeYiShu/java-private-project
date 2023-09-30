package com.codeshu.file;

import org.junit.Test;

import java.io.File;
import java.util.Objects;

/**
 * File 对象的常用方法
 * <p>
 * public String getAbsolutePath()：获取绝对路径
 * public String getPath()：获取路径
 * public String getName() ：获取名称
 * public String getParent()：获取上层文件目录路径。若无，返回null
 * public long length() ：获取文件长度（即字节数）。不能获取目录的长度。
 * public long lastModified()：获取最后一次的修改时间，毫秒值
 * public String[] list() ：获取指定目录下的所有文件或者文件目录的名称数组
 * public File[] listFiles() ：获取指定目录下的所有文件或者文件目录的File数组
 * public boolean renameTo(File dest)：把文件重命名为指定的文件路径
 *
 * @author CodeShu
 * @date 2023/9/25 15:34
 */
public class CommonMethodsTest1 {
	/**
	 * 文件不真实存在时调用以上方法
	 */
	@Test
	public void noExistFileTest() {
		//创建一个 File 对象（相对路径）
		File file1 = new File("noExistHello1.txt");
		//创建一个 File 对象（绝对路径）
		File file2 = new File("E:\\java\\program\\java-private-project" +
				"\\interview-project\\io-module" +
				"\\src\\main\\java\\com\\codeshu\\file\\noExistHello2.txt");

		System.out.println("file1.getAbsolutePath() = " + file1.getAbsolutePath());
		System.out.println("file1.getPath() = " + file1.getPath());
		System.out.println("file1.getName() = " + file1.getName());
		System.out.println("file1.getParent() = " + file1.getParent());
		System.out.println("file1.length() = " + file1.length());
		System.out.println("file1.lastModified() = " + file1.lastModified());
		System.out.println();
		System.out.println("file2.getAbsolutePath() = " + file2.getAbsolutePath());
		System.out.println("file2.getPath() = " + file2.getPath());
		System.out.println("file2.getName() = " + file2.getName());
		System.out.println("file2.getParent() = " + file2.getParent());
		System.out.println("file2.length() = " + file2.length());
		System.out.println("file2.lastModified() = " + file2.lastModified());
	}

	/**
	 * 文件不真实存在时调用以上方法
	 */
	@Test
	public void hasExistFileTest() {
		//创建一个 File 对象（相对路径）
		File file1 = new File("src\\main\\java\\com\\codeshu\\file\\Hello1.txt");
		//创建一个 File 对象（绝对路径）
		File file2 = new File("E:\\java\\program\\java-private-project" +
				"\\interview-project\\io-module" +
				"\\src\\main\\java\\com\\codeshu\\file\\Hello2.txt");

		System.out.println("file1.getAbsolutePath() = " + file1.getAbsolutePath());
		System.out.println("file1.getPath() = " + file1.getPath());
		System.out.println("file1.getName() = " + file1.getName());
		System.out.println("file1.getParent() = " + file1.getParent());
		System.out.println("file1.length() = " + file1.length());
		System.out.println("file1.lastModified() = " + file1.lastModified());
		System.out.println();
		System.out.println("file2.getAbsolutePath() = " + file2.getAbsolutePath());
		System.out.println("file2.getPath() = " + file2.getPath());
		System.out.println("file2.getName() = " + file2.getName());
		System.out.println("file2.getParent() = " + file2.getParent());
		System.out.println("file2.length() = " + file2.length());
		System.out.println("file2.lastModified() = " + file2.lastModified());
	}

	/**
	 * 获取目录下的所有文件名称
	 */
	@Test
	public void listFileNameTest() {
		//创建一个 File 对象（相对路径）
		File file = new File("src\\main\\java\\com\\codeshu\\file");
		String[] fileNames = file.list();
		if (Objects.nonNull(fileNames)) {
			for (String fileName : fileNames) {
				System.out.println(fileName);
			}
		}
	}

	/**
	 * 获取目录下的所有文件对象
	 */
	@Test
	public void listFileTest() {
		//创建一个 File 对象（相对路径）
		File file = new File("src\\main\\java\\com\\codeshu\\file");
		File[] files = file.listFiles();
		if (Objects.nonNull(files)) {
			for (File f : files) {
				System.out.println(f.getName());
			}
		}
	}

	/**
	 * 将文件重命名为指定的文件路径（剪切）
	 */
	@Test
	public void renameToTest() {
		//将 Hello3.txt 更名为 rename.txt，目录不变
		File sourceFile = new File("src\\main\\java\\com\\codeshu\\file\\Hello3.txt");
		File desFile = new File("src\\main\\java\\com\\codeshu\\file\\rename.txt");
		boolean renameTo = sourceFile.renameTo(desFile);
		System.out.println(renameTo);
	}
}
