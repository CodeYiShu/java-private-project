package com.codeshu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CodeShu
 * @date 2023/9/20 17:39
 */
@RestController
public class TestController {
	@GetMapping("test")
	public String test() {
		return "IG 牛逼";
	}
}
