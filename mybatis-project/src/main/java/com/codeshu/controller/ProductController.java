package com.codeshu.controller;

import com.codeshu.annotation.ProjectFilter;
import com.codeshu.entity.ProductEntity;
import com.codeshu.mapper.ProductMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CodeShu
 * @date 2023/7/22 17:20
 */
@RestController
public class ProductController {
	@Resource
	private ProductMapper productMapper;

	/**
	 * project_id 字段在 product 表中
	 * 由于是单表查询 product 表，所以不用添加别名
	 */
	@GetMapping("getProduct")
	@ProjectFilter()
	public List<ProductEntity> getProduct() {
		return productMapper.getProduct();
	}
}
