package com.yiyjm.nest.util;

import com.aliyun.oss.OSSClient;
import com.yiyjm.nest.config.Config;
import com.yiyjm.nest.config.ConfigI;

import java.io.InputStream;

/**
 * 本地图片管理工具类
 *
 * @author jonny
 * @date 2020/05/04
 */
public class LocalImageUtil {



	/**
	 * 上传图片到本地指定目录
	 *
	 * @param name  的名字
	 * @param input 输入
	 */
	public static void uploadLocalImg(String name, InputStream input) {
//		String path = ConfigI.LOCAL_FILE_PATH + ConfigI.IMAGES_FOLDER_NAME;

		OSSClient ossClient = new OSSClient(Config.OSS_PROTOCOL + Config.OSS_ENDPOINT,
				Config.OSS_ACCESS_KEY_ID, Config.OSS_ACCESS_KEY_SECRET);
		// 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
		ossClient.putObject(Config.OSS_BUCKET_NAME, name, input);
		ossClient.shutdown();
	}

	/**
	 * 判断图片是否存在
	 *
	 * @param name 图片文件名
	 * @return boolean
	 */
	public static boolean localImgExist(String name) {
		OSSClient ossClient = new OSSClient(Config.OSS_PROTOCOL + Config.OSS_ENDPOINT,
				Config.OSS_ACCESS_KEY_ID, Config.OSS_ACCESS_KEY_SECRET);
		return ossClient.doesObjectExist(Config.OSS_BUCKET_NAME, name);
	}

	/**
	 * 删除图片
	 *
	 * @param name 图片文件名
	 */
	public static void deleteLocalImg(String name) {
		OSSClient ossClient = new OSSClient(Config.OSS_PROTOCOL + Config.OSS_ENDPOINT,
				Config.OSS_ACCESS_KEY_ID, Config.OSS_ACCESS_KEY_SECRET);
		ossClient.deleteObject(Config.OSS_BUCKET_NAME, name);
	}

	/**
	 * 获取图片的 Url
	 *
	 * @param fileName 图片文件名
	 * @return {@link String}
	 */
	public static String getLocalImgUrl(String fileName) {
		return Config.OSS_URL_PREFIX + fileName;
	}
}
