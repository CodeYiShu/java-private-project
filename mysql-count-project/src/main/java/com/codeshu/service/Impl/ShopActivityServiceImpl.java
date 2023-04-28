package com.codeshu.service.Impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.codeshu.entity.ShopActivityEntity;
import com.codeshu.mapper.ShopActivityMapper;
import com.codeshu.request.GetOrderAndRangeRequest;
import com.codeshu.request.GetRangeRequest;
import com.codeshu.response.GetOrderAndRangeResponse;
import com.codeshu.response.GetRangeResponse;
import com.codeshu.response.GetRangeSingleResponse;
import com.codeshu.service.ShopActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author CodeShu
 * @date 2023/1/14 12:44
 */
@Service
public class ShopActivityServiceImpl implements ShopActivityService {
	@Autowired
	private ShopActivityMapper mapper;

	@Override
	public List<ShopActivityEntity> getAll() {
		return mapper.selectAll();
	}

	@Override
	public List<GetOrderAndRangeResponse> getOrderAndRange(GetOrderAndRangeRequest request) {
		//request.setStartDate(request.getStartDate());
		//request.setEndDate(request.getEndDate());
		return mapper.getOrderAndRange(request);
	}

	@Override
	public List<GetRangeResponse> getRange(GetRangeRequest request) {
		List<GetRangeResponse> responseList = new ArrayList<>();

		//传入统计开始时间和结束时间，统计出每天各种活动类型举办的数量
		List<GetRangeSingleResponse> singleResponseList = mapper.getRange(request);

		//得到开始统计时间和结束时间这一段时间范围内的所有天数（横坐标 yyyy-MM-dd）
		DateTime startDate = DateUtil.parse(request.getStartDate());
		DateTime endDate = DateUtil.parse(request.getEndDate());
		//由于工具方法获取不到开始时间和结束时间这一天，这里给开始时间往前推一天，结束时间往后推一天
		startDate = DateUtil.offsetDay(startDate, -1);
		endDate = DateUtil.offsetDay(endDate, 1);
		List<String> startDateAndEndDateList = getDateListByStartDateAndEndDate(startDate, endDate);

		for (String startAndEndDate : startDateAndEndDateList) {
			//一个横坐标点就是一天
			GetRangeResponse response = new GetRangeResponse();
			response.setSubResponseList(new ArrayList<>());
			response.setDate(startAndEndDate);
			//在此天举办的活动类型和数量
			for (GetRangeSingleResponse singleResponse : singleResponseList) {
				if (startAndEndDate.equals(singleResponse.getDate())) {
					response.getSubResponseList().add(singleResponse);
				}
			}
			responseList.add(response);
		}

		return responseList;
	}

	public static List<String> getDateListByStartDateAndEndDate(Date startDate, Date endDate) {
		List<String> result = new ArrayList<>();
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(startDate);
		tempStart.add(Calendar.DAY_OF_YEAR, 1);

		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(endDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		while (tempStart.before(tempEnd)) {
			result.add(sdf.format(tempStart.getTime()));
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}
		return result;
	}
}
