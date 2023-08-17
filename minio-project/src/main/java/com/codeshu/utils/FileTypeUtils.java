package com.codeshu.utils;

import cn.hutool.core.io.FileTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件类型工具类
 */
@Slf4j
public class FileTypeUtils {

	private final static String IMAGE_TYPE = "image/";
	private final static String AUDIO_TYPE = "audio/";
	private final static String VIDEO_TYPE = "video/";
	private final static String APPLICATION_TYPE = "application/";
	private final static String TXT_TYPE = "text/";

	/**
	 * 获取文件类型
	 *
	 * @param multipartFile 文件
	 * @return 文件类型
	 */
	public static String getFileType(MultipartFile multipartFile) {
		InputStream inputStream;
		String type;
		try {
			// 获取此文件的输入流
			inputStream = multipartFile.getInputStream();
			// 使用 Hutool 获取此文件类型
			type = FileTypeUtil.getType(inputStream);
			log.info("Hutool 获取当前文件类型：{}", type);

			//如果文件类型是图像文件，则拼接上 image/ 前缀
			if (type.equalsIgnoreCase("JPG") || type.equalsIgnoreCase("JPEG")
					|| type.equalsIgnoreCase("GIF") || type.equalsIgnoreCase("PNG")
					|| type.equalsIgnoreCase("BMP") || type.equalsIgnoreCase("PCX")
					|| type.equalsIgnoreCase("TGA") || type.equalsIgnoreCase("PSD")
					|| type.equalsIgnoreCase("TIFF")) {
				return IMAGE_TYPE + type;
			}

			//如果文件类型是音频文件，则拼接上 audio/ 前缀
			if (type.equalsIgnoreCase("mp3") || type.equalsIgnoreCase("OGG")
					|| type.equalsIgnoreCase("WAV") || type.equalsIgnoreCase("REAL")
					|| type.equalsIgnoreCase("APE") || type.equalsIgnoreCase("MODULE")
					|| type.equalsIgnoreCase("MIDI") || type.equalsIgnoreCase("VQF")
					|| type.equalsIgnoreCase("CD")) {
				return AUDIO_TYPE + type;
			}

			//如果文件类型是视频文件，则拼接上 video/ 前缀
			if (type.equalsIgnoreCase("mp4") || type.equalsIgnoreCase("avi")
					|| type.equalsIgnoreCase("MPEG-1") || type.equalsIgnoreCase("RM")
					|| type.equalsIgnoreCase("ASF") || type.equalsIgnoreCase("WMV")
					|| type.equalsIgnoreCase("qlv") || type.equalsIgnoreCase("MPEG-2")
					|| type.equalsIgnoreCase("MPEG4") || type.equalsIgnoreCase("mov")
					|| type.equalsIgnoreCase("3gp")) {
				return VIDEO_TYPE + type;
			}

			//如果文件类型是文档文件，则拼接上 application/ 前缀
			if (type.equalsIgnoreCase("doc") || type.equalsIgnoreCase("docx")
					|| type.equalsIgnoreCase("ppt") || type.equalsIgnoreCase("pptx")
					|| type.equalsIgnoreCase("xls") || type.equalsIgnoreCase("xlsx")
					|| type.equalsIgnoreCase("zip") || type.equalsIgnoreCase("jar")) {
				return APPLICATION_TYPE + type;
			}

			//如果文件类型是 txt 文件，则拼接上 text/ 前缀
			if (type.equalsIgnoreCase("txt")) {
				return TXT_TYPE + type;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

