package com.codeshu.controller;

import com.codeshu.entity.ShopActivityEntity;
import com.codeshu.request.GetOrderAndRangeRequest;
import com.codeshu.request.GetRangeRequest;
import com.codeshu.response.GetOrderAndRangeResponse;
import com.codeshu.response.GetRangeResponse;
import com.codeshu.service.ShopActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/1/14 12:45
 */
@RestController
public class ShopActivityController {
	@Autowired
	private ShopActivityService service;

	@GetMapping("/getAll")
	public List<ShopActivityEntity> getAll() {
		return service.getAll();
	}

	@GetMapping("/getOrderAndRange")
	public List<GetOrderAndRangeResponse> getOrderAndRange(GetOrderAndRangeRequest request) {
		return service.getOrderAndRange(request);
	}

	@GetMapping("/getRange")
	public List<GetRangeResponse> getRange(GetRangeRequest request) {
		return service.getRange(request);
	}

}
