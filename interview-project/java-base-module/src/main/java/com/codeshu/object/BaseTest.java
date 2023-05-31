package com.codeshu.object;

import org.junit.Test;

/**
 * @author CodeShu
 * @date 2023/5/24 16:56
 */
@SuppressWarnings("all")
public class BaseTest {
	@Test
	public void test1() {
		int a = 1;
		int b = 1;
		System.out.println(a == b);
	}

	@Test
	public void test2() {
		Person person1 = new Person("zhangsan");
		Person person2 = new Person("zhangsan");
		System.out.println(person1 == person2);
	}

	@Test
	public void test3() {
		Person person1 = new Person("zhangsan");
		Person person2 = new Person("zhangsan");
		System.out.println(person1.equals(person2));
	}

	@Test
	public void test4() {
		String str1 = "abc";
		String str2 = new String("abc");
		System.out.println(str1 == str2);
		System.out.println(str1.equals(str2));
	}

	@Test
	public void test5() {
		Person person1 = new Person("zhangsan");
		Person person2 = new Person("zhangsan");
		System.out.println(person1.hashCode() == person2.hashCode());
	}
}

class Person {
	String name;

	public Person(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;  //两个为真正的对象
		if (!(o instanceof Person)) return false; //类型不同

		Person person = (Person) o;

		return name.equals(person.name); //类型都是Person，且属性值相同，则认为相同
	}

	@Override
	public int hashCode() {
		return name.hashCode(); //根据属性来进行计算哈希值，而不是对象地址来计算
	}
}
