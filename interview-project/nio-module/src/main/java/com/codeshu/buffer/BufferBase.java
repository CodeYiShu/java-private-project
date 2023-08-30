package com.codeshu.buffer;

import java.nio.CharBuffer;

/**
 * @author CodeShu
 * @date 2023/8/30 16:52
 */
public class BufferBase {
	/**
	 *  put()：从缓冲区中写入数据到当前位置position所指位置
	 *  get()：从缓冲区中从当前位置position读取数据
	 *  compact()：当缓冲区中还有没有读取的数据，调用此方法之后
	 * 		将所有未读取的数据复制到Buffer缓冲区的起始位置（position和limit之间的数据）
	 * 		将当前位置position设置到最后一个未读元素的后面
	 * 		将limit设置为capacity
	 *  capacity()：可以返回缓冲区的大小
	 *  limit()：返回limit上限的位置
	 *  mark()：设置mark标志缓冲区的位置指定为当前位置position所在位置
	 *  reset()：将当前位置position设置为mark标志的位置
	 *  hasRemaining()：判断当前position位置后面是否还有可以处理的数据，也就是说判断position和limit之间是否还有数据可以处理
	 *  remaing()：返回当前位置position和上限limit之间的数据量，也就是limit-position
	 *  flip()：将缓冲区从写模式切换为读模式
	 * 		上限limit设置为当前位置position
	 * 		将position设置为0
	 *  rewind()：将当前位置position设置为0，取消mark标志
	 *  clear()：清空缓冲区，仅仅是修改position为0，设置limit为capacity，缓冲区中的数据没有被清除
	 */

	public static void main(String[] args) {
		//1、创建一个CharBuffer缓冲区对象
		CharBuffer charBuffer = CharBuffer.allocate(12);

		//2、打印Capacity（最大容量）、limit（上限位置）和position（当前位置）
		System.out.println("charBuffer.capacity() = " + charBuffer.capacity());
		System.out.println("charBuffer.limit() = " + charBuffer.limit());
		System.out.println("charBuffer.position() = " + charBuffer.position());

		//3、向缓冲区中存储数据
		charBuffer.put('我');
		charBuffer.put('有');
		charBuffer.put('矿');
		charBuffer.put('你');
		charBuffer.put('爱');
		charBuffer.put('我');
		charBuffer.put('吗');

		//4、打印Capacity（最大容量）、limit（上限位置）和position（当前位置）
		System.out.println("----------------------------------");
		System.out.println("charBuffer.capacity() = " + charBuffer.capacity());
		System.out.println("charBuffer.limit() = " + charBuffer.limit());
		System.out.println("charBuffer.position() = " + charBuffer.position());

		//5、调用flip()，切换缓冲区为读取模式
		charBuffer.flip();

		//6、打印Capacity（最大容量）、limit（上限位置）和position（当前位置）
		System.out.println("----------------------------------");
		System.out.println("charBuffer.capacity() = " + charBuffer.capacity());
		System.out.println("charBuffer.limit() = " + charBuffer.limit());
		System.out.println("charBuffer.position() = " + charBuffer.position());

		//7、读取position所指数据
		System.out.println(charBuffer.get());

		//8、打印Capacity（最大容量）、limit（上限位置）和position（当前位置）
		System.out.println("----------------------------------");
		System.out.println("charBuffer.capacity() = " + charBuffer.capacity());
		System.out.println("charBuffer.limit() = " + charBuffer.limit());
		System.out.println("charBuffer.position() = " + charBuffer.position());

		//9、存入数据X，到当前位置position
		charBuffer.put("X");
		//10、打印Capacity（最大容量）、limit（上限位置）和position（当前位置）
		System.out.println("----------------------------------");
		System.out.println("charBuffer.capacity() = " + charBuffer.capacity());
		System.out.println("charBuffer.limit() = " + charBuffer.limit());
		System.out.println("charBuffer.position() = " + charBuffer.position());

		//11、在当前位置position设置一个标志
		charBuffer.mark();

		//12、再去获取当前位置position位置的数据，接着position往后移动
		System.out.println(charBuffer.get());

		//13、打印Capacity（最大容量）、limit（上限位置）和position（当前位置）
		System.out.println("----------------------------------");
		System.out.println("charBuffer.capacity() = " + charBuffer.capacity());
		System.out.println("charBuffer.limit() = " + charBuffer.limit());
		System.out.println("charBuffer.position() = " + charBuffer.position());

		//14、将当前位置position设置为mark标志的位置
		charBuffer.reset();

		//15、打印Capacity（最大容量）、limit（上限位置）和position（当前位置）
		System.out.println("----------------------------------");
		System.out.println("charBuffer.capacity() = " + charBuffer.capacity());
		System.out.println("charBuffer.limit() = " + charBuffer.limit());
		System.out.println("charBuffer.position() = " + charBuffer.position());

		//16、调用compact()
		charBuffer.compact();

		//17、打印Capacity（最大容量）、limit（上限位置）和position（当前位置）
		System.out.println("----------------------------------");
		System.out.println("charBuffer.capacity() = " + charBuffer.capacity());
		System.out.println("charBuffer.limit() = " + charBuffer.limit());
		System.out.println("charBuffer.position() = " + charBuffer.position());

		//18、调用clear()去清空
		charBuffer.clear();

		//19、打印Capacity（最大容量）、limit（上限位置）和position（当前位置）
		System.out.println("----------------------------------");
		System.out.println("charBuffer.capacity() = " + charBuffer.capacity());
		System.out.println("charBuffer.limit() = " + charBuffer.limit());
		System.out.println("charBuffer.position() = " + charBuffer.position());

		//20、查看当前位置position和上限limit之间的数据量
		System.out.println("charBuffer.remaining() = " + charBuffer.remaining());
		//21、循环遍历缓冲区的数据
		while (charBuffer.hasRemaining()){
			System.out.print(charBuffer.get());
		}
	}
}
