package com.codeshu;

import com.codeshu.master.entity.User;
import com.codeshu.master.mapper.UserMapper;
import com.codeshu.test02.entity.Test02User;
import com.codeshu.test02.mapper.Test02UserMapper;
import com.codeshu.test03.entity.Test03User;
import com.codeshu.test03.mapper.Test03UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/5/6 17:32
 */
@SpringBootTest
public class ServiceTest {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private Test02UserMapper test02UserMapper;

	@Autowired
	private Test03UserMapper test03UserMapper;


	@Test
	public void insertTest(){
		List<User> list = userMapper.selectList();
		list.forEach(System.out::println);

		System.out.println("============");
		List<Test02User> test02UserList = test02UserMapper.selectList();
		test02UserList.forEach(System.out::println);

		System.out.println("============");
		List<Test03User> test03UserList = test03UserMapper.selectList();
		test03UserList.forEach(System.out::println);
	}

}
