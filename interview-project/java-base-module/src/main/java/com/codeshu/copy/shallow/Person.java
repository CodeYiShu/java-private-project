package com.codeshu.copy.shallow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CodeShu
 * @date 2023/5/23 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Cloneable{
	private Address address;  //引用类型的成员

	@Override
	public Person clone() {  //重写clone()
		try {
			Person person = (Person) super.clone(); //调用Object类的clone()
			return person;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
