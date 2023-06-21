package com.codeshu.controller;

import com.codeshu.service.MinioService;
import com.codeshu.utils.FileTypeUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author crush
 */
@RequestMapping("/minio")
@RestController
public class MinioController {

	private final MinioService minioService;


	public MinioController(MinioService minioService) {
		this.minioService = minioService;
	}

	/**
	 * 文件上传
	 */
	@PostMapping("/upload")
	public String uploadFile(MultipartFile file, String bucketName) {
		String fileType = FileTypeUtils.getFileType(file);
		if (fileType != null) {
			return minioService.putObject(file, bucketName, fileType);
		}
		return "不支持的文件格式。请确认格式，重新上传！！！";
	}

	/**
	 * 创建桶
	 */
	@PostMapping("/addBucket/{bucketName}")
	public String addBucket(@PathVariable String bucketName) {
		minioService.makeBucket(bucketName);
		return "创建成功";
	}

	/**
	 * 列出存储桶中的所有对象名称
	 */
	@GetMapping("/show/{bucketName}")
	public List<String> show(@PathVariable String bucketName) {
		return minioService.listObjectNames(bucketName);
	}

	/**
	 * 列出所有存储桶名称
	 */
	@GetMapping("/showBucketName")
	public List<String> showBucketName() {
		return minioService.listBucketName();
	}

	/**
	 * 列出存储桶中的所有对象名称
	 */
	@GetMapping("/showListObjectNameAndDownloadUrl/{bucketName}")
	public Map<String, String> showListObjectNameAndDownloadUrl(@PathVariable String bucketName) {
		Map<String, String> map = new HashMap<>();
		List<String> listObjectNames = minioService.listObjectNames(bucketName);
		String url = "localhost:8085/minio/download/" + bucketName + "/";
		listObjectNames.forEach(System.out::println);
		for (String listObjectName : listObjectNames) {
			map.put(listObjectName, url + listObjectName);
		}
		return map;
	}

	/**
	 * 根据桶名删除桶
	 */
	@DeleteMapping("/removeBucket/{bucketName}")
	public String delBucketName(@PathVariable String bucketName) {
		return minioService.removeBucket(bucketName) ? "删除成功" : "删除失败";
	}

	/**
	 * 删除文件
	 */
	@DeleteMapping("/removeObject/{bucketName}/{objectName}")
	public String delObject(@PathVariable("bucketName") String bucketName, @PathVariable("objectName") String objectName) {
		return minioService.removeObject(bucketName, objectName) ? "删除成功" : "删除失败";
	}

	/**
	 * 批量删除文件
	 */
	@DeleteMapping("/removeListObject/{bucketName}")
	public String delListObject(@PathVariable("bucketName") String bucketName, @RequestBody List<String> objectNameList) {
		return minioService.removeListObject(bucketName, objectNameList) ? "删除成功" : "删除失败";
	}

	/**
	 * 下载文件
	 */
	@RequestMapping("/download/{bucketName}/{objectName}")
	public void download(HttpServletResponse response, @PathVariable("bucketName") String bucketName, @PathVariable("objectName") String objectName) {
		InputStream in = null;
		try {
			in = minioService.downloadObject(bucketName, objectName);
			response.setHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(objectName, "UTF-8"));
			response.setCharacterEncoding("UTF-8");
			//将字节从InputStream复制到OutputStream 。
			IOUtils.copy(in, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

