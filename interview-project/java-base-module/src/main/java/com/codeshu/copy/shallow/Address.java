package com.codeshu.copy.shallow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CodeShu
 * @date 2023/5/23 9:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Cloneable{
	private String name;

	@Override
	public Address clone() { //重写clone()
		try {
			return (Address) super.clone(); //调用Object类的clone()
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
