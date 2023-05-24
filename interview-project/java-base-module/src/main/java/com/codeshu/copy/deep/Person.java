package com.codeshu.copy.deep;

import com.codeshu.copy.shallow.Address;
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
	public Person clone() {
		try {
			Person person = (Person) super.clone();
			person.setAddress(person.getAddress().clone()); //拷贝Person对象时，同时拷贝Address成员
			return person;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
