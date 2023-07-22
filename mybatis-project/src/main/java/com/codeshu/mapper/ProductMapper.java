package com.codeshu.mapper;

import com.codeshu.entity.ProductEntity;
import com.codeshu.response.GetUserInfoResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/7/22 16:52
 */
public interface ProductMapper {
	List<ProductEntity> getProduct();
}
