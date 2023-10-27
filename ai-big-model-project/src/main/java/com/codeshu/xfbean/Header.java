package com.codeshu.xfbean;

import lombok.Data;

/**
 * @author CodeShu
 * @date 2023/10/27 10:26
 */
@Data
public class Header {
	/**
	 * 0表示正常 非0表示出错
	 */
	private int code;
	/**
	 * 会话状态，取值为[0,1,2]；0代表首次结果；1代表中间结果；2代表最后一个结果
	 */
	private int status;
	/**
	 * 会话的唯一id，用于讯飞技术人员查询服务端会话日志使用,出现调用错误时建议留存该字段
	 */
	private String sid;
}
