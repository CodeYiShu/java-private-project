package com.codeshu.service.impl;

import com.codeshu.config.MinioProperties;
import com.codeshu.service.MinioService;
import com.codeshu.utils.MinioUtils;
import io.minio.MinioClient;
import io.minio.messages.Bucket;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class MinioServiceImpl implements MinioService {

	private final MinioUtils minioUtil;
	private final MinioClient minioClient;
	private final MinioProperties minioProperties;

	public MinioServiceImpl(MinioUtils minioUtil, MinioClient minioClient, MinioProperties minioProperties) {
		this.minioUtil = minioUtil;
		this.minioClient = minioClient;
		this.minioProperties = minioProperties;
	}

	@Override
	public boolean bucketExists(String bucketName) {
		return minioUtil.bucketExists(bucketName);
	}


	@Override
	public void makeBucket(String bucketName) {
		boolean flag = minioUtil.makeBucket(bucketName);
		if (!flag) {
			throw new RuntimeException("创建桶失败");
		}
	}

	@Override
	public List<String> listBucketName() {
		return minioUtil.listBucketNames();
	}

	@Override
	public List<Bucket> listBuckets() {
		return minioUtil.listBuckets();
	}

	@Override
	public boolean removeBucket(String bucketName) {
		return minioUtil.removeBucket(bucketName);
	}


	@Override
	public List<String> listObjectNames(String bucketName) {
		return minioUtil.listObjectNames(bucketName);
	}


	@Override
	public String putObject(MultipartFile file, String bucketName, String fileType) {
		try {
			bucketName = StringUtils.isNotBlank(bucketName) ? bucketName : minioProperties.getBucketName();
			if (!this.bucketExists(bucketName)) {
				this.makeBucket(bucketName);
			}
			String fileName = file.getOriginalFilename();

			assert fileName != null;
			String objectName = UUID.randomUUID().toString().replaceAll("-", "")
					+ fileName.substring(fileName.lastIndexOf("."));
			minioUtil.putObject(bucketName, file, objectName, fileType);
			return minioProperties.getEndpoint() + "/" + bucketName + "/" + objectName;
		} catch (Exception e) {
			e.printStackTrace();
			return "上传失败";
		}
	}

	@Override
	public InputStream downloadObject(String bucketName, String objectName) {
		return minioUtil.getObject(bucketName, objectName);
	}

	@Override
	public boolean removeObject(String bucketName, String objectName) {
		return minioUtil.removeObject(bucketName, objectName);
	}

	@Override
	public boolean removeListObject(String bucketName, List<String> objectNameList) {
		return minioUtil.removeObject(bucketName, objectNameList);
	}

	@Override
	public String getObjectUrl(String bucketName, String objectName) {
		return minioUtil.getObjectUrl(bucketName, objectName);
	}
}

