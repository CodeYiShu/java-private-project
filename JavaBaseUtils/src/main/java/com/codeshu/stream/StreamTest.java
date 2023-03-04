package com.codeshu.stream;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author CodeShu
 * @date 2023/3/4 14:51
 */
public class StreamTest {
	List<Person> personList = new ArrayList<>();

	@Before
	public void before() {
		Person person1 = new Person(1L, "张三");
		Person person2 = new Person(2L, "李四");
		Person person3 = new Person(3L, null);
		Person person4 = new Person(3L, null);
		Person person5 = null;
		personList.addAll(Arrays.asList(person1, person2, person3, person4, person5));
	}

	@Test
	public void testCollectionForNullElement() {
		List<Person> peopleCollection = personList.stream()
				.filter(Objects::nonNull)
				.filter(person -> "张三".equals(person.getName()) || "李四".equals(person.getName()))
				.collect(Collectors.toList());
		System.out.println(peopleCollection);
	}

	@Test
	public void testCollectionForNullAttribute() {
		//当id为3的person对象，没有姓名时，设置此标志为true
		boolean noName = false;

		List<String> nameList = personList.stream()
				.filter(Objects::nonNull)
				.filter(person -> Long.valueOf(3L).equals(person.getId()))
				.map(Person::getName)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());

		//当集合为空时，表示person对象没有名字
		if (CollectionUtils.isEmpty(nameList)) {
			noName = true;
		}
		System.out.println(noName);
	}

	@Test
	public void testDistinct() {
		List<Long> personIds = personList.stream()
				.filter(Objects::nonNull)
				.map(Person::getId)
				.filter(Objects::nonNull)
				.distinct()
				.collect(Collectors.toList());
		System.out.println(personIds);
	}

	@Test
	public void testCollectionToSingle() {
		Person person1 = new Person(1L, "张三");
		Person person2 = new Person(2L, "李四");
		Person person3 = new Person(3L, null);
		List<Person> personList = new ArrayList<>(Arrays.asList(person1, person2, person3));

		//筛选出ID为1的对象
		List<Person> persons = personList.stream()
				.filter(Objects::nonNull)
				.filter(person -> Long.valueOf(1L).equals(person.getId()))
				.collect(Collectors.toList());

		if (!CollectionUtils.isEmpty(persons)) {
			Person person = persons.get(0);
			System.out.println(person);
		}
	}

	@Test
	public void testFindFirst() {
		List<Integer> list = Arrays.asList(null, 1);
		//获取集合第一个元素，放入Optional容器
		Optional<Integer> firstElementOptional = list.stream().findFirst();
		//从Optional容器中获取出元素，当元素为null时则返回null，而不是抛异常
		Integer firstElement = firstElementOptional.orElse(null);
		System.out.println(firstElement);
	}

	@Test
	public void testFindFirst2() {
		Person person1 = new Person(1L, "张三");
		Person person2 = new Person(2L, "李四");
		Person person3 = new Person(3L, null);
		List<Person> personList = new ArrayList<>(Arrays.asList(person1, person2, person3));

		String name = personList.stream()
				.filter(Objects::nonNull)
				.filter(person -> Long.valueOf(3L).equals(person.getId()))
				.map(Person::getName)
				.filter(Objects::nonNull)
				.findFirst().orElse(null);
		System.out.println(name);
	}

}
