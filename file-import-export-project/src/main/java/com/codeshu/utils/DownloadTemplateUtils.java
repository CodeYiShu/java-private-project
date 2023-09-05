package com.codeshu.utils;

import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 下载 Excel 模板
 *
 * @author CodeShu
 * @date 2023/9/5 15:16
 */
public class DownloadTemplateUtils {
	private static final String PATH = "excelTemplate/";

	/**
	 * 下载模板
	 *
	 * @param fileName 模板文件名称
	 * @param response 响应对象
	 */
	public static void downloadTemplate(String fileName, HttpServletResponse response) {
		try {
			ClassPathResource resource = new ClassPathResource(PATH + fileName);
			File file = resource.getFile();
			// 以流的形式读取文件进来
			InputStream fis = resource.getInputStream();

			// 缓冲区指定为 10 M
			byte[] buffer = new byte[10485760];
			// 读取文件到缓冲区
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream out = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("multipart/form-data");
			out.write(buffer);
			out.flush();
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
