package com.codeshu.buffer;

import java.nio.CharBuffer;
import java.util.Arrays;

/**
 * @author CodeShu
 * @date 2023/8/30 17:08
 */
public class BufferBatch {
	public static void main(String[] args) {
		//创建一个CharBuffer，容量为16；初始状态capacity为16，limit也为16，position为0
		CharBuffer charBuffer = CharBuffer.allocate(16);
		//将字符串写入到缓冲区中；此时capacity为16，limit也为16，position为16
		charBuffer.put("hello动力节点的蛙课网很牛B");
		//写入数据后，切换为读模式；也就是设置position为0，limit为capacity16
		charBuffer.flip();
		System.out.println(charBuffer);

		//定义一个容量为12的字符数组
		char[] dst = new char[12];
		/*
           将Buffer中的数据读取到字符数组当中
           返回值是一个Buffer，里面存放剩余未读取的数据
           注意：批量传输时大小是固定的，如果没有指定传输的大小，意味着会将数组填满
         */
		CharBuffer remainingBuffer = charBuffer.get(dst);
		//打印字符数组
		System.out.println(Arrays.toString(dst));
		//查看返回的缓冲区，剩下的就是未读取的数据
		System.out.println(remainingBuffer);
		System.out.println(remainingBuffer == charBuffer);

		//清空缓冲区
		charBuffer.clear();
		//判断当前position位置后面是否还有可以处理的数据，也就是说判断position和limit之间是否还有数据可以处理
		while (charBuffer.hasRemaining()) {
			//判断字符数组长度和Buffer剩余数据量的大小
			//如果剩余数据量比字符数组长度大，那么就可以使用字符数组长度
			//如果剩余数据量比字符数组长度小，那么就使用剩余数据量，避免缓冲区溢出
			int len = Math.min(dst.length, charBuffer.remaining());
			charBuffer.get(dst, 0, len);
			System.out.print(Arrays.toString(dst));
			System.out.println();
		}
		//创建一个字符数组
		char[] contents = {'a', 'b', 'c', 'd'};
		//设置当前位置position为14
		charBuffer.position(14);
		//将字符数组从0索引开始的字符，依次写入到缓冲区中，缓冲区剩余多少个位置可以写就写入多少个字符
		charBuffer.put(contents, 0, charBuffer.remaining());
		//切换为读模式
		charBuffer.flip();
		//查看缓冲区数据
		System.out.println(charBuffer);
	}
}
