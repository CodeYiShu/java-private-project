package com.codeshu.test02.mapper;

import com.codeshu.test02.entity.Test02User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/5/6 17:15
 */
@Mapper
public interface Test02UserMapper {
	/**
	 * 查询所有用户
	 */
	List<Test02User> selectList();
}
