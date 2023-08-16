package com.codeshu.controller;

import com.codeshu.common.Result;
import com.codeshu.entity.UserEntity;
import com.codeshu.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CodeShu
 * @date 2023/7/22 16:54
 */
@RestController
@RequestMapping("sys/user")
public class UserController {
	@Resource
	private UserMapper userMapper;

	@PostMapping("insertBatch")
	public Result<String> insertBatch(@RequestBody List<UserEntity> userEntityList) {
		userMapper.insertBatch(userEntityList);
		return new Result<String>().setMsg("保存成功");
	}
}
