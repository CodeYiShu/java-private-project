package com.codeshu.controller;

import com.codeshu.common.Result;
import com.codeshu.entity.SysUserEntity;
import com.codeshu.mapper.SysUserMapper;
import com.codeshu.response.GetUserInfoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class SysUserController {
	@Resource
	private SysUserMapper userMapper;

	@GetMapping("getUserInfo/{id}")
	public GetUserInfoResponse getUserInfo(@PathVariable("id") Long id) {
		return userMapper.getUserInfo(id);
	}

	@PostMapping("insertBatch")
	public Result<String> insertBatch(@RequestBody List<SysUserEntity> userEntityList) {
		userMapper.insertBatch(userEntityList);
		return new Result<String>().setMsg("保存成功");
	}
}
