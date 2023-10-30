package com.codeshu.common;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Service 接口层继承 MP 的 IService，指定实体类型
 *
 * @author CodeShu
 * @date 2023/10/30 15:04
 */
public interface MyIBaseService<T> extends IService<T> {
	/**
	 * 批量插入
	 * <p>
	 * 说明：具体查看 InsertBatchSomeColumn 类的说明
	 * （1）只支持 MySQL；数据库主键字段最好不要自增；
	 * （2）发出的 SQL：INSERT INTO table_name (column1,column2) VALUES (?,?) , (?,?)
	 *
	 * @param entityList 实体集合
	 */
	int insertBatch(List<T> entityList);
}
