package com.codeshu.service.impl;

import com.codeshu.entity.DeptEntity;
import com.codeshu.entity.UserEntity;
import com.codeshu.mapper.UserMapper;
import com.codeshu.response.UserWithDeptNameResponse;
import com.codeshu.service.DeptService;
import com.codeshu.service.UserService;
import com.codeshu.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author CodeShu
 * @date 2023/1/7 17:43
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private DeptService deptService;

	@Override
	public List<UserEntity> getAll() {
		return userMapper.selectAll();
	}

	@Override
	public List<UserWithDeptNameResponse> getUserWithDeptName() {
		List<UserWithDeptNameResponse> responseList = new ArrayList<>();

		//1、查询出所有用户
		List<UserEntity> userEntityList = userMapper.selectAll();
		//2、遍历所有用户
		for (UserEntity userEntity : userEntityList) {
			UserWithDeptNameResponse response = ConvertUtils.sourceToTarget(userEntity, UserWithDeptNameResponse.class);
			//2.1、得到此用户的部门ID
			Long deptId = userEntity.getDeptId();
			//2.2、使用DeptService去根据ID查询部门信息
			DeptEntity deptEntity = deptService.getById(deptId);
			//2.3、将部门名称设置给响应对象
			response.setDeptName(deptEntity.getDeptName());
			responseList.add(response);
		}

		return responseList;
	}

	@Override
	public List<UserWithDeptNameResponse> getUserWithDeptNameOptimize1() {
		List<UserWithDeptNameResponse> responseList = new ArrayList<>();

		//1、查询出所有用户
		List<UserEntity> userEntityList = userMapper.selectAll();
		//2、查询出所有部门（关联数据）
		List<DeptEntity> deptEntityList = deptService.getAll();

		//3、遍历所有用户
		for (UserEntity userEntity : userEntityList) {
			UserWithDeptNameResponse response = ConvertUtils.sourceToTarget(userEntity, UserWithDeptNameResponse.class);
			//4、嵌套遍历所有部门，根据用户中的deptId，匹配当前遍历的部门id，匹配到则将部门名称设置给响应对象
			deptEntityList.forEach(deptEntity -> {
				if (userEntity.getDeptId().equals(deptEntity.getId())){
					response.setDeptName(deptEntity.getDeptName());
				}
			});
			responseList.add(response);
		}

		return responseList;
	}

	@Override
	public List<UserWithDeptNameResponse> getUserWithDeptNameOptimize2() {
		List<UserWithDeptNameResponse> responseList = new ArrayList<>();

		//1、查询出所有用户
		List<UserEntity> userEntityList = userMapper.selectAll();
		//2、查询出被用户关联的所有部门（关联数据）
		List<DeptEntity> deptEntityList = deptService.getInUserId();

		//3、遍历所有用户
		for (UserEntity userEntity : userEntityList) {
			UserWithDeptNameResponse response = ConvertUtils.sourceToTarget(userEntity, UserWithDeptNameResponse.class);
			//4、嵌套遍历所有部门，根据用户中的deptId，匹配当前遍历的部门id，匹配到则将部门名称设置给响应对象
			deptEntityList.forEach(deptEntity -> {
				if (userEntity.getDeptId().equals(deptEntity.getId())){
					response.setDeptName(deptEntity.getDeptName());
				}
			});
			responseList.add(response);
		}

		return responseList;
	}

	@Override
	public List<UserWithDeptNameResponse> getUserWithDeptNameOptimize3() {
		List<UserWithDeptNameResponse> responseList = new ArrayList<>();

		//1、查询出所有用户
		List<UserEntity> userEntityList = userMapper.selectAll();
		//2、得到当前API需要的所有用户ID
		List<Long> userIds = new ArrayList<>();
		userEntityList.forEach(userEntity -> userIds.add(userEntity.getId()));

		//3、查询出被当前API所需要的用户，所关联的所有部门（关联数据）
		List<DeptEntity> deptEntityList = deptService.getInUserId2(userIds);

		//4、遍历所有用户
		for (UserEntity userEntity : userEntityList) {
			UserWithDeptNameResponse response = ConvertUtils.sourceToTarget(userEntity, UserWithDeptNameResponse.class);
			//5、嵌套遍历所有部门，根据用户中的deptId，匹配当前遍历的部门id，匹配到则将部门名称设置给响应对象
			deptEntityList.forEach(deptEntity -> {
				if (userEntity.getDeptId().equals(deptEntity.getId())){
					response.setDeptName(deptEntity.getDeptName());
				}
			});
			responseList.add(response);
		}
		return responseList;
	}

	@Override
	public List<UserWithDeptNameResponse> getUserWithDeptNameOptimize4() {
		List<UserWithDeptNameResponse> responseList = new ArrayList<>();

		//1、查询出所有用户
		List<UserEntity> userEntityList = userMapper.selectAll();
		//2、根据当前用户数据，得到关联数据的部门ID
		List<Long> deptIds = new ArrayList<>();
		userEntityList.forEach(userEntity -> deptIds.add(userEntity.getDeptId()));

		//3、查询出被当前API所需要的用户，所关联的所有部门（关联数据）
		List<DeptEntity> deptEntityList = deptService.getByIds(deptIds);

		//4、遍历所有用户
		for (UserEntity userEntity : userEntityList) {
			UserWithDeptNameResponse response = ConvertUtils.sourceToTarget(userEntity, UserWithDeptNameResponse.class);
			//5、嵌套遍历所有部门，根据用户中的deptId，匹配当前遍历的部门id，匹配到则将部门名称设置给响应对象
			deptEntityList.forEach(deptEntity -> {
				if (userEntity.getDeptId().equals(deptEntity.getId())){
					response.setDeptName(deptEntity.getDeptName());
				}
			});
			responseList.add(response);
		}
		return responseList;
	}
}
