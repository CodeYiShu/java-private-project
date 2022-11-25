package com.codeshu.redislock.Controller;

import com.codeshu.redislock.utils.BusinessCodeEnum;
import com.codeshu.redislock.utils.GenerateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CodeShu
 * @date 2022/11/22 16:15
 */
@RestController
public class TestGenerateUtil {
	@Autowired
	private GenerateCodeUtil generateUtil;
	@GetMapping("/test")
	public void test() throws InterruptedException {
		String generate = generateUtil.generate(BusinessCodeEnum.XJRW);
		System.out.println(generate);
	}
}
