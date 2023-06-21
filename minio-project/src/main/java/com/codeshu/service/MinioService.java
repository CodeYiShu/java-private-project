package com.codeshu.service;

import io.minio.messages.Bucket;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @Author crush
 * @Date 2021/7/25 9:58
 * @Description: MinioService
 */
public interface MinioService {

	/**
	 * 判断 bucket是否存在
	 */
	boolean bucketExists(String bucketName);

	/**
	 * 创建 bucket
	 */
	void makeBucket(String bucketName);

	/**
	 * 列出所有存储桶名称
	 */
	List<String> listBucketName();

	/**
	 * 列出所有存储桶 信息
	 */
	List<Bucket> listBuckets();

	/**
	 * 根据桶名删除桶
	 */
	boolean removeBucket(String bucketName);

	/**
	 * 列出存储桶中的所有对象名称
	 */
	List<String> listObjectNames(String bucketName);

	/**
	 * 文件上传
	 */
	String putObject(MultipartFile multipartFile, String bucketName, String fileType);

	/**
	 * 文件流下载
	 */
	InputStream downloadObject(String bucketName, String objectName);


	/**
	 * 删除文件
	 */
	boolean removeObject(String bucketName, String objectName);


	/**
	 * 批量删除文件
	 */
	boolean removeListObject(String bucketName, List<String> objectNameList);

	/**
	 * 获取文件路径
	 */
	String getObjectUrl(String bucketName, String objectName);
}

