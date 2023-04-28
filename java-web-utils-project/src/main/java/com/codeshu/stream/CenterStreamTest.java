package com.codeshu.stream;

import com.codeshu.stream.brean.Employee;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeshu.stream.brean.Employee.getEmployees;

/**
 * @author CodeShu
 * @date 2023/2/5 14:53
 */
public class CenterStreamTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(CenterStreamTest.class);

	@Test
	public void testCallChaining() {
		List<Integer> integerList = Arrays.asList(1, 2, 3);

		Stream<Integer> stream1 = integerList.stream();
		Stream<Integer> stream2 = stream1.filter(i -> i < 3);
		Stream<Integer> stream3 = stream2.limit(1);
		stream3.forEach(i -> LOGGER.debug(String.valueOf(i)));

		//以上等同于链式调用
		integerList.stream().filter(i -> i < 3).limit(1).forEach(i -> LOGGER.debug(String.valueOf(i)));
	}

	@Test
	public void testOnlyUseOnce() {
		List<Integer> integerList = Arrays.asList(1, 2, 3);

		Stream<Integer> stream1 = integerList.stream();
		Stream<Integer> stream2 = stream1.filter(i -> i < 3);
		//stream1流对象已经操作过，还继续去调用stream1流对象进行中间操作或终止操作，就会报错
		stream1.forEach(i -> LOGGER.debug(String.valueOf(i)));
	}

	@Test
	public void testNotInfluenceSource() {
		//源对象
		List<Integer> integerList = Arrays.asList(1, 2, 3);

		//使用Stream流对源对象进行操作
		integerList.stream().filter(i -> i < 3).forEach(i -> LOGGER.debug(String.valueOf(i)));

		//输出源对象，不影响源对象本身
		LOGGER.debug(String.valueOf(integerList));
	}


	/**
	 * 要调用中间操作或终止操作都需要一个Stream对象，第一个Stream对象就是通过容器等方式去得到，往后流水线的对象则由各个中间操作返回。
	 * <p>
	 * 通过Stream可以对容器内的数据元素进行筛选和切片，常用的有四个API，中间操作的返回值都是一个新的Stream对象：
	 * 1.Stream<T> filter(Predicate p)：接收Predicate函数式接口实现对象， 从流中排除某些元素
	 * 2.Stream<T> distinct()：筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
	 * 3.Stream<T> limit(long maxSize)：截断流，使其元素不超过给定数量
	 * 4.Stream<T> skip(long n)：跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回个空流。与 limit(n) 互补
	 * <p>
	 * 需要注意：Stream的任何操作，都不会影响源对象（容器本身）。
	 */
	@Test
	public void testCenter() {
	}

	/**
	 * Stream<T> filter(Predicate p)：接收Predicate函数式接口实现对象， 从流中排除某些元素
	 * Predicate函数式接口只有一个boolean test(T t)方法，结果为true的元素留下，其余排除。
	 */
	@Test
	public void testFilter() {
		List<Employee> employees = getEmployees();

		//过滤出List集合中元素年龄大于30的元素
		Stream<Employee> stream1 = employees.stream();
		Stream<Employee> stream2 = stream1.filter(new Predicate<Employee>() {
			@Override
			public boolean test(Employee employee) {
				//为true的元素留在容器
				return employee != null && employee.getAge() != null && employee.getAge() > 30;
			}
		});
		stream2.forEach(employee -> LOGGER.debug(String.valueOf(employee)));

		//链式调用
		employees.stream().filter(employee -> employee != null && employee.getAge() != null && employee.getAge() > 30)
				.forEach(employee -> LOGGER.debug(String.valueOf(employee)));
	}

	/**
	 * Stream<T> distinct()：去重，通过流所生成元素的hashCode()和equals()去除重复元素，所以使用此中间操作的数据必须实现hashCode()和equals()
	 */
	@Test
	public void testDistinct() {
		List<Employee> employees = getEmployees();
		//重复的数据
		employees.add(new Employee(10, "zhangsan", 12, 1000000d));
		employees.add(new Employee(10, "zhangsan", 12, 1000000d));

		employees.stream().distinct().forEach(employee -> LOGGER.debug(String.valueOf(employee)));
	}

	@Test
	public void testMap1(){
		List<Employee> employees = getEmployees();
		Stream<Integer> idStream = employees.stream().map(new Function<Employee, Integer>() {
			@Override
			public Integer apply(Employee employee) {
				//跳过对象为NULL的元素
				if (employee != null){
					return employee.getId();
				}
				return null;
			}
		});
		//过滤id属性为NULL的元素
		Stream<Integer> filterStream = idStream.filter(id -> id != null);
		filterStream.forEach(id -> System.out.println(id));
	}

	@Test
	public void testMap2(){
		List<Employee> employees = getEmployees();
		Stream<Integer> idStream = employees.stream().map(employee -> {
			if (employee != null && employee.getId() != null){
				return employee.getId();
			}
			return null;
		});
		Stream<Integer> filterStream = idStream.filter(id -> id != null);
		Stream<Integer> sorted = filterStream.sorted((o1, o2) -> o2 - o1);
		sorted.forEach(id -> System.out.println(id));
	}

	@Test
	public void test1(){
		List<Employee> employees = getEmployees();

		//用两个filter进行过滤，先对存储Employee对象的集合过滤掉为NULL的元素
		//再对此集合进行map筛选出对象的ID属性，最后对存储ID属性的集合进行过滤掉为NULL的元素

		List<Integer> collect1 = employees.stream().filter(new Predicate<Employee>() {
			@Override
			public boolean test(Employee employee) {
				return Objects.nonNull(employee);
			}
		}).map(new Function<Employee, Integer>() {
			@Override
			public Integer apply(Employee employee) {
				return employee.getId();
			}
		}).filter(new Predicate<Integer>() {
			@Override
			public boolean test(Integer id) {
				return Objects.nonNull(id);
			}
		}).collect(Collectors.toList());

		List<Integer> collect2 = employees.stream().filter(employee -> Objects.nonNull(employee)).
				map(employee -> employee.getId()).filter(id -> Objects.nonNull(id)).collect(Collectors.toList());

		List<Integer> collect3 = employees.stream().filter(Objects::nonNull).
				map(Employee::getId).filter(Objects::nonNull).collect(Collectors.toList());

		List<Integer> collect4 = employees.stream().filter(employee -> Objects.nonNull(employee) && Objects.nonNull(employee.getId()))
				.map(employee -> employee.getId()).collect(Collectors.toList());

		System.out.println(collect1);
		System.out.println(collect2);
		System.out.println(collect3);
	}

}
