package com.codeshu.controller;

import com.codeshu.response.DaLeTouResponse;
import com.codeshu.response.ShuangSeQiuResponse;
import com.codeshu.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/7/15 22:14
 */
@RestController
public class LotteryController {
	@Autowired
	private LotteryService service;

	@GetMapping(value = "daLeTou")
	private List<DaLeTouResponse> daLeTou() {
		return service.daLeTou();
	}

	@GetMapping(value = "shuangSeQiu")
	private List<ShuangSeQiuResponse> shuangSeQiu() {
		return service.shuangSeQiu();
	}
}
