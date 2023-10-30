package com.codeshu;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.codeshu.dao.UserDao;
import com.codeshu.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author CodeShu
 * @date 2023/10/30 14:00
 */
@SpringBootTest
public class MapperTest {
	@Resource
	private UserDao userDao;

	/**
	 * 插入（不支持批量）
	 * <p>
	 * 细节：插入后实体类会自动赋值上 ID
	 */
	@Test
	public void testInsert() {
		User user1 = new User(null, "张三", 12, "张三描述");
		User user2 = new User(null, "李四", 13, "李四描述");
		userDao.insert(user1);
		userDao.insert(user2);
		// User(id=1718871293790613506, name=张三, age=12, description=张三描述)
		// User(id=1718871297989111809, name=李四, age=13, description=李四描述)
		System.out.println(user1);
		System.out.println(user2);
	}

	/**
	 * 更新（不支持批量）
	 * <p>
	 * 可根据条件更新，也可根据 ID 更新
	 */
	@Test
	public void testUpdate() {
		//根据 ID 更新，不会更新为 NULL 的字段：UPDATE mybatis_plus_user SET age=11 WHERE id=1718872607253635073;
		User user1 = new User();
		user1.setId(1718872607253635073L);
		user1.setAge(11);
		userDao.updateById(user1);

		//条件更新（结合实体对象），不会更新为 NULL 的字段：UPDATE mybatis_plus_user SET name='赵六' WHERE (name = '王五');
		User user2 = new User();
		user2.setName("赵六");
		LambdaUpdateWrapper<User> updateWrapper1 = new LambdaUpdateWrapper<>();
		updateWrapper1.eq(User::getName, "王五");
		userDao.update(user2, updateWrapper1);

		//条件更新（不结合实体对象），不会更新为 NULL 的字段：UPDATE mybatis_plus_user SET name='王五' WHERE (name = '赵六');
		LambdaUpdateWrapper<User> updateWrapper2 = new LambdaUpdateWrapper<>();
		updateWrapper2.set(User::getName, "王五").eq(User::getName, "赵六");
		userDao.update(null, updateWrapper2);

		/*
			更新字段为 NULL：
				(1)使用 LambdaUpdateWrapper set 字段为 NULL
				(2)在实体类的字段使用指定策略 @TableField(strategy = FieldStrategy.IGNORED)
			发出的 SQL：UPDATE mybatis_plus_user SET name=null WHERE (name = '王五');
		 */
		LambdaUpdateWrapper<User> updateWrapper3 = new LambdaUpdateWrapper<>();
		updateWrapper3.set(User::getName, null).eq(User::getName, "王五");
		userDao.update(null, updateWrapper3);
	}

	/**
	 * 删除（支持批量）
	 */
	@Test
	public void testDelete() {
		//根据 ID 删除：DELETE FROM mybatis_plus_user WHERE id=1;
		userDao.deleteById(1);

		//批量根据 ID 删除：DELETE FROM mybatis_plus_user WHERE id IN ( 2 , 3 );
		//注意：入参的 ID 集合不可为空集合！否则 IN 语句报错
		userDao.deleteBatchIds(Arrays.asList(2, 3));

		//根据条件删除：DELETE FROM mybatis_plus_user WHERE (name = 'ddd');
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(User::getName, "ddd");
		userDao.delete(queryWrapper);
	}

	/**
	 * 查询所有
	 */
	@Test
	public void testSelectAll() {
		List<User> users = userDao.selectList(null);
		System.out.println(users);
	}

	/**
	 * 条件查询
	 */
	@Test
	public void testSelectLambdaQueryWrapper() {
		//单个条件（等值）：SELECT *  FROM mybatis_plus_user WHERE (name = '张三');
		LambdaQueryWrapper<User> queryWrapper1 = new LambdaQueryWrapper<>();
		queryWrapper1.eq(User::getName, "张三");
		List<User> user1 = userDao.selectList(queryWrapper1);
		System.out.println(user1);

		//多个条件（AND）SELECT *  FROM mybatis_plus_user WHERE (name = '张三' AND name <> '李四');
		LambdaQueryWrapper<User> queryWrapper2 = new LambdaQueryWrapper<>();
		queryWrapper2.eq(User::getName, "张三").ne(User::getName, "李四");
		List<User> user2 = userDao.selectList(queryWrapper2);
		System.out.println(user2);

		//多个条件（OR）SELECT *  FROM mybatis_plus_user WHERE (name = '张三' OR name = '李四');
		LambdaQueryWrapper<User> queryWrapper3 = new LambdaQueryWrapper<>();
		queryWrapper3.eq(User::getName, "张三").or().eq(User::getName, "李四");
		List<User> user3 = userDao.selectList(queryWrapper3);
		System.out.println(user3);
	}

	/**
	 * 查询只返回指定字段
	 */
	@Test
	public void testSelectOnlyNeedColumn() {
		//SELECT id,name FROM mybatis_plus_user WHERE (name = '张三');
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(User::getName, "张三").select(User::getId, User::getName);
		List<User> user = userDao.selectList(queryWrapper);
		System.out.println(user);
	}

	/**
	 * 查询时指定 SQL 语句
	 */
	@Test
	public void testSelectAppointSQL() {
		//SQL 末尾加自定义 SQL：SELECT * FROM mybatis_plus_user WHERE (name = '张三') LIMIT 1;
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(User::getName, "张三").last("limit 1");
		List<User> users = userDao.selectList(queryWrapper);
		System.out.println(users);
	}
}
