package com.codeshu;

import com.codeshu.entity.User;
import com.codeshu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Service 很多操作方法和 Mapper 差不多
 * 这里只演示几个不同的
 *
 * @author CodeShu
 * @date 2023/10/30 14:41
 */
@SpringBootTest
@Slf4j
public class ServiceTest {
	@Autowired
	private UserService userService;

	/**
	 * 批量插入（自定义实现）
	 */
	@Test
	public void batchInsertTest() {
		long startTime = System.currentTimeMillis();
		List<User> userList = new ArrayList<>();
		for (int i = 0; i < 100000; i++) {
			userList.add(new User(null, "" + i, 1, ""));
		}
		userService.insertBatch(userList);
		long endTime = System.currentTimeMillis();
		log.error("耗时 秒：{}，毫秒：{}", (endTime - startTime) / 1000, endTime - startTime);
	}

	/**
	 * 批量更新（用 MP 的）
	 */
	@Test
	public void batchUpdateBatchById() {
		long startTime = System.currentTimeMillis();
		List<User> userList = new ArrayList<>();
		for (long i = 1718914723287744514L; i < 1718914723287744523L; i++) {
			userList.add(new User(i, "" + i, 2, ""));
		}
		userService.updateBatchById(userList);
		long endTime = System.currentTimeMillis();
		log.error("耗时 秒：{}，毫秒：{}", (endTime - startTime) / 1000, endTime - startTime);
	}

	/**
	 * 数据库存在时更新，不存在则插入
	 */
	@Test
	public void testSaveOrUpdate() {
		User user1 = new User(null, "zhangsan", 1, "");
		userService.saveOrUpdate(user1);

		User user2 = new User(1718918118329319425L, "lisi", 1, "");
		userService.saveOrUpdate(user2);
	}
}
