package com.codeshu.common;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Service 实现层，继承 MP 的 ServiceImpl
 * 第一个泛型：Mapper 类型
 * 第二个泛型：实体类型
 *
 * @author CodeShu
 * @date 2023/10/30 15:05
 */
public class MyBaseServiceImpl<M extends MyBaseMapper<T>, T> extends ServiceImpl<M, T> implements MyIBaseService<T> {
	@Autowired
	protected M baseDao;

	@Override
	public M getBaseMapper() {
		return baseDao;
	}

	@Override
	public int insertBatch(List<T> entityList) {
		return baseDao.insertBatchSomeColumn(entityList);
	}
}
