package com.codeshu.jvm;

/**
 * @author CodeShu
 * @date 2023/5/29 14:27
 */
@SuppressWarnings("all")
public class JVMTest {
	public static void main(String[] args){
		byte[] allocation1, allocation2,allocation3,allocation4,allocation5;
		allocation1 = new byte[30900*1024];
		allocation2 = new byte[30900*1024];
		allocation3 = new byte[30900*1024];
		allocation4 = new byte[30900*1024];
		allocation5 = new byte[30900*1024];
	}
}
