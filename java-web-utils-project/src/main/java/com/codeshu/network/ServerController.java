package com.codeshu.network;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CodeShu
 * @date 2023/6/28 15:53
 */
@RestController
public class ServerController {
	@GetMapping("test/get")
	public String testGet(@RequestParam("param") String param) {
		return param;
	}

	@PostMapping("test/post")
	public PostParam testPost(@RequestBody PostParam param) {
		return param;
	}

	@Data
	static class PostParam {
		private Long id;

		private String name;
	}
}
