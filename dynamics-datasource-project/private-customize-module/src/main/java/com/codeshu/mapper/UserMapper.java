package com.codeshu.mapper;

import com.codeshu.annotation.DynamicsDataSourceAnnotation;
import com.codeshu.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/5/8 10:11
 */
@Mapper
public interface UserMapper {
	List<User> test01();

	@DynamicsDataSourceAnnotation("test02")
	List<User> test02();

	@DynamicsDataSourceAnnotation("test03")
	List<User> test03();
}
