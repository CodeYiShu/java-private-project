package com.codeshu;

import com.codeshu.entity.User;
import com.codeshu.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PrivateCustomizeModuleApplicationTests {
	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {
	}

	@Test
	public void selectTest() {
		List<User> userList1 = userMapper.test01();
		List<User> userList2 = userMapper.test02();
		List<User> userList3 = userMapper.test03();

		System.out.println(userList1);
		System.out.println(userList2);
		System.out.println(userList3);
	}

}
