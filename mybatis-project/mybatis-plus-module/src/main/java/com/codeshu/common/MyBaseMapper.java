package com.codeshu.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 继承 MP 的 BaseMapper，指定实体类型
 *
 * @author CodeShu
 * @date 2023/10/30 16:10
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {
	/**
	 * 批量插入
	 * <p>
	 * 说明：具体查看 InsertBatchSomeColumn 类的说明
	 * （1）只支持 MySQL；数据库主键字段最好不要自增；
	 * （2）发出的 SQL：INSERT INTO table_name (column1,column2) VALUES (?,?) , (?,?)
	 *
	 * @param entityList 实体集合
	 */
	int insertBatchSomeColumn(@Param("list") List<T> entityList);
}
