package com.codeshu.sign;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务端校验客户端签名的过程源码
 * @author ShuCode
 * @date 2022/4/13 16:28
 * @Email 13828965090@163.com
 */
public class AuthClientSignSource {

	public static void main(String[] args) {
		new AuthClientSignSource().auth();
	}

	/**
	 * 校验客户端签名
	 * @return
	 */
	public void  auth() {
		//指定密钥
		String token ="{'sbm':'wtYKXFn8vgiseUOB7dF6RkkHglhALdz8','sopt':'4Q4R0jApJ9O2yE3JK2D66GDdCATaGOIS'}";
		//将配置文件中的密钥，转为JSON对象
		JSONObject tokenJson = JSONUtil.parseObj(token);

		//创建一个保存验证签名状态的TokenInfo对象
		TokenInfo tokenInfo = new TokenInfo();

		//从请求中得到携带过来的签名sign、时间戳t以及接口分配的appkey
		String sign = "FBFC0378D53864470A0158A397304AAD";
		String t = "1648278660";
		String appKey = "sbm";
		//String username = "zhangsan"; 普通参数

		//判断以上三个必要参数是否为空
		if (StringUtils.isBlank(sign) || StringUtils.isBlank(appKey) || StringUtils.isBlank(t)) {
			//在TokenInfo对象中设置验证不通过
			tokenInfo.setAuth(false);
			tokenInfo.setCode(10002);
			tokenInfo.setMsg("缺少参数，数据不完整");
			return;
		}
		//判断密钥中是否存在appKey对应的密钥
		if (StringUtils.isBlank(tokenJson.getStr(appKey))) {
			//在TokenInfo对象中设置验证不通过
			tokenInfo.setAuth(false);
			tokenInfo.setCode(10000);
			tokenInfo.setMsg("appkey不存在");
			return;
		}

		// 计算验签
		Map<String, Object> map = new HashMap<>();
		//1、普通参数
		//map.put("username","zhangsan");
		//2、时间戳
		map.put("t",t);
		//3、appkey
		map.put("appkey",appKey);
		//4、根据appKey，得到他对应的密钥，比如sbm对应的密钥是wtYKXF...
		String secret = tokenJson.getStr(appKey);
		//传入密钥和存放所有请求参数的map，去得到一个签名sign
		String adminSign = getSign(secret, map);

		//6、判断后台得出的签名sign，和请求携带过来的sign是否一致，一致则通过
		if (!sign.equals(adminSign)) {
			tokenInfo.setAuth(false);
			tokenInfo.setCode(10005);
			tokenInfo.setMsg("签名验证错误");
			return;
		}
		//通过则设置TokenInfo对象中的isAuth为true
		tokenInfo.setAuth(true);
		System.out.println("签名验证通过!");
		System.out.println("客户端携带的签名sign：" + sign);
		System.out.println("服务端生成的签名sign：" + adminSign);
	}

	/**
	 * 后台得到签名sign
	 * @param secret appKey对应的密钥
	 * @param map  保存所有请求参数的map（除了sign参数）
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
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @return md5 string
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
