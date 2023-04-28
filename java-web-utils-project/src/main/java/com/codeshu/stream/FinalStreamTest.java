package com.codeshu.stream;

import com.codeshu.stream.brean.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author CodeShu
 * @date 2023/2/5 16:17
 */
public class FinalStreamTest {
	@Test
	public void testCollectToList() {
		List<Employee> employees = Employee.getEmployees();

		//筛选出Employee对象的ID
		Stream<Integer> mapStream = employees.stream().map(employee -> {
			if (employee != null && employee.getId() != null) {
				return employee.getId();
			}
			return null;
		});
		//过滤掉为NULL的ID元素
		Stream<Integer> filterStream = mapStream.filter(id -> id != null);
		//去重
		Stream<Integer> distinctStream = filterStream.distinct();

		//将ID收集到List集合
		List<Integer> ids = distinctStream.collect(Collectors.toList());
		System.out.println(ids);

		//Lambda
		List<Integer> lambdaIds = employees.stream().map(employee -> {
			if (employee != null && employee.getId() != null) {
				return employee.getId();
			}
			return null;
		}).filter(id -> id != null).distinct().collect(Collectors.toList());
		System.out.println(lambdaIds);
	}

	@Test
	public void testCollectToSet() {
		List<Employee> employees = Employee.getEmployees();

		//筛选出Employee对象的ID
		Stream<Integer> mapStream = employees.stream().map(employee -> {
			if (employee != null && employee.getId() != null) {
				return employee.getId();
			}
			return null;
		});
		//过滤掉为NULL的ID元素
		Stream<Integer> filterStream = mapStream.filter(id -> id != null);
		//Set会自动去重

		//将ID收集到Set集合
		Set<Integer> ids = filterStream.collect(Collectors.toSet());
		System.out.println(ids);

		//Lambda
		Set<Integer> lambdaIds = employees.stream().map(employee -> {
			if (employee != null && employee.getId() != null) {
				return employee.getId();
			}
			return null;
		}).filter(id -> id != null).collect(Collectors.toSet());
		System.out.println(lambdaIds);
	}
}
