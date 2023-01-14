package com.codeshu.service;

import com.codeshu.entity.ShopActivityEntity;
import com.codeshu.request.GetOrderAndRangeRequest;
import com.codeshu.request.GetRangeRequest;
import com.codeshu.response.GetOrderAndRangeResponse;
import com.codeshu.response.GetRangeResponse;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/1/14 12:44
 */
public interface ShopActivityService {
	List<ShopActivityEntity> getAll();

	/**
	 * 统计特定时间段内，举办数量前三的活动类型排名
	 */
	List<GetOrderAndRangeResponse> getOrderAndRange(GetOrderAndRangeRequest request);

	/**
	 * 统计特定时间段内，各种活动类型的举办数量（以天为单位）
	 */
	List<GetRangeResponse> getRange(GetRangeRequest request);
}
