package com.codeshu.stream;

import com.codeshu.stream.brean.Employee;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author CodeShu
 * @date 2023/2/5 14:32
 */
public class GetStreamTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(GetStreamTest.class);

	@Test
	public void testAggregate() {
		List<Employee> employees = Employee.getEmployees();
		Stream<Employee> stream1 = employees.stream();
		Stream<Employee> stream2 = employees.parallelStream();
		stream1.forEach(employee -> LOGGER.debug(String.valueOf(employee)));
		stream2.forEach(employee -> LOGGER.debug(String.valueOf(employee)));
	}

	@Test
	public void testArray() {
		int[] ints = {1, 2, 3};
		IntStream intStream = Arrays.stream(ints);
		intStream.forEach(i -> LOGGER.debug(String.valueOf(i)));
	}
}
