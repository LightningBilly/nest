package com.yiyjm.nest.service.impl;

import com.yiyjm.nest.config.Config;
import com.yiyjm.nest.dao.ImageDao;
import com.yiyjm.nest.entity.Image;
import com.yiyjm.nest.service.OssImgService;
import com.yiyjm.nest.util.ImageUtil;
import com.yiyjm.nest.util.OssImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ossimg服务实现类
 *
 * @author Jonny.Chang
 * @date 2020/05/05
 */
@Component("ossImgServiceId")
public class OssImgServiceImpl implements OssImgService {
	private static final Logger logger = LoggerFactory.getLogger(com.yiyjm.nest.service.AdminService.class);
	private ImageDao imageDao;
	private static final String GIF = ".gif";
	private static final String JPG = ".jpg";


	@Autowired
	public void setImageDao(ImageDao imageDao) {
		this.imageDao = imageDao;
	}


	@Override
	public String deleteImage(Integer iid, Object adminToken) {
//		// 用户鉴权
//		if (!Config.TOKEN_DO_LOGIN.equals(adminToken)) {
//			return "删除失败：未授权用户";
//		}

		Image image = imageDao.queryImage(iid);
		if (image == null || image.getName() == null) {
			return "删除失败：数据库中不存在";
		}

		String ossResult;
		if (OssImageUtil.aliOssExist(image.getName())) {
			OssImageUtil.aliOssDelete(image.getName());
			ossResult = "oss 中已删除，";
		} else {
			ossResult = "oss 中不存在，";
		}

		int result = imageDao.deleteImage(iid);
		return ossResult + "数据库删除数量：" + result + "，服务器文件中暂不删除";
	}

	@Override
	public Map<String, Object> uploadImage(MultipartFile file, Integer bid, Object adminToken) {
		HashMap<String, Object> map = new HashMap<>();
		// success=0 表示失败，=1表示成功
		map.put("success", 0);

		if (bid == null) {
			logger.error("上传错误：bid 错误");
			map.put("message", "上传错误：bid 错误");
			return map;
		}

		// 分开博客和相册照片 在阿里云oss的存放位置
		String folder = Config.OSS_BLOG_FOLDER;
		if (bid < 0) {
			folder = Config.OSS_PHOTOS_FOLDER;
		}

//		// 用户鉴权
//		if (!Config.TOKEN_DO_LOGIN.equals(adminToken)) {
//			logger.error("上传错误：管理员token错误");
//			map.put("message", "上传错误：管理员token错误");
//			return map;
//		}

		// 是否有有效文件
		if (file == null || file.isEmpty()) {
			logger.error("上传错误：文件为空");
			map.put("message", "上传错误：文件为空");
			return map;
		}

		if (file.getSize() > 1024 * 1024 * 20) {
			map.put("message", "上传错误：上传错误：大小超过20M");
			return map;
		}

		// 检测后缀
		String extension = ImageUtil.getSuffix(file.getOriginalFilename());
		if (extension == null) {
			map.put("message", "上传错误：该图片无后缀");
			return map;
		}
		extension = extension.toLowerCase();
		if (!Config.IMAGE_SUFFIX.contains(extension)) {
			map.put("message", "上传错误：文件后缀只支持：" + Config.IMAGE_SUFFIX);
			return map;
		}

		// 建立目录（存放压缩的图片）
		File localFile = new File(Config.IMAGE_LOCAL_PATH);
		if (!localFile.exists()) {
			boolean res = localFile.mkdirs();
			if (!res) {
				logger.error("上传错误：上传目录创建失败");
				map.put("message", "上传错误：上传目录创建失败");
				return map;
			}
		}

		// 获取新的文件名，无后缀
		String fileName = ImageUtil.getNameByTime();

		// 如果是动图，直接上传，否则压缩成jpg后上传
		if (extension.equalsIgnoreCase(GIF)) {
			fileName = folder + fileName + GIF;

			try {
				OssImageUtil.uploadAliOss(fileName, file.getInputStream());
			} catch (Exception e) {
				logger.error("上传错误：gif上传阿里云OOS错误");
				map.put("message", "上传错误：gif上传阿里云OOS错误");
				return map;
			}
		} else {
			fileName += JPG;
			localFile = new File(Config.IMAGE_LOCAL_PATH + fileName);
			try {
				ImageUtil.zipImage(file.getInputStream(), localFile, Config.IMAGE_MAX_SIZE, Config.IMAGE_ZIP_QUALITY);
			} catch (Exception e) {
				logger.error("上传错误：图片压缩成jpg失败");
				map.put("message", "上传错误：图片压缩成jpg失败");
				return map;
			}

			fileName = folder + fileName;
			try {
				InputStream inputStream = new FileInputStream(localFile);
				OssImageUtil.uploadAliOss(fileName, inputStream);
			} catch (Exception e) {
				logger.error("上传错误：jpg上传阿里云OOS错误");
				map.put("message", "上传错误：jpg上传阿里云OOS错误");
				return map;
			}
		}

		Image image = new Image();
		image.setName(fileName);
		image.setBid(bid);
		image.setPubtime(new Timestamp(System.currentTimeMillis()));
		imageDao.insertImage(image);

		map.put("success", 1);
		map.put("message", "上传成功");
		map.put("url", OssImageUtil.getAliOssUrl(fileName));
		return map;
	}

	@Override
	public int countImage() {
		return imageDao.getTotal();
	}

	@Override
	public List<Image> listImage(int page, int number) {
		return imageDao.listImageByPage((page - 1) * number, number);
	}
}
