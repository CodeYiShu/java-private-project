package com.codeshu.controller;

import com.codeshu.common.Result;
import com.codeshu.entity.UserEntity;
import com.codeshu.mapper.UserMapper;
import com.codeshu.response.SelectBatchResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CodeShu
 * @date 2023/7/22 16:54
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserMapper userMapper;

	@GetMapping("selectBatch")
	@ApiOperation("批量查询")
	public Result<List<SelectBatchResponse>> selectBatch(@RequestParam("ids") List<Long> ids) {
		//IN 语句如果传入的为空集合会报错，要提前判断
		if (CollectionUtils.isEmpty(ids)) {
			return new Result<>();
		}
		List<SelectBatchResponse> responseList = userMapper.selectBatch(ids);
		return new Result<List<SelectBatchResponse>>().ok(responseList);
	}

	@PostMapping("insertBatch")
	@ApiOperation("批量插入")
	public Result<String> insertBatch(@RequestBody List<UserEntity> userEntityList) {
		userMapper.insertBatch(userEntityList);
		return new Result<String>().setMsg("保存成功");
	}

	@PutMapping("updateBatch")
	@ApiOperation("批量更新")
	public Result<String> updateBatch(@RequestBody List<UserEntity> userEntityList) {
		//批量更新必须在配置文件的数据源 url 加上 allowMultiQueries=true
		userMapper.updateBatch(userEntityList);
		return new Result<String>().setMsg("更新成功");
	}

	@DeleteMapping("deleteBatch")
	@ApiOperation("批量删除")
	public Result<String> deleteBatch(@RequestBody List<Long> ids) {
		//IN 语句如果传入的为空集合会报错，要提前判断
		if (CollectionUtils.isEmpty(ids)) {
			return new Result<>();
		}
		userMapper.deleteBatch(ids);
		return new Result<String>().setMsg("删除成功");
	}
}
