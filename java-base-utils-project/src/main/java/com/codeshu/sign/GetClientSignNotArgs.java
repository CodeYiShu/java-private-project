package com.codeshu.sign;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成客户端sign（无普通参数）
 *
 * @author ShuCode
 * @date 2022/4/13 16:28
 * @Email 13828965090@163.com
 */
public class GetClientSignNotArgs {

	public static void main(String[] args) {
		new GetClientSignNotArgs().auth();
	}

	/**
	 * 生成客户端sign
	 *
	 * @return
	 */
	public void auth() {
		//指定密钥
		String token = "{'sbm':'odrek6JEHrskcxRAgagOWusAgujCdc19'}";
		//将配置文件中的密钥，转为JSON对象
		JSONObject tokenJson = JSONUtil.parseObj(token);

		//模拟请求参数
		String t = "1649159957";  //时间戳
		String appKey = "sbm";  //appkey

		//根据四个决定因素生成sign签名
		Map<String, Object> map = new HashMap<>();
		//1、没有普通参数
		//2、时间戳
		map.put("t", t);
		//3、appkey
		map.put("appkey", appKey);
		//4、根据appKey，得到他对应的密钥，比如sbm对应的密钥是odrek6JEHrskcxRAgagOWusAgujCdc19
		String secret = tokenJson.getStr(appKey);
		//传入密钥和存放所有请求参数的map，去得到一个签名sign
		String adminSign = getSign(secret, map);

		//拿到指定时间戳、appkey、密钥和普通参数生成的签名sign，可用于测试
		System.out.println("===================================");
		System.out.println("四个决定因素情况：");
		System.out.println("1、时间戳t\t= " + t +
				"\n2、appkey\t= " + appKey +
				"\n3、token\t= " + secret +
				"\n4、普通参数\t= 无");
		System.out.println("得到的签名：\n" + adminSign);
		System.out.println("===================================");
	}

	/**
	 * 后台得到签名sign
	 *
	 * @param secret appKey对应的密钥
	 * @param map    保存所有请求参数的map（除了sign参数）
	 * @return
	 */
	public static String getSign(String secret, Map<String, Object> map) {
		//得到Map所有的key，也就是所有的请求参数名，比如appkey、a、d、c、b、t
		Object[] keys = map.keySet().toArray();
		//按参数名升序排序（从小到大），比如a、appkey、b、c、d、t
		Arrays.sort(keys);
		StringBuilder str = new StringBuilder();
		//对排序后的请求参数进行拼接，比如得到aa_value|appkeysbm|bb_value|cc_value|dd_value|t1465404570（没有|）
		for (Object o : keys) {  //遍历排序后所有的key请求参数名
			String key = String.valueOf(o);
			String value = String.valueOf(map.get(key));
			//将请求参数名及其对应的参数值进行拼接
			str.append(key + value);
		}
		//对拼接后的字符串追加appsecret密钥，这里就是sbm这个key对应的密钥wtYKXF...
		//那么得到的就是aa_value|appkeysbm|bb_value|cc_value|dd_value|t1465404570|wtYKXF...（没有|）
		String info = str.append(secret).toString();
		//传入字符串，进行MD5加密，得到加密后的字符串，作为后台的出来的签名sign
		String adminSign = getMd5(info);
		return adminSign;
	}

	/**
	 * 将指定字符串进行MD5加密
	 *
	 * @return md5 string
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	private static String getMd5(String input) {
		byte[] out = {};
		try {
			//所有请求参数在计算 sign 前需要进行 utf-8 编码，然后URLEncoder
			String encode = URLEncoder.encode(input, "utf-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(encode.getBytes("UTF-8"));
			out = md.digest();
		} catch (Exception e) {
			throw new RuntimeException("MD5 error", e);
		}
		//得到MD5加密的字符串
		return toHexString(out);
	}

	/**
	 * 转换成16进制
	 */
	private static String toHexString(byte[] out) {
		StringBuilder buf = new StringBuilder();
		for (byte b : out) {
			buf.append(String.format("%02X", b));
		}
		return buf.toString();
	}
}
