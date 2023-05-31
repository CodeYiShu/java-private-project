package com.codeshu.generic;

/**
 * @author CodeShu
 * @date 2023/5/25 11:27
 */
public class GenericTest {

	Generic<Integer> genericInteger = new Generic<>(123456);
}

class Generic<T>{

	private T key;

	public Generic(T key) {
		this.key = key;
	}

	public T getKey(){
		return key;
	}
}
