package com.codeshu.controller;

import com.codeshu.utils.DownloadTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author CodeShu
 * @date 2023/9/5 15:23
 */
@RestController
public class DownloadTemplateController {
	@GetMapping("downloadTemplate")
	public void downloadTemplate(HttpServletResponse response) {
		DownloadTemplateUtils.downloadTemplate("template.xlsx", response);
	}
}
