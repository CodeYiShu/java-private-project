package com.codeshu.aspect;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.codeshu.annotation.ProjectFilter;
import com.codeshu.mapper.UserMapper;
import com.codeshu.response.GetUserInfoResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 为当前线程生成项目过滤 SQL 的拦截器
 */
@Aspect
@Component
public class ProjectFilterAspect {
	@Resource
	private UserMapper userMapper;

	/**
	 * 存放当前线程生成的项目权限 SQL
	 */
	public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

	public ProjectFilterAspect() {
	}

	@Pointcut("@annotation(com.codeshu.annotation.ProjectFilter)")
	public void aspect() {
		//拦截所有标注了 ProjectFilter 注解的方法
	}

	@Before("aspect()")
	public void before(JoinPoint point) {
		//获取当前用户的 ID，这里先写死
		Long userId = 1L;
		try {
			//获取此用户的项目过滤 SQL 语句
			String sqlFilter = this.getSqlFilter(userId, point);
			//将 SQL 语句设置进当前线程的本地变量
			THREAD_LOCAL.set(sqlFilter);
		} catch (Exception e) {
			throw new RuntimeException("错误");
		}
	}

	@After("aspect()")
	public void after() {
		THREAD_LOCAL.remove();
	}

	/**
	 * 获取过滤项目的 SQL
	 *
	 * @param userId 用户 ID
	 * @param point  执行的方法
	 * @return 过滤项目的 SQL，比如 (project_id in(1,2))
	 */
	private String getSqlFilter(Long userId, JoinPoint point) {
		//获取注解上的表别名信息
		MethodSignature signature = (MethodSignature) point.getSignature();
		ProjectFilter projectFilter = signature.getMethod().getAnnotation(ProjectFilter.class);
		String tableAlias = projectFilter.tableAlias();
		if (StringUtils.isNotBlank(tableAlias)) {
			tableAlias = tableAlias + ".";
		}

		StringBuilder sqlFilter = new StringBuilder();
		sqlFilter.append(" (");

		//查询当前用户信息
		GetUserInfoResponse userInfo = userMapper.getUserInfo(userId);

		if (Objects.nonNull(userInfo)) {
			//获取当前用户具备权限的项目 ID
			List<Long> projectIdList;
			if (!CollectionUtils.isEmpty(userInfo.getProjectIds())) {
				projectIdList = userInfo.getProjectIds();
			} else {
				ArrayList<Long> arrayList = new ArrayList<>();
				arrayList.add(-1L);
				projectIdList = arrayList;
			}

			//使用 IN 语句进行拼接
			sqlFilter.append(tableAlias).append(projectFilter.projectId());
			sqlFilter.append(" in(").append(CollUtil.join(projectIdList, ",")).append(")");
			sqlFilter.append(")");
			return sqlFilter.toString();
		} else {
			return null;
		}
	}
}
