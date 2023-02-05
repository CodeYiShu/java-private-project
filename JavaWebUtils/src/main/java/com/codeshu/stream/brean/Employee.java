package com.codeshu.stream.brean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CodeShu
 * @date 2023/2/5 14:31
 */
@Data
@AllArgsConstructor
public class Employee {
	private Integer id;
	private String name;
	private Integer age;
	private Double salary;

	public static List<Employee> getEmployees() {
		List<Employee> list = new ArrayList<>();
		//注意空指针异常
		list.add(new Employee(1, "马化腾", null, 6000.38));
		list.add(new Employee(2, "马云", 12, 9876.12));
		list.add(new Employee(3, "刘强东", 34, 3000.82));
		list.add(new Employee(4, "雷军", 26, 7000.38));
		list.add(new Employee(5, "李彦宏", 13, 6000.38));
		list.add(new Employee(6, "比尔盖茨", 11, 9000.38));
		list.add(new Employee(7, "任正非", 10, 10000.38));
		list.add(new Employee(8, "扎克伯格", 9, 20000.38));
		//注意空指针异常
		list.add(null);
		return list;
	}
}
