package com.codeshu.copy.deep;

import com.codeshu.copy.shallow.Address;
import com.codeshu.copy.shallow.Person;

/**
 * @author CodeShu
 * @date 2023/5/23 9:47
 */
public class Test {
	public static void main(String[] args) {
		com.codeshu.copy.shallow.Person person1 = new com.codeshu.copy.shallow.Person(new Address("武汉"));
		//浅拷贝一份Person对象，赋值给person1Copy引用
		Person person1Copy = person1.clone();
		System.out.println(person1 == person1Copy); //false
		System.out.println(person1.getAddress() == person1Copy.getAddress()); //true
	}
}
