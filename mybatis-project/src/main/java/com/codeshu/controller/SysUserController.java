package com.codeshu.controller;

import com.codeshu.mapper.SysUserMapper;
import com.codeshu.response.GetUserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author CodeShu
 * @date 2023/7/22 16:54
 */
@RestController
public class SysUserController {
	@Resource
	private SysUserMapper userMapper;

	@GetMapping("getUserInfo/{id}")
	public GetUserInfoResponse getUserInfo(@PathVariable("id") Long id) {
		return userMapper.getUserInfo(id);
	}
}
