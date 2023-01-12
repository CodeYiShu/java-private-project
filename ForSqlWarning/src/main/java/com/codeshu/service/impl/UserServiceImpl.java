package com.codeshu.service.impl;

import com.codeshu.entity.DeptEntity;
import com.codeshu.entity.PostEntity;
import com.codeshu.entity.UserEntity;
import com.codeshu.entity.UserPostEntity;
import com.codeshu.mapper.UserMapper;
import com.codeshu.response.UserWithDeptNameResponse;
import com.codeshu.response.UserWithPostResponse;
import com.codeshu.service.DeptService;
import com.codeshu.service.PostService;
import com.codeshu.service.UserPostService;
import com.codeshu.service.UserService;
import com.codeshu.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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
	@Autowired
	private PostService postService;
	@Autowired
	private UserPostService userPostService;

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
				if (userEntity.getDeptId().equals(deptEntity.getId())) {
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
				if (userEntity.getDeptId().equals(deptEntity.getId())) {
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
				if (userEntity.getDeptId().equals(deptEntity.getId())) {
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
		userEntityList.forEach(userEntity -> {
			//注意判空，否则会将null存入List集合
			if (Objects.nonNull(userEntity.getDeptId())) {
				deptIds.add(userEntity.getDeptId());
			}
		});

		//3、查询出被当前API所需要的用户，所关联的所有部门（关联数据）
		List<DeptEntity> deptEntityList = deptService.getByIds(deptIds);

		//4、遍历所有用户
		for (UserEntity userEntity : userEntityList) {
			UserWithDeptNameResponse response = ConvertUtils.sourceToTarget(userEntity, UserWithDeptNameResponse.class);
			//5、嵌套遍历所有部门，根据用户中的deptId，匹配当前遍历的部门id，匹配到则将部门名称设置给响应对象
			deptEntityList.forEach(deptEntity -> {
				if (userEntity.getDeptId().equals(deptEntity.getId())) {
					response.setDeptName(deptEntity.getDeptName());
				}
			});
			responseList.add(response);
		}
		return responseList;
	}

	@Override
	public List<UserWithPostResponse> getUserWithPost() {
		List<UserWithPostResponse> responseList = new ArrayList<>();

		//1、获取所有用户
		List<UserEntity> userEntityList = userMapper.selectAll();

		//2、收集这些用户的ID
		List<Long> userIds = new ArrayList<>();
		userEntityList.forEach(userEntity -> userIds.add(userEntity.getId()));

		//3、根据用户ID集合查询出关联的所有岗位关联关系
		List<UserPostEntity> userPostEntityList = userPostService.getByUserIds(userIds);

		//4、收集岗位ID，和建立用户和岗位关联的Map
		Set<Long> postIds = new LinkedHashSet<>();
		Map<Long, List<Long>> userPostMap = new HashMap<>();
		userPostEntityList.forEach(userPostEntity -> {
			Long userId = userPostEntity.getUserId();
			Long postId = userPostEntity.getPostId();
			postIds.add(postId);
			if (Objects.isNull(userPostMap.get(userId))) {
				userPostMap.put(userId, new ArrayList<>());
			}
			//注意判断，否则会将null存入List集合
			if (Objects.nonNull(postId)) {
				userPostMap.get(userId).add(postId);
			}
		});

		//5、批量查询出关联的岗位
		List<PostEntity> postEntityList = postService.getByIds(postIds);

		for (UserEntity userEntity : userEntityList) {
			UserWithPostResponse response = ConvertUtils.sourceToTarget(userEntity, UserWithPostResponse.class);
			response.setPostIds(new ArrayList<>());
			response.setPostNames(new ArrayList<>());

			//6、从Map中获取出当前用户的所有岗位ID
			List<Long> currentPostIds = userPostMap.get(userEntity.getId());
			if (!currentPostIds.isEmpty()) {
				for (PostEntity postEntity : postEntityList) {
					if (currentPostIds.contains(postEntity.getId())) {
						response.getPostIds().add(postEntity.getId());
						response.getPostNames().add(postEntity.getPostName());
					}
				}
			}
			responseList.add(response);
		}

		return responseList;
	}
}
