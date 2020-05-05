package com.yiyjm.nest.util;

import com.aliyun.oss.OSSClient;
import com.yiyjm.nest.config.Config;

import java.io.InputStream;

/**
 * oss形象跑龙套
 * OssImageUtil
 *
 * @author Jonny.Chang
 * @date 2020/05/01
 */
public class OssImageUtil {

	/**
	 * 上传阿里oss
	 *
	 * @param name  的名字
	 * @param input 输入
	 */
	public static void uploadAliOss(String name, InputStream input) {
		OSSClient ossClient = new OSSClient(Config.HTTPS_PROTOCOL + Config.OSS_ENDPOINT,
				Config.OSS_ACCESS_KEY_ID, Config.OSS_ACCESS_KEY_SECRET);
		// 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
		ossClient.putObject(Config.OSS_BUCKET_NAME, name, input);
		ossClient.shutdown();
	}

	/**
	 * 阿里oss存在
	 *
	 * @param name 的名字
	 * @return boolean
	 */
	public static boolean aliOssExist(String name) {
		OSSClient ossClient = new OSSClient(Config.HTTPS_PROTOCOL + Config.OSS_ENDPOINT,
				Config.OSS_ACCESS_KEY_ID, Config.OSS_ACCESS_KEY_SECRET);
		return ossClient.doesObjectExist(Config.OSS_BUCKET_NAME, name);
	}

	/**
	 * 阿里oss删除
	 *
	 * @param name 的名字
	 */
	public static void aliOssDelete(String name) {
		OSSClient ossClient = new OSSClient(Config.HTTPS_PROTOCOL + Config.OSS_ENDPOINT,
				Config.OSS_ACCESS_KEY_ID, Config.OSS_ACCESS_KEY_SECRET);
		ossClient.deleteObject(Config.OSS_BUCKET_NAME, name);
	}

	/**
	 * get 阿里ossurl
	 *
	 * @param fileName 文件名称
	 * @return {@link String}
	 */
	public static String getAliOssUrl(String fileName) {
		return Config.OSS_URL_PREFIX + fileName;
	}

}
