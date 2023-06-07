package com.codeshu.jvm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CodeShu
 * @date 2023/6/1 9:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	private String name;

	public void sayName(){
		System.out.println("名字为：" + name);
	}
}
