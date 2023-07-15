package com.codeshu.service;

import com.codeshu.response.DaLeTouResponse;
import com.codeshu.response.ShuangSeQiuResponse;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/7/15 22:15
 */
public interface LotteryService {
	List<DaLeTouResponse> daLeTou();

	List<ShuangSeQiuResponse> shuangSeQiu();
}
